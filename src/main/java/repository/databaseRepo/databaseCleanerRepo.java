package repository.databaseRepo;

import model.Cleaner;
import model.Cleaning;
import model.Client;
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
        manager.getTransaction().begin();

        Cleaner cleaner1 = new Cleaner("Andu", "Andreescu","anduandre","1234");
        Cleaner cleaner2 = new Cleaner("Laura", "Halmaciu","lauramaciu","2222");
        Cleaner cleaner3 = new Cleaner("Catalina", "Vasiu","catasiu","24siu");
        manager.persist(cleaner1);
        manager.persist(cleaner2);
        manager.persist(cleaner3);
        manager.getTransaction().commit();
    }
    @Override
    public void add(Cleaner cleaner) {
        manager.getTransaction().begin();
        manager.persist(cleaner);
        manager.getTransaction().commit();
    }
    @Override
    public void delete(Integer cleanerId) {
        manager.getTransaction().begin();
        Cleaner cl =  manager.find(Cleaner.class,cleanerId);
        manager.remove(cl);
        manager.getTransaction().commit();
        /*
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("DELETE FROM Cleaner WHERE id=:idCleaner",Cleaner.class);
        query.setParameter("idCleaner", Integer.toString(cleanerId));
        query.executeUpdate();
        manager.getTransaction().commit();

         */
    }

    @Override
    public void update(Integer id, Cleaner cleaner) {
        //manager.getTransaction().begin();
        Cleaner cl =  manager.find(Cleaner.class,id);
        cl.setFirstName(cleaner.getFirstName());
        cl.setLastName(cleaner.getFirstName());
        cl.setUsername(cleaner.getUsername());
        cl.setPassword(cleaner.getPassword());
        cl.setSalary(cleaner.getSalary());
        manager.merge(cl);
       // manager.getTransaction().commit();
        /*
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("UPDATE Cleaner SET firstname=:clFN, lastname=:clLN, username=:clU, password=:clP WHERE id=:clId",Cleaner.class);
        query.setParameter("clFN", cleaner.getFirstName());
        query.setParameter("clLN", cleaner.getLastName());
        query.setParameter("clU", cleaner.getUsername());
        query.setParameter("clP", cleaner.getPassword());
        query.setParameter("clId", Integer.toString(id));
        query.executeUpdate();
        manager.getTransaction().commit();

         */
    }

    @Override
    public Cleaner findByUsername(String username) {
        Cleaner c ;
        try
        {
            //manager.getTransaction().begin();
            Query query = manager.createNativeQuery("SELECT * FROM Cleaner WHERE username=:clU",Cleaner.class);
            query.setParameter("clU", username);
            c = (Cleaner) query.getSingleResult();
          //  manager.getTransaction().commit();
            return c;

        }catch (Exception exception)
        {
            return null;
        }


    }
    public Cleaner findById(Integer id){
        Cleaner c ;
       // manager.getTransaction().begin();
         c  = manager.find(Cleaner.class, id);
       // manager.getTransaction().commit();
        return c;
        /*
        try {
            Cleaner c  = null;
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("SELECT * FROM Cleaner WHERE id=:clId",Cleaner.class);
            query.setParameter("clId", Integer.toString(id));
            c=  (Cleaner) query.getSingleResult();
            manager.getTransaction().commit();
            return c;
        }

        return c;

         */
    }

    @Override
    public List<Cleaner> getAll() {
        List<Cleaner> cleaners;
        try
        {
           // manager.getTransaction().begin();
            Query query = manager.createNativeQuery("SELECT * FROM Cleaner",Cleaner.class);
            cleaners =  (List<Cleaner>) query.getResultList();
           // manager.getTransaction().commit();
            return cleaners;
        }catch (Exception exception)
        {
            return new ArrayList<>();
        }

    }

}
