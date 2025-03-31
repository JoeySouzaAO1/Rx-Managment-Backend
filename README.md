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


## Database Configuration

This project uses H2 in-memory database for several key reasons:
- **Zero Configuration**: No external database setup required
- **Instant Startup**: In-memory operation provides immediate availability
- **Portability**: Runs anywhere with Java, no additional installations needed
- **Testing**: Ideal for demonstrating functionality and running test cases

The application uses Spring Data JPA/Hibernate for database operations, making it production-ready and database-agnostic. Switching to a different database (MySQL, PostgreSQL, etc.) requires only:
1. Adding the appropriate database dependency
2. Updating application properties with connection details
3. No code changes needed due to JPA abstraction

### H2 Console Access
The H2 console is available at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: ` ` (empty)

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
- `GET /` - Retrieve all drugs in the system (paginated)
  - Query Parameters:
    - `page`: Page number (0-based, default: 0)
    - `size`: Items per page (default: 20)
    - `sortBy`: Field to sort by (default: name)
    - `direction`: Sort direction (asc/desc, default: asc)
- `GET /{id}` - Retrieve a specific drug by its UUID
- `POST /` - Create a new drug entry
- `PUT /{id}` - Update an existing drug by its UUID
- `DELETE /{id}` - Delete a drug by its UUID

#### Bulk Operations
- `POST /bulk` - Create multiple drugs in a single request
- `PUT /bulk` - Update multiple drugs in a single request
- `DELETE /bulk` - Delete multiple drugs by their UUIDs

#### Search Operations
All search endpoints support pagination with the same query parameters as above.

- `GET /search/name?name={drugName}` - Search drugs by name (case-insensitive)
- `GET /search/manufacturer?manufacturer={manufacturerName}` - Search drugs by manufacturer name (case-insensitive)
- `GET /search/price-range?minPrice={minPrice}&maxPrice={maxPrice}` - Search drugs within a specific price range

### Request/Response Format

#### Single Drug Format
```json
{
    "uid": "UUID",
    "name": "string",
    "manufacturerName": "string",
    "quantity": "integer",
    "price": "decimal"
}
```

#### Bulk Request Format
For POST /bulk and PUT /bulk:
```json
{
    "drugs": [
        {
            "uid": "UUID",
            "name": "string",
            "manufacturerName": "string",
            "quantity": "integer",
            "price": "decimal"
        }
    ]
}
```

For DELETE /bulk:
```json
{
    "ids": ["UUID1", "UUID2", "UUID3"]
}
```

#### Paginated Response Format
```json
{
    "content": [
        // Array of drug items
        ],
    "totalElements": 100,
    "totalPages": 5,
    "number": 0,
    "size": 20,
    "first": true,
    "last": false
}
```

## Testing Strategy

The project includes a comprehensive testing approach, demonstrating both implemented tests and planned test coverage through method stubs.

### Implemented Tests
The `DrugControllerTest` class contains fully functional unit tests for core CRUD operations:
- `createDrug_Success()`: Validates successful drug creation workflow
- `getAllDrugs_Success()`: Tests paginated drug retrieval
- `deleteDrug_Success()`: Ensures proper drug deletion
- `updateDrug_Success()`: Verifies drug update functionality

The `DrugIntegrationTest` class demonstrates end-to-end testing with a running application context:
- `createAndRetrieveDrug_Success()`: Tests the complete flow of creating and retrieving a drug
  - Validates HTTP responses
  - Verifies database persistence
  - Confirms JSON serialization/deserialization
  - Tests API endpoint integration

### Planned Test Coverage
To demonstrate comprehensive test planning, additional test methods are stubbed out to show what would be implemented with more time:

#### Controller Tests
- `createDrug_WithInvalidData_ShouldReturnBadRequest()`: Validates API behavior with invalid drug data
- `createDrug_WithNullPrice_ShouldReturnBadRequest()`: Tests price validation requirements
- `searchByName_ShouldReturnMatchingDrugs()`: Verifies name search functionality
- `searchByManufacturer_ShouldReturnMatchingDrugs()`: Tests manufacturer search capability
- `getAllDrugs_WithInvalidPagination_ShouldReturnBadRequest()`: Validates pagination parameter handling
- `updateDrug_WithNonexistentId_ShouldReturnNotFound()`: Tests error handling for non-existent drugs

#### Integration Tests
- `testBulkDrugCreation_Success()`: Tests creating multiple drugs in a single request and verifies retrieval
- `testSearchDrugs_WithFilters()`: Tests search functionality with combinations of name, manufacturer, price range, and quantity filters
- `testPaginationAndSorting()`: Validates pagination with different page sizes and sorting by different fields
- `testDeleteDrug_WithRelatedData()`: Verifies proper cleanup of related data during deletion
- `testErrorScenarios()`: Tests various error scenarios including invalid data, non-existent drug retrieval, and database constraints