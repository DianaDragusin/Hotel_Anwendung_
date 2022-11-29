package service;

import model.*;
import repository.inMemoryRepo.*;
import views.ManagerView;

import java.util.List;
import java.util.Objects;

public class ManagerController {
    private String password;
    private InMemoryRoomRepo roomRepo;
    private InMemoryClientRepo clientRepo;
    private InMemoryCleanerRepo cleanerRepo;
    private InMemoryCleaningsRepo cleaningsRepo;
    private InMemoryReservationRepo reservationRepo;


    public ManagerController(InMemoryRoomRepo roomRepo, InMemoryClientRepo clientRepo, InMemoryCleanerRepo cleanerRepo, InMemoryCleaningsRepo cleaningsRepo, InMemoryReservationRepo reservationRepo, String password) {
        this.password = password;
        this.roomRepo = roomRepo;
        this.clientRepo = clientRepo;
        this.cleanerRepo = cleanerRepo;
        this.cleaningsRepo = cleaningsRepo;
        this.reservationRepo = reservationRepo;
    }

    // PASSWORD

    public boolean login(String password){
        return password.equals(this.password);
    }
    public String changePassword(String password){
        this.password=password;
        return "Password changed successfully!";
    }

    // CLIENT

    public List<Client> seeAllClients(){
        return clientRepo.getAll();
    }
    public Client findClientById(int id){
        return clientRepo.findById(id);
    }
    public Client findClientByUsername(String username){return clientRepo.findByUsername(username);}
    public Client deleteClient(Integer id){
        Client C = clientRepo.findById(id);
        if(C!=null){
            clientRepo.delete(id);
            return C;
        }
        return null;
    }


    // ROOM


    public List<Room> seeAllRooms(){
        return roomRepo.getAll();
    }
    public void addRoom(Type type, double price, int nrPers){
        roomRepo.add(new Room(type,price,nrPers));
    }
    public Room deleteRoom(Integer id){
        Room r = roomRepo.findById(id);
        if(r != null )
        {
            roomRepo.delete(id);
            return r;
        }
        return null;
    }
    public Room updateRoom(int id, Room room){
        Room r = roomRepo.findById(id);
        if(r != null )
        {
            roomRepo.update(id,room);
            return r;
        }

        return null;
    }
    public Room findRoomById(int id){
        return roomRepo.findById(id);
    }


    // CLEANER

    public List<Cleaner> seeAllCleaners(){
        return cleanerRepo.getAll();
    }
    public Cleaner findCleanerByUsername(String username){
        return cleanerRepo.findByUsername(username);
    }
    public Cleaner findCleanerById(int id){
        return cleanerRepo.findById(id);
    }

    public Cleaner setSalaryCleaner(int id, int salary)
    {
        Cleaner c = cleanerRepo.findById(id);
        if (c != null)
        {
            c.setSalary(salary);
            return c;
        }
        return null;
    }

    public Cleaner deleteCleaner(int id){
        Cleaner c = cleanerRepo.findById(id);
        if(c != null){
            cleanerRepo.delete(id);
            return c;
        }
        return null;
    }

    // CLEANING

    public List<Cleaning> seeAllCleanings(){
        return cleaningsRepo.seeAllCleanings();
    }
    public List<Cleaning> seeAllCleaningsForCleaner(Cleaner cleaner){
        return cleaningsRepo.seeAllCleaningsForCleaner(cleaner);
    }
    public List<Cleaning> seeAllCleaningsForRoom(Room room){
        return cleaningsRepo.seeAllCleaningsForRoom(room);
    }

    // RESERVATION

//    public List<Reservation> seeAllReservations(){
//        return reservationRepo.getAll();
//    }

}
