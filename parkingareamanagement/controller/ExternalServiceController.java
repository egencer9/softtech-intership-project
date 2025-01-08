package com.example.demo.parkingareamanagement.controller;

import com.example.demo.parkingareamanagement.dto.CheckInRequestDto;
import com.example.demo.parkingareamanagement.dto.CheckOutRequestDto;
import com.example.demo.parkingareamanagement.dto.CheckOutResponseDto;
import com.example.demo.parkingareamanagement.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/external-services")
@RestController
public class ExternalServiceController {
    private final ExternalService externalService;

    public ExternalServiceController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<String> checkInVehicle(@RequestBody CheckInRequestDto checkInRequestDto) {
        String result = externalService.checkInVehicle(checkInRequestDto);

        return switch (result) {
            case "Parking area not found" -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            case "No available spots in this parking area" ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            case "Vehicle checked in successfully" -> ResponseEntity.ok(result);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        };
    }

    @PostMapping("/check-out")
    public ResponseEntity<CheckOutResponseDto> checkOutVehicle(@RequestBody CheckOutRequestDto checkOutRequestDto) {

        try {
            CheckOutResponseDto checkOutResponseDto = externalService.checkOutVehicle(checkOutRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(checkOutResponseDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
