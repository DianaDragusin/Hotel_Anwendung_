package repository.inMemoryRepo;

import model.Cleaner;
import model.Room;
import repository.ICleanerRepository;

import java.util.List;

public class InMemoryCleanerRepo implements ICleanerRepository {
    private List<Cleaner> cleanerList;

    public InMemoryCleanerRepo(List<Cleaner> cleanerList) {
        this.cleanerList = cleanerList;
    }



    @Override
    public boolean add(Cleaner cleaner) {
        cleanerList.add(cleaner);
        return true;
    }

    @Override
    public void delete(Integer id) {
        cleanerList.remove(cleanerList.get(id));
    }

    @Override
    public boolean update(Integer id, Cleaner cleaner) {
        Cleaner oldCleaner = cleanerList.get(id);
        oldCleaner.setFirstName(cleaner.getFirstName());
        oldCleaner.setLastName(cleaner.getLastName());
        oldCleaner.setUsername(cleaner.getUsername());
        oldCleaner.setPassword(cleaner.getPassword());
        oldCleaner.setSalary(cleaner.getSalary());
        return true;
    }

    @Override
    public Cleaner findbyID(Integer id) {
        return cleanerList.get(id);
    }

    @Override
    public List<Cleaner> getAll() {
        return cleanerList;
    }

    public List<Cleaner> seeAllCleaners(){
        return cleanerList;
    }

}
