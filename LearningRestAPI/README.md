# 🎓 Learning REST API - Complete MVC Implementation

## ✨ Project Overview

A professional Spring Boot REST API with complete MVC (Model-View-Controller) architecture implementing all CRUD operations (Create, Read, Update, Delete) for Student Management.

### 🎯 Project Status
- ✅ **BUILD SUCCESSFUL** - All code compiled and packaged
- ✅ **DEPENDENCIES RESOLVED** - All Maven dependencies installed
- ✅ **MVC ARCHITECTURE** - Fully implemented with separation of concerns
- ✅ **DATABASE READY** - H2 in-memory database configured
- ✅ **API ENDPOINTS** - All 5 REST endpoints implemented

---

## 📦 What's Included

### Core Files Created

| File | Purpose | Status |
|------|---------|--------|
| `src/main/java/.../enitity/Student.java` | JPA Entity (Database Model) | ✅ Created |
| `src/main/java/.../repository/StudentRepository.java` | Data Access Layer | ✅ Created |
| `src/main/java/.../service/StudentService.java` | Business Logic Layer | ✅ Created |
| `src/main/java/.../Controller/StudentController.java` | REST API Endpoints | ✅ Updated |
| `src/main/resources/application.properties` | Configuration | ✅ Updated |
| `pom.xml` | Maven Dependencies | ✅ Updated |

### Documentation Files

| File | Content |
|------|---------|
| `SETUP_SUMMARY.md` | Project overview and setup instructions |
| `API_DOCUMENTATION.md` | Complete API documentation with examples |
| `TESTING_GUIDE.md` | Testing with Postman, curl, and cURL |
| `ARCHITECTURE.md` | Detailed architecture diagrams and flows |
| `README.md` | This file |

---

## 🏗️ Architecture

### MVC Layers

```
Controller Layer        → REST Endpoints (@RestController)
         ↓
Service Layer          → Business Logic (@Service)
         ↓
Repository Layer       → Data Access (@Repository)
         ↓
Entity Layer           → Database Model (@Entity)
         ↓
Database               → H2 In-Memory Database
```

### Key Components

1. **Entity (Student.java)**
   - JPA Entity mapped to `students` table
   - Fields: id, name, email, phoneNumber, address
   - Auto-generated primary key

2. **Repository (StudentRepository.java)**
   - Extends `JpaRepository<Student, Long>`
   - Automatic CRUD operations
   - Database query execution

3. **Service (StudentService.java)**
   - Business logic implementation
   - Data validation
   - Transaction management
   - Methods: getAllStudents, getStudentById, createStudent, updateStudent, deleteStudent

4. **Controller (StudentController.java)**
   - REST endpoint definitions
   - HTTP request/response handling
   - Maps to `/api/students` base path

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+
- Spring Boot 3.5.7

### Installation

1. **Navigate to project directory**
   ```bash
   cd "c:\Users\nr580\OneDrive\Desktop\Language Folder\Spring boot\LearningRestAPI"
   ```

2. **Build the project**
   ```bash
   .\mvnw.cmd package -DskipTests
   ```

3. **Run the application**
   ```bash
   .\mvnw.cmd spring-boot:run
   ```
   OR directly execute the JAR:
   ```bash
   java -jar target/LearningRestAPI-0.0.1-SNAPSHOT.jar
   ```

4. **Application starts on**: `http://localhost:8080`

---

## 📡 API Endpoints

### Base URL: `http://localhost:8080/api/students`

| Method | Endpoint | Operation | Status Code |
|--------|----------|-----------|-------------|
| GET | `/api/students` | Get all students | 200 OK |
| GET | `/api/students/{id}` | Get student by ID | 200 OK / 404 |
| POST | `/api/students` | Create new student | 201 Created |
| PUT | `/api/students/{id}` | Update student | 200 OK / 404 |
| DELETE | `/api/students/{id}` | Delete student | 204 No Content / 404 |

### Request/Response Examples

#### 1. Get All Students
```bash
GET /api/students
```
**Response:**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "phoneNumber": "9876543210",
    "address": "456 Oak Avenue"
  }
]
```

#### 2. Create Student
```bash
POST /api/students
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "456 Oak Avenue"
}
```
**Response (201 Created):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "456 Oak Avenue"
}
```

