package service;

import model.*;
import repository.inMemoryRepo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    private final InMemoryClientRepo clientRepo;
    private final InMemoryRoomRepo roomRepo;
    private final InMemoryReservationRepo reservationRepo;
    private InMemoryCleanerRepo cleanerRepo;


    public ClientController(InMemoryClientRepo clientRepo, InMemoryRoomRepo roomRepo, InMemoryReservationRepo reservationRepo, InMemoryCleanerRepo cleanerRepo) {
        this.clientRepo = clientRepo;
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;
        this.cleanerRepo = cleanerRepo;

    }

    private List<Room> searchAvailableTypeRoom(LocalDate checkIn, LocalDate checkOut, Type t) {
        List<Room> rooms = roomRepo.returnRoomsOfType(t);
        List<Room> unavailableRooms = reservationRepo.returnAllUnAvailableRooms(checkIn, checkOut);

        for (Room room : unavailableRooms) {
            rooms.remove(room);
        }

        return rooms;
    }

    public List<Coupon> showCoupons(Integer id) {
        return clientRepo.findById(id).getCouponList();
    }

    public List<Option> generateOptions(LocalDate checkIn, LocalDate checkOut, int nrPers) {
        List<Room> apartmentsR = searchAvailableTypeRoom(checkIn, checkOut, Type.APARTMENT);
        List<Room> tripleR = searchAvailableTypeRoom(checkIn, checkOut, Type.TRIPLE);
        List<Room> doubleR = searchAvailableTypeRoom(checkIn, checkOut, Type.DOUBLE);
        List<Room> singleR = searchAvailableTypeRoom(checkIn, checkOut, Type.SINGLE);
        List<Room> rooms = new ArrayList<>();

        double totalPrice = 0;
        while (nrPers > 0) {
            if (!apartmentsR.isEmpty() && nrPers >= 4) {
                rooms.add(apartmentsR.get(0));
                totalPrice = totalPrice + apartmentsR.get(0).getPrice();
                apartmentsR.remove(apartmentsR.get(0));
                nrPers = nrPers - 4;
            } else if (!tripleR.isEmpty() && nrPers >= 3) {
                rooms.add(tripleR.get(0));
                totalPrice = totalPrice + tripleR.get(0).getPrice();
                tripleR.remove(tripleR.get(0));
                nrPers = nrPers - 3;
            } else if (!doubleR.isEmpty() && nrPers >= 2) {

                rooms.add(doubleR.get(0));
                totalPrice = totalPrice + doubleR.get(0).getPrice();
                doubleR.remove(doubleR.get(0));
                nrPers = nrPers - 2;
            } else if (!singleR.isEmpty()) {
                rooms.add(singleR.get(0));
                totalPrice = totalPrice + singleR.get(0).getPrice();
                singleR.remove(singleR.get(0));
                nrPers = nrPers - 1;
            } else {
                totalPrice = 0;
                rooms = new ArrayList<>();
            }

        }
        Option option = new Option(totalPrice, rooms);
        List<Option> options = new ArrayList<>();
        options.add(option);
        return options;
    }

    public String makeReservationWithCoupon(Option option, Coupon coupon, Integer clientId, LocalDate start, LocalDate end) {
        // work to do
        Reservation reservation = new Reservation(clientId, start, end, option.getTotalPrice());
        reservation.setPrice(applyCoupon(coupon, option.getTotalPrice()));
        reservation.setRooms(option.getRooms());
        reservationRepo.addReservation(reservation);

        return "Reservation created successfully";
    }

    public String makeReservation(Option option, Integer clientId, LocalDate start, LocalDate end) {
        // work to do
        Reservation reservation = new Reservation(clientId, start, end, option.getTotalPrice());
        reservation.setRooms(option.getRooms());
        reservationRepo.addReservation(reservation);

        return "Reservation created successfully";
    }

    private double applyCoupon(Coupon coupon, double price) {

        int per = (100 - coupon.getPercentage()) / 100;
        price = price * per;
        return price;

    }

    public String deleteReservation(Reservation reservation) {
        for (Reservation r : reservationRepo.getReservations()) {
            if (r == reservation) {
                reservationRepo.deleteReservation(reservation.getId());
                return "Reservation deleted successfully";
            }
        }

        return "Reservation not found";


    }

    public List<Reservation> seeAllReservations(int id) {

        return reservationRepo.GetAllReservationsForAUser(id);

    }

    public List<Room> seeAllReservedRooms(int id) {
        return reservationRepo.GetAllReservedRoomsForAUser(id);
    }

    public boolean register(String firstName, String lastName, String username, String password) {
        Client c = clientRepo.findByUsername(username);
        if (c == null) {
            clientRepo.add(new Client(firstName, lastName, username, password));
            return true;
        } else return false;
    }

    public String changeDetails(String newfirstName, String newlastName, int id) {
        Client client = clientRepo.findById(id);
        if (client != null) {
            Client c = new Client(newfirstName, newlastName, client.getUsername(), client.getPassword());
            c.setCouponList(client.getCouponList());
            clientRepo.update(id, c);
            return "Details changed successfully";

        } else return "Invalid user, the details were not changed";

    }

    public boolean login(String username, String password) {
        for (Client c : clientRepo.getAll()) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String changePassword(int id, String newPassword) {
        clientRepo.findById(id).setPassword(newPassword);
        return "Password changed successfully!";
    }

    public boolean findUser(String username) {
        Client c = clientRepo.findByUsername(username);
        return c != null;

    }

    public boolean addCoupon(Coupon c, int client_id)
    {
        clientRepo.addCoupon(c,client_id);
        return true;
    }
}
