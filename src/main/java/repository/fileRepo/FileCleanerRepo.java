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
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(String s, Cleaner cleaner) {
        return false;
    }

    @Override
    public Cleaner findbyusername(String s) {
        return null;
    }

    @Override
    public List<Cleaner> getAll() {
        return null;
    }

}
