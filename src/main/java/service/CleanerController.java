package service;

import model.Cleaner;
import model.Cleaning;
import model.Room;
import repository.inMemoryRepo.InMemoryCleanerRepo;
import repository.inMemoryRepo.InMemoryCleaningRepo;
import repository.inMemoryRepo.InMemoryRoomRepo;

import java.time.LocalDate;
import java.util.List;

public class CleanerController {
    private InMemoryCleanerRepo cleanerRepo;
    private InMemoryRoomRepo roomRepo;
    private InMemoryCleaningRepo cleaningRepo;

    public CleanerController(InMemoryCleanerRepo cleanerRepo, InMemoryRoomRepo roomRepo,InMemoryCleaningRepo cleaningRepo) {
        this.cleanerRepo = cleanerRepo;
        this.roomRepo = roomRepo;
        this.cleaningRepo = cleaningRepo;
    }

    public boolean register(String firstName, String lastName, String username, String password){
        Cleaner c  = cleanerRepo.findByUsername(username);
        if (c == null){
            cleanerRepo.add(new Cleaner(firstName,lastName,username,password));
            return true;
        }
        return false;
    }

    public boolean login(String username, String password){
        for( Cleaner c : cleanerRepo.getAll()){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean changePassword(Integer id, String newPassword){
        if (cleanerRepo.findById(id)!= null)
        {
            cleanerRepo.findById(id).setPassword(newPassword);
            return true;
        }

        return false;
    }
    public boolean changeDetails(String firstName, String lastName, Integer id)
    {
        if (cleanerRepo.findById(id)!=null) {
            cleanerRepo.findById(id).setFirstName(firstName);
            cleanerRepo.findById(id).setLastName(lastName);
           return  true;
        }
        return  false;
    }
    public Cleaning cleanRoom(int cleanerId, int roomId, LocalDate date)
    {
        Cleaner c  = cleanerRepo.findById(cleanerId);
        Room r  = roomRepo.findById(roomId);
        Cleaning cleaning = new Cleaning(c,r,date);
        cleaningRepo.addCleaning(cleaning);
        return cleaning;
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
