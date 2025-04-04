# Rx Managment Backend Documentation

## Table of Contents
- [Project Overview](#project-overview)
- [Project Planning and Brainstorming](#project-planning-and-brainstorming)
- [Key Features](#key-features)
- [Assumptions and Constraints](#assumptions-and-constraints)
- [Design Decisions](#design-decisions)
- [API Standards](#api-standards)
- [Testing Strategy](#testing-strategy)
- [Future Enhancements](#future-enhancements)
- [AI Tool Usage](#ai-tool-usage)

## Project Overview
The Rx Management Service is a Java-based REST API designed for managing drug information. It supports CRUD operations, bulk data processing, and advanced search capabilities while ensuring scalability and efficient data handling.

## Project Planning and Brainstorming
The initial planning phase of the Rx Management Service utilized collaborative tools to establish a clear project direction and maintain organized documentation.

### Mural Collaboration
- **To-Do List Creation**: Developed a comprehensive task breakdown using Mural's digital whiteboard
  - Prioritized features based on core functionality requirements
  - Created sprint-based task groupings
  - Established dependencies between different components
  
- **Mind Mapping**: Generated a detailed mind map to visualize:
  - Core service components
  - Data flow relationships
  - Integration points
  - Potential scalability challenges
  - Future enhancement possibilities

### Notion Documentation
- **Structured Documentation**: Maintained living documentation throughout development
  - Technical specifications
  - API endpoint planning
  - Database schema evolution
  - Meeting notes and decisions
  - Progress tracking
  - Resource links and references

## Key Features
- **CRUD Operations**: Create, retrieve, update, and delete drug information
- **Bulk Data Processing**: Handle multiple records in a single submission
- **Advanced Search**: Search drugs by name, manufacturer, or price range
- **JSON Support**: Accepts and returns data in JSON format
- **Scalability**: Designed to handle thousands of manufacturers efficiently

## Assumptions and Constraints

### Data Model
- **UID**: Uses UUID for global uniqueness
- **Manufacturer Representation**: Stored as a string; no separate entity at this stage
- **Quantity**: Integer type; assumes whole units (no fractional quantities)
- **Price**: BigDecimal type with precision (10,2) for monetary values
- **Nullable Fields**: All fields except UID are mandatory

### Database
- Initially uses H2 for rapid development/testing
- Future transition to MySQL for production environments

### Scalability
- Designed to handle high data volumes (thousands of manufacturers)

### API Design
- RESTful principles with endpoints for CRUD and bulk operations

### Search Functionality
- Case-insensitive search by name and manufacturer
- Filtering by price range

### Technical Assumptions
- No authentication or security layers implemented
- Transactions managed at the service layer
- No soft delete functionality (all deletions are permanent)

## Design Decisions

### Entity Design
- Refactored using Lombok annotations (@Data, @NoArgsConstructor, @AllArgsConstructor) for cleaner code
- Chose UUID for UID to ensure global uniqueness in distributed systems

### Error Handling
Standardized error responses with custom exceptions:
- `DrugNotFoundException`: Thrown when a drug is not found
- `InvalidDrugRequestException`: For invalid input data during create/update operations
- `BulkProcessingException`: Handles errors during bulk operations
- `GenericApplicationException`: Catch-all for unexpected issues

### API Standards

#### URL Structure
```text
GET    /api/drugs              # List all drugs
GET    /api/drugs/{id}         # Get single drug
POST   /api/drugs              # Create drug
PUT    /api/drugs/{id}         # Update drug
DELETE /api/drugs/{id}         # Delete drug
GET    /api/drugs/search/*     # Search endpoints
```

#### HTTP Status Codes
- 200 OK: Successful retrieval or update
- 201 Created: Successful creation
- 204 No Content: Successful deletion
- 404 Not Found: Resource not found
- 400 Bad Request: Validation errors

#### Complete API Endpoints

##### Drug Management
All endpoints are prefixed with `/api/drugs`

###### Basic CRUD Operations
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

###### Bulk Operations
- `POST /bulk` - Create multiple drugs in a single request
- `PUT /bulk` - Update multiple drugs in a single request
- `DELETE /bulk` - Delete multiple drugs by their UUIDs

###### Search Operations
All search endpoints support pagination with the same query parameters as above.

- `GET /search/name?name={drugName}` - Search drugs by name (case-insensitive)
- `GET /search/manufacturer?manufacturer={manufacturerName}` - Search drugs by manufacturer name (case-insensitive)
- `GET /search/price-range?minPrice={minPrice}&maxPrice={maxPrice}` - Search drugs within a specific price range

#### Request/Response Formats

##### Single Drug Format
```json
{
    "uid": "UUID",
    "name": "string",
    "manufacturerName": "string",
    "quantity": "integer",
    "price": "decimal"
}
```

##### Bulk Request Format
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

##### Paginated Response Format
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

### Implemented Tests
#### Unit Tests
- Validate core CRUD operations (createDrug_Success, updateDrug_Success, etc.)

#### Integration Tests
- End-to-end testing of API workflow (createAndRetrieveDrug_Success)

### Planned Tests
#### Controller Tests
- Validate invalid inputs (createDrug_WithInvalidData_ShouldReturnBadRequest)
- Test search functionality (searchByName_ShouldReturnMatchingDrugs)

#### Integration Tests
- Bulk operations (testBulkDrugCreation_Success)
- Pagination and sorting (testPaginationAndSorting)

## Future Enhancements
1. Transition to MySQL for production environments
2. Dockerization of the application for portability and scalability
3. Paralel processing system (such as Spring Batch) for bulk requests to improve performance
4. Advanced caching mechanisms for frequent queries
5. Additional fields such as expiration date and dosage form in the Drug entity
6. Creation of a separate Manufacturer entity for detailed manufacturer information

## AI Tool Usage

### Key Contributions
#### Entity Design
- AI refactored the Drug entity using Lombok annotations to simplify code structure and reduce boilerplate code

#### Swagger Integration
- CursorAI assisted in implementing Swagger UI but required iterative refinements due to initial disruptions in file organization

#### Dependency Resolution
- CursorAI identified compatibility issues with JDK versions and recommended upgrading to JDK 21, resolving dependency conflicts seamlessly

#### Error Handling Strategy
- ChatGPT recommended custom exceptions (DrugNotFoundException, etc.) and standardized error response structures

### Challenges Encountered
- File rearrangements during Swagger integration disrupted functionality but were resolved through iterative updates with AI tools.
- Initial dependency conflicts required manual intervention before AI recommendations proved effective.
- I attempted to implement parallel/asynchronous processing using CursorAI. However, the features were not truly asynchronous, as each iteration remained blocking. The complexity of achieving true asynchronous behavior was beyond the necessary scope of the project, so I decided not to pursue it further.
