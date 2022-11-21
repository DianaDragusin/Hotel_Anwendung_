package repository.fileRepo;

import model.Room;
import model.Type;
import repository.IRoomRepository;

import java.util.List;

public class FileRoomRepo implements IRoomRepository {
    @Override
    public boolean add(Room room) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(String id, Room room) {
        return false;
    }

    @Override
    public Room findbyusername(String id) {
        return null;
    }

    @Override
    public List<Room> getAll() {
        return null;
    }


    @Override
    public List<Room> typeRooms(Type t) {
        return null;
    }
}
