package service;

import model.Cleaner;
import model.Cleaning;
import model.Room;
import model.Type;
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
       // populate_cleanings();
    }
    private void populate_cleanings(){
        Room rooms1 = roomRepo.findById(1);
        Room rooms2 =  roomRepo.findById(2);

        cleanRoom(1,rooms1.getId(),LocalDate.of(2022,12,12));
        cleanRoom(1,rooms2.getId(),LocalDate.of(2022,12,12));
        cleanRoom(2,9,LocalDate.of(2022,12,12));
        cleanRoom(2,4,LocalDate.of(2020,1,1));
        cleanRoom(2,4,LocalDate.of(2019,1,1));
        cleanRoom(2,3,LocalDate.of(2019,1,1));
        cleanRoom(3,4,LocalDate.of(2018,1,1));
        cleanRoom(4,5,LocalDate.of(2018,1,1));
        cleanRoom(5,6,LocalDate.of(2018,1,1));
        cleanRoom(5,6,LocalDate.of(2018,5,1));
        cleanRoom(4,5,LocalDate.of(2018,5,1));
        cleanRoom(3,4,LocalDate.of(2018,5,1));
        cleanRoom(3,9, LocalDate.of(2018,5,1));
        cleanRoom(3,10, LocalDate.of(2018,5,1));






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
