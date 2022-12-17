package repository.databaseRepo;

import model.*;
import repository.IRoomRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class databaseRoomRepo implements IRoomRepository {
    EntityManager manager;

    public databaseRoomRepo(EntityManager manager) {
        this.manager = manager;
        manager.getTransaction().begin();
      //  populate_rooms();
        manager.getTransaction().commit();
    }

    private void populate_rooms()
    {

        Room rooms1 = new Room(Type.SINGLE,300,1);
        Room rooms2 = new Room(Type.SINGLE,300,1);
        Room rooms3 = new Room(Type.SINGLE,300,1);
        Room roomd1 = new Room(Type.DOUBLE,400,2);
        Room roomd2 = new Room(Type.DOUBLE,400,2);
        Room roomt1 = new Room(Type.TRIPLE,700,3);
        Room roomt2 = new Room(Type.TRIPLE,700,3);
        Room roomt3 = new Room(Type.TRIPLE,700,3);
        Room rooma1 = new Room(Type.APARTMENT,1000,4);
        Room rooma2 = new Room(Type.APARTMENT,1000,4);
        manager.persist(rooms1);
        manager.persist(rooms2);
        manager.persist(rooms3);
        manager.persist(roomd1);
        manager.persist(roomd2);
        manager.persist(roomt1);
        manager.persist(roomt2);
        manager.persist(roomt3);
        manager.persist(rooma1);
        manager.persist(rooma2);
    }
    @Override
    public void add(Room room) {
        manager.getTransaction().begin();
        manager.persist(room);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Integer roomId) {
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("DELETE FROM Room WHERE id=:idRoom",Room.class);
        query.setParameter("idRoom", Integer.toString(roomId));
        query.executeUpdate();
        manager.getTransaction().commit();
    }

    @Override
    public void update(Integer id, Room room) {
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("UPDATE Room SET nrPers=:roomNrP, price=:roomPr, type=:roomT", Room.class);
        query.setParameter("roomNrP", room.getNrPers());
        query.setParameter("roomPr", room.getPrice());
        query.setParameter("roomT", room.getType());
        query.executeUpdate();
        manager.getTransaction().commit();
    }
    @Override
    public Room findById(Integer id){
        for(Room r : getAll()){
            if(r.getId() == id){
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("SELECT * FROM Room", Room.class);
        rooms = (List<Room>) query.getResultList();
        manager.getTransaction().commit();
        return  rooms;
    }


    @Override
    public List<Room> returnRoomsOfType(Type t) {
        List<Room>typeRooms = new ArrayList<>();
        for (Room room : getAll())
        {
            if (room.getType() == t)
            {
                typeRooms.add(room);
            }
        }
        return typeRooms;
    }
}
