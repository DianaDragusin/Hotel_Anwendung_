package service;

import model.Cleaner;
import model.Cleaning;
import model.Room;
import repository.ICleanerRepository;
import repository.ICleaningRepository;
import repository.IRoomRepository;

import java.time.LocalDate;
import java.util.List;

public class CleanerController {
    private ICleanerRepository cleanerRepo;
    private IRoomRepository roomRepo;
    private ICleaningRepository cleaningRepo;

    public CleanerController(ICleanerRepository cleanerRepo, IRoomRepository roomRepo, ICleaningRepository cleaningRepo) {
        this.cleanerRepo = cleanerRepo;
        this.roomRepo = roomRepo;
        this.cleaningRepo = cleaningRepo;
    }

    public Cleaner register(String firstName, String lastName, String username, String password){
        if (cleanerRepo.findByUsername(username) == null){
            Cleaner c = new Cleaner(firstName,lastName,username,password);
            cleanerRepo.add(c);
            return c;
        }
        return null;
    }

    public Cleaner login(String username, String password){
        for( Cleaner c : cleanerRepo.getAll()){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return c;
            }
        }
        return null;
    }

    public void changePassword(Integer id, String newPassword){
        if (cleanerRepo.findById(id)!= null)
        {
            cleanerRepo.findById(id).setPassword(newPassword);
        }
    }
    public void changeDetails(String firstName, String lastName, Integer id)
    {
        if (cleanerRepo.findById(id)!=null) {
            cleanerRepo.findById(id).setFirstName(firstName);
            cleanerRepo.findById(id).setLastName(lastName);
        }
    }
    public void cleanRoom(int cleanerId, int roomId, LocalDate date)
    {
        Cleaner c  = cleanerRepo.findById(cleanerId);
        Room r  = roomRepo.findById(roomId);
        Cleaning cleaning = new Cleaning(c,r,date);
        cleaningRepo.addCleaning(cleaning);
    }
    public Cleaner findUserByUsername(String username)
    {
        for(Cleaner c : cleanerRepo.getAll())
        {
            if(c.getUsername().equals(username))
            {
                return c;
            }
        }
        return null;
    }
    public List<Cleaning> getPersonalCleanings(int cleanerId){
        return cleaningRepo.getCleaningsForCleaner(cleanerId);
    }
    public List<Room> getRooms(){
        return roomRepo.getAll();
    }
}
