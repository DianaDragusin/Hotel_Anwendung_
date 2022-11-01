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
    public void add(Cleaner cleaner) {
        cleanerList.add(cleaner);
    }

    @Override
    public void delete(Integer id) {
        cleanerList.remove(cleanerList.get(id));
    }

    @Override
    public void update(Integer id, Cleaner cleaner) {
        Cleaner oldCleaner = cleanerList.get(id);
        oldCleaner.setFirstName(cleaner.getFirstName());
        oldCleaner.setLastName(cleaner.getLastName());
        oldCleaner.setUsername(cleaner.getUsername());
        oldCleaner.setPassword(cleaner.getPassword());
        oldCleaner.setSalary(cleaner.getSalary());
    }

    @Override
    public Cleaner findbyID(Integer id) {
        return cleanerList.get(id);
    }

    public List<Cleaner> seeAllCleaners(){
        return cleanerList;
    }

}
