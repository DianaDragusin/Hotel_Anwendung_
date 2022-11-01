package service;

import model.Room;
import repository.inMemoryRepo.*;

public class ManagerController {
    private InMemoryClientRepo clientRepo;
    private InMemoryRoomRepo roomRepo;
    private InMemoryReservationRepo reservationRepo;
    private InMemoryCleanerRepo cleanerRepo;
    private InMemoryCleaningsRepo cleaningsRepo;

    public void addRoom(Room room){
        roomRepo.add(room);
    }
    public void deleteRoom(int id){
        roomRepo.delete(id);
    }
    public void updateRoom(int id, Room room){
        roomRepo.update(id,room);
    }
    public Room findRoomById(int id){
        return roomRepo.findbyID(id);
    }
}
