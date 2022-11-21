package service;

import model.*;
import repository.inMemoryRepo.*;
import views.ManagerView;

import java.util.List;
import java.util.Objects;

public class ManagerController {
    private String password;
    private int ct;
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
        this.ct=0;
    }
    public String setSalaryCleaner(String username, int salary)
    {
        for (Cleaner c : cleanerRepo.getAll())
        {
            if (Objects.equals(c.getUsername(), username))
            {
                c.setSalary(salary);
                return "Salary was succesfully set";
            }
        }
        return "Salary was not set";
    }
    // PASSWORD

    public boolean login(String password){
        return password.equals(this.password);
    }
    public String changePassword(String password){
        this.password=password;
        return "Password changed successfully!";
    }

    // ROOM

    private String generateRoomId(Type type){
        ct++;
        return ct + type.toString();
    }
    public List<Room> seeAllRooms(){
        return roomRepo.getAll();
    }
    public String addRoom(Type type, double price, int nrPers){
        if(roomRepo.add(new Room(type,price,nrPers,generateRoomId(type)))){
            return "Room added successfully!";
        }
        return "Couldn't add room!";
    }
    public String deleteRoom(String id){
        if(roomRepo.delete(id)){
            return "Room deleted successfully!";
        }
        return "Room not found!";
    }
    public String updateRoom(String id, Room room){
        if(roomRepo.update(id,room)){
            return "Room updated successfully";
        }
        return "Couldn't update room!";
    }
    public Room findRoomById(String id){
        return roomRepo.findbyusername(id);
    }

    // CLIENT

    public List<Client> seeAllClients(){
        return clientRepo.getAll();
    }
    public String deleteClient(String id){
        if(clientRepo.findbyusername(id)!=null){
            clientRepo.delete(id);
            return "Client deleted successfully!";
        }
        return "Client deleted successfully!";
    }
    public Client findClientByUsername(String username){
        return clientRepo.findbyusername(username);
    }

    // CLEANER

    public List<Cleaner> seeAllCleaners(){
        return cleanerRepo.getAll();
    }
    public String deleteCleaner(String id){
        if(cleanerRepo.findbyusername(id)!=null){
            cleanerRepo.delete(id);
            return "Cleaner deleted successfully!";
        }
        return "Cleaner deleted successfully!";
    }
    public Cleaner findCleanerByUsername(String username){
        return cleanerRepo.findbyusername(username);
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
