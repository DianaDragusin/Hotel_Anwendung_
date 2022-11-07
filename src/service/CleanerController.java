package service;

import model.Cleaner;
import model.Coupon;
import repository.inMemoryRepo.InMemoryCleanerRepo;

import java.util.List;

public class CleanerController {
    private InMemoryCleanerRepo cleanerRepo;

    public CleanerController(InMemoryCleanerRepo cleanerRepo) {
        this.cleanerRepo = cleanerRepo;
    }

    public String register(String firstName, String lastName, String username, String password, int salary){
        if(cleanerRepo.add(new Cleaner(firstName,lastName,username,password,salary))){
            return "Cleaner registered successfully!";
        }
        return "Couldn't register cleaner!";
    }

    public boolean login(String username, String password){
        for( Cleaner c : cleanerRepo.getAll()){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public String changePassword(String username, String newPassword){
        cleanerRepo.findByUsername(username).setPassword(newPassword);
        return "Password changed successfully!";
    }
    public String changeDetails(String firstName, String lastName, String username, List<Coupon> coupons)
    {
        if (cleanerRepo.findByUsername(username)!=null) {
            cleanerRepo.findByUsername(username).setFirstName(firstName);
            cleanerRepo.findByUsername(username).setLastName(lastName);

        }
        return "Details changed succesfully";
    }


}
