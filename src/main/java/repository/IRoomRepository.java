package repository;

import model.Room;
import model.Type;

import java.util.List;

public interface IRoomRepository extends ICrud<String, Room>{

      List<Room>typeRooms(Type t);
}
