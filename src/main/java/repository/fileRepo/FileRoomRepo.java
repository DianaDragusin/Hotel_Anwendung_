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
    public void delete(String id) {

    }

    @Override
    public void update(String id, Room room) {

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
    public String generateRoomId(Type type) {
        return null;
    }

    @Override
    public List<Room> returnRoomsOfType(Type t) {
        return null;
    }
}
