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

## API Documentation

This project uses OpenAPI 3.0 (Swagger) for API documentation. After starting the application, you can access:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- OpenAPI YAML: `http://localhost:8080/v3/api-docs.yaml`

### Using Swagger UI

1. Navigate to `http://localhost:8080/swagger-ui.html` in your web browser
2. You'll see a list of all available endpoints with:
   - Detailed descriptions
   - Request/response schemas
   - Example payloads
   - Try-it-out functionality

## API Endpoints

### Drug Management
All endpoints are prefixed with `/api/drugs`

#### Basic CRUD Operations
- `GET /` - Retrieve all drugs in the system
- `GET /{id}` - Retrieve a specific drug by its UUID
- `POST /` - Create a new drug entry
  - Requires drug details in request body
- `PUT /{id}` - Update an existing drug by its UUID
  - Requires updated drug details in request body
- `DELETE /{id}` - Delete a drug by its UUID

#### Search Operations
- `GET /search/name?name={drugName}` - Search drugs by name (case-insensitive)
- `GET /search/manufacturer?manufacturer={manufacturerName}` - Search drugs by manufacturer name (case-insensitive)
- `GET /search/price-range?minPrice={minPrice}&maxPrice={maxPrice}` - Search drugs within a specific price range

### Request/Response Format
Drugs are represented using the following structure:
```json
{
    "uid": "UUID",
    "name": "string",
    "manufacturerName": "string",
    "quantity": "integer",
    "price": "decimal"
}
```

