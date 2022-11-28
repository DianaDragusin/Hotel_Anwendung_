package repository.fileRepo;

import model.Room;
import model.Type;
import repository.IRoomRepository;

import java.util.List;

public class FileRoomRepo implements IRoomRepository {
    @Override
    public void add(Room room) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id, Room room) {

    }

    @Override
    public Room findById(Integer id) {
        return null;
    }

    @Override
    public List<Room> getAll() {
        return null;
    }


    @Override
    public List<Room> returnRoomsOfType(Type t) {
        return null;
    }
}
