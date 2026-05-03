# Library Management System

A Spring Boot web application for managing authors and books, built as part of the BITS Database Systems SGA2 assignment.

## Overview

The application manages two entities — **Authors** and **Books** — with a Many-to-One relationship. It supports Create, Read, and Update operations for both entities, with a custom JPQL inner join query powering the books list view.

## Tech Stack

- **Backend:** Spring Boot 3.5.14, Spring Data JPA, Hibernate 6
- **Database:** MySQL 9.6
- **View Layer:** JSP + JSTL
- **Testing:** JUnit 5 + Mockito
- **Build:** Maven

## Project Structure

```
src/main/java/com/library/library_management/
├── controller/        # MVC controllers (Home, Author, Book)
├── service/           # Business logic layer
├── repository/        # JPA repositories with custom query
├── entity/            # JPA entity classes (Author, Book)
├── dto/               # BookSummary DTO for inner join result
├── exception/         # ResourceNotFoundException
└── DataInitializer    # Seeds 10 authors and 10 books on startup
```

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL (running on port 3306)

### Setup

1. Start MySQL:
   ```bash
   brew services start mysql
   ```

2. Clone the repository:
   ```bash
   git clone https://github.com/abdurrahmaan11265/library-management.git
   cd library-management
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Open your browser at:
   ```
   http://localhost:8080
   ```

The database `library_db` is created automatically on first run. Sample data (10 authors, 10 books) is seeded on first startup.

## Running Tests

```bash
mvn test
```

All 20 tests pass across 4 test classes — 2 service tests (Mockito) and 2 repository tests (@DataJpaTest with H2).

## Features

- **Authors:** Add, view, and edit authors with name, nationality, and bio
- **Books:** Add, view, and edit books with title, genre, year, and author
- **Inner Join Query:** Books list fetches title, genre, year, author name, and nationality in a single JPQL join query
- **Exception Handling:** `ResourceNotFoundException` for missing records, `DataIntegrityViolationException` for constraint violations
- **Data Seeding:** 10 authors and 10 books pre-loaded on first startup