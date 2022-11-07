package service;

import model.*;
import repository.inMemoryRepo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    private InMemoryClientRepo clientRepo;
    private InMemoryRoomRepo roomRepo;
    private InMemoryReservationRepo reservationRepo;
    private InMemoryCleanerRepo cleanerRepo;
    private InMemoryCleaningsRepo cleaningsRepo;


    private List<Room> searchAvailableTypeRoom(LocalDate checkIn, LocalDate checkOut,Type t)
    {
        List<Room> rooms = roomRepo.typeRooms(t);
        for (Integer roomId : reservationRepo.returnAllUnAvailableRooms(checkIn,checkOut))
        {
            rooms.removeIf(room -> roomId == room.getId());
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
    public String makeReservation(Option option, Coupon coupon, String username,LocalDate start, LocalDate end){
       // work to do
        Reservation reservation = new Reservation(username,start,end, option.getTotalPrice());
        reservation.setPrice(applyCoupon(coupon,option.getTotalPrice()));
        reservationRepo.add(reservation,option.getRooms());



        return "Reservation created sucssfully";
    }
    private double applyCoupon(Coupon coupon,double price){

       int per = (100 -  coupon.getPercentage())/100;
       price = price * per;
       return price;

    }
    public String deleteReservation(Reservation reservation){
        reservationRepo.delete(reservation.getId());


        return "Reservation deleted sucssfully";
        //return "Reservation not found";
    }
    public List<Reservation> seeAllReservations(String username){
        List<Reservation> reservationList = new ArrayList<>() ;
        for(Reservation res: reservationRepo.getAll())
        {
            if (res.getIdUser().equals(username))
            {
                reservationList.add(res);
            }
        }
        return  reservationList;
    }
    public String register(String firstName, String lastName, String username, String password){
        if(clientRepo.add(new Client(firstName,lastName,username,password))){
            return "Client registered successfully!";
        }
        return "Couldn't register client!";
    }
    public String changeDetails(String firstName, String lastName, String username, List<Coupon> coupons)
    {
        if (clientRepo.findByUsername(username)!=null) {
            clientRepo.findByUsername(username).setFirstName(firstName);
            clientRepo.findByUsername(username).setLastName(lastName);
            clientRepo.findByUsername(username).setCouponList(coupons);
        }
        return "Details changed succesfully";
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
        clientRepo.findByUsername(username).setPassword(newPassword);
        return "Password changed successfully!";
    }

}
