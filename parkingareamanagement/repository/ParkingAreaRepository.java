package com.example.demo.parkingareamanagement.repository;

import com.example.demo.parkingareamanagement.entity.ParkingAreaEntity;
import com.example.demo.parkingareamanagement.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ParkingAreaRepository extends JpaRepository<ParkingAreaEntity, Long> {
    List<ParkingAreaEntity> findByLocation(String location);
    Optional<ParkingAreaEntity> findByName(String name);
    Optional<ParkingAreaEntity> findByIdAndName(Long id, String name);

    //with query

    @Query("SELECT v FROM VehicleEntity v WHERE v.parkingArea.id = :parkingAreaId")
    List<VehicleEntity> findAllVehiclesByParkingAreaId(@Param("parkingAreaId") Long parkingAreaId);

}
