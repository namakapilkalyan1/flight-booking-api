package com.example.flightbookingapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateFlightRequest {

    @NotBlank(message = "Flight number is required")
    private String flightNumber;

    @Positive(message = "Capacity must be greater than zero")
    private int capacity;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
