package repository.fileRepo;

import model.Cleaner;
import repository.ICleanerRepository;

import java.util.List;

public class FileCleanerRepo implements ICleanerRepository {
    @Override
    public List<Cleaner> seeAllCleaners() {
        return null;
    }

    @Override
    public boolean add(Cleaner cleaner) {
        return false;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public boolean update(Integer integer, Cleaner cleaner) {
        return false;
    }

    @Override
    public Cleaner findbyID(Integer integer) {
        return null;
    }

    @Override
    public List<Cleaner> getAll() {
        return null;
    }
}
