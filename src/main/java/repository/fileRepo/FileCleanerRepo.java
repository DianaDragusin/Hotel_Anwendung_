package repository.fileRepo;

import model.Cleaner;
import repository.ICleanerRepository;

import java.util.List;

public class FileCleanerRepo implements ICleanerRepository {
    @Override
    public void add(Cleaner cleaner) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id, Cleaner cleaner) {

    }

    @Override
    public Cleaner findByUsername(String username) {
        return null;
    }
    @Override
    public Cleaner findById(Integer id){return null;}

    @Override
    public List<Cleaner> getAll() {
        return null;
    }

}
