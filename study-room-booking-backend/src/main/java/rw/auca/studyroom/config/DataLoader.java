package rw.auca.studyroom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rw.auca.studyroom.model.Room;
import rw.auca.studyroom.repository.RoomRepository;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (roomRepository.count() == 0) {
            roomRepository.save(new Room("Library Room A", 10, "Main Library, Floor 1", true));
            roomRepository.save(new Room("Study Hall 101", 20, "Academic Building, Floor 1", true));
            roomRepository.save(new Room("Computer Lab A", 15, "IT Building, Floor 1", true));
            
            System.out.println("✓ 3 default rooms loaded successfully!");
        }
    }
}
