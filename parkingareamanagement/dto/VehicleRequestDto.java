package com.example.demo.parkingareamanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VehicleRequestDto {
    private String plateNumber;
    private LocalDateTime checkInDate;
    private String type;
}
