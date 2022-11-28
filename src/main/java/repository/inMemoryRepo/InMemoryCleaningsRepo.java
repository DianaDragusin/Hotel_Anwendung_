package repository.inMemoryRepo;

import model.Cleaner;
import model.Cleaning;
import model.Room;
import repository.ICleaningsrepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryCleaningsRepo implements ICleaningsrepository {
    private List<Cleaning> cleaningsList;

    public InMemoryCleaningsRepo(List<Cleaning> cleaningsList) {
        this.cleaningsList = cleaningsList;
    }

    public void add(Cleaning cleaning){
        cleaningsList.add(cleaning);
    }

    public List<Cleaning> seeAllCleanings(){
        return cleaningsList;
    }

    public List<Cleaning> seeAllCleaningsForRoom(Room room){
        List<Cleaning> cleanings=new ArrayList<>();
        for(Cleaning c:cleaningsList){
            if(c.getRoom()==room){
                cleanings.add(c);
            }
        }
        return cleanings;
    }
    public List<Cleaning> seeAllCleaningsForCleaner(Cleaner cleaner){
        List<Cleaning> cleanings=new ArrayList<>();
        for(Cleaning c:cleaningsList){
            if(c.getCleaner()==cleaner){
                cleanings.add(c);
            }
        }
        return cleanings;
    }
}
