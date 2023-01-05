package service;

import model.*;
import repository.ICleanerRepository;
import repository.ICleaningRepository;
import repository.IClientRespository;
import repository.IRoomRepository;
import utils.CustomIllegalArgument;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerController {
    private String password;
    private IRoomRepository roomRepo;
    private IClientRespository clientRepo;
    private ICleanerRepository cleanerRepo;
    private ICleaningRepository cleaningRepo;


    public ManagerController(IRoomRepository roomRepo, IClientRespository clientRepo, ICleanerRepository cleanerRepo,ICleaningRepository cleaningRepo, String password) {
        this.password = password;
        this.roomRepo = roomRepo;
        this.clientRepo = clientRepo;
        this.cleanerRepo = cleanerRepo;
        this.cleaningRepo = cleaningRepo;
    }

    // PASSWORD

    public boolean login(String password) {
        return password.equals(this.password);
    }
    public void changePassword(String password){
        this.password=password;
    }

    // CLIENT

    public List<Client> seeAllClients(){
        return clientRepo.getAll();
    }
    public Client findClientByUsername(String username) {
        return clientRepo.findByUsername(username);
    }
    public Client deleteClient(Integer id) {
        Client c = clientRepo.findById(id);
        if(c != null){
            clientRepo.delete(id);
        }
        return c;
    }


    // ROOM


    public List<Room> searchAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        List<Room> rooms = new ArrayList<>(roomRepo.getAll());
        List<Room> unavailableRooms = clientRepo.returnAllUnAvailableRooms(checkIn, checkOut);
        for (Room r : unavailableRooms )
        {
            rooms.remove(r);
        }
        return rooms;
    }
    public List<Room> seeAllRooms(){
        return roomRepo.getAll();
    }
    private boolean checkTypeRoom (Type type, int nrPers)
    {

        if (type.equals(Type.SINGLE) && nrPers == 1)
        {
            return  true;
        }
        else if (type.equals(Type.DOUBLE) && nrPers == 2)
        {
            return  true;
        }
        else if (type.equals(Type.TRIPLE) && nrPers == 3)
        {
            return  true;
        }
        else if (type.equals(Type.APARTMENT) && nrPers == 4)
        {
            return  true;
        }
        return false;
    }
    public void addRoom(Type type, double price, int nrPers) {
        if (checkTypeRoom(type,nrPers)) {
            roomRepo.add(new Room(type,price,nrPers));
        }
    }

    public Room deleteRoom(Integer id){
        Room r = roomRepo.findById(id);
        if(r != null ) {
            for (Cleaning cleaning : getRoomCleanings(id)) {
                cleaningRepo.deleteCleaning(cleaning);
            }
            for(Reservation res : this.findReservationsForRoom(id)){
                for(Client client : clientRepo.getAll()){
                    if(client.getReservationList().contains(res)){
                        clientRepo.removeReservation(res.getId(), client.getId());
                    }
                }
            }
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
            for (Cleaning cleaning : getCleanerCleanings(id))
            {
                cleaningRepo.deleteCleaning(cleaning);
            }
            cleanerRepo.delete(id);
            return c;
        }
        return null;
    }

    // CLEANING

    public List<Cleaning> getRoomCleanings(int roomId){
        return cleaningRepo.getCleaningsForRoom(roomId);
    }
    public List<Cleaning> getCleanerCleanings(int cleanerId){
        return cleaningRepo.getCleaningsForCleaner(cleanerId);
    }
    public List<Cleaning> getCleanings(){
        return cleaningRepo.getCleanings();
    }

    // RESERVATION

    public List<Reservation> seeAllReservations(){
        return clientRepo.getAllReservations();
    }

    public List<Reservation> findReservationsForRoom(int rid){
        List<Reservation> reservations = new ArrayList<>();
        Room r = roomRepo.findById(rid);
        for(Reservation res : this.seeAllReservations()){
            if(res.getRooms().contains(r)){
                reservations.add(res);
            }
        }
        return reservations;
    }

}
