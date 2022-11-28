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
        return clientRepo.findbyusername(username);
    }
    public String deleteClient(String id){
        if(clientRepo.findbyusername(id)!=null){
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
        roomRepo.add(new Room(type,price,nrPers,roomRepo.generateRoomId(type)));
    }
    public boolean deleteRoom(String id){
        Room r = roomRepo.findbyusername(id);
        if(r == null )
        {
            roomRepo.delete(id);
            return true ;
        }
        return false;
    }
    public String updateRoom(String id, Room room){
        Room r = roomRepo.findbyusername(id);
        if(r == null )
        {
            roomRepo.update(id,room);
            return "Room updated successfully";
        }

        return "Couldn't update room!";
    }
    public Room findRoomById(String id){
        return roomRepo.findbyusername(id);
    }


    // CLEANER

    public List<Cleaner> seeAllCleaners(){
        return cleanerRepo.getAll();
    }
    public Cleaner findCleanerByUsername(String username){
        return cleanerRepo.findbyusername(username);
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

    public boolean deleteCleaner(String username){
        if(cleanerRepo.findbyusername(username)!=null){
            cleanerRepo.delete(username);
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
