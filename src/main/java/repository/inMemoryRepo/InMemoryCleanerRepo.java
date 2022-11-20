package repository.inMemoryRepo;

import model.Cleaner;
import model.Client;
import repository.ICleanerRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCleanerRepo implements ICleanerRepository {
    private List<Cleaner> cleaners;

    public InMemoryCleanerRepo(List<Cleaner> cleanerList) {
        this.cleaners = populate_cleaners();
    }
    private List<Cleaner> populate_cleaners(){
        List<Cleaner> cleaners = new ArrayList<>();
        Cleaner cleaner1 = new Cleaner("Andu", "Andreescu","anduandre","1234");
        Cleaner cleaner2 = new Cleaner("Laura", "Halmaciu","lauramaciu","2222");
        Cleaner cleaner3 = new Cleaner("Catalina", "Vasiu","catasiu","24siu");
        cleaners.add(cleaner1);
        cleaners.add(cleaner2);
        cleaners.add(cleaner3);
        return cleaners;
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
        //oldCleaner.setSalary(cleaner.getSalary());
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
