package service;

import model.Cleaner;
import model.Coupon;
import repository.inMemoryRepo.InMemoryCleanerRepo;
import views.CleanerView;

import java.util.List;

public class CleanerController {
    private InMemoryCleanerRepo cleanerRepo;


    public CleanerController(InMemoryCleanerRepo cleanerRepo) {
        this.cleanerRepo = cleanerRepo;
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
}
