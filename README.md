# SureCost Take Home Project

A Java-powered REST API service built on Spring Boot, designed for comprehensive drug information management. This application excels in performing CRUD operations, optimizing data handling processes, and providing robust search capabilities for pharmaceutical data.
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

## API Endpoints

- `GET /` - Returns "Hello, World!"
[Add more endpoints as you develop them]

## Project Structure
src
├── main
│ ├── java
│ │ └── com
│ │ └── surecostproject
│ │ └── takehome
│ │ ├── TakehomeApplication.java
│ │ └── HelloController.java
│ └── resources
│ └── application.properties
└── test
└── java
└── com
└── surecostproject
└── takehome
└── TakehomeApplicationTests.java

## Technologies Used

- Spring Boot 3.4.4
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Maven
