package com.example.demo.parkingareamanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CheckOutRequestDto {
    private String parkingAreaName;
    private String plateNumber;
    private LocalDateTime checkInDate;
    private String type;
}
