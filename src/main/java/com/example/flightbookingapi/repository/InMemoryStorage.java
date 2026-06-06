package com.example.flightbookingapi.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.example.flightbookingapi.model.Booking;
import com.example.flightbookingapi.model.Flight;

@Component
public class InMemoryStorage {

    private final Map<String, Flight> flights = new ConcurrentHashMap<>();

    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public Map<String, Flight> getFlights() {
        return flights;
    }

    public Map<String, Booking> getBookings() {
        return bookings;
    }
}
