# 🚀 Quick Reference Card

## Start Application
```bash
cd "c:\Users\nr580\OneDrive\Desktop\Language Folder\Spring boot\LearningRestAPI"
.\mvnw.cmd spring-boot:run
```
**Access**: `http://localhost:8080`

---

## API Endpoints Cheat Sheet

### GET All
```
GET /api/students
Response: 200 - JSON array of students
```

### POST Create
```
POST /api/students
Body: {"name":"...","email":"...","phoneNumber":"...","address":"..."}
Response: 201 - Created student with ID
```

### GET One
```
GET /api/students/{id}
Response: 200 - Student object OR 404 - Not found
```

### PUT Update
```
PUT /api/students/{id}
Body: {"name":"...","email":"..."} (partial or full)
Response: 200 - Updated student OR 404 - Not found
```

### DELETE
```
DELETE /api/students/{id}
Response: 204 - No content OR 404 - Not found
```

---

## cURL Commands

```bash
# GET All
curl http://localhost:8080/api/students

# POST Create
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@test.com","phoneNumber":"123","address":"123 St"}'

# GET One
curl http://localhost:8080/api/students/1

# PUT Update
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"email":"new@test.com"}'

# DELETE
curl -X DELETE http://localhost:8080/api/students/1
```

---

## File Locations

| File | Location |
|------|----------|
| Controller | `src/main/java/com/example/LearningRestAPI/Controller/StudentController.java` |
| Service | `src/main/java/com/example/LearningRestAPI/service/StudentService.java` |
| Repository | `src/main/java/com/example/LearningRestAPI/repository/StudentRepository.java` |
| Entity | `src/main/java/com/example/LearningRestAPI/enitity/Student.java` |
| Config | `src/main/resources/application.properties` |
| Build | `pom.xml` |

---

## Database

**H2 Console**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **User**: `sa`
- **Password**: (blank)

**Query**: `SELECT * FROM STUDENTS;`

---

## Key Classes

### StudentController.java (6 methods)
```java
@RestController
@RequestMapping("/api/students")
public class StudentController {
    getAllStudents()          // GET /api/students
    getStudentById(id)        // GET /api/students/{id}
    createStudent(student)    // POST /api/students
    updateStudent(id, ...)    // PUT /api/students/{id}
    deleteStudent(id)         // DELETE /api/students/{id}
}
```

### StudentService.java (5 methods)
```java
@Service
public class StudentService {
    getAllStudents()
    getStudentById(Long id)
    createStudent(Student)
    updateStudent(Long id, Student)
    deleteStudent(Long id)
}
```

### StudentRepository.java
```java
@Repository
public interface StudentRepository 
    extends JpaRepository<Student, Long> {
    // Auto-provides: findAll(), findById(), save(), delete(), etc.
}
```

### Student.java
```java
@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
```

---

## HTTP Status Codes

| Code | Method | Meaning |
|------|--------|---------|
| 200 | GET, PUT | Success |
| 201 | POST | Created |
| 204 | DELETE | Deleted (no content) |
| 404 | GET, PUT, DELETE | Not found |
| 500 | Any | Server error |

---

## Build Commands

```bash
# Compile
.\mvnw.cmd compile

# Build/Package
.\mvnw.cmd package -DskipTests

# Clean
.\mvnw.cmd clean

# Run
.\mvnw.cmd spring-boot:run

# Run JAR
java -jar target/LearningRestAPI-0.0.1-SNAPSHOT.jar
```

---

## Troubleshooting

**Port 8080 in use?**
- Change in `application.properties`: `server.port=8081`

**Build fails?**
- Run: `.\mvnw.cmd clean package -DskipTests`

**Database not accessible?**
- Check H2 URL: `http://localhost:8080/h2-console`

**404 errors?**
- Verify student ID exists
- Check URL spelling and case sensitivity

---

## Test Workflow

1. **Start App**: `.\mvnw.cmd spring-boot:run`
2. **Create Student**: POST to `/api/students`
3. **Get All**: GET `/api/students`
4. **Get One**: GET `/api/students/1`
5. **Update**: PUT `/api/students/1`
6. **Delete**: DELETE `/api/students/1`
7. **Verify**: Use H2 console to check database

---

## Documentation Reference

| Document | Content |
|----------|---------|
| `README.md` | Complete guide |
| `SETUP_SUMMARY.md` | Setup overview |
| `API_DOCUMENTATION.md` | API reference |
| `TESTING_GUIDE.md` | Testing methods |
| `ARCHITECTURE.md` | Architecture diagrams |

---

## Student Object Structure

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "123 Main Street"
}
```

---

**Everything is ready to go! Start developing! 🎉**
