package repository;

import model.Cleaner;
import model.Cleaning;
import model.Room;

import java.time.LocalDate;
import java.util.List;

public interface ICleaningsrepository {

    List<Cleaning> seeAllCleanings();
    List<Cleaning> seeAllCleaningsForRoom(Room room);
    List<Cleaning> seeAllCleaningsForCleaner(Cleaner cleaner);
    }
