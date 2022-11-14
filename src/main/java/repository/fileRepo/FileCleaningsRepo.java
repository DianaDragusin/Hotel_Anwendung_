package repository.fileRepo;

import model.Cleaner;
import model.Cleaning;
import model.Room;
import repository.ICleaningsrepository;

import java.time.LocalDate;
import java.util.List;

public class FileCleaningsRepo implements ICleaningsrepository {
    @Override
    public void cleanRoom(Cleaner cleaner, Room room, LocalDate date) {

    }

    @Override
    public List<Cleaning> seeAllCleanings() {
        return null;
    }

    @Override
    public List<Cleaning> seeAllCleaningsForRoom(Room room) {
        return null;
    }

    @Override
    public List<Cleaning> seeAllCleaningsForCleaner(Cleaner cleaner) {
        return null;
    }
}
