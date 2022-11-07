package repository.inMemoryRepo;

import model.Reservation;
import repository.IReservationRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryReservationRepo implements IReservationRepository {
    List<Reservation> reservations;

    public InMemoryReservationRepo() {
        this.reservations = new ArrayList<Reservation>();
    }

    @Override
    public boolean add(Reservation reservation) {
        reservations.add(reservation);
        return true;
    }

    @Override
    public void delete(Integer id) {
        reservations.remove(reservations.get(id));
    }

    @Override
    public boolean update(Integer id, Reservation reservation) {
        Reservation oldRes = reservations.get(id);
        oldRes.setIdUser(reservation.getIdUser());
        oldRes.setStart(reservation.getStart());
        oldRes.setEnd(reservation.getEnd());
        return true;
    }

    @Override
    public Reservation findbyID(Integer id) {
        return reservations.get(id);
    }

    @Override
    public List<Reservation> getAll() {
        return reservations;
    }

}
