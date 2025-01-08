package com.example.demo.parkingareamanagement.controller;

import com.example.demo.parkingareamanagement.dto.CheckInRequestDto;
import com.example.demo.parkingareamanagement.entity.ParkingAreaEntity;
import com.example.demo.parkingareamanagement.entity.VehicleEntity;
import com.example.demo.parkingareamanagement.repository.ParkingAreaRepository;
import com.example.demo.parkingareamanagement.service.ParkingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/example")
public class ParkingAreaController {

    private final ParkingAreaRepository repository;


    @Autowired
    private final ParkingAreaService parkingareaservice;

    public ParkingAreaController(ParkingAreaRepository repository, ParkingAreaService parkingareaservice) {
        this.repository = repository;
        this.parkingareaservice = parkingareaservice;
    }
        @GetMapping
        public List<ParkingAreaEntity> getAll() {
            return repository.findAll();
        }

    // Get all vehicles for a specific parking area
    @GetMapping("/{parkingAreaId}/vehicles")
    public List<VehicleEntity> getAllVehicles(@PathVariable Long parkingAreaId) {
        return repository.findAllVehiclesByParkingAreaId(parkingAreaId);
    }


        @PostMapping
        public ParkingAreaEntity create(@RequestBody ParkingAreaEntity entity) {
            System.out.print(entity.getName()+" is created");
            return parkingareaservice.createParkingArea(entity);

        }

        @PutMapping
        public ParkingAreaEntity update(@RequestBody ParkingAreaEntity entity) {
            System.out.print(entity.getName()+ " is updated");
            return parkingareaservice.updateParkingArea(entity);
        }

        @DeleteMapping
        public void delete(@RequestBody Long id) {
            System.out.print("area with id "+ id + " is deleted");
             parkingareaservice.deleteParkingArea(id);
        }


}
