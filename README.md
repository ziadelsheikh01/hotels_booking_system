# 🏨 Hotel Booking System

A **RESTful Hotel Booking System backend** built with **Java and Spring Boot**. The system provides hotel and room management, user authentication, role-based authorization, hotel search, and room booking.

The project focuses on applying real-world backend concepts including **JWT authentication, Spring Security, JPA/Hibernate, transaction management, concurrency control, DTO mapping, validation, exception handling, pagination, dynamic search, and automated testing**.

---

## 🚀 Features

### 🔐 Authentication & Authorization

* User registration and login
* JWT-based stateless authentication
* Password encryption using BCrypt
* Role-based authorization
* Custom authentication and access-denied handling
* Protected REST API endpoints

### 🏨 Hotel Management

* Create hotels
* Retrieve hotels
* Retrieve a hotel by ID
* Update hotel information
* Search hotels using dynamic criteria
* Pagination support

### 🛏️ Room Management

* Add rooms to hotels
* Retrieve rooms belonging to a hotel
* Check room availability
* Prevent duplicate room numbers within the same hotel
* Support different room types and room statuses

### 📅 Booking Management

* Create room bookings
* Validate check-in and check-out dates
* Calculate booking price based on the number of nights
* Detect overlapping bookings
* Handle concurrent booking requests using pessimistic locking
* Prevent booking unavailable or maintenance rooms

### 🛡️ Validation & Exception Handling

* Request validation using Jakarta Bean Validation
* Centralized exception handling using `@ControllerAdvice`
* Custom business exceptions
* Consistent HTTP response status codes

### 📖 API Documentation

* OpenAPI / Swagger documentation
* Interactive API documentation for testing endpoints

### 🧪 Testing

* Unit testing
* Controller testing
* Repository testing
* Integration testing
* H2 in-memory database for testing

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
MySQL Database
```

Additional components:

```text
DTOs
  ↓
MapStruct Mappers
  ↓
Entities

Spring Security
  ├── JWT Filter
  ├── JWT Service
  ├── UserDetailsService
  ├── Authentication Provider
  └── Authorization

Exception Handler
  └── Global Error Handling

Specifications
  └── Dynamic Hotel Search
```

---

## 🧰 Technologies

| Technology         | Purpose                        |
| ------------------ | ------------------------------ |
| Java 21            | Programming Language           |
| Spring Boot 3.3.4  | Backend Framework              |
| Spring Web         | RESTful APIs                   |
| Spring Data JPA    | Data Access                    |
| Hibernate          | ORM                            |
| Spring Security    | Authentication & Authorization |
| JWT                | Stateless Authentication       |
| MySQL              | Production Database            |
| H2                 | Testing Database               |
| MapStruct          | DTO Mapping                    |
| Jakarta Validation | Request Validation             |
| JUnit              | Testing                        |
| Mockito            | Unit Testing                   |
| Maven              | Build & Dependency Management  |
| OpenAPI / Swagger  | API Documentation              |

---

## 🔐 Authentication Flow

The application uses **Spring Security with JWT authentication**.

```text
Client
  │
  │ Login
  ▼
Authentication Controller
  │
  ▼
Authentication Service
  │
  ▼
Spring Security Authentication
  │
  ▼
JWT Generated
  │
  ▼
Client
```

For protected endpoints, the client sends the JWT in the request header:

```http
Authorization: Bearer <JWT>
```

The custom JWT filter validates the token and authenticates the user.

### Roles

The application supports role-based authorization for:

* `ADMIN`
* `HotelManager`
* `USER`

---

## 📅 Booking & Concurrency Handling

One of the main challenges in the system is preventing **double booking** when multiple users attempt to reserve the same room.

The booking process:

1. Validates the booking dates.
2. Locks the requested room using a pessimistic database lock.
3. Checks room availability.
4. Checks for overlapping bookings.
5. Calculates the total booking price.
6. Creates the booking inside a transaction.

### Booking Overlap Detection

Two bookings overlap when:

```text
newCheckIn < existingCheckOut
AND
newCheckOut > existingCheckIn
```

Example:

```text
Existing:
10 Aug ───────── 15 Aug

New:
12 Aug ───────────── 17 Aug

Result: ❌ Overlapping
```

While:

```text
Existing:
10 Aug ───────── 15 Aug

New:
15 Aug ───────────── 20 Aug

Result: ✅ Available
```

### Pessimistic Locking

The room is locked before checking and creating the booking:

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
```

The booking operation is executed within a transactional boundary to maintain data consistency.

---

## 🔎 Hotel Search

The system uses **Spring Data JPA Specifications** to implement dynamic hotel searching.

This allows multiple search criteria to be combined without creating a separate repository query for every possible combination.

Pagination is also supported for hotel results.

---

## 📦 DTO & Mapping

The application does not expose JPA entities directly through the REST API.

Instead, it uses separate request and response DTOs.

```text
Request DTO
    ↓
Service Layer
    ↓
Entity
    ↓
Repository
    ↓
Entity
    ↓
MapStruct
    ↓
Response DTO
```

**MapStruct** is used to simplify entity-to-DTO and DTO-to-entity mapping.

---

## 🗄️ Database Model

