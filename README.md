# 📚 Study Room Booking System

A full-stack web application for managing study room bookings at AUCA (Adventist University of Central Africa). This system allows students to view available rooms, make bookings, and manage their reservations efficiently.

## 👨‍💻 Project Information

- **Student Name:** Mugabo Alain
- **Student ID:** 26450
- **Course:** BIT312 - Web Technology and Internet
- **Date:** May 18, 2026
- **GitHub Repository:** [study_room_booking_system](https://github.com/Alain296/study_room_booking_system)

## 🎯 Problem Statement

Students at AUCA often face challenges when trying to book study rooms:
- No centralized system to view available rooms
- Manual booking process is time-consuming and error-prone
- Difficulty tracking room availability in real-time
- No easy way to manage or cancel existing bookings
- Room conflicts and double bookings occur frequently

## ✨ Solution

This Study Room Booking System provides:
- **Real-time Room Availability:** View all rooms and their current status instantly
- **Easy Booking Process:** Simple form-based booking with date and time slot selection
- **Booking Management:** View, release, or cancel bookings with one click
- **Room Management:** Add and update room information dynamically
- **Automatic Status Updates:** Room availability updates automatically when booked or released
- **User-Friendly Interface:** Clean, intuitive React-based frontend
- **RESTful API:** Well-structured backend with proper separation of concerns

## 🏗️ Architecture

### Technology Stack

**Backend:**
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- PostgreSQL Database
- Maven Build Tool
- Lombok for boilerplate reduction

**Frontend:**
- React 18.2.0
- React Router DOM 6.22.0
- Axios for HTTP requests
- Modern JavaScript (ES6+)
- CSS3 for styling

### System Architecture

```
┌─────────────────┐         ┌─────────────────┐         ┌─────────────────┐
│                 │         │                 │         │                 │
│  React Frontend │◄───────►│  Spring Boot    │◄───────►│   PostgreSQL    │
│  (Port 3000)    │  HTTP   │  REST API       │   JPA   │   Database      │
│                 │         │  (Port 8080)    │         │                 │
└─────────────────┘         └─────────────────┘         └─────────────────┘
```

## 📁 Project Structure

```
26450_MugaboAlain/
├── study-room-booking-backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/rw/auca/studyroom/
│   │   │   │   ├── CampusRoomReservationApplication.java
│   │   │   │   ├── config/
│   │   │   │   │   └── DataLoader.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Room.java
│   │   │   │   │   └── Booking.java
│   │   │   │   ├── repository/
│   │   │   │   │   ├── RoomRepository.java
│   │   │   │   │   └── BookingRepository.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── RoomService.java
│   │   │   │   │   └── BookingService.java
│   │   │   │   └── controller/
│   │   │   │       ├── RoomController.java
│   │   │   │       └── BookingController.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
│
├── study-room-booking-frontend/
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── components/
│   │   │   ├── RoomsPage.js
│   │   │   ├── BookingPage.js
│   │   │   └── BookingsList.js
│   │   ├── App.js
│   │   └── index.js
│   └── package.json
│
├── screenshots/
│   ├── rooms-table.png
│   ├── bookings-table.png
│   └── bookings-with-room-details.png
│
└── README.md
```

## 🚀 Features

### Backend Features (Spring Boot)
- ✅ RESTful API with proper HTTP methods (GET, POST, PUT, DELETE)
- ✅ JPA entities with relationships (Room ↔ Booking)
- ✅ Repository pattern for data access
- ✅ Service layer for business logic
- ✅ CORS enabled for frontend communication
- ✅ Automatic database schema generation
- ✅ Data loader for initial sample data
- ✅ UUID-based primary keys
- ✅ Room availability management
- ✅ Booking release functionality

### Frontend Features (React)
- ✅ Single Page Application (SPA) with React Router
- ✅ Three main pages: Rooms, Booking, Bookings List
- ✅ Real-time data fetching with Axios
- ✅ Form validation and error handling
- ✅ Success/error message notifications
- ✅ Responsive design with inline CSS
- ✅ Dynamic room selection dropdown
- ✅ Time slot selection for bookings
- ✅ One-click booking cancellation
- ✅ One-click booking release

## 📊 Database Schema

### Rooms Table
| Column    | Type         | Description                    |
|-----------|--------------|--------------------------------|
| id        | UUID         | Primary key                    |
| name      | VARCHAR(255) | Room name                      |
| capacity  | INTEGER      | Maximum number of students     |
| location  | VARCHAR(255) | Physical location of the room  |
| available | BOOLEAN      | Current availability status    |

### Bookings Table
| Column        | Type         | Description                    |
|---------------|--------------|--------------------------------|
| id            | UUID         | Primary key                    |
| student_name  | VARCHAR(255) | Name of the student            |
| student_id    | VARCHAR(50)  | Student identification number  |
| room_id       | UUID         | Foreign key to rooms table     |
| booking_date  | DATE         | Date of the booking            |
| time_slot     | VARCHAR(50)  | Time slot (e.g., "9:00-11:00") |
| released      | BOOLEAN      | Whether booking is released    |

## 🔌 API Endpoints

### Room Endpoints

| Method | Endpoint          | Description           | Request Body          |
|--------|-------------------|-----------------------|-----------------------|
| GET    | `/api/rooms`      | Get all rooms         | -                     |
| GET    | `/api/rooms/{id}` | Get room by ID        | -                     |
| POST   | `/api/rooms`      | Create new room       | Room object           |
| PUT    | `/api/rooms/{id}` | Update room           | Room object           |

### Booking Endpoints

| Method | Endpoint                    | Description           | Request Body          |
|--------|-----------------------------|-----------------------|-----------------------|
| GET    | `/api/bookings`             | Get all bookings      | -                     |
| POST   | `/api/bookings`             | Create new booking    | Booking object        |
| DELETE | `/api/bookings/{id}`        | Cancel booking        | -                     |
| PUT    | `/api/bookings/{id}/release`| Release booking       | -                     |

### Example API Requests

**Create a Room:**
```json
POST /api/rooms
{
  "name": "Library Room A",
  "capacity": 10,
  "location": "Building A, Floor 1",
  "available": true
}
```

**Create a Booking:**
```json
POST /api/bookings
{
  "studentName": "Mugabo Alain",
  "studentId": "26450",
  "roomId": "550e8400-e29b-41d4-a716-446655440000",
  "bookingDate": "2026-05-20",
  "timeSlot": "9:00-11:00"
}
```

## 💻 Installation & Setup

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Node.js 16 or higher
- npm 8 or higher
- PostgreSQL 12 or higher

### Database Setup

1. **Install PostgreSQL** (if not already installed)

2. **Create the database:**
   ```sql
   CREATE DATABASE studyroom_db;
   ```

3. **Update credentials** (if needed):
   - Open `study-room-booking-backend/src/main/resources/application.properties`
   - Update username and password:
     ```properties
     spring.datasource.username=postgres
     spring.datasource.password=your_password
     ```

### Backend Setup

1. **Navigate to backend directory:**
   ```bash
   cd study-room-booking-backend
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Verify it's running:**
   - Backend will start on `http://localhost:8080`
   - Test endpoint: `http://localhost:8080/api/rooms`

### Frontend Setup

1. **Navigate to frontend directory:**
   ```bash
   cd study-room-booking-frontend
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```

3. **Start the development server:**
   ```bash
   npm start
   ```

4. **Access the application:**
   - Frontend will open automatically at `http://localhost:3000`

## 📖 Usage Guide

### 1. Viewing Rooms
- Navigate to the **Rooms** page (default landing page)
- View all available rooms with their details
- See room capacity, location, and availability status

### 2. Adding a Room
- On the **Rooms** page, use the "Add New Room" form
- Enter room name, capacity, and location
- Click "Add Room" to create

### 3. Making a Booking
- Navigate to the **Book Room** page
- Fill in your student name and ID
- Select an available room from the dropdown
- Choose a booking date and time slot
- Click "Book Room" to confirm
- Success message will appear, and room status updates to "Booked"

### 4. Managing Bookings
- Navigate to the **My Bookings** page
- View all your bookings in a table format
- **Release a booking:** Click "Release" to mark as released (room becomes available)
- **Cancel a booking:** Click "Cancel" to delete the booking (room becomes available)

## 🧪 Testing

### Manual Testing Steps

1. **Test Room Management:**
   - Add a room: "Library Room A", capacity 10, location "Building A, Floor 1"
   - Verify it appears in the rooms list
   - Check that it shows as "Available"

2. **Test Booking Creation:**
   - Go to "Book Room" page
   - Enter student details
   - Select the room you created
   - Choose a date and time slot
   - Submit and verify success message
   - Return to Rooms page and verify room status is now "Booked"

3. **Test Booking Release:**
   - Go to "My Bookings" page
   - Click "Release" on a booking
   - Verify booking status changes to "Released"
   - Check that room becomes available again

4. **Test Booking Cancellation:**
   - Create a new booking
   - Go to "My Bookings" page
   - Click "Cancel" on the booking
   - Verify booking is removed from the list
   - Check that room becomes available

### Database Verification

Connect to PostgreSQL and run these queries:

```sql
-- View all rooms
SELECT * FROM rooms ORDER BY name;

-- View all bookings
SELECT * FROM bookings ORDER BY booking_date DESC;

-- View bookings with room details
SELECT 
    b.id,
    b.student_name,
    b.student_id,
    r.name AS room_name,
    r.location,
    b.booking_date,
    b.time_slot,
    b.released
FROM bookings b
JOIN rooms r ON b.room_id = r.id
ORDER BY b.booking_date DESC;
```

## 🐛 Troubleshooting

### Backend Issues

**Port 8080 already in use:**
- Change port in `application.properties`:
  ```properties
  server.port=8081
  ```

**Database connection failed:**
- Verify PostgreSQL is running: `pg_isready`
- Check database exists: `psql -l`
- Verify credentials in `application.properties`

**Build fails:**
- Ensure Java 17 is installed: `java -version`
- Clear Maven cache: `mvn clean`

### Frontend Issues

**Port 3000 already in use:**
- The app will prompt to use another port automatically

**Dependencies installation fails:**
- Clear npm cache: `npm cache clean --force`
- Delete `node_modules` and reinstall:
  ```bash
  rmdir /s /q node_modules
  npm install
  ```

**API calls fail:**
- Ensure backend is running on port 8080
- Check browser console for CORS errors
- Verify API URL in component files

## 🔒 Security Considerations

- CORS is enabled for development (should be restricted in production)
- Input validation on both frontend and backend
- SQL injection prevention through JPA/Hibernate
- UUID-based IDs prevent enumeration attacks

## 🚀 Future Enhancements

- User authentication and authorization
- Email notifications for booking confirmations
- Room search and filtering
- Booking history and analytics
- Admin dashboard for room management
- Calendar view for bookings
- Conflict detection for overlapping bookings
- Mobile responsive design improvements
- Real-time updates using WebSockets

## 📝 License

This project is created for educational purposes as part of the BIT312 course at AUCA.

## 🤝 Contributing

This is a student project. For any questions or suggestions, please contact:
- **Email:** mugabo.alain@student.auca.ac.rw
- **GitHub:** [@Alain296](https://github.com/Alain296)

## 🙏 Acknowledgments

- AUCA Faculty of Information Technology
- BIT312 - Web Technology and Internet Course
- Spring Boot and React communities for excellent documentation

---

**Made with ❤️ by Mugabo Alain (26450)**
