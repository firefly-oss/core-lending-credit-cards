# Core Lending Credit Cards Service

## Overview
The Core Lending Credit Cards Service is a microservice component of the Firefly platform that manages credit card revolving lines, billing cycles, statements, transactions, and payments. It provides a comprehensive API for credit card management operations.

## Architecture
This service follows a modular, hexagonal architecture pattern with the following key components:

- **Reactive Programming**: Built using Spring WebFlux for non-blocking, reactive API endpoints
- **Database Access**: Uses R2DBC for reactive database operations
- **API Documentation**: Integrated OpenAPI/Swagger for API documentation
- **Modular Design**: Separated into interfaces, core logic, data models, and web layers

## Module Structure

### core-lending-credit-cards-interfaces
Contains Data Transfer Objects (DTOs) and interfaces that define the contract for the service.
- DTOs for data exchange
- Enums for standardized values
- Interface definitions

### core-lending-credit-cards-models
Contains database entities and repositories.
- Entity classes mapped to database tables
- Repository interfaces for data access
- Database migration scripts

### core-lending-credit-cards-core
Contains the business logic and service implementations.
- Service implementations
- Mappers for converting between DTOs and entities
- Business logic and validation

### core-lending-credit-cards-web
Contains the web layer with controllers and application configuration.
- REST controllers
- Application entry point
- Web-specific configurations

## Key Features
- Credit card revolving line management
- Billing cycle tracking
- Statement generation and management
- Transaction recording and processing
- Payment processing and tracking
- Reactive API endpoints
- Comprehensive filtering and pagination

## Database Schema
The service uses a PostgreSQL database with the following main tables:
- `cc_revolving_line`: Stores credit card revolving line information
- `cc_billing_cycle`: Tracks billing cycles for revolving lines
- `cc_statement`: Contains statement information for billing cycles
- `cc_transaction`: Records transactions against revolving lines
- `cc_payment`: Tracks payments made against revolving lines or statements

## API Endpoints

### Revolving Lines
- `GET /api/v1/cc-revolving-lines`: List or search revolving lines
- `POST /api/v1/cc-revolving-lines`: Create a new revolving line
- `GET /api/v1/cc-revolving-lines/{id}`: Get a revolving line by ID
- `PUT /api/v1/cc-revolving-lines/{id}`: Update a revolving line
- `DELETE /api/v1/cc-revolving-lines/{id}`: Delete a revolving line

Similar endpoints exist for billing cycles, statements, transactions, and payments.

## Setup and Installation

### Prerequisites
- Java 21
- Maven
- PostgreSQL database

### Building the Service
```bash
mvn clean install
```

### Running the Service
```bash
java -jar core-lending-credit-cards-web/target/core-lending-credit-cards-web-1.0.0-SNAPSHOT.jar
```

### Configuration
The service can be configured through the `application.yaml` file in the `core-lending-credit-cards-web` module.

## Development Guidelines

### Adding New Features
1. Define DTOs in the interfaces module
2. Create entity classes in the models module
3. Implement service interfaces in the core module
4. Add controllers in the web module

### Testing
- Unit tests should be added for all new functionality
- Integration tests should verify the API endpoints

## Integration with Other Services
This service integrates with other Firefly platform services:
- Accounts domain for account information
- Card Management domain for card details
- Product domain for product information
- Transactions domain for transaction processing
- Payment domain for payment processing

## Dependencies
- Spring Boot
- Spring WebFlux
- Spring Data R2DBC
- Lombok
- Swagger/OpenAPI
- Jackson
- Flyway for database migrations