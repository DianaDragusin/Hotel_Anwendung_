package service;

import model.Cleaner;
import model.Cleaning;
import model.Coupon;
import model.Room;
import repository.inMemoryRepo.InMemoryCleanerRepo;
import repository.inMemoryRepo.InMemoryCleaningsRepo;
import repository.inMemoryRepo.InMemoryRoomRepo;
import views.CleanerView;

import java.time.LocalDate;
import java.util.List;

public class CleanerController {
    private InMemoryCleanerRepo cleanerRepo;
    private InMemoryCleaningsRepo cleaningsRepo;
    private InMemoryRoomRepo roomRepo;

    public CleanerController(InMemoryCleanerRepo cleanerRepo, InMemoryCleaningsRepo cleaningsRepo, InMemoryRoomRepo roomRepo) {
        this.cleanerRepo = cleanerRepo;
        this.cleaningsRepo = cleaningsRepo;
        this.roomRepo = roomRepo;
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
    public boolean clean_room(Integer id,Integer room, LocalDate date)
    {
        Cleaner c  = cleanerRepo.findById(id);
        Room r  = roomRepo.findById(room);
        // trebuie  implementat o metoda de a verifica camera data spfre curatare
        //camera trebuie sa nu fi fost cuarata si sa fie in lista de rezervari viitoare
        if (c!=null) {
            cleaningsRepo.add(new Cleaning(r,c,date));
            return  true;
        }
        return  false;


    }
    public List<Room> roomsToClean()
    {
        return roomRepo.getAll();
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
}
