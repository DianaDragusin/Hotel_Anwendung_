package service;

import model.Room;
import repository.memoryRepo.*;

public class ManagerController {
    private MemoryClientRepo clientRepo;
    private MemoryRoomRepo roomRepo;
    private MemoryReservationRepo reservationRepo;
    private MemoryCleanerRepo cleanerRepo;
    private MemoryCleaningsRepo cleaningsRepo;

    public void addRoom(Room room){
        roomRepo.add(room);
    }
    public void deleteRoom(int id){
        roomRepo.delete(id);
    }
    public void updateRoom(int id, Room room){
        roomRepo.update(id,room);
    }
}
