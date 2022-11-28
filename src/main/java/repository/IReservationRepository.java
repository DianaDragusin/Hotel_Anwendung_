package repository;

import model.Reservation;
import model.Reservation_Room;
import model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IReservationRepository  {
      List<Integer> returnAllUnAvailableRooms(LocalDate start, LocalDate end);
      List<Integer>  GetAllReservedRoomsForAUser(int id);
      List<Reservation>  GetAllReservationsForAUser(int id);
      void addReservation(Reservation reservation, List<Room> rooms);
      boolean deleteReservation (int id);
     // returntype la getall
     // void getAll();

      Reservation findReservationById(int id);

}
