package repository.fileRepo;

import model.Reservation;
import model.Reservation_Room;
import model.Room;
import repository.IReservationRepository;

import java.time.LocalDate;
import java.util.List;

public class FileReservationRepo implements IReservationRepository {

    @Override
    public List<Integer> returnAllUnAvailableRooms(LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public List<Integer> GetAllReservedRoomsForAUser(String username) {
        return null;
    }

    @Override
    public List<Reservation> GetAllReservationsForAUser(String username) {
        return null;
    }

    @Override
    public void addReservation(Reservation reservation, List<Room> rooms) {

    }

    @Override
    public boolean deleteReservation(String id) {
        return false;
    }

}
