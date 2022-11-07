package repository.fileRepo;

import model.Reservation;
import repository.IReservationRepository;

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
}
