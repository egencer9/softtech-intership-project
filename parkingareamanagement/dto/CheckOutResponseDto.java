package com.example.demo.parkingareamanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CheckOutResponseDto {
    private String parkingAreaName;
    private String plateNumber;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String type;
    private Double fee;
}
