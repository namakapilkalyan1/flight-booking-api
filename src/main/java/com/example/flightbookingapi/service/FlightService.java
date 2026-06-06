package com.example.flightbookingapi.service;

import org.springframework.stereotype.Service;

import com.example.flightbookingapi.dto.CreateFlightRequest;
import com.example.flightbookingapi.exception.DuplicateFlightException;
import com.example.flightbookingapi.model.Flight;
import com.example.flightbookingapi.repository.InMemoryStorage;

@Service
public class FlightService {

    private final InMemoryStorage storage;

    public FlightService(InMemoryStorage storage) {
        this.storage = storage;
    }

    public Flight createFlight(CreateFlightRequest request) {

        String flightNumber = request.getFlightNumber().trim();

        Flight flight = new Flight(
                flightNumber,
                request.getCapacity()
        );

        if (storage.getFlights().containsKey(flightNumber)) {

            throw new DuplicateFlightException(
                "Flight " + flightNumber
                        + " already exists"
            );
        }

        storage.getFlights().put(
                flightNumber,
                flight
        );

        return flight;
    }
}