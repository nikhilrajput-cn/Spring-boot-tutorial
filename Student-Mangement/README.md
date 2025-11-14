# Student Management (refactor)

This project is a small Spring Boot application redesigned to follow a layered architecture for learning purposes.

Roadmap / Topics covered
- @SpringBootApplication internal working (bootstrapping, auto-configuration)
- IoC Container & Dependency Injection in Spring Boot (constructor injection used)
- Perform CRUD Operation (Student Management)
  - Controller -> Service -> Repository layers
- Use in-memory DB (H2) for CRUD and quick testing
- Test CRUD APIs in Postman (collection included)

What I changed
- Added DTOs and a mapper to decouple API from JPA entity.
- Implemented `StudentService` and `StudentServiceImpl` to contain business logic.
- Controller now uses the service layer and exposes CRUD endpoints: GET/POST/PUT/DELETE at `/api/students`.
- Configured H2 as the default datasource for development. The H2 console is available at `/h2-console`.
- `data.sql` provides two initial rows (Alice, Bob).

How to run

1. Build and run (Windows PowerShell):

```powershell
cd "c:\Users\nr580\OneDrive\Desktop\Language Folder\Spring boot\Student-Mangement"
.\mvnw spring-boot:run
```

2. Open H2 console (if needed): http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:studentdb`
   - User: `sa` (no password)

3. API endpoints (base `http://localhost:8080`)
   - GET /api/students
   - GET /api/students/{id}
   - POST /api/students  (JSON body: {"name":"X","email":"x@x.com"})
   - PUT /api/students/{id} (JSON body for updates)
   - DELETE /api/students/{id}

Postman
- A small Postman collection is included as `Student-Mangement.postman_collection.json`.

Notes
- The project compiles with Java 21 configured as the release target in `pom.xml`.
- If you want to use MySQL, update `application.properties` or create an `application-mysql.properties` and provide DB credentials.

Next steps (optional)
- Add validation (javax/hibernate-validator) and better error handling
- Add unit and integration tests
- Add DTO input validation and mapping frameworks (MapStruct)
