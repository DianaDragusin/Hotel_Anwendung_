package repository.inMemoryRepo;

import model.Cleaner;
import model.Client;
import repository.ICleanerRepository;

import java.util.List;

public class InMemoryCleanerRepo implements ICleanerRepository {
    private List<Cleaner> cleaners;

    public InMemoryCleanerRepo(List<Cleaner> cleanerList) {
        this.cleaners = cleanerList;
    }

    @Override
    public boolean add(Cleaner cleaner) {
        //uberprufe, dass der username unique bleibt
        if(cleaner_exists(cleaner)){
            return false;
        }
        this.cleaners.add(cleaner);
        return true;
    }

    private boolean cleaner_exists(Cleaner cleaner){
        for(Cleaner u : cleaners){
            if(u.getUsername().equals(cleaner.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if(findbyID(id)!=null){
            cleaners.remove(cleaners.get(id));
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Integer id, Cleaner cleaner) {
        Cleaner oldCleaner = cleaners.get(id);
        oldCleaner.setFirstName(cleaner.getFirstName());
        oldCleaner.setLastName(cleaner.getLastName());
        oldCleaner.setUsername(cleaner.getUsername());
        oldCleaner.setPassword(cleaner.getPassword());
        oldCleaner.setSalary(cleaner.getSalary());
        return true;
    }

    @Override
    public Cleaner findbyID(Integer id) {
        return cleaners.get(id);
    }

    @Override
    public List<Cleaner> getAll() {
        return cleaners;
    }

    @Override
    public Cleaner findByUsername(String username) {
        for(Cleaner c : cleaners){
            if(c.getUsername().equals(username)){
                return c;
            }
        }
        return null;
    }

}