#### 3. Get Student by ID
```bash
GET /api/students/1
```
**Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "456 Oak Avenue"
}
```

#### 4. Update Student
```bash
PUT /api/students/1
Content-Type: application/json

{
  "email": "newemail@example.com",
  "phoneNumber": "1234567890"
}
```
**Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "newemail@example.com",
  "phoneNumber": "1234567890",
  "address": "456 Oak Avenue"
}
```

#### 5. Delete Student
```bash
DELETE /api/students/1
```
**Response:** 204 No Content

---

## 🧪 Testing the API

### Using Postman (GUI)
1. Import collection from `TESTING_GUIDE.md`
2. Set base URL to `http://localhost:8080`
3. Run requests from the collection

### Using cURL
```bash
# Get all students
curl -X GET http://localhost:8080/api/students

# Create student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","phoneNumber":"9876543210","address":"456 Oak"}'

# Get by ID
curl -X GET http://localhost:8080/api/students/1

# Update
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"email":"new@example.com"}'

# Delete
curl -X DELETE http://localhost:8080/api/students/1
```

### Using H2 Console
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank)
- **Query**: `SELECT * FROM STUDENTS;`

---

## 📊 Database Schema

### STUDENTS Table

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| ID | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier |
| NAME | VARCHAR(255) | - | Student name |
| EMAIL | VARCHAR(255) | - | Student email |
| PHONE_NUMBER | VARCHAR(255) | - | Contact number |
| ADDRESS | VARCHAR(255) | - | Street address |

```sql
CREATE TABLE STUDENTS (
  ID BIGINT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(255),
  EMAIL VARCHAR(255),
  PHONE_NUMBER VARCHAR(255),
  ADDRESS VARCHAR(255)
);
```

---

## 🛠️ Dependencies

```xml
<!-- Spring Boot Web -->
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

<!-- Lombok -->
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>
```

---

## 📝 Key Annotations

### JPA/Entity Annotations
- `@Entity` - Marks class as database entity
- `@Table(name = "students")` - Maps to database table
- `@Id` - Marks as primary key
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` - Auto-increment

### Lombok Annotations
- `@Data` - Generates getters, setters, toString, equals, hashCode
- `@AllArgsConstructor` - Constructor with all fields
- `@NoArgsConstructor` - No-argument constructor

### Spring Annotations
- `@RestController` - REST endpoint provider
- `@RequestMapping("/api/students")` - Base URL path
- `@GetMapping` - Maps GET requests
- `@PostMapping` - Maps POST requests
- `@PutMapping` - Maps PUT requests
- `@DeleteMapping` - Maps DELETE requests
- `@PathVariable` - Extracts from URL path (e.g., {id})
- `@RequestBody` - Binds request body to parameter
- `@Autowired` - Dependency injection
- `@Service` - Service layer component
- `@Repository` - Repository layer component

---

## 📂 Project Structure

```
LearningRestAPI/
├── src/
│   ├── main/
│   │   ├── java/com/example/LearningRestAPI/
│   │   │   ├── Controller/
│   │   │   │   └── StudentController.java      (REST Endpoints)
│   │   │   ├── enitity/
│   │   │   │   └── Student.java                (JPA Entity)
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java      (Data Access)
│   │   │   ├── service/
│   │   │   │   └── StudentService.java         (Business Logic)
│   │   │   ├── dto/
│   │   │   │   └── StudentDto.java             (Data Transfer Object)
│   │   │   └── LearningRestApiApplication.java (Main Class)
│   │   └── resources/
│   │       └── application.properties          (Configuration)
│   └── test/
│       └── java/.../LearningRestApiApplicationTests.java
├── pom.xml                                      (Maven Config)
├── README.md                                    (This file)
├── SETUP_SUMMARY.md                             (Setup Guide)
├── API_DOCUMENTATION.md                         (API Reference)
├── TESTING_GUIDE.md                             (Testing Instructions)
└── ARCHITECTURE.md                              (Architecture Details)
```

---

## 🔄 Request-Response Cycle

```
1. Client sends HTTP request to /api/students
   ↓
