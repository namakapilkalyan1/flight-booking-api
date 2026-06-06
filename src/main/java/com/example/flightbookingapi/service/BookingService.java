package com.example.flightbookingapi.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.flightbookingapi.dto.BookingRequest;
import com.example.flightbookingapi.dto.BookingResponse;
import com.example.flightbookingapi.exception.FlightFullException;
import com.example.flightbookingapi.exception.FlightNotFoundException;
import com.example.flightbookingapi.model.Booking;
import com.example.flightbookingapi.model.Flight;
import com.example.flightbookingapi.repository.InMemoryStorage;

@Service
public class BookingService {

    private final InMemoryStorage storage;

    public BookingService(InMemoryStorage storage) {
        this.storage = storage;
    }

    public BookingResponse createBooking(BookingRequest request) {

        String flightNumber = request.getFlightNumber().trim();

        String passengerName = request.getPassengerName().trim();

        Flight flight = storage.getFlights().get(flightNumber);

        if (flight == null) {
            throw new FlightNotFoundException(
                    "Flight " + request.getFlightNumber() + " not found"
            );
        }

        synchronized (flight) {

            if (flight.getBookedSeats() >= flight.getCapacity()) {

                throw new FlightFullException(
                        "Flight " + flightNumber
                                + " is fully booked"
                );
            }

            flight.setBookedSeats(
                    flight.getBookedSeats() + 1
            );
        }

        String bookingId = UUID.randomUUID().toString();

        Booking booking = new Booking(
                bookingId,
                request.getFlightNumber(),
                request.getPassengerName()
        );

        storage.getBookings().put(
                bookingId,
                booking
        );

        return new BookingResponse(
                bookingId,
                booking.getFlightNumber(),
                booking.getPassengerName()
        );
    }
}