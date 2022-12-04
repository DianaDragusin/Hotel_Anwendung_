package repository.inMemoryRepo;

import model.Reservation;
import model.Room;
import repository.IReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryReservationRepo implements IReservationRepository {
    List<Reservation> reservations;

    public InMemoryReservationRepo(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public List<Reservation>getReservations()
    {
        return reservations;
    }

    @Override
    public boolean deleteReservation(int id) {
        Reservation reservation = this.findReservationById(id);
        if(reservation != null) {
            reservations.remove(reservation);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Reservation findReservationById(int id) {
        for(Reservation r : reservations){
            if(r.getId() == id){
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Room> returnAllUnAvailableRooms(LocalDate start, LocalDate end) {
        List<Room> rooms = new ArrayList<>();
        for (Reservation reservation : reservations)
        {

          /*  if ((reservation.getStart() == start && reservation.getEnd() == end ) ||
                    (reservation.getStart().isBefore(start) && reservation.getEnd().isBefore(end) ) ||
                    (reservation.getStart().isAfter(start) && reservation.getEnd().isAfter(end) ) ||
                    (reservation.getStart().isBefore(start) && reservation.getEnd().isAfter(end) ) )

          */

            if ((reservation.getStart().isAfter(start) && reservation.getStart().isBefore(end)) ||
                    (reservation.getStart() == start) ||
                    (reservation.getEnd().isAfter(start) && reservation.getEnd().isBefore(end)) ||
                    (reservation.getEnd() == end) ||
                    (reservation.getStart().isBefore(start) && reservation.getEnd().isAfter(end)))
            {
                rooms.addAll(reservation.getRooms());

            }

        }

        return rooms;
    }

    @Override
    public List<Room> GetAllReservedRoomsForAUser(int id) {
        List<Room> reservedRooms = new ArrayList<>();

        for(Reservation res : reservations)
        {
            if (res.getClientId()==id)
            {
                reservedRooms.addAll(res.getRooms());
            }
        }
        return reservedRooms;
    }

    @Override
    public List<Reservation> GetAllReservationsForAUser(int id) {
        List<Reservation> userReservations = new ArrayList<>();
       for(Reservation res : reservations)
       {
           if (res.getClientId() == id)
           {
               userReservations.add(res);
           }
       }
       return userReservations;
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);

    }
}
