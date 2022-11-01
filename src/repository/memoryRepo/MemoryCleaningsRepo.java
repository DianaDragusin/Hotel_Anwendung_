package repository.memoryRepo;

import model.Cleaner;
import model.Cleaning;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public class MemoryCleaningsRepo {
    private List<Cleaning> cleaningsList;

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
