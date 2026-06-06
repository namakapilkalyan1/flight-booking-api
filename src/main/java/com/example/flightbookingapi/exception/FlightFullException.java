package com.example.flightbookingapi.exception;

public class FlightFullException extends RuntimeException {

    public FlightFullException(String message) {
        super(message);
    }
}
