# Learning REST API - Complete MVC Implementation

## Overview
This is a fully functional Spring Boot REST API with complete CRUD (Create, Read, Update, Delete) operations following the MVC (Model-View-Controller) architecture pattern.

## Project Structure

```
src/main/java/com/example/LearningRestAPI/
├── Controller/
│   └── StudentController.java      # REST endpoints
├── enitity/
│   └── Student.java                # JPA Entity (Database Model)
├── dto/
│   └── StudentDto.java             # Data Transfer Object
├── service/
│   └── StudentService.java         # Business Logic Layer
├── repository/
│   └── StudentRepository.java      # Data Access Layer
└── LearningRestApiApplication.java # Main Application Class
```

## Architecture Pattern: MVC

- **Model (M)**: `Student.java` - Entity class representing the database table
- **View (V)**: JSON responses from the controller
- **Controller (C)**: `StudentController.java` - Handles HTTP requests and responses

## Layer Responsibilities

### 1. Entity Layer (`enitity/Student.java`)
- Represents the Student table in the database
- Contains all student attributes: id, name, email, phoneNumber, address

### 2. Repository Layer (`repository/StudentRepository.java`)
- Extends `JpaRepository` for database operations
- Provides CRUD methods (findAll, findById, save, delete, etc.)

### 3. Service Layer (`service/StudentService.java`)
- Contains business logic
- Methods for CRUD operations
- Handles data validation and transformation

### 4. Controller Layer (`Controller/StudentController.java`)
- Exposes REST endpoints
- Handles HTTP requests
- Returns appropriate HTTP responses

## API Endpoints

### Base URL: `http://localhost:8080/api/students`

### 1. Get All Students
```
GET /api/students
Response: 200 OK
Content-Type: application/json

[
  {
    "id": 1,
    "name": "Nikhil",
    "email": "nikhil@gmail.com",
    "phoneNumber": "1234567890",
    "address": "123 Main St"
  },
  ...
]
```

### 2. Get Student by ID
```
GET /api/students/{id}
Example: GET /api/students/1
Response: 200 OK

{
  "id": 1,
  "name": "Nikhil",
  "email": "nikhil@gmail.com",
  "phoneNumber": "1234567890",
  "address": "123 Main St"
}
```

### 3. Create New Student
```
POST /api/students
Content-Type: application/json

Request Body:
{
  "name": "Nikhil",
  "email": "nikhil@gmail.com",
  "phoneNumber": "1234567890",
  "address": "123 Main St"
}

Response: 201 CREATED
{
  "id": 1,
  "name": "Nikhil",
  "email": "nikhil@gmail.com",
  "phoneNumber": "1234567890",
  "address": "123 Main St"
}
```

### 4. Update Student
```
PUT /api/students/{id}
Example: PUT /api/students/1
Content-Type: application/json

Request Body:
{
  "name": "Updated Name",
  "email": "updated@gmail.com",
  "phoneNumber": "9876543210",
  "address": "456 Oak Ave"
}

Response: 200 OK
{
  "id": 1,
  "name": "Updated Name",
  "email": "updated@gmail.com",
  "phoneNumber": "9876543210",
  "address": "456 Oak Ave"
}
```

### 5. Delete Student
```
DELETE /api/students/{id}
Example: DELETE /api/students/1
Response: 204 NO CONTENT
```

## HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK - Request successful |
| 201 | CREATED - Resource created successfully |
| 204 | NO CONTENT - Successful deletion |
| 404 | NOT FOUND - Resource not found |
| 500 | INTERNAL SERVER ERROR |

## Database Configuration

The application uses **H2 Database** (in-memory database) for testing purposes.

### H2 Console Access
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave blank)

To switch to MySQL/PostgreSQL, add the dependency in `pom.xml` and update `application.properties`.

## Dependencies Used

```xml
<dependencies>
  <!-- Spring Boot Web Starter -->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  
  <!-- Spring Data JPA -->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  
  <!-- H2 Database -->
  <dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
  </dependency>
  
  <!-- Lombok (for @Data, @AllArgsConstructor, etc.) -->
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
</dependencies>
```

## How to Run

1. **Build the project:**
   ```bash
   mvn clean install
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the main class: `LearningRestApiApplication.java`

3. **Test the API:**
   - Use Postman, Insomnia, or curl
   - Base URL: `http://localhost:8080/api/students`

## Testing with curl

```bash
# Get all students
curl -X GET http://localhost:8080/api/students

# Get student by ID
curl -X GET http://localhost:8080/api/students/1

# Create student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Nikhil","email":"nikhil@gmail.com","phoneNumber":"1234567890","address":"123 Main St"}'

# Update student
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Name","email":"updated@gmail.com"}'

# Delete student
curl -X DELETE http://localhost:8080/api/students/1
```

## Annotations Explained

### Entity Annotations (@enitity/Student.java)
- `@Entity` - Marks class as JPA entity
- `@Table(name = "students")` - Specifies table name
- `@Id` - Marks primary key
- `@GeneratedValue` - Auto-generates ID values

### Lombok Annotations
- `@Data` - Generates getters, setters, toString, equals, hashCode
- `@NoArgsConstructor` - Generates no-arg constructor
- `@AllArgsConstructor` - Generates constructor with all fields

### Spring Annotations
- `@RestController` - Marks controller as REST endpoint provider
- `@RequestMapping` - Maps URL paths
- `@GetMapping` - Maps GET requests
- `@PostMapping` - Maps POST requests
- `@PutMapping` - Maps PUT requests
- `@DeleteMapping` - Maps DELETE requests
- `@PathVariable` - Extracts value from URL path
- `@RequestBody` - Binds request body to method parameter
- `@Autowired` - Dependency injection
- `@Service` - Marks class as service layer
- `@Repository` - Marks class as repository layer

## Future Enhancements

1. Add request validation (Jakarta Validation)
2. Add error handling with custom exceptions
3. Add authentication and authorization
4. Add pagination and sorting
5. Add logging
6. Add unit tests
7. Add Swagger/OpenAPI documentation
8. Switch to production database (MySQL/PostgreSQL)

---

**Created**: November 12, 2025  
**Version**: 1.0  
**Author**: Learning REST API
