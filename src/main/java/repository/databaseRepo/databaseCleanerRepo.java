package repository.databaseRepo;

import model.Cleaner;
import repository.ICleanerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class databaseCleanerRepo implements ICleanerRepository {
    EntityManager manager;

    public databaseCleanerRepo(EntityManager manager) {
        this.manager = manager;
        populate_cleaners();
    }
    private void populate_cleaners(){

        Cleaner cleaner1 = new Cleaner("Andu", "Andreescu","anduandre","1234");
        Cleaner cleaner2 = new Cleaner("Laura", "Halmaciu","lauramaciu","2222");
        Cleaner cleaner3 = new Cleaner("Catalina", "Vasiu","catasiu","24siu");
        manager.persist(cleaner1);
        manager.persist(cleaner2);
        manager.persist(cleaner3);
       // manager.getTransaction().commit();
    }
    @Override
    public void add(Cleaner cleaner) {
        manager.persist(cleaner);
       // manager.getTransaction().commit();
    }
    @Override
    public void delete(Integer cleanerId) {
        Query query = manager.createNativeQuery("DELETE FROM Cleaner WHERE id=:idCleaner",Cleaner.class);
        query.setParameter("idCleaner", Integer.toString(cleanerId));
        query.executeUpdate();
       // manager.getTransaction().commit();
    }

    @Override
    public void update(Integer id, Cleaner cleaner) {
        Query query = manager.createNativeQuery("UPDATE Cleaner SET firstname=:clFN, lastname=:clLN, username=:clU, password=:clP WHERE id=:clId",Cleaner.class);
        query.setParameter("clFN", cleaner.getFirstName());
        query.setParameter("clLN", cleaner.getLastName());
        query.setParameter("clU", cleaner.getUsername());
        query.setParameter("clP", cleaner.getPassword());
        query.setParameter("clId", Integer.toString(id));
        query.executeUpdate();
      //  manager.getTransaction().commit();
    }

    @Override
    public Cleaner findByUsername(String username) {
        Query query = manager.createNativeQuery("SELECT * FROM Cleaner WHERE username=:clU",Cleaner.class);
        query.setParameter("clU", username);
        return (Cleaner) query.getSingleResult();
    }
    public Cleaner findById(Integer id){
        Query query = manager.createNativeQuery("SELECT * FROM Cleaner WHERE id=:clId",Cleaner.class);
        query.setParameter("clId", Integer.toString(id));
        return (Cleaner) query.getSingleResult();
    }

    @Override
    public List<Cleaner> getAll() {
        Query query = manager.createNativeQuery("SELECT * FROM Cleaner",Cleaner.class);
        return (List<Cleaner>) query.getResultList();
    }

}
