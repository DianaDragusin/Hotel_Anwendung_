package repository;

import model.Reservation;
import model.Reservation_Room;
import model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IReservationRepository  {
      List<Integer> returnAllUnAvailableRooms(LocalDate start, LocalDate end);
      List<Integer>  GetAllReservedRoomsForAUser(String username);
      List<Reservation>  GetAllReservationsForAUser(String username);
      void addReservation(Reservation reservation, List<Room> rooms);
      boolean deleteReservation (Integer id);
     // returntype la getall
     // void getAll();

}
