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
    public void add(Cleaner cleaner) {
        //uberprufe, dass der username unique bleibt
        this.cleaners.add(cleaner);

    }

    private String getCleanerUsername(Cleaner cleaner){
        for(Cleaner u : cleaners){
            if (u.equals(cleaner))
            {
                return u.getUsername();
            }

        }
        return null;
    }

    @Override
    public void delete(Integer id) {

            cleaners.remove(findById(id));

    }

    @Override
    public void update(Integer id, Cleaner cleaner) {
        Cleaner oldCleaner = findById(id);
        oldCleaner.setFirstName(cleaner.getFirstName());
        oldCleaner.setLastName(cleaner.getLastName());
        oldCleaner.setUsername(cleaner.getUsername());
        oldCleaner.setPassword(cleaner.getPassword());
        //oldCleaner.setSalary(cleaner.getSalary());

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
    public Cleaner findById(Integer id){
        for(Cleaner c : cleaners){
            if(c.getId()==id){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Cleaner> getAll() {
        return cleaners;
    }


}
