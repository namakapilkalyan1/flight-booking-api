package com.example.flightbookingapi.dto;

import jakarta.validation.constraints.NotBlank;

public class BookingRequest {

   @NotBlank(message = "Flight number is required")
    private String flightNumber;

    @NotBlank(message = "Passenger name is required")
    private String passengerName;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
}
