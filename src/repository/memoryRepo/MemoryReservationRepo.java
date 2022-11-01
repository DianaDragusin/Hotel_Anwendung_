package repository.memoryRepo;

import model.Reservation;
import repository.ICrud;

import java.util.ArrayList;
import java.util.List;

public class MemoryReservationRepo implements ICrud<Integer, Reservation> {
    List<Reservation> reservations;

    public MemoryReservationRepo() {
        this.reservations = new ArrayList<Reservation>();
    }

    @Override
    public void add(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public void delete(Integer id) {
        reservations.remove(reservations.get(id));
    }

    @Override
    public void update(Integer id, Reservation reservation) {
        reservations.get(id).setIdClient(reservation.getIdClient());
        reservations.get(id).setStart(reservation.getStart());
        reservations.get(id).setEnd(reservation.getEnd());
    }

    @Override
    public Reservation findbyID(Integer id) {
        return reservations.get(id);
    }
}
