# SureCost Take Home Project

A Java-powered REST API service built on Spring Boot, designed for comprehensive drug information management. This application excels in performing CRUD operations, optimizing data handling processes, and providing robust search capabilities for pharmaceutical data.

## Technologies Used

- Spring Boot 3.4.4
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Maven
- SpringDoc OpenAPI UI 2.3.0
- JUnit 5 & Mockito

## Prerequisites

- Java JDK 21
- Apache Maven 3.9.9

## Getting Started

### Installation

1. Clone the repository
```bash
git clone https://github.com/JoeySouzaAO1/SureCostTakeHome.git
```

2. Navigate to the project directory
```bash
cd SureCostTakeHome
```

3. Build the project
```bash
mvn clean install
```

### Running the Application

To run the application:
```bash
mvn spring-boot:run
```

The application will start and be available at `http://localhost:8080`

## Database Configuration

This project uses H2 in-memory database for quick setup and testing:
- **Development Ready**: Perfect for local development and testing
- **Production Ready**: Easily switchable to MySQL/PostgreSQL with minimal configuration changes

### H2 Console Access
The H2 console is available at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `` (empty)

## Quick Start Guide

### Basic API Usage Example
Create a new drug:
```bash
curl -X POST http://localhost:8080/api/drugs \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Aspirin",
    "manufacturerName": "Bayer",
    "quantity": 100,
    "price": 9.99
  }'
```

## API Documentation

API documentation is available in multiple formats:

1. **Interactive Documentation**:
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

2. **OpenAPI Specifications**:
   - JSON: `http://localhost:8080/v3/api-docs`
   - YAML: `http://localhost:8080/v3/api-docs.yaml`

For detailed API documentation, including:
- Complete endpoint listings
- Request/response formats
- Testing strategy
- Implementation details
- Design decisions

Please refer to our comprehensive [DOCS.md](DOCS.md) file.