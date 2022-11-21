package repository;

import model.Room;
import model.Type;

import java.util.List;

public interface IRoomRepository extends ICrud<String, Room>{
      public String generateRoomId(Type type);
      List<Room> returnRoomsOfType(Type t);
}
