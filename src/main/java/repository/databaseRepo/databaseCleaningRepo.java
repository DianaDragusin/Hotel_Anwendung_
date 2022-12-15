package repository.databaseRepo;

import model.Cleaner;
import model.Cleaning;
import repository.ICleaningRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class databaseCleaningRepo implements ICleaningRepository {

    EntityManager manager;

    public databaseCleaningRepo(EntityManager manager) {
        this.manager = manager;
    }

    public List<Cleaning> getCleanings() {
        Query query = manager.createNativeQuery("SELECT * FROM Cleaning", Cleaning.class);
        return (List<Cleaning>) query.getResultList();
    }

    @Override
    public String toString() {
        return "InMemoryCleaningRepo{" +
                "cleanings=" + getCleanings() +
                '}';
    }

    @Override
    public List<Cleaning> getCleaningsForRoom(int roomId) {
        List<Cleaning> roomCleanings = new ArrayList<>();
        for(Cleaning c : getCleanings()){
            if(c.getRoom().getId() == roomId){
                roomCleanings.add(c);
            }
        }
        return roomCleanings;
    }

    @Override
    public List<Cleaning> getCleaningsForCleaner(int cleanerId) {
        List<Cleaning> roomCleanings = new ArrayList<>();
        for(Cleaning c : getCleanings()){
            if(c.getCleaner().getId() == cleanerId){
                roomCleanings.add(c);
            }
        }
        return roomCleanings;
    }
    public void addCleaning(Cleaning cleaning){
        manager.persist(cleaning);
        manager.getTransaction().commit();
    }
    public void removeCleaning(Cleaning cleaning){
        Query query = manager.createNativeQuery("DELETE FROM Cleaning WHERE id=:idCleaning",Cleaning.class);
        query.setParameter("idCleaning", Integer.toString(cleaning.getId()));
        query.executeUpdate();
        manager.getTransaction().commit();
    }
}