2. Controller receives request
   ├─ Validates URL path
   ├─ Extracts path variables (@PathVariable)
   ├─ Parses request body (@RequestBody)
   └─ Calls Service method
   ↓
3. Service executes business logic
   ├─ Validates data
   ├─ Applies business rules
   └─ Calls Repository method
   ↓
4. Repository executes database operation
   ├─ Builds SQL query
   ├─ Executes on database
   └─ Returns result as Java object
   ↓
5. Service returns result to Controller
   ↓
6. Controller builds HTTP response
   ├─ Sets status code (200, 201, 204, 404, 500)
   ├─ Converts object to JSON
   └─ Returns to client
   ↓
7. Client receives response
```

---

## 🐛 Troubleshooting

### Application won't start
```
Check port 8080 is not in use
Or specify different port: server.port=8081 in application.properties
```

### H2 Console not accessible
```
URL must be exactly: http://localhost:8080/h2-console
Note: It's case-sensitive
```

### 404 Not Found errors
```
Verify student ID exists in database
Use H2 console to check: SELECT * FROM STUDENTS;
```

### Database errors
```
Check application.properties for correct H2 settings
Or check logs for SQL errors
```

---

## 🔒 Best Practices Implemented

✅ **Separation of Concerns** - Each layer has single responsibility
✅ **Dependency Injection** - Using @Autowired for loose coupling
✅ **RESTful Design** - Proper HTTP methods and status codes
✅ **Data Validation** - Input validation in service layer
✅ **Exception Handling** - Returns appropriate HTTP status codes
✅ **Code Documentation** - Clear comments and annotations
✅ **Professional Structure** - Organized package structure
✅ **Database Mapping** - Proper JPA entity mapping

---

## 📈 Performance Considerations

- H2 is in-memory (suitable for development/testing)
- For production, use MySQL, PostgreSQL, or Oracle
- Add caching for frequently accessed data
- Implement pagination for large datasets
- Add database indexes on frequently queried columns

---

## 🔐 Security Recommendations

For production deployment:
1. Add Spring Security
2. Implement JWT authentication
3. Add input validation and sanitization
4. Use HTTPS
5. Add rate limiting
6. Implement CORS properly
7. Use environment variables for sensitive data
8. Add API versioning (/api/v1/students)

---

## 📚 Documentation Files

- **SETUP_SUMMARY.md** - Quick setup and overview
- **API_DOCUMENTATION.md** - Complete API reference
- **TESTING_GUIDE.md** - How to test with tools
- **ARCHITECTURE.md** - Visual diagrams and flows
- **README.md** - This comprehensive guide

---

## 🚀 Next Steps

1. **Test All Endpoints**
   - Use Postman or cURL to verify functionality

2. **Add Error Handling**
   - Implement `@ControllerAdvice` for exceptions
   - Create custom exception classes

3. **Add Validation**
   - Use Jakarta Validation annotations
   - Add request body validation

4. **Switch to Production Database**
   - Add MySQL or PostgreSQL driver
   - Update connection properties

5. **Add Logging**
   - Configure SLF4J and Logback
   - Add logging statements

6. **Add Unit Tests**
   - Create test classes with JUnit 5
   - Mock services with Mockito

7. **Add API Documentation**
   - Integrate Springdoc OpenAPI (Swagger)
   - Generate interactive API docs

---

## 📞 Support & Resources

### Spring Boot Documentation
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

### REST API Best Practices
- [RESTful Web Services](https://restfulapi.net/)
- [HTTP Status Codes](https://httpwg.org/specs/rfc9110.html#status.codes)

### Tools Used
- **Maven** - Build automation
- **Spring Boot** - Framework
- **JPA/Hibernate** - ORM
- **H2** - Database
- **Lombok** - Code generation

---

## 📄 License & Credits

**Created**: November 12, 2025  
**Version**: 1.0  
**Framework**: Spring Boot 3.5.7  
**Java Version**: 17+  
**Build Tool**: Maven

---

## ✅ Build Status Summary

```
BUILD SUCCESS
├── 6 Java files compiled
├── All dependencies resolved
├── JAR package created
└── Ready to run!
```

---

**Your Learning REST API is ready to use! 🎉**

Start the application and test the endpoints using any of the provided guides.

For questions, refer to the documentation files included in the project.
