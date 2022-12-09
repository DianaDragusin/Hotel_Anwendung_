package repository.databaseRepo;

import model.Cleaner;
import repository.ICleanerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class databaseCleanerRepo implements ICleanerRepository {
    private List<Cleaner> cleaners;
    private int id_cleaner;
    EntityManagerFactory factory;
    EntityManager manager;

    public databaseCleanerRepo(List<Cleaner> cleanerList) {
        this.cleaners =  new ArrayList<>();
        populate_cleaners();
        factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
    }
    private void populate_cleaners(){

        Cleaner cleaner1 = new Cleaner("Andu", "Andreescu","anduandre","1234");
        Cleaner cleaner2 = new Cleaner("Laura", "Halmaciu","lauramaciu","2222");
        Cleaner cleaner3 = new Cleaner("Catalina", "Vasiu","catasiu","24siu");
        manager.persist(cleaner1);
        manager.persist(cleaner2);
        manager.persist(cleaner3);
    }
    @Override
    public void add(Cleaner cleaner) {
        manager.persist(cleaner);
        manager.getTransaction().commit();
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
