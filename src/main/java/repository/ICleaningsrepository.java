package repository;

import model.Cleaner;
import model.Cleaning;
import model.Room;

import java.time.LocalDate;
import java.util.List;

public interface ICleaningsrepository {
    void cleanRoom(Cleaner cleaner, Room room, LocalDate date);
    List<Cleaning> seeAllCleanings();
    List<Cleaning> seeAllCleaningsForRoom(Room room);
    List<Cleaning> seeAllCleaningsForCleaner(Cleaner cleaner);
    }