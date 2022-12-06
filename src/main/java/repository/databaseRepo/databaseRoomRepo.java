package repository.databaseRepo;

import model.Room;
import model.Type;
import repository.IRoomRepository;

import java.util.ArrayList;
import java.util.List;

public class databaseRoomRepo implements IRoomRepository {
    private List<Room> rooms;
    private int room_id;


    public databaseRoomRepo() {
        this.rooms = new ArrayList<>();
        populate_rooms();
        room_id = 0;
    }

    private void populate_rooms()
    {

        Room room1 = new Room(Type.SINGLE,300,1);
        Room room2 = new Room(Type.DOUBLE,400,2);
        Room room3 = new Room(Type.TRIPLE,700,3);
        Room room4 = new Room(Type.APARTMENT,1000,4);
        this.add(room1);
        this.add(room1);
        this.add(room1);
        this.add(room1);
        this.add(room2);
        this.add(room2);
        this.add(room3);
        this.add(room3);
        this.add(room3);
        this.add(room4);
        this.add(room4);
        this.add(room4);


    }
    @Override
    public void add(Room room) {
        room_id++;
        room.setId(room_id);
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
