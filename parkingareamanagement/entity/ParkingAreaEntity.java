package com.example.demo.parkingareamanagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "parkingarea", schema = "parkingproject")
@NoArgsConstructor // Default constructor for JPA
@AllArgsConstructor // Constructor with all arguments
@Setter
@Getter
public class ParkingAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "occupied_lot")
    private int occupiedLot;

    //    @JsonIgnore
    @OneToMany(mappedBy = "parkingArea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "vehicleList_id")
    private List<VehicleEntity> vehicles;

    @Column(name = "has_elevator")
    private boolean hasElevator = false;

    @Column(name = "price")
    private String price;


}
