# Testing Guide for LearningRestAPI

## Using Postman

Here's how to test each endpoint in Postman:

### 1. GET All Students
**Request:**
- Method: `GET`
- URL: `http://localhost:8080/api/students`
- Headers: None required

**Expected Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Nikhil Kumar",
    "email": "nikhil@example.com",
    "phoneNumber": "9876543210",
    "address": "123 Main Street"
  }
]
```

---

### 2. Create New Student
**Request:**
- Method: `POST`
- URL: `http://localhost:8080/api/students`
- Headers: 
  - Content-Type: `application/json`
- Body (raw JSON):
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "456 Oak Avenue"
}
```

**Expected Response (201 CREATED):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "456 Oak Avenue"
}
```

---

### 3. Get Student by ID
**Request:**
- Method: `GET`
- URL: `http://localhost:8080/api/students/1`
- Headers: None required

**Expected Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "456 Oak Avenue"
}
```

**Error Response (404 NOT FOUND):**
If student doesn't exist, you'll get a 404 status with empty body.

---

### 4. Update Student
**Request:**
- Method: `PUT`
- URL: `http://localhost:8080/api/students/1`
- Headers:
  - Content-Type: `application/json`
- Body (raw JSON):
```json
{
  "name": "Jane Doe",
  "email": "jane@example.com",
  "phoneNumber": "1234567890",
  "address": "789 Pine Road"
}
```

**Expected Response (200 OK):**
```json
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane@example.com",
  "phoneNumber": "1234567890",
  "address": "789 Pine Road"
}
```

**Notes:**
- You can send only the fields you want to update
- Fields not provided in the request will remain unchanged
- Example partial update:
```json
{
  "email": "newemail@example.com"
}
```

---

### 5. Delete Student
**Request:**
- Method: `DELETE`
- URL: `http://localhost:8080/api/students/1`
- Headers: None required
- Body: Empty

**Expected Response (204 NO CONTENT):**
- Status: 204 No Content
- Body: Empty

---

## Using cURL (Command Line)

### 1. Get All Students
```bash
curl -X GET http://localhost:8080/api/students
```

### 2. Create New Student
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phoneNumber": "9876543210",
    "address": "456 Oak Avenue"
  }'
```

### 3. Get Student by ID
```bash
curl -X GET http://localhost:8080/api/students/1
```

### 4. Update Student
```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "email": "jane@example.com",
    "phoneNumber": "1234567890",
    "address": "789 Pine Road"
  }'
```

### 5. Delete Student
```bash
curl -X DELETE http://localhost:8080/api/students/1
```

---

## Using Postman Collection JSON

Save this as a `.json` file and import into Postman:

```json
{
  "info": {
    "name": "Learning REST API",
    "description": "Complete CRUD API for Student Management",
    "version": "1.0.0"
  },
  "item": [
    {
      "name": "Get All Students",
      "request": {
        "method": "GET",
        "url": "http://localhost:8080/api/students",
        "header": []
      }
    },
    {
      "name": "Create Student",
      "request": {
        "method": "POST",
        "url": "http://localhost:8080/api/students",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"phoneNumber\":\"9876543210\",\"address\":\"456 Oak Avenue\"}"
        }
      }
    },
    {
      "name": "Get Student by ID",
      "request": {
        "method": "GET",
        "url": "http://localhost:8080/api/students/1",
        "header": []
      }
    },
    {
      "name": "Update Student",
      "request": {
        "method": "PUT",
        "url": "http://localhost:8080/api/students/1",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"name\":\"Jane Doe\",\"email\":\"jane@example.com\",\"phoneNumber\":\"1234567890\",\"address\":\"789 Pine Road\"}"
        }
      }
    },
    {
      "name": "Delete Student",
      "request": {
        "method": "DELETE",
        "url": "http://localhost:8080/api/students/1",
        "header": []
      }
    }
  ]
}
```

---

## Common Test Scenarios

### Scenario 1: Create Multiple Students
1. Call "Create Student" endpoint 3-5 times with different data
2. Call "Get All Students" to see all created records
3. Verify IDs are auto-incrementing

### Scenario 2: Update and Verify
1. Create a student
2. Update the student with new email
3. Get the student by ID to verify changes
4. Verify old data is replaced

### Scenario 3: Delete and Verify
1. Create a student
2. Delete the student using ID
3. Try to get the deleted student (should get 404)
4. Get all students (deleted one should not appear)

### Scenario 4: Error Handling
1. Try to get student with ID that doesn't exist (should get 404)
2. Try to update student with ID that doesn't exist (should get 404)
3. Try to delete student with ID that doesn't exist (should get 404)

---

## Response Status Codes

| Code | Endpoint | Meaning |
|------|----------|---------|
| 200 | GET, PUT | Success - Request processed |
| 201 | POST | Created - Resource created successfully |
| 204 | DELETE | No Content - Successful deletion |
| 404 | GET, PUT, DELETE | Not Found - Resource doesn't exist |
| 500 | Any | Server Error - Internal error occurred |

---

## Tips for Testing

1. **Use Postman Environment Variables**
   - Create a variable `baseUrl` = `http://localhost:8080`
   - Use `{{baseUrl}}/api/students` in URLs

2. **Chain Requests**
   - Use POST response ID in GET request
   - Example: In Create Student response, copy ID and use in Get Student URL

3. **Test Invalid Requests**
   - Send empty JSON `{}`
   - Send wrong data types
   - Send missing required fields

4. **Monitor Logs**
   - Check console for SQL queries
   - Verify Hibernate is creating/updating tables
   - Look for validation errors

5. **Use H2 Console**
   - Access `http://localhost:8080/h2-console`
   - Run: `SELECT * FROM STUDENTS;` to verify data

---

**Ready to test! Start your application and use any of the above methods to test the API.**
