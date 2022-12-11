package repository.databaseRepo;

import model.*;
import repository.IClientRespository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class databaseClientRepo implements IClientRespository {

    EntityManagerFactory factory;
    EntityManager manager;

    public databaseClientRepo( ) {
        populate_clients();
        factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
    }

    private void populate_clients(){

        Client client1 = new Client("Bob", "Pop","bobpop","00bob");
        Client client2 = new Client("Laura", "Georgescu","laurgeor","22laura");
        Client client3 = new Client("Catalin", "Olariu","cataola","24catalin");
        manager.persist(client1);
        manager.persist(client2);
        manager.persist(client3);
    }

    @Override
    public void add(Client client) {
        manager.persist(client);
        manager.getTransaction().commit();
    }
    @Override
    public void delete(Integer clientId) {
        Query query = manager.createNativeQuery("DELETE FROM Client WHERE id=:idClient", Client.class);
        query.setParameter("idClient", Integer.toString(clientId));
        query.executeUpdate();
    }

    @Override
    public void update(Integer clientId, Client client) {
        Query query = manager.createNativeQuery("UPDATE Client SET firstname=:clFN, lastname=:clLN, username=:clU, password=:clP WHERE id=:clId",Client.class);
        query.setParameter("clFN", client.getFirstName());
        query.setParameter("clLN", client.getLastName());
        query.setParameter("clU", client.getUsername());
        query.setParameter("clP", client.getPassword());
        query.setParameter("clId", Integer.toString(clientId));
        query.executeUpdate();

    }

    @Override
    public Client findByUsername(String username) {
        Query query = manager.createNativeQuery("SELECT * FROM Client WHERE username=:clU",Client.class);
        query.setParameter("clU", username);
        return (Client) query.getSingleResult();
    }
    @Override
    public Client findById(Integer clientId){
        Query query = manager.createNativeQuery("SELECT * FROM Client WHERE id=:clId",Client.class);
        query.setParameter("clId", Integer.toString(clientId));
        return (Client) query.getSingleResult();
    }

    @Override
    public List<Client> getAll() {
        Query query = manager.createNativeQuery("SELECT * FROM Client",Client.class);
        return (List<Client>) query.getResultList();
    }
    public void addCoupon(Coupon c, int client_id)
    {
        manager.persist(c);
    }
    public void removeCoupon(Coupon coupon, int couponId)
    {
        Query query = manager.createNativeQuery("DELETE FROM Coupon WHERE id=:idCoupon", Coupon.class);
        query.setParameter("idCoupon", Integer.toString(couponId));
        query.executeUpdate();
    }


    // RESERVATION

    public void addReservation(Reservation r, int clientId)
    {
        manager.persist(r);
    }
    public Reservation findReservationById(int reservationId){
        //Reservations
        Query query1 = manager.createNativeQuery("SELECT * FROM Reservation WHERE id=:resId",Reservation.class);
        query1.setParameter("resId", Integer.toString(reservationId));
        //Reservation Rooms
        Query query2 = manager.createNativeQuery("SELECT room_id FROM reservation_room WHERE reservation_id=:resId",Reservation.class);
        query2.setParameter("resId", Integer.toString(reservationId));
        //Rooms
        Query query3 = manager.createNativeQuery("SELECT * FROM Room",Room.class);
        List<Room> allRooms = (List<Room>) query3.getResultList();
        //set Rooms for Reservation
        List<Integer> roomIds = (List<Integer>) query2.getResultList();
        List<Room> rooms = new ArrayList<>();
        for(int roomId : roomIds) {
            for (Room room : allRooms) {
                if (room.getId() == roomId) {
                    rooms.add(room);
                    break;
                }
            }
        }
        Reservation reservation = (Reservation) query1.getSingleResult();
        reservation.setRooms(rooms);
        return reservation;
    }
    public Reservation removeReservation(int resIid, int clientId)
    {
        Reservation reservation = findReservationById(resIid);
        Query query = manager.createNativeQuery("DELETE FROM Reservation WHERE id=:idRes", Reservation.class);
        query.setParameter("idRes", Integer.toString(resIid));
        query.executeUpdate();
        return reservation;
    }
    public List<Reservation> getReservationsForClient(int clientId){
        Query query = manager.createNativeQuery("SELECT reservation_id FROM Reservation WHERE client_id=:idRes", Reservation.class);
        List<Integer> resIds = (List<Integer>) query.getResultList();
        List<Reservation> reservationsForClient = new ArrayList<>();
        for(int resId : resIds) {
            reservationsForClient.add(findReservationById(resId));
        }
        return reservationsForClient;
    }

    public List<Reservation> getAllReservations(){
//        List<Reservation> reservations = new ArrayList<>();
//        for(Client c : clients){
//            reservations.addAll(c.getReservationList());
//        }
        return new ArrayList<>();
    }

    public List<Room> returnAllUnAvailableRooms(LocalDate start, LocalDate end) {
        List<Room> rooms = new ArrayList<>();
        for (Reservation reservation : getAllReservations())
        {
            if ((reservation.getStart().isAfter(start) && reservation.getStart().isBefore(end)) ||
                    (reservation.getStart() == start) ||
                    (reservation.getEnd().isAfter(start) && reservation.getEnd().isBefore(end)) ||
                    (reservation.getEnd() == end) ||
                    (reservation.getStart().isBefore(start) && reservation.getEnd().isAfter(end)))
            {
                rooms.addAll(reservation.getRooms());

            }
        }
        return rooms;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    public Coupon findCouponById(int couponId, int clientId)
    {
//        for (Client client : clients)
//        {
//            if (client.getId() == clientId)
//            {
//                List<Coupon> coupons = client.getCouponList();
//                for (Coupon coupon : coupons)
//                {
//                    if (coupon.getCode() == couponId)
//                    {
//                        return coupon;
//                    }
//                }
//            }
//        }
        return null;
    }
}

