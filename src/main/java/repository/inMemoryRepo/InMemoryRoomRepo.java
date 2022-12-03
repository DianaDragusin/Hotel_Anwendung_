package repository.inMemoryRepo;

import model.Cleaning;
import model.Client;
import model.Room;
import model.Type;
import repository.IRoomRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRoomRepo implements IRoomRepository {
    private List<Room> rooms;



    public InMemoryRoomRepo(List<Room> rooms) {
        this.rooms =populate_rooms();
    }

    private List<Room> populate_rooms()
    {
        List<Room>rooms = new ArrayList<>();
        Room room1 = new Room(Type.SINGLE,300,1);
        Room room2 = new Room(Type.DOUBLE,400,2);
        Room room3 = new Room(Type.TRIPLE,700,3);
        Room room4 = new Room(Type.APARTMENT,1000,4);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        return  rooms;

    }
    @Override
    public void add(Room room) {
     rooms.add(room);

    }

    @Override
    public void delete(Integer id) {
        if(findById(id)!=null){
            rooms.remove(findById(id));
        }

    }

    @Override
    public void update(Integer id, Room room) {
        findById(id).setPrice(room.getPrice());
        findById(id).setType(room.getType());
        findById(id).setNrPers(room.getNrPers());
    }
    @Override
    public Room findById(Integer id){
        for(Room r : rooms){
            if(r.getId() == id){
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
