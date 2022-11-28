package repository.inMemoryRepo;

import model.Cleaning;
import model.Room;
import model.Type;
import repository.IRoomRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRoomRepo implements IRoomRepository {
    private List<Room> rooms;
    private int ct;

    public InMemoryRoomRepo(List<Room> rooms) {
        this.ct= 0;
        this.rooms =populate_rooms();
    }

    private List<Room> populate_rooms()
    {
        List<Room>rooms = new ArrayList<>();
        Room room1 = new Room(Type.SINGLE,300,1,generateRoomId(Type.SINGLE));
        Room room2 = new Room(Type.DOUBLE,400,2,generateRoomId(Type.DOUBLE));
        Room room3 = new Room(Type.TRIPLE,700,3,generateRoomId(Type.TRIPLE));
        Room room4 = new Room(Type.APARTMENT,1000,4,generateRoomId(Type.APARTMENT));
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        return  rooms;

    }
    public String generateRoomId(Type type){
        ct++;
        return ct + type.toString();
    }
    @Override
    public void add(Room room) {
     rooms.add(room);

    }

    @Override
    public void delete(String id) {
        if(findbyusername(id)!=null){
            rooms.remove(findbyusername(id));
        }

    }

    @Override
    public void update(String id, Room room) {
        findbyusername(id).setPrice(room.getPrice());
        findbyusername(id).setType(room.getType());
        findbyusername(id).setNrPers(room.getNrPers());
    }

    @Override
    public Room findbyusername(String id) {
        for(Room r: rooms){
            if(r.getId().equals(id)){
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Room> getAll() {
        return rooms;
    }


    @Override
    public List<Room> returnRoomsOfType(Type t) {
        List<Room>typeRooms = new ArrayList<>();
        for (Room room : rooms)
        {
            if (room.getType() == t)
            {
                typeRooms.add(room);
            }
        }
        return typeRooms;
    }
}
