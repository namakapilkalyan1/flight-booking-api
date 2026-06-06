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

        Flight flight = new Flight(
                request.getFlightNumber(),
                request.getCapacity()
        );

        if (storage.getFlights().containsKey(request.getFlightNumber())) {

            throw new DuplicateFlightException(
                    "Flight already exists"
            );
        }

        storage.getFlights().put(
                request.getFlightNumber(),
                flight
        );

        return flight;
    }
}
