package repository.fileRepo;

import model.Cleaner;
import repository.ICleanerRepository;

import java.util.List;

public class FileCleanerRepo implements ICleanerRepository {
    @Override
    public boolean add(Cleaner cleaner) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
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

    @Override
    public Cleaner findByUsername(String username) {
        return null;
    }
}
