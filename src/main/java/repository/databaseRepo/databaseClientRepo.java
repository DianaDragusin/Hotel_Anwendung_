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
import java.util.stream.Collectors;

public class databaseClientRepo implements IClientRespository {
    EntityManager manager;

    public databaseClientRepo(EntityManager manager) {
        this.manager = manager;


       // populate_clients();

    }

    private void populate_clients(){
        manager.getTransaction().begin();
        Client client1 = new Client("Bob", "Pop","bobpop","00bob");
        Client client2 = new Client("Laura", "Georgescu","laurgeor","22laura");
        Client client3 = new Client("Catalin", "Olariu","cataola","24catalin");
        manager.persist(client1);
        manager.persist(client2);
        manager.persist(client3);
        manager.getTransaction().commit();

    }

    @Override
    public void add(Client client) {
        manager.getTransaction().begin();
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
        Client c = null;

        try {

            Query query = manager.createNativeQuery("SELECT * FROM Client WHERE username=:clU", Client.class);
            query.setParameter("clU", username);
            c = (Client) query.getSingleResult();

        }catch (Exception e)
        {
            return c;
        }
       return  c;

    }
    @Override
    public Client findById(Integer clientId){
        Client c = null;
        try {
            Query query = manager.createNativeQuery("SELECT * FROM Client WHERE id=:clId",Client.class);
            query.setParameter("clId", Integer.toString(clientId));
            c = (Client) query.getSingleResult();
        }catch (Exception exception)
        {
            return c;
        }


        return c ;
    }

    @Override
    public List<Client> getAll() {
        List<Client>clients = new ArrayList<>();
        try {
            Query query = manager.createNativeQuery("SELECT * FROM Client",Client.class);
            clients =  (List<Client>) query.getResultList();
        }catch (Exception exception)
        {
            return clients;
        }


        return clients;
    }
    public void addCoupon(Coupon c, int client_id)
    {
        manager.getTransaction().begin();
        findById(client_id).addCoupon(c);
        manager.persist(c);
        manager.getTransaction().commit();

    }
    public void removeCoupon(Coupon coupon, int clientId)
    {
        //manager.getTransaction().begin();
        Query query = manager.createNativeQuery("DELETE FROM Coupon WHERE code=:idCoupon AND client_id=:idCl", Coupon.class);
        query.setParameter("idCoupon", Integer.toString(coupon.getCode()));
        query.setParameter("idCl", Integer.toString(clientId));
        query.executeUpdate();
        //manager.getTransaction().commit();

    }


    // RESERVATION

    public void addReservation(Reservation r, int clientId)
    {
        manager.getTransaction().begin();
        findById(clientId).addReservation(r);
        manager.persist(r);
        manager.getTransaction().commit();

    }
    public Reservation findReservationById(int reservationId){
        //manager.getTransaction().begin();
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
        //manager.getTransaction().commit();
        return reservation;
    }
    public void removeReservation(int resIid, int clientId)
    {
       // manager.getTransaction().begin();
        Reservation reservation = findReservationById(resIid);
        Query query = manager.createNativeQuery("DELETE FROM Reservation WHERE id=:idRes AND client_id=:idCl", Reservation.class);
        query.setParameter("idRes", Integer.toString(resIid));
        query.setParameter("idCl", Integer.toString(clientId));
        query.executeUpdate();
       // manager.getTransaction().commit();
    }
    public List<Reservation> getReservationsForClient(int clientId){
       // manager.getTransaction().begin();
        try{
            Query query = manager.createNativeQuery("SELECT id FROM Reservation WHERE client_id=:idCl", Reservation.class);
            query.setParameter("idCl", Integer.toString(clientId));
            List<Integer> resIds = (List<Integer>) query.getResultList();
            List<Reservation> reservationsForClient = new ArrayList<>();
            for(int resId : resIds) {
                reservationsForClient.add(findReservationById(resId));
            }
            return reservationsForClient;
        } catch (Exception exception)
        {
            return new ArrayList<>();
        }

        //manager.getTransaction().commit();

    }

    public List<Reservation> getAllReservations(){
       // manager.getTransaction().begin();
        List<Integer> clientIds = getAll().stream().map(Client::getId).toList();
        List<Reservation> reservations = new ArrayList<>();
        for(int clientId : clientIds){
            reservations.addAll(getReservationsForClient(clientId));
        }
        //manager.getTransaction().commit();
        return reservations;
    }

    public List<Room> returnAllUnAvailableRooms(LocalDate start, LocalDate end) {
        //manager.getTransaction().begin();
        List<Room> rooms = new ArrayList<>();
        for (Reservation reservation : getAllReservations())
        {
            if (reservation.getStart().isBefore(end) && reservation.getEnd().isAfter(start) ||
                reservation.getStart().isBefore(end) && reservation.getEnd().isEqual(start) ||
                reservation.getStart().isEqual(end) && reservation.getEnd().isAfter(start) ||
                reservation.getStart().isEqual(end) && reservation.getEnd().isEqual(start))
            {
                rooms.addAll(reservation.getRooms());
            }
        }
      //  manager.getTransaction().commit();
        return rooms;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    public Coupon findCouponById(int couponId, int clientId)
    {
        Coupon c ;
       // manager.getTransaction().begin();
        Query query = manager.createNativeQuery("SELECT * FROM Coupon WHERE code=:coId AND client_id=:clId",Coupon.class);
        query.setParameter("coId", Integer.toString(couponId));
        query.setParameter("clId", Integer.toString(clientId));
        c = (Coupon) query.getSingleResult();
        //manager.getTransaction().commit();
        return  c;
    }
}

