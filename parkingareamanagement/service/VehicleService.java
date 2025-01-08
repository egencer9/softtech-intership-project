package com.example.demo.parkingareamanagement.service;

import com.example.demo.parkingareamanagement.dto.VehicleRequestDto;
import com.example.demo.parkingareamanagement.entity.VehicleEntity;
import com.example.demo.parkingareamanagement.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public void save(VehicleRequestDto vehicleRequestDto) {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setPlateNumber(vehicleRequestDto.getPlateNumber());
        vehicleEntity.setType(vehicleRequestDto.getType());

        vehicleRepository.save(vehicleEntity);
    }

    public List<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }


    public Optional<VehicleEntity> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    public VehicleEntity update(Long id, VehicleRequestDto vehicleRequestDto) {
        VehicleEntity vehicleEntity = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + id));

        vehicleEntity.setPlateNumber(vehicleRequestDto.getPlateNumber());
        vehicleEntity.setType(vehicleRequestDto.getType());

        return vehicleRepository.save(vehicleEntity);
    }

    public void deleteById(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }


}
