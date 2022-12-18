package service;

import model.*;
import repository.ICleanerRepository;
import repository.ICleaningRepository;
import repository.IClientRespository;
import repository.IRoomRepository;
import repository.databaseRepo.*;
import utils.CustomIllegalArgument;
import views.ManagerView;

import java.util.List;
import java.util.Objects;

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

    public void login(String password) throws CustomIllegalArgument {
        if (!password.equals(this.password))
        {
            throw  new CustomIllegalArgument("Invalid password");
        }

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
    public void deleteClient(Integer id) throws  CustomIllegalArgument{
        Client C = clientRepo.findById(id);
        if(C!=null){
            clientRepo.delete(id);

        }
        else{
            throw  new CustomIllegalArgument("There is no client with this id in our database!");
        }
    }


    // ROOM


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
    public void addRoom(Type type, double price, int nrPers) throws  CustomIllegalArgument{

        if (checkTypeRoom(type,nrPers))
        {
            roomRepo.add(new Room(type,price,nrPers));
        }
        else throw  new CustomIllegalArgument("Type must be equal to the number of persons in meaning");

    }
    public Room deleteRoom(Integer id){
        Room r = roomRepo.findById(id);
        if(r != null )
        {
            for (Cleaning cleaning : getRoomCleanings(id))
            {
                cleaningRepo.deleteCleaning(cleaning);
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

}
