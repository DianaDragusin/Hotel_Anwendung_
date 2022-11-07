package repository;

import model.Reservation;
import model.Reservation_Room;
import model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IReservationRepository extends ICrud<Integer, Reservation> {
      List<Integer> returnAllUnAvailableRooms(LocalDate start, LocalDate end);
      List<Reservation_Room>  GetAllReservedRooms();
      void add(Reservation reservation, List<Room> rooms);
}
