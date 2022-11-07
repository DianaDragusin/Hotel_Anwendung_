package repository.inMemoryRepo;

import model.Reservation;
import model.Reservation_Room;
import model.Room;
import repository.IReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryReservationRepo implements IReservationRepository {
    List<Reservation> reservations;
    List<Reservation_Room> reservations_rooms ;

    public InMemoryReservationRepo() {
        this.reservations = new ArrayList<Reservation>();
    }

    @Override
    public boolean add(Reservation reservation) {
        reservations.add(reservation);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
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

    @Override
    public List<Integer> returnAllUnAvailableRooms(LocalDate start, LocalDate end) {
        List<Integer> rooms = new ArrayList<>();
        for (Reservation reservation : reservations)
        {
            if ((reservation.getStart() == start && reservation.getEnd() == end) || (reservation.getStart().isBefore(start) && reservation.getEnd().isBefore(end) ) || (reservation.getStart().isAfter(start) && reservation.getEnd().isAfter(end) ) || (reservation.getStart().isBefore(start) && reservation.getEnd().isAfter(end) ) )
            {
                for (Reservation_Room line : reservations_rooms)
                {
                    if (line.getReservation() == reservation.getId())
                    {
                        if (!rooms.contains(line.getRoom()) )
                        {
                            rooms.add(line.getRoom());
                        }
                    }
                }
            }

        }
        return rooms;
    }
}
