package com.example.demo.parkingareamanagement.enums;

public enum VehicleType {
    AUTOMOBILE(1),
    SUV(1.5),
    MINIVAN(2);

    private final double multiplier;

    VehicleType(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
