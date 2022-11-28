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
    public Client findClientByUsername(String username){
        return clientRepo.findByUsername(username);
    }
    public String deleteClient(Integer id){
        if(clientRepo.findById(id)!=null){
            clientRepo.delete(id);
            return "Client deleted successfully!";
        }
        return "Client deleted successfully!";
    }


    // ROOM


    public List<Room> seeAllRooms(){
        return roomRepo.getAll();
    }
    public void addRoom(Type type, double price, int nrPers){
        roomRepo.add(new Room(type,price,nrPers));
    }
    public boolean deleteRoom(Integer id){
        Room r = roomRepo.findById(id);
        if(r == null )
        {
            roomRepo.delete(id);
            return true ;
        }
        return false;
    }
    public String updateRoom(int id, Room room){
        Room r = roomRepo.findById(id);
        if(r == null )
        {
            roomRepo.update(id,room);
            return "Room updated successfully";
        }

        return "Couldn't update room!";
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
    public boolean setSalaryCleaner(String username, int salary)
    {
        for (Cleaner c : cleanerRepo.getAll())
        {
            if (Objects.equals(c.getUsername(), username))
            {
                c.setSalary(salary);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCleaner(Integer id){
        if(cleanerRepo.findById(id)!=null){
            cleanerRepo.delete(id);
            return true;
        }
        return false;
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
