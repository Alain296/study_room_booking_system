# API Documentation

## Base URL
```
http://localhost:8080/api
```

---

## Room Endpoints

### 1. Get All Rooms
Retrieve a list of all rooms in the system.

**Endpoint:** `GET /rooms`

**Response:** `200 OK`
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Library Room A",
    "capacity": 10,
    "location": "Building A, Floor 1",
    "available": true
  },
  {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "name": "Study Hall B",
    "capacity": 20,
    "location": "Building B, Floor 2",
    "available": false
  }
]
```

---

### 2. Get Room by ID
Retrieve details of a specific room.

**Endpoint:** `GET /rooms/{id}`

**Path Parameters:**
- `id` (UUID) - The unique identifier of the room

**Response:** `200 OK`
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Library Room A",
  "capacity": 10,
  "location": "Building A, Floor 1",
  "available": true
}
```

**Error Response:** `404 Not Found`
```json
{
  "error": "Room not found"
}
```

---

### 3. Create New Room
Add a new room to the system.

**Endpoint:** `POST /rooms`

**Request Body:**
```json
{
  "name": "Conference Room C",
  "capacity": 15,
  "location": "Building C, Floor 3",
  "available": true
}
```

**Response:** `201 Created`
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440002",
  "name": "Conference Room C",
  "capacity": 15,
  "location": "Building C, Floor 3",
  "available": true
}
```

**Validation Rules:**
- `name`: Required, max 255 characters
- `capacity`: Required, must be positive integer
- `location`: Required, max 255 characters
- `available`: Optional, defaults to true

---

### 4. Update Room
Update an existing room's information.

**Endpoint:** `PUT /rooms/{id}`

**Path Parameters:**
- `id` (UUID) - The unique identifier of the room

**Request Body:**
```json
{
  "name": "Updated Library Room A",
  "capacity": 12,
  "location": "Building A, Floor 1",
  "available": true
}
```

**Response:** `200 OK`
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Updated Library Room A",
  "capacity": 12,
  "location": "Building A, Floor 1",
  "available": true
}
```

**Error Response:** `404 Not Found`
```json
{
  "error": "Room not found"
}
```

---

## Booking Endpoints

### 1. Get All Bookings
Retrieve a list of all bookings in the system.

**Endpoint:** `GET /bookings`

**Response:** `200 OK`
```json
[
  {
    "id": "660e8400-e29b-41d4-a716-446655440000",
    "studentName": "Mugabo Alain",
    "studentId": "26450",
    "roomId": "550e8400-e29b-41d4-a716-446655440000",
    "bookingDate": "2026-05-20",
    "timeSlot": "9:00-11:00",
    "released": false
  },
  {
    "id": "660e8400-e29b-41d4-a716-446655440001",
    "studentName": "John Doe",
    "studentId": "12345",
    "roomId": "550e8400-e29b-41d4-a716-446655440001",
    "bookingDate": "2026-05-21",
    "timeSlot": "14:00-16:00",
    "released": true
  }
]
```

---

### 2. Create New Booking
Create a new room booking.

**Endpoint:** `POST /bookings`

**Request Body:**
```json
{
  "studentName": "Mugabo Alain",
  "studentId": "26450",
  "roomId": "550e8400-e29b-41d4-a716-446655440000",
  "bookingDate": "2026-05-20",
  "timeSlot": "9:00-11:00"
}
```

**Response:** `201 Created`
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440000",
  "studentName": "Mugabo Alain",
  "studentId": "26450",
  "roomId": "550e8400-e29b-41d4-a716-446655440000",
  "bookingDate": "2026-05-20",
  "timeSlot": "9:00-11:00",
  "released": false
}
```

**Side Effects:**
- The room's `available` status is automatically set to `false`

**Validation Rules:**
- `studentName`: Required, max 255 characters
- `studentId`: Required, max 50 characters
- `roomId`: Required, must be a valid UUID of an existing room
- `bookingDate`: Required, must be in ISO format (YYYY-MM-DD)
- `timeSlot`: Required, max 50 characters

**Error Response:** `400 Bad Request`
```json
{
  "error": "Room is not available"
}
```

---

### 3. Cancel Booking
Delete a booking and make the room available again.

**Endpoint:** `DELETE /bookings/{id}`

**Path Parameters:**
- `id` (UUID) - The unique identifier of the booking

**Response:** `204 No Content`

**Side Effects:**
- The booking is permanently deleted
- The associated room's `available` status is set to `true`

**Error Response:** `404 Not Found`
```json
{
  "error": "Booking not found"
}
```

---

### 4. Release Booking
Mark a booking as released without deleting it.

**Endpoint:** `PUT /bookings/{id}/release`

**Path Parameters:**
- `id` (UUID) - The unique identifier of the booking

**Response:** `200 OK`
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440000",
  "studentName": "Mugabo Alain",
  "studentId": "26450",
  "roomId": "550e8400-e29b-41d4-a716-446655440000",
  "bookingDate": "2026-05-20",
  "timeSlot": "9:00-11:00",
  "released": true
}
```

