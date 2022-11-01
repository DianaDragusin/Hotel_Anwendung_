package repository.memoryRepo;

import model.Room;
import repository.ICrud;
import repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

public class MemoryRoomRepo implements RoomRepository {
    private List<Room> rooms;

    public MemoryRoomRepo(List<Room> rooms) {
        this.rooms = new ArrayList<>();
    }

    @Override
    public void add(Room room) {
     rooms.add(room)  ;
    }

    @Override
    public void delete(Integer id) {
        rooms.remove(rooms.get(id));
    }

    @Override
    public void update(Integer id, Room room) {
        rooms.get(id).setPrice(room.getPrice());
        rooms.get(id).setType(room.getType());
        rooms.get(id).setNrPers(room.getNrPers());
    }

    @Override
    public Room findbyID(Integer id) {
        return rooms.get(id);
    }
}
