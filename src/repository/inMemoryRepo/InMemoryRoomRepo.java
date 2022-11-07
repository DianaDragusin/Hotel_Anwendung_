package repository.inMemoryRepo;

import model.Room;
import model.Type;
import repository.IRoomRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRoomRepo implements IRoomRepository {
    private List<Room> rooms;

    public InMemoryRoomRepo(List<Room> rooms) {
        this.rooms = new ArrayList<>();
    }


    @Override
    public boolean add(Room room) {
     rooms.add(room);
     return true;
    }

    @Override
    public void delete(Integer id) {
        rooms.remove(rooms.get(id));
    }

    @Override
    public boolean update(Integer id, Room room) {
        rooms.get(id).setPrice(room.getPrice());
        rooms.get(id).setType(room.getType());
        rooms.get(id).setNrPers(room.getNrPers());
        return true;
    }

    @Override
    public Room findbyID(Integer id) {
        return rooms.get(id);
    }

    @Override
    public List<Room> getAll() {
        return rooms;
    }



    @Override
    public List<Room> typeRooms(Type t) {
        List<Room>typeRooms = new ArrayList<>();
        for (Room room : rooms)
        {
            if (room.getType() == t)
            {
                rooms.add(room);
            }
        }
        return rooms;
    }
}