The main domain entities are:

```text
User
 │
 ├───────────────┐
 │               │
 ▼               ▼
Booking        Review

Hotel
 │
 ▼
Room
 │
 ▼
Booking
```

### Main Entities

* `User`
* `Hotel`
* `Room`
* `Booking`
* `Review`

### Main Relationships

* A hotel contains multiple rooms.
* A room belongs to a hotel.
* A booking belongs to a room.
* A booking belongs to a user.
* A hotel can have multiple reviews.

---

## 🧪 Testing

The project contains multiple types of automated tests:

```text
                    Tests
                      │
        ┌─────────────┼─────────────┐
        ▼             ▼             ▼
   Controller      Service      Repository
      Tests          Tests          Tests
        │             │             │
        └─────────────┼─────────────┘
                      ▼
                Integration
                   Tests
```

Testing technologies include:

* JUnit
* Mockito
* Spring Boot Test
* MockMvc
* Spring Data JPA Test
* H2

---

## 📖 API Documentation

Swagger/OpenAPI is used to document and test the REST APIs.

After starting the application, access the Swagger UI through:

```text
http://localhost:8080/docs
```

---

## 📌 Main API Endpoints

### Authentication

| Method | Endpoint             | Description           |
| ------ | -------------------- | --------------------- |
| POST   | `/api/auth/register` | Register a new user   |
| POST   | `/api/auth/login`    | Login and receive JWT |

### Hotels

| Method | Endpoint            | Description     |
| ------ | ------------------- | --------------- |
| GET    | `/api/hotel`        | Get all hotels  |
| GET    | `/api/hotel/{id}`   | Get hotel by ID |
| POST   | `/api/hotel`        | Create a hotel  |
| PATCH  | `/api/hotel/{id}`   | Update a hotel  |
| POST   | `/api/hotel/search` | Search hotels   |

### Rooms

| Method | Endpoint                      | Description             |
| ------ | ----------------------------- | ----------------------- |
| GET    | `/api/hotel/{hotelId}/room`   | Get rooms for a hotel   |
| POST   | `/api/hotel/{hotelId}/room`   | Add a room to a hotel   |
| GET    | `/api/room/{id}/availability` | Check room availability |

### Bookings

| Method | Endpoint       | Description      |
| ------ | -------------- | ---------------- |
| POST   | `/api/booking` | Create a booking |

---

## ⚙️ Getting Started

### Prerequisites

Make sure you have the following installed:

* Java 21
* Maven
* MySQL

Check your Java version:

```bash
java -version
```

---

### 1. Clone the Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd hotels_booking_system
```

---

### 2. Create the Database

Create a MySQL database:

```sql
CREATE DATABASE booking_system_db;
```

---

### 3. Configure the Application

For security, database credentials and JWT secrets should be provided through environment variables.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/booking_system_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

Jwt.secret=${JWT_SECRET}
```

> **Important:** Never commit real database passwords, JWT secrets, API keys, or other credentials to GitHub.

---

### 4. Run the Application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On Linux/macOS:

```bash
./mvnw spring-boot:run
```

---

## 🧪 Running Tests

On Windows:

```bash
mvnw.cmd test
```

On Linux/macOS:

```bash
./mvnw test
```

---

## 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.example.hotelmanagmentsystem
│   │       ├── configuration
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── enums
│   │       ├── exceptionHandler
│   │       ├── mapper
│   │       ├── repository
│   │       ├── security
│   │       ├── service
│   │       └── specification
│   │
│   └── resources
│       └── application.properties
│
└── test
    ├── java
    │   └── com.example.hotelmanagmentsystem
    │       ├── controller
    │       ├── integration
    │       ├── repository
    │       └── service
    │
    └── resources
        └── application-test.properties
```

---

## 🎯 Backend Concepts Demonstrated

This project demonstrates practical experience with:

* Java
* Object-Oriented Programming
* Spring Boot
* RESTful API Design
* Dependency Injection
* Spring Security
* JWT Authentication
* Role-Based Authorization
* Spring Data JPA
* Hibernate
* Entity Relationships
* Lazy Loading
* DTO Pattern
* MapStruct
* Bean Validation
* Global Exception Handling
* Pagination
* JPA Specifications
* Transactions
* Pessimistic Locking
* Concurrency Handling
* MySQL
* Unit Testing
* Integration Testing
* Repository Testing
* OpenAPI / Swagger

---

## 🔮 Future Improvements

Planned improvements include:

* Refresh token authentication
* Improved user ownership and authorization
* Flyway or Liquibase database migrations
* Redis caching
* More comprehensive security and booking tests
* Improved pagination metadata
* Docker and Docker Compose
* CI/CD with GitHub Actions
* Centralized logging and monitoring
* Booking cancellation and modification
* Email notifications for booking confirmation

---

## 👨‍💻 Project Purpose

This project was developed as a hands-on **Java Spring Boot backend project** to apply real-world backend development concepts in a hotel reservation domain.

The main goal was to go beyond basic CRUD operations and implement important backend concerns such as:

**Authentication → Authorization → Validation → Transactions → Concurrency → Booking Conflict Detection → Testing**

---

⭐ If you find this project useful, feel free to explore the source code and provide feedback.
