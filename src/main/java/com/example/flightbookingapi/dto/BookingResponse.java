package com.example.flightbookingapi.dto;

public class BookingResponse {

    private String bookingId;
    private String flightNumber;
    private String passengerName;

    public BookingResponse(String bookingId,
                           String flightNumber,
                           String passengerName) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }
}
