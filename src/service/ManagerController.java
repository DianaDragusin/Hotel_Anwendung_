package service;

import model.*;
import repository.inMemoryRepo.*;
import views.ManagerView;

import java.util.List;

public class ManagerController {
    private String password;
    private InMemoryRoomRepo roomRepo;
    private InMemoryClientRepo clientRepo;
    private InMemoryCleanerRepo cleanerRepo;
    private InMemoryCleaningsRepo cleaningsRepo;
    private InMemoryReservationRepo reservationRepo;
    private ManagerView managerview;

    public ManagerController(InMemoryRoomRepo roomRepo, InMemoryClientRepo clientRepo, InMemoryCleanerRepo cleanerRepo, InMemoryCleaningsRepo cleaningsRepo, InMemoryReservationRepo reservationRepo) {
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

    // ROOM

    public List<Room> seeAllRooms(){
        return roomRepo.getAll();
    }
    public String addRoom(Type type, double price, int nrPers){
        if(roomRepo.add(new Room(type,price,nrPers))){
            return "Room added successfully!";
        }
        return "Couldn't add room!";
    }
    public String deleteRoom(int id){
        if(roomRepo.delete(id)){
            return "Room deleted successfully!";
        }
        return "Room not found!";
    }
    public String updateRoom(int id, Room room){
        if(roomRepo.update(id,room)){
            return "Room updated successfully";
        }
        return "Couldn't update room!";
    }
    public Room findRoomById(int id){
        return roomRepo.findbyID(id);
    }

    // CLIENT

    public List<Client> seeAllClients(){
        return clientRepo.getAll();
    }
    public String deleteClient(int id){
        if(clientRepo.findbyID(id)!=null){
            clientRepo.delete(id);
            return "Client deleted successfully!";
        }
        return "Client deleted successfully!";
    }
    public Client findClientById(int id){
        return clientRepo.findbyID(id);
    }
    public Client findClientByUsername(String username){
        return clientRepo.findByUsername(username);
    }

    // CLEANER

    public List<Cleaner> seeAllCleaners(){
        return cleanerRepo.getAll();
    }
    public String deleteCleaner(int id){
        if(cleanerRepo.findbyID(id)!=null){
            cleanerRepo.delete(id);
            return "Cleaner deleted successfully!";
        }
        return "Cleaner deleted successfully!";
    }
    public Cleaner findCleanerById(int id){
        return cleanerRepo.findbyID(id);
    }
    public Cleaner findCleanerByUsername(String username){
        return cleanerRepo.findByUsername(username);
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

    public List<Reservation> seeAllReservations(){
        return reservationRepo.getAll();
    }

}
