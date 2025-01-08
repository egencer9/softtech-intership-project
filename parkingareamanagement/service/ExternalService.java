package com.example.demo.parkingareamanagement.service;

import com.example.demo.parkingareamanagement.dto.CheckInRequestDto;
import com.example.demo.parkingareamanagement.dto.CheckOutRequestDto;
import com.example.demo.parkingareamanagement.dto.CheckOutResponseDto;
import com.example.demo.parkingareamanagement.entity.ParkingAreaEntity;
import com.example.demo.parkingareamanagement.entity.VehicleEntity;
import com.example.demo.parkingareamanagement.enums.VehicleType;
import com.example.demo.parkingareamanagement.repository.ParkingAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExternalService {
    private final ParkingAreaRepository parkingAreaRepository;

    @Transactional
    public String checkInVehicle(CheckInRequestDto checkInRequestDto) {

        //Map
        String parkingAreaName = checkInRequestDto.getParkingAreaName();

        // Find the parking area by name
        Optional<ParkingAreaEntity> parkingAreaOpt = parkingAreaRepository.findByName(parkingAreaName);

        if (parkingAreaOpt.isEmpty()) {
            return "Parking area not found";
        }

        ParkingAreaEntity parkingArea = parkingAreaOpt.get();

        if (parkingArea.getOccupiedLot() >= parkingArea.getCapacity()) {
            return "No available spots in this parking area";
        }

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setPlateNumber(checkInRequestDto.getPlateNumber());
        vehicleEntity.setType(checkInRequestDto.getType());
        //vehicleEntity.setCheckInDate(checkInRequestDto.getCheckInDate());
        vehicleEntity.setCheckInDate(LocalDateTime.now());
        List<VehicleEntity> vehicles = new ArrayList<>();
        vehicles.add(vehicleEntity);
        parkingArea.setVehicles(vehicles);

        parkingArea.setOccupiedLot(parkingArea.getOccupiedLot() + 1);
        parkingAreaRepository.save(parkingArea);

        return "Vehicle checked in successfully";
    }


    @Transactional
    public CheckOutResponseDto checkOutVehicle(CheckOutRequestDto checkOutRequestDto) {

        //Map
        String parkingAreaName = checkOutRequestDto.getParkingAreaName();
        String plateNumber = checkOutRequestDto.getPlateNumber();
        //VehicleType type = VehicleType.valueOf(checkOutRequestDto.getType().toUpperCase());
        //LocalDateTime checkInDate = checkOutRequestDto.getCheckInDate();

        // Find the parking area by name
        ParkingAreaEntity parkingArea = parkingAreaRepository.findByName(parkingAreaName)
                .orElseThrow(() -> new IllegalArgumentException("Parking area not found"));

        List<VehicleEntity> vehicles = parkingArea.getVehicles();

        // Find the vehicle by plate number
        VehicleEntity vehicleToCheckOut = vehicles.stream()
                .filter(v -> v.getPlateNumber().equals(plateNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));

        //Calculate fee
        LocalDateTime checkOutDate = LocalDateTime.now();
        Duration duration = Duration.between(vehicleToCheckOut.getCheckInDate(), checkOutDate);

        String priceStr = parkingArea.getPrice();
        int basePrice = Integer.parseInt(priceStr);

        // Calculate the fee based on the duration and vehicle type multiplier
        long hoursParked = duration.toHours();
        double fee = hoursParked * basePrice * (VehicleType.valueOf(vehicleToCheckOut.getType()).getMultiplier());

        vehicles.remove(vehicleToCheckOut);
        parkingArea.setOccupiedLot(parkingArea.getOccupiedLot() - 1);
        parkingAreaRepository.save(parkingArea);

        CheckOutResponseDto checkOutResponseDto = new CheckOutResponseDto();
        checkOutResponseDto.setParkingAreaName(checkOutRequestDto.getParkingAreaName());
        checkOutResponseDto.setPlateNumber(checkOutRequestDto.getPlateNumber());
        checkOutResponseDto.setCheckInDate(vehicleToCheckOut.getCheckInDate());
        checkOutResponseDto.setCheckOutDate(checkOutDate);
        checkOutResponseDto.setType(vehicleToCheckOut.getType());
        checkOutResponseDto.setFee(fee);

        return checkOutResponseDto;
    }

}
