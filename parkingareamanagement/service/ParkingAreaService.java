package com.example.demo.parkingareamanagement.service;

import com.example.demo.parkingareamanagement.entity.ParkingAreaEntity;
import com.example.demo.parkingareamanagement.repository.ParkingAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ParkingAreaService {

    private final ParkingAreaRepository repository;

    public ParkingAreaEntity createParkingArea(ParkingAreaEntity parkingareaentity) {
        return repository.save(parkingareaentity);
    }

    public ParkingAreaEntity updateParkingArea(ParkingAreaEntity parkingAreaEntity) {
        if(!repository.existsById(parkingAreaEntity.getId())) {
            throw new IllegalArgumentException("Parking area with ID " + parkingAreaEntity.getId() + " does not exist.");
        }
        return repository.save(parkingAreaEntity);
    }

    public void deleteParkingArea(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Parking area with ID " + id + " does not exist.");
        }
        repository.deleteById(id);
    }



//    public List<VehicleEntity> getAllVehicles(Long parkingAreaId) {
//        // Find the parking area by its ID
//        ParkingAreaEntity parkingArea = repository.findById(parkingAreaId)
//                .orElseThrow(() -> new IllegalArgumentException("Parking area with ID " + parkingAreaId + " does not exist."));
//
//        // Return the list of vehicles associated with this parking area
//        return parkingArea.getVehicles();
//    }

    //Calculations
    public ParkingAreaEntity dailyIncome(ParkingAreaEntity parkingAreaEntity) {
        if(!repository.existsById(parkingAreaEntity.getId())) {
            throw new IllegalArgumentException("Parking area with ID " + parkingAreaEntity.getId() + " does not exist.");
        }

        //Get all the vegicled logged in that day

        return null;
    }


}
