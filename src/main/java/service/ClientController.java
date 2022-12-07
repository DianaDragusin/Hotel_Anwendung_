package service;

import model.*;
import repository.inMemoryRepo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private List<Room> searchAvailableRoom(LocalDate checkIn, LocalDate checkOut) {
        List<Room> rooms = roomRepo.getAll();
        List<Room> unavailableRooms = reservationRepo.returnAllUnAvailableRooms(checkIn, checkOut);

        for (Room room : unavailableRooms) {
            rooms.remove(room);
        }

        return rooms;
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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ALGORITM GENERARE OPTIUNI

    // functie generare combinatii de n cate r
    // returneaza o lista de array-uri = combinatii
    public List<int[]> generateCombinations(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }
        while (combination[r - 1] < n) {
            combinations.add(combination.clone());
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }
        return combinations;
    }

    // scrie combinatiile de numere ca si combinatii ale unor elemente dintr-o lista de nrPersons
    public List<int[]> transform_combinations(int n, int k, List<Integer> nrPersons){
        List<int[]> list = generateCombinations(n,k);
        int[] room_comb = new int[k];
        List<int[]> room_combs = new ArrayList<>();
        for(int[] i : list){
            int ct = 0;
            for(int j : i){
                room_comb[ct] = nrPersons.get(j);
                ct++;
            }
            room_combs.add(room_comb.clone());
        }
        return room_combs;
    }

    //verifica daca o lista de array-uri de int-uri contine un array din parametru
    private static boolean contains_array(List<int[]> list, int[] array) {
        for (int[] i : list) {
            if (Arrays.equals(i, array)) {
                return true;
            }
        }
        return false;
    }

    private Room findARoomByType(List<Room> rooms,Type type){
        for(Room r : rooms){
            if(r.getType().equals(type)){
                return r;
            }
        }
        return null;
    }

    private Type findTypeByNrPers(int nrPers){
        if(nrPers == 1){
            return Type.SINGLE;
        } else if (nrPers == 2) {
            return Type.DOUBLE;
        } else if (nrPers == 3) {
            return Type.TRIPLE;
        }
        return Type.APARTMENT;
    }

    public List<Option> generateOptions(LocalDate checkIn, LocalDate checkOut, int nrTotalPers) {
        List<Room> availableRooms = searchAvailableRoom(checkIn,checkOut);

        //nrPersList va fi lista de nrPersoane din fiecare camera
        List<Integer> nrPersList = availableRooms.stream()
                .map(Room::getNrPers).toList();

        //calculam combinatiile de toate felurile pentru a obtine optiuni si le punem intr-o lista mare combinations
        List<int[]> combinations = new ArrayList<>();
        for(int i=1;i<nrPersList.size();i++){
            combinations = Stream.concat(combinations.stream(), transform_combinations(nrPersList.size(),i, nrPersList).stream()).toList();
        }

        double totalPrice = 0;
        List<Room> option_rooms = new ArrayList<>();
        List<int[]> options = new ArrayList<>();
        List<Option> final_options = new ArrayList<>();

        for(int[] i : combinations){
            option_rooms.clear();
            totalPrice = 0;
            if(Arrays.stream(i).sum() == nrTotalPers){
                if(!contains_array(options,i)){
                    options.add(i);
                    for(int el : i){
                        Room r = findARoomByType(availableRooms,findTypeByNrPers(el));
                        option_rooms.add(r);
                        assert r != null;
                        totalPrice += r.getPrice();
                    }
                    Option option = new Option(totalPrice,option_rooms.stream().toList());
                    final_options.add(option);
                }
            }
        }
        return final_options;
    }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