**Side Effects:**
- The booking's `released` status is set to `true`
- The associated room's `available` status is set to `true`
- The booking record is preserved for history

**Error Response:** `404 Not Found`
```json
{
  "error": "Booking not found"
}
```

---

## Time Slots

The system supports the following predefined time slots:

- `9:00-11:00` - Morning Session 1
- `11:00-13:00` - Morning Session 2
- `14:00-16:00` - Afternoon Session 1
- `16:00-18:00` - Afternoon Session 2
- `18:00-20:00` - Evening Session

---

## Error Codes

| Status Code | Description |
|-------------|-------------|
| 200 | OK - Request successful |
| 201 | Created - Resource created successfully |
| 204 | No Content - Request successful, no content to return |
| 400 | Bad Request - Invalid request data |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error - Server error |

---

## CORS Configuration

The API has CORS enabled for all origins during development. In production, this should be restricted to specific domains.

**Allowed Origins:** `*` (all)  
**Allowed Methods:** `GET`, `POST`, `PUT`, `DELETE`  
**Allowed Headers:** `*` (all)

---

## Example Usage with cURL

### Create a Room
```bash
curl -X POST http://localhost:8080/api/rooms \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Study Room D",
    "capacity": 8,
    "location": "Building D, Floor 1",
    "available": true
  }'
```

### Get All Rooms
```bash
curl http://localhost:8080/api/rooms
```

### Create a Booking
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "studentName": "Mugabo Alain",
    "studentId": "26450",
    "roomId": "550e8400-e29b-41d4-a716-446655440000",
    "bookingDate": "2026-05-20",
    "timeSlot": "9:00-11:00"
  }'
```

### Release a Booking
```bash
curl -X PUT http://localhost:8080/api/bookings/660e8400-e29b-41d4-a716-446655440000/release
```

### Cancel a Booking
```bash
curl -X DELETE http://localhost:8080/api/bookings/660e8400-e29b-41d4-a716-446655440000
```

---

## Example Usage with JavaScript (Axios)

### Get All Rooms
```javascript
import axios from 'axios';

const getRooms = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/rooms');
    console.log(response.data);
  } catch (error) {
    console.error('Error fetching rooms:', error);
  }
};
```

### Create a Booking
```javascript
const createBooking = async (bookingData) => {
  try {
    const response = await axios.post(
      'http://localhost:8080/api/bookings',
      bookingData
    );
    console.log('Booking created:', response.data);
  } catch (error) {
    console.error('Error creating booking:', error);
  }
};

// Usage
createBooking({
  studentName: 'Mugabo Alain',
  studentId: '26450',
  roomId: '550e8400-e29b-41d4-a716-446655440000',
  bookingDate: '2026-05-20',
  timeSlot: '9:00-11:00'
});
```

---

## Database Schema Reference

### Rooms Table
```sql
CREATE TABLE rooms (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    capacity INTEGER NOT NULL,
    location VARCHAR(255) NOT NULL,
    available BOOLEAN DEFAULT true
);
```

### Bookings Table
```sql
CREATE TABLE bookings (
    id UUID PRIMARY KEY,
    student_name VARCHAR(255) NOT NULL,
    student_id VARCHAR(50) NOT NULL,
    room_id UUID NOT NULL,
    booking_date DATE NOT NULL,
    time_slot VARCHAR(50) NOT NULL,
    released BOOLEAN DEFAULT false,
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);
```

---

## Rate Limiting

Currently, there is no rate limiting implemented. For production use, consider implementing:
- Request rate limiting per IP address
- Authentication-based rate limiting
- API key management

---

## Authentication

The current version does not implement authentication. Future versions should include:
- JWT-based authentication
- Role-based access control (Student, Admin)
- Session management
- Password encryption

---

**API Version:** 1.0.0  
**Last Updated:** May 19, 2026  
**Maintained by:** Mugabo Alain (26450)
