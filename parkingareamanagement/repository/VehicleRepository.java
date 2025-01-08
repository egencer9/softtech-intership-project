package com.example.demo.parkingareamanagement.repository;

import com.example.demo.parkingareamanagement.entity.ParkingAreaEntity;
import com.example.demo.parkingareamanagement.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}
