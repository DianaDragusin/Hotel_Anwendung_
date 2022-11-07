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
        Reservation reservation = new Reservation(username,start,end);
        reservationRepo.add(reservation);
        for (Room room : option.getRooms())
        {
            Reservation_Room res_room = new Reservation_Room(reservation.getId(),room.getId());

        }

        applyCoupon(coupon);
        return "Reservation created sucssfully";
    }
    private String applyCoupon(Coupon coupon){
        return "Coupon applied sucssfully";
        //return "Coupon not found";
    }
    public String deleteReservation(Reservation reservation){
        return "Reservation deleted sucssfully";
        //return "Reservation not found";
    }
    public List<Reservation> seeAllReservations(){
        return null;
    }

}
