package service;

import model.*;
import repository.ICleanerRepository;
import repository.IClientRespository;
import repository.IRoomRepository;
import utils.CustomIllegalArgument;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ClientController {

    private final IClientRespository clientRepo;
    private final IRoomRepository roomRepo;
    private ICleanerRepository cleanerRepo;


    public ClientController(IClientRespository clientRepo, IRoomRepository roomRepo, ICleanerRepository cleanerRepo) {
        this.clientRepo = clientRepo;
        this.roomRepo = roomRepo;
        this.cleanerRepo = cleanerRepo;

    }
    // make private
    public List<Room> searchAvailableRoom(LocalDate checkIn, LocalDate checkOut)  {
        List<Room> rooms = roomRepo.getAll();
        List<Room> unavailableRooms = clientRepo.returnAllUnAvailableRooms(checkIn, checkOut);
        for (Room r : unavailableRooms )
        {
          rooms.remove(r);

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
    private List<int[]> generateCombinations(int n, int r) {
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
    private List<int[]> transform_combinations(int n, int k, List<Integer> nrPersons){
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

    private Room findARoomByType(List<Room> rooms,Type type) {
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

    public Option cheapestOption(List<Option> options) {
        Option optionMinPreis = options.get(0);
        for (Option option : options) {
            if (option.getTotalPrice() < optionMinPreis.getTotalPrice()) {
                optionMinPreis.setId(option.getId());
                optionMinPreis.setRooms(option.getRooms());
                optionMinPreis.setTotalPrice(option.getTotalPrice());
            }
        }
        return optionMinPreis;
    }
    public List<Option> generateOptions(LocalDate checkIn, LocalDate checkOut, int nrTotalPers) throws CustomIllegalArgument{
        List<Room> availableRooms = searchAvailableRoom(checkIn,checkOut);


        if (availableRooms.size()<1)
        {
            throw new CustomIllegalArgument("There are no available rooms in this period");
        }

        //nrPersList va fi lista de nrPersoane din fiecare camera
        List<Integer> nrPersList = availableRooms.stream()
                .map(Room::getNrPers).toList();

        //calculam combinatiile de toate felurile pentru a obtine optiuni si le punem intr-o lista mare combinations
        List<int[]> combinations = new ArrayList<>();
        for(int i=1;i<nrPersList.size();i++){
            combinations = Stream.concat(combinations.stream(), transform_combinations(nrPersList.size(),i, nrPersList).stream()).toList();
        }

        int totalPrice = 0;
        List<Room> option_rooms = new ArrayList<>();
        List<int[]> options = new ArrayList<>();
        List<Option> final_options = new ArrayList<>();
        int optionId = 1;

        for(int[] i : combinations){
            int ok = 1;
            option_rooms.clear();
            totalPrice = 0;
            if(Arrays.stream(i).sum() == nrTotalPers){
                if(!contains_array(options,i)){
                    List<Room> availableRoomsAux = new ArrayList<>(availableRooms);
                    options.add(i);
                    for(int el : i){
                        Room r = findARoomByType(availableRooms,findTypeByNrPers(el));
                        while(option_rooms.contains(r)){
                            availableRoomsAux.remove(r);
                            r = findARoomByType(availableRoomsAux,findTypeByNrPers(el));
                        }

                    option_rooms.add(r);
                    assert r != null;
                    totalPrice += r.getPrice();
                    }

                    Option option = new Option(totalPrice,option_rooms.stream().toList());
                    option.setId(optionId);
                    final_options.add(option);

                    optionId++;
                }
            }
        }
        final_options.sort(Comparator.comparing(Option::getTotalPrice));
        return final_options;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Reservation makeReservationWithCoupon(Option option, int couponid, int clientId, LocalDate start, LocalDate end) {
        // work to do
       // System.out.println("Se creeaza rezervarea cu cupon.");
        Coupon coupon = findCouponById(couponid,clientId);
        if (coupon!= null)
        {

            Reservation reservation = new Reservation(start, end, applyCoupon(coupon, option.getTotalPrice()));
            // System.out.println("Am stetat pretul rezervarii actualizat.");
            reservation.setRooms(option.getRooms());
            clientRepo.addReservation(reservation,clientId);

            return reservation;
        }
        else return null;


    }

    public Reservation makeReservation(Option option, Integer clientId, LocalDate start, LocalDate end) {
        // work to do
        Reservation reservation = new Reservation(start, end, option.getTotalPrice());
        reservation.setRooms(option.getRooms());
        clientRepo.addReservation(reservation,clientId);
        return reservation;
    }

    private int applyCoupon(Coupon coupon, int price) {

        float per = (float)(100 - coupon.getPercentage()) / 100;
        float price2 =  price * per;
        price = (int) price2;
        return price;

    }
    public Client findUserById (int id)
    {
        List<Client>clients = clientRepo.getAll();
        for (Client c : clients)
        {
            if (c.getId() == id)
                return  c;
        }
        return null;
    }

   // not working
    public Reservation findReservationById (int clientId, int reservationId)
    {
        List<Client>clients = clientRepo.getAll();
        Reservation reservation = null;
        for (Client c : clients)
        {
            if (c.getId() == clientId)
            {
                List<Reservation>reservations = c.getReservationList();
                for (Reservation res : reservations)
                {
                    if (res.getId() == reservationId)
                        return res;
                }
            }


        }
        return null;
    }


    public void deleteReservation(int resId, int clientId) throws  CustomIllegalArgument {
        if (findReservationById(clientId,resId) != null) {
            clientRepo.removeReservation(resId, clientId);
        }
        else {
            throw new CustomIllegalArgument("Reservation not found!");
        }




    }

    public List<Reservation> seeAllReservations(int id) {
        return clientRepo.getReservationsForClient(id);
    }

    public void register(String firstName, String lastName, String username, String password) throws CustomIllegalArgument{
        Client c = clientRepo.findByUsername(username);

        if (c == null) {
            clientRepo.add(new Client(firstName, lastName, username, password));

        } else throw new CustomIllegalArgument("There is already a user with this username in our system");
    }

    public void changeDetails(String newfirstName, String newlastName, int id) throws  CustomIllegalArgument {
        Client client = clientRepo.findById(id);
        if (client != null) {
            Client c = new Client(newfirstName, newlastName, client.getUsername(), client.getPassword());
            c.setCouponList(client.getCouponList());
            clientRepo.update(id, c);


        } else throw new CustomIllegalArgument("You are not in our database");

    }

    public void login(String username, String password)throws CustomIllegalArgument {
        int ok = 0;
        for (Client c : clientRepo.getAll()) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                ok = 1;
                break;
            }
        }
        if (ok == 0 )
            throw  new CustomIllegalArgument("Invalid credentials!");

    }

    public void changePassword(int id, String newPassword) {
        Client c = clientRepo.findById(id);
        c.setPassword(newPassword);
        clientRepo.update(id,c);
    }

    public Client findClientByUsername(String username) {
        return clientRepo.findByUsername(username);

    }

    public void addCoupon(Coupon c, int clientId)
    {
        clientRepo.addCoupon(c,clientId);
    }
    public void removeCoupon(int couponid, int client_id)
    {
        clientRepo.removeCoupon(couponid,client_id);
    }
    public Coupon findCouponById(int couponId, int clientid)
    {
        return clientRepo.findCouponById(couponId,clientid);
    }
}
