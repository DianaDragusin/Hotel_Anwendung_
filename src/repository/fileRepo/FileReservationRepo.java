package repository.fileRepo;

import model.Reservation;
import model.Reservation_Room;
import model.Room;
import repository.IReservationRepository;

import java.time.LocalDate;
import java.util.List;

public class FileReservationRepo implements IReservationRepository {
    @Override
    public boolean add(Reservation reservation) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {

    }

    @Override
    public boolean update(Integer integer, Reservation reservation) {
        return false;
    }

    @Override
    public Reservation findbyID(Integer integer) {
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    public List<Integer> returnAllUnAvailableRooms(LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public List<Reservation_Room> GetAllReservedRooms() {
        return null;
    }

    @Override
    public void add(Reservation reservation, List<Room> rooms) {

    }
}
