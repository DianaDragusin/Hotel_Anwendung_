package repository;

import model.Reservation;
import model.Reservation_Room;
import model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IReservationRepository  {
      List<String> returnAllUnAvailableRooms(LocalDate start, LocalDate end);
      List<String>  GetAllReservedRoomsForAUser(String username);
      List<Reservation>  GetAllReservationsForAUser(String username);
      void addReservation(Reservation reservation, List<Room> rooms);
      boolean deleteReservation (String id);
     // returntype la getall
     // void getAll();

      Reservation findReservationById(String id);

}
