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
    public boolean delete(Integer integer) {

    }

    @Override
    public boolean update(Integer integer, Room room) {
        return false;
    }

    @Override
    public Room findbyID(Integer integer) {
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
