# API Testing — Smart Clinic Management System

## 1. Authentication API

**Endpoint:** `POST /api/auth/login`

**Request body:**
```json
{
  "email": "admin@smartclinic.com",
  "password": "Admin@123"
}
```

**Expected result:** HTTP 200 OK with a JWT token for the demonstration credentials.

Invalid credentials should return HTTP 401 Unauthorized. Missing email or password should return HTTP 400 Bad Request.

## 2. Doctor Availability API

**Endpoint:** `GET /api/doctors/{doctorId}/availability?date=2026-10-20`

**Required header:**
`Authorization: Bearer <token>`

**Expected result:** HTTP 200 OK with the doctor ID, requested date, and sample available times when a Bearer-prefixed header is provided.

Requests without that header should return HTTP 401 Unauthorized.

**Note:** The current controller checks the header format but does not validate the JWT token itself.

## 3. Prescription API

**Endpoint:** `GET /api/prescriptions`

**Expected result:** HTTP 200 OK with the prescription list, if the application and database are configured correctly.

**Endpoint:** `POST /api/prescriptions`

**Sample request body:**
```json
{
  "appointment": {
    "appointmentId": 1
  },
  "medicines": "Paracetamol 500mg",
  "instructions": "Take as directed by the doctor"
}
```

**Expected result:** A successful response containing the saved prescription if appointment ID 1 exists and the database is configured correctly.

## 4. Testing Status

These are planned API test cases and expected outcomes. Actual results must be recorded after running the application and sending the requests.

