package rw.auca.studyroom.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(nullable = false)
    private String studentName;
    
    @Column(nullable = false)
    private String studentId;
    
    @Column(nullable = false)
    private UUID roomId;
    
    @Column(nullable = false)
    private String bookingDate;
    
    @Column(nullable = false)
    private Boolean released = false;
    
    public Booking() {
    }
    
    public Booking(String studentName, String studentId, UUID roomId, String bookingDate, Boolean released) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.roomId = roomId;
        this.bookingDate = bookingDate;
        this.released = released;
    }
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public UUID getRoomId() {
        return roomId;
    }
    
    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }
    
    public String getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public Boolean getReleased() {
        return released;
    }
    
    public void setReleased(Boolean released) {
        this.released = released;
    }
}
