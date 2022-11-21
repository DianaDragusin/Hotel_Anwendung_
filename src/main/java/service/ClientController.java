package service;

import model.*;
import repository.inMemoryRepo.*;
import views.ClientView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientController {

    private InMemoryClientRepo clientRepo;
    private InMemoryRoomRepo roomRepo;
    private InMemoryReservationRepo reservationRepo;
    private InMemoryCleanerRepo cleanerRepo;
    private InMemoryCleaningsRepo cleaningsRepo;

    public ClientController(InMemoryClientRepo clientRepo, InMemoryRoomRepo roomRepo, InMemoryReservationRepo reservationRepo, InMemoryCleanerRepo cleanerRepo, InMemoryCleaningsRepo cleaningsRepo) {
        this.clientRepo = clientRepo;
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;
        this.cleanerRepo = cleanerRepo;
        this.cleaningsRepo = cleaningsRepo;
    }

    private List<Room> searchAvailableTypeRoom(LocalDate checkIn, LocalDate checkOut, Type t)
    {
        List<Room> rooms = roomRepo.typeRooms(t);
        for (String roomId : reservationRepo.returnAllUnAvailableRooms(checkIn,checkOut))
        {

            for (Room r : rooms)
            {

                if (r.getId().equals(roomId))
                {

                    rooms.remove(r);

                }
            }
          //  rooms.removeIf(room -> roomId == room.getId());
        }

        return rooms;
    }
    public List<Option> generateOptions(LocalDate checkIn, LocalDate checkOut, int nrPers){
        List<Room> apartmentsR = searchAvailableTypeRoom(checkIn,checkOut,Type.APARTMENT);
        List<Room> tripleR = searchAvailableTypeRoom(checkIn,checkOut,Type.TRIPLE);
        List<Room> doubleR = searchAvailableTypeRoom(checkIn,checkOut,Type.DOUBLE);
        List<Room> singleR = searchAvailableTypeRoom(checkIn,checkOut,Type.SINGLE);
        List<Room> rooms = new ArrayList<>();

        double totalPrice = 0;
        while(nrPers >0)
        {
            if (!apartmentsR.isEmpty() && nrPers>=4 )
            {
                rooms.add(apartmentsR.get(0));
                totalPrice = totalPrice + apartmentsR.get(0).getPrice();
                apartmentsR.remove(apartmentsR.get(0));
                nrPers = nrPers - 4;
            }
            else if(!tripleR.isEmpty() && nrPers>=3) {
                rooms.add(tripleR.get(0));
                totalPrice = totalPrice + tripleR.get(0).getPrice();
                tripleR.remove(tripleR.get(0));
                nrPers = nrPers - 3;
            }
            else if(!doubleR.isEmpty() && nrPers>=2) {

                rooms.add(doubleR.get(0));
                totalPrice = totalPrice + doubleR.get(0).getPrice();
                doubleR.remove(doubleR.get(0));
                nrPers = nrPers - 2;
            }
            else if(!singleR.isEmpty()) {
                rooms.add(singleR.get(0));
                totalPrice = totalPrice + singleR.get(0).getPrice();
                singleR.remove(singleR.get(0));
                nrPers = nrPers - 1;
            }
           else
            {
                totalPrice = 0;
                rooms = new ArrayList<>();
            }

        }
        Option option = new Option(totalPrice,rooms);
        List<Option> options = new ArrayList<>();
        options.add(option);
        return  options;
    }

    public String generateReservationId(LocalDate checkIn, String username){
        return checkIn.toString()+"-"+username;
    }
    public String makeReservationWithCoupon(Option option, Coupon coupon, String username,LocalDate start, LocalDate end){
       // work to do
        Reservation reservation = new Reservation(generateReservationId(start,username),username,start,end, option.getTotalPrice());
        reservation.setPrice(applyCoupon(coupon,option.getTotalPrice()));
        reservationRepo.addReservation(reservation,option.getRooms());

        return "Reservation created sucssfully";
    }
    public String makeReservation(Option option, String username,LocalDate start, LocalDate end){
        // work to do
        Reservation reservation = new Reservation(generateReservationId(start,username),username,start,end, option.getTotalPrice());
        reservationRepo.addReservation(reservation,option.getRooms());

        return "Reservation created sucssfully";
    }
    private double applyCoupon(Coupon coupon,double price){

       int per = (100 -  coupon.getPercentage())/100;
       price = price * per;
       return price;

    }
    public String deleteReservation(Reservation reservation){
       if(reservationRepo.deleteReservation(reservation.getId()))
       {
           return "Reservation deleted succssfully";
       }
       else
       {
           return "Reservation not found";
       }




    }
    public List<Reservation> seeAllReservations(String username) {

       return reservationRepo.GetAllReservationsForAUser(username);

    }
    public List<Room> seeAllReservedRooms(String username){
        List<String> userRoomsString = reservationRepo.GetAllReservedRoomsForAUser(username);
        List<Room>allRooms = roomRepo.getAll();
        List<Room>userRooms = new ArrayList<>();
        for (String roomid: userRoomsString)
        {
            for (Room room : allRooms)
            {

                if (room.getId().equals(roomid))
                {
                    userRooms.add(room);
                }
            }
        }
        return userRooms;

    }
    public String register(String firstName, String lastName, String username, String password){
        if(clientRepo.add(new Client(firstName,lastName,username,password))){
            return "Client registered successfully!";
        }
        return "Couldn't register client!";
    }
    public String changeDetails(String newfirstName, String newlastName, String username)
    {   Client client = clientRepo.findbyusername(username);
        if (client!=null) {
            Client c = new Client(newfirstName,newlastName,username,client.getPassword());
            c.setCouponList(client.getCouponList());
            clientRepo.update(client.getUsername(),c);
            return "Details changed succesfully";

        }
        else  return "Invalid user, the details were not changed";

    }
    public boolean login(String username, String password){
        for( Client c : clientRepo.getAll()){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public String changePassword(String username, String newPassword){
        clientRepo.findbyusername(username).setPassword(newPassword);
        return "Password changed successfully!";
    }

}
