package com.example.demo.parkingareamanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor // Default constructor for JPA
@AllArgsConstructor // Constructor with all arguments
@Setter
@Getter
@Entity
@Table(name = "vehicles", schema = "parkingproject")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "check_in_date")
    private LocalDateTime checkInDate; // Check-in date and time

    @Column(name = "check_out_date", nullable = true)
    private LocalDateTime checkOutDate;


    @JoinColumn(name = "parking_area_id")
    private ParkingAreaEntity parkingArea;

}
