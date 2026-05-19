package rw.auca.studyroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.studyroom.model.Booking;
import java.util.UUID;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByRoomIdAndBookingDateAndReleasedFalse(UUID roomId, String bookingDate);
}
