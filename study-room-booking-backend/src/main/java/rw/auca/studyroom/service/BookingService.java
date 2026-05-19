package rw.auca.studyroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.studyroom.model.Booking;
import rw.auca.studyroom.model.Room;
import rw.auca.studyroom.repository.BookingRepository;
import rw.auca.studyroom.repository.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Booking createBooking(Booking booking) {
        Optional<Room> roomOptional = roomRepository.findById(booking.getRoomId());
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            
            List<Booking> existingBookings = bookingRepository.findByRoomIdAndBookingDateAndReleasedFalse(
                booking.getRoomId(), 
                booking.getBookingDate()
            );
            
            if (!existingBookings.isEmpty()) {
                throw new RuntimeException("Room is already booked for this date");
            }
            
            if (room.getAvailable()) {
                room.setAvailable(false);
                roomRepository.save(room);
                return bookingRepository.save(booking);
            }
        }
        return null;
    }
    
    public boolean cancelBooking(UUID id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            Optional<Room> roomOptional = roomRepository.findById(booking.getRoomId());
            if (roomOptional.isPresent()) {
                Room room = roomOptional.get();
                room.setAvailable(true);
                roomRepository.save(room);
            }
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public Booking releaseBooking(UUID bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            booking.setReleased(true);
            Booking updatedBooking = bookingRepository.save(booking);
            Optional<Room> roomOptional = roomRepository.findById(booking.getRoomId());
            if (roomOptional.isPresent()) {
                Room room = roomOptional.get();
                room.setAvailable(true);
                roomRepository.save(room);
            }
            return updatedBooking;
        }
        return null;
    }
}
