package repository;

import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IReservationRepository extends ICrud<Integer, Reservation> {
      List<Integer> returnAllUnAvailableRooms(LocalDate start, LocalDate end);
}
