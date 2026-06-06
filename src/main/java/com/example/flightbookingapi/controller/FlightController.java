package com.example.flightbookingapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightbookingapi.dto.CreateFlightRequest;
import com.example.flightbookingapi.model.Flight;
import com.example.flightbookingapi.service.FlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flight createFlight(
            @Valid @RequestBody CreateFlightRequest request) {

        return flightService.createFlight(request);
    }
}
