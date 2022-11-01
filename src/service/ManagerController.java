package service;

import model.Room;

public class ManagerController {
    private RoomRepository roomRepository;

    public void addRoom(Room room){
        roomRepository.add(room);
    }
    public void deleteRoom(int id){
        roomRepository.delete(id);
    }
    public void updateRoom(Room room){
        roomRepository.update(room);
    }
}
