package com.example.flightbookingapi.exception;

public class DuplicateFlightException extends RuntimeException {

    public DuplicateFlightException(String message) {
        super(message);
    }
}
