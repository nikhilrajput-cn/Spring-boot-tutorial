# MVC Architecture Diagram

## Complete Request-Response Flow

```
┌─────────────────────────────────────────────────────────────────┐
│                      HTTP CLIENT (Browser/Postman/curl)         │
└────────────────────────────┬──────────────────────────────────┘
                             │
                             │ HTTP Request
                             ▼
┌──────────────────────────────────────────────────────────────────┐
│                  @RestController                                 │
│              StudentController.java                              │
├──────────────────────────────────────────────────────────────────┤
│ @GetMapping                → List<Student>                       │
│ @GetMapping("/{id}")       → Student                             │
│ @PostMapping               → Student (201 Created)               │
│ @PutMapping("/{id}")       → Student                             │
│ @DeleteMapping("/{id}")    → void (204 No Content)               │
├──────────────────────────────────────────────────────────────────┤
│ - Validates request format                                       │
│ - Extracts path variables and body                              │
│ - Calls appropriate service method                               │
│ - Returns proper HTTP status codes                               │
└────────────────────────────┬──────────────────────────────────┘
                             │
                             │ Business Logic Call
                             ▼
┌──────────────────────────────────────────────────────────────────┐
│                    @Service                                      │
│              StudentService.java                                 │
├──────────────────────────────────────────────────────────────────┤
│ getAllStudents()           → List<Student>                       │
│ getStudentById(Long id)    → Optional<Student>                   │
│ createStudent(Student)     → Student                             │
│ updateStudent(Long, Student) → Optional<Student>                 │
│ deleteStudent(Long id)     → boolean                             │
├──────────────────────────────────────────────────────────────────┤
│ - Implements business logic                                      │
│ - Handles data validation                                        │
│ - Calls repository for data operations                           │
│ - Manages transactions                                           │
└────────────────────────────┬──────────────────────────────────┘
                             │
                             │ Data Access Call
                             ▼
┌──────────────────────────────────────────────────────────────────┐
│                   @Repository                                    │
│            StudentRepository.java                                │
│        extends JpaRepository<Student, Long>                      │
├──────────────────────────────────────────────────────────────────┤
│ inherited: findAll()                                             │
│ inherited: findById(Long)                                        │
│ inherited: save(Student)                                         │
│ inherited: delete(Student)                                       │
│ inherited: deleteById(Long)                                      │
│ inherited: existsById(Long)                                      │
├──────────────────────────────────────────────────────────────────┤
│ - Auto-generates SQL queries                                     │
│ - Manages database connections                                   │
│ - Returns database results                                       │
│ - Handles JDBC connections                                       │
└────────────────────────────┬──────────────────────────────────┘
                             │
                             │ SQL Queries
                             ▼
┌──────────────────────────────────────────────────────────────────┐
│                  @Entity (JPA)                                   │
│              Student.java                                        │
├──────────────────────────────────────────────────────────────────┤
│ @Table(name = "students")                                        │
│ ┌────────────────────────────┐                                   │
│ │ @Id                        │                                   │
│ │ Long id                    │ ← Auto-incremented primary key    │
│ ├────────────────────────────┤                                   │
│ │ String name                │                                   │
│ │ String email               │                                   │
│ │ String phoneNumber         │                                   │
│ │ String address             │                                   │
│ └────────────────────────────┘                                   │
├──────────────────────────────────────────────────────────────────┤
│ - Maps to database table                                         │
│ - Defines column structure                                       │
│ - Uses Lombok for getters/setters                               │
└────────────────────────────┬──────────────────────────────────┘
                             │
                             │ Table Mapping
                             ▼
┌──────────────────────────────────────────────────────────────────┐
│                  H2 Database (in-memory)                         │
│              STUDENTS TABLE                                      │
├────────┬──────────┬────────────────────┬──────────┬──────────────┤
│   ID   │  NAME    │  EMAIL             │ PHONE    │  ADDRESS     │
├────────┼──────────┼────────────────────┼──────────┼──────────────┤
│   1    │ John Doe │ john@example.com   │ 98765... │ 456 Oak Ave  │
│   2    │ Jane Doe │ jane@example.com   │ 12345... │ 789 Pine Rd  │
│   3    │ Bob Smith│ bob@example.com    │ 55555... │ 321 Main St  │
└────────┴──────────┴────────────────────┴──────────┴──────────────┘
```

---

## Data Flow for Each Operation

### 1. GET All Students
```
Client (GET /api/students)
        ↓
Controller.getAllStudents()
        ↓
Service.getAllStudents()
        ↓
Repository.findAll()
        ↓
Query: SELECT * FROM STUDENTS
        ↓
Database returns List<Student>
        ↓
[{id:1, name:"John", ...}, {id:2, name:"Jane", ...}]
        ↓
Client receives 200 OK + JSON array
```

### 2. POST Create Student
```
Client (POST /api/students, body: {name, email, phone, address})
        ↓
Controller.createStudent(Student)
        ↓
Service.createStudent(Student)
        ↓
Repository.save(Student)
        ↓
Query: INSERT INTO STUDENTS (name, email, phone, address) 
       VALUES (?, ?, ?, ?)
        ↓
Database generates ID (auto-increment)
        ↓
Database returns saved Student with ID
        ↓
{id:1, name:"John", email:"john@example.com", ...}
        ↓
Client receives 201 CREATED + JSON object
```

