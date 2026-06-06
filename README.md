README

# Flight Booking API

A simple REST API for managing flight bookings built using Spring Boot and Java.

---

# Assumptions

The following assumptions were made during implementation:

* Clients already know the flight number.
* Flight search functionality is not required.
* Authentication and authorization are not required.
* Data is stored in memory only.
* One booking reserves exactly one seat.
* Overbooking is not allowed.
* Booking retrieval APIs are not required.
* Single application instance only.
* Flight capacity is fixed after creation.

---

# Technology Stack

* Java 21
* Spring Boot 3
* Maven
* ConcurrentHashMap (In-Memory Storage)

---

# Project Structure

```text
src/main/java/com/example/flightbookingapi

├── controller
├── service
├── repository
├── model
├── dto
├── exception
└── FlightBookingApplication
```

---

# How to Run

## Prerequisites

* Java 21
* Maven 3.9+

Verify installation:

```bash
java -version
mvn -version
```

## Build

```bash
mvn clean package
```

## Run

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

---

# API Endpoints

## Health Check

### Request

```http
GET /flights/health
```

### Response

```text
UP
```

---

## Create Flight

### Request

```http
POST /flights
```

```json
{
  "flightNumber": "AI101",
  "capacity": 2
}
```

### Response

```json
{
  "flightNumber": "AI101",
  "capacity": 2,
  "bookedSeats": 0
}
```

### Status

```text
201 Created
```

---

## Create Booking

### Request

```http
POST /bookings
```

```json
{
  "flightNumber": "AI101",
  "passengerName": "Kapil"
}
```

### Response

```json
{
  "bookingId": "generated-uuid",
  "flightNumber": "AI101",
  "passengerName": "Kapil"
}
```

### Status

```text
201 Created
```

---

# Error Responses

## Flight Not Found

### Response

```json
{
  "message": "Flight AI999 not found"
}
```

### Status

```text
404 Not Found
```

---

## Flight Already Exists

### Response

```json
{
  "message": "Flight AI101 already exists"
}
```

### Status

```text
409 Conflict
```

---

## Flight Fully Booked

### Response

```json
{
  "message": "Flight AI101 is fully booked"
}
```

### Status

```text
409 Conflict
```

---

## Validation Failure

### Response

```json
{
  "message": "Validation failed",
  "errors": [
    "flightNumber: Flight number is required",
    "passengerName: Passenger name is required"
  ]
}
```

### Status

```text
400 Bad Request
```

---

# Design Decisions

## Layered Architecture

The application follows a simple layered architecture:

* Controller layer handles HTTP requests.
* Service layer contains business logic.
* Repository layer contains storage abstraction.
* DTOs define API contracts.
* Model classes represent domain objects.

## In-Memory Storage

ConcurrentHashMap is used for storing:

* Flights
* Bookings

This keeps the implementation simple and avoids introducing database complexity.

## Booking IDs

Booking identifiers are generated using UUID.

## Exception Handling

A centralized exception handler translates business exceptions into meaningful HTTP responses.

## Overbooking Prevention

The booking operation validates seat availability before creating a booking.

A synchronized block is used during seat allocation to reduce the risk of overbooking under concurrent requests in a single application instance.

---

# Manual Review and Improvements

After generating the solution using AI, the following manual improvements were performed.

## Manual Fixes

* Added validation annotations to booking request fields.
* Added @Valid processing in BookingController.
* Added centralized HTTP 400 handling for validation failures.
* Made seat allocation atomic using synchronization to reduce overbooking risk under concurrent requests.

## Manual Improvements

* Enhanced validation responses to return all validation errors instead of only the first failure.
* Added input sanitization by trimming request values before processing.
* Improved exception messages to include contextual information such as flight numbers.
* Added a lightweight health-check endpoint for easier local verification and testing.

---

# AI Prompts Used

## Commit 1

Create a Spring Boot application using Java and Maven.

Generate a minimal project structure including:

* Main application class
* Maven configuration
* Standard package structure

Follow clean code principles.

## Commit 2

Implement flight creation functionality.

Entity:

* flightNumber
* capacity
* bookedSeats

Endpoint:

POST /flights

Validation Rules:

* flightNumber cannot be blank
* capacity must be greater than zero

Store flights in ConcurrentHashMap.

Use DTOs and service layer.

## Commit 3

Implement flight booking functionality.

Entity:

* bookingId
* flightNumber
* passengerName

Endpoint:

POST /bookings

Business Rules:

* Flight must exist
* One booking reserves one seat
* Booking ID should be UUID based
* Do not allow overbooking

Store bookings in memory.

## Commit 4

Add centralized exception handling.

Create:

* FlightNotFoundException
* FlightFullException
* DuplicateFlightException

Implement GlobalExceptionHandler.

Return appropriate HTTP status codes and JSON error responses.

## Commit 5

This commit was not a prompt. I have manually reviewed and improved the AI-generated code.

Focused on:

* Validation
* Error handling
* Concurrency
* Input sanitization
* API usability

---

# What I Would Improve With More Time

## Persistence

Replace in-memory storage with a relational database.

## Automated Testing

Add:

* Unit tests
* Integration tests

## API Documentation

Add Swagger/OpenAPI support.

## Concurrency

Introduce stronger concurrency guarantees and load testing.

## Monitoring

Add health indicators, metrics, and structured logging.

## Validation

Provide field-level validation response objects with error codes.

## Deployment

Containerize the application using Docker and add CI/CD support.