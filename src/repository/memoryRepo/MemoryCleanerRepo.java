package repository.memoryRepo;

import model.Cleaner;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public class MemoryCleanerRepo {
    private List<Cleaner> cleanerList;

    public MemoryCleanerRepo(List<Cleaner> cleanerList) {
        this.cleanerList = cleanerList;
    }

    public void cleanRoom(Cleaner cleaner, Room room){
        cleaner.cleanRoom(room);
    }
}