### 3. GET Student by ID
```
Client (GET /api/students/1)
        ↓
Controller.getStudentById(1)
        ↓
Service.getStudentById(1)
        ↓
Repository.findById(1)
        ↓
Query: SELECT * FROM STUDENTS WHERE ID = 1
        ↓
Database returns Optional<Student>
        ↓
if (present) {
  Client receives 200 OK + {id:1, name:"John", ...}
} else {
  Client receives 404 NOT FOUND
}
```

### 4. PUT Update Student
```
Client (PUT /api/students/1, body: {email:"newemail@example.com"})
        ↓
Controller.updateStudent(1, Student)
        ↓
Service.updateStudent(1, Student)
        ├─ Repository.findById(1)
        │  ↓
        │  Query: SELECT * FROM STUDENTS WHERE ID = 1
        │  ↓
        │  Merge: Update only provided fields
        │
        └─ Repository.save(mergedStudent)
           ↓
           Query: UPDATE STUDENTS SET email = ? WHERE ID = 1
           ↓
           Database returns updated Student
           ↓
           {id:1, name:"John", email:"newemail@example.com", ...}
           ↓
           Client receives 200 OK + JSON object
```

### 5. DELETE Student
```
Client (DELETE /api/students/1)
        ↓
Controller.deleteStudent(1)
        ↓
Service.deleteStudent(1)
        ├─ Repository.existsById(1)
        │  ↓
        │  Query: SELECT COUNT(*) FROM STUDENTS WHERE ID = 1
        │  ↓
        │  if (exists) {
        │
        └─   Repository.deleteById(1)
            ↓
            Query: DELETE FROM STUDENTS WHERE ID = 1
            ↓
            Database deletes record
            ↓
            Client receives 204 NO CONTENT
```

---

## Layer Responsibilities Summary

```
┌─────────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                           │
│              (HTTP Communication)                               │
│         - StudentController.java                                │
│         - Handles HTTP requests/responses                       │
│         - Maps URLs to methods                                  │
└─────────────────────────────────────────────────────────────────┘
                              │
                              │
┌─────────────────────────────────────────────────────────────────┐
│                   BUSINESS LOGIC LAYER                          │
│              (Application Logic)                                │
│         - StudentService.java                                   │
│         - Implements use cases                                  │
│         - Validates data                                        │
│         - Coordinates between controller and data layer         │
└─────────────────────────────────────────────────────────────────┘
                              │
                              │
┌─────────────────────────────────────────────────────────────────┐
│                    DATA ACCESS LAYER                            │
│              (Database Operations)                              │
│         - StudentRepository.java                                │
│         - Generates SQL queries                                 │
│         - Manages database connections                          │
│         - Maps objects to tables                                │
└─────────────────────────────────────────────────────────────────┘
                              │
                              │
┌─────────────────────────────────────────────────────────────────┐
│                  PERSISTENCE LAYER                              │
│              (Data Storage)                                     │
│         - Student.java (Entity)                                 │
│         - H2 Database (STUDENTS table)                          │
│         - Stores actual data                                    │
└─────────────────────────────────────────────────────────────────┘
```

---

## API Endpoint Summary

```
┌─────────┬──────────────────────┬─────────────────────────────────┐
│ METHOD  │ ENDPOINT             │ OPERATION                       │
├─────────┼──────────────────────┼─────────────────────────────────┤
│ GET     │ /api/students        │ Read all students               │
│ POST    │ /api/students        │ Create new student              │
│ GET     │ /api/students/{id}   │ Read specific student           │
│ PUT     │ /api/students/{id}   │ Update specific student         │
│ DELETE  │ /api/students/{id}   │ Delete specific student         │
└─────────┴──────────────────────┴─────────────────────────────────┘

HTTP Status Codes:
  ✓ 200 OK              - Successful GET/PUT
  ✓ 201 Created         - Successful POST
  ✓ 204 No Content      - Successful DELETE
  ✗ 404 Not Found       - Resource doesn't exist
  ✗ 500 Server Error    - Internal error
```

---

## Dependency Flow

```
StudentController
    ├── depends on → StudentService (@Autowired)
    │
StudentService
    ├── depends on → StudentRepository (@Autowired)
    │
StudentRepository
    ├── extends → JpaRepository<Student, Long>
    │
Student (Entity)
    ├── maps to → STUDENTS table
    ├── uses → @Id, @GeneratedValue
    ├── uses → Lombok (@Data, @AllArgsConstructor, @NoArgsConstructor)
    │
H2 Database
    └── manages → STUDENTS table with columns:
        id, name, email, phoneNumber, address
```

---

## Annotation Hierarchy

```
@Entity (Student.java)
├── @Table(name = "students")
├── @Id
├── @GeneratedValue(strategy = GenerationType.IDENTITY)
├── @Data (Lombok)
├── @NoArgsConstructor (Lombok)
└── @AllArgsConstructor (Lombok)

@Repository (StudentRepository.java)
├── extends JpaRepository<Student, Long>
└── provides automatic CRUD operations

@Service (StudentService.java)
├── @Autowired StudentRepository
├── public List<Student> getAllStudents()
├── public Optional<Student> getStudentById(Long id)
├── public Student createStudent(Student student)
├── public Optional<Student> updateStudent(Long id, Student details)
└── public boolean deleteStudent(Long id)

@RestController (StudentController.java)
├── @RequestMapping("/api/students")
├── @GetMapping
├── @PostMapping
├── @PutMapping("/{id}")
├── @DeleteMapping("/{id}")
└── @Autowired StudentService
```

---

**This MVC architecture ensures:**
- ✅ Separation of concerns
- ✅ Easy testing and maintenance
- ✅ Scalability
- ✅ Reusability
- ✅ Clear dependency flow
- ✅ Professional code organization
