package service;

import model.Coupon;
import model.Option;
import model.Reservation;
import repository.inMemoryRepo.*;

import java.time.LocalDate;
import java.util.List;

public class ClientController {

    private InMemoryClientRepo clientRepo;
    private InMemoryRoomRepo roomRepo;
    private InMemoryReservationRepo reservationRepo;
    private InMemoryCleanerRepo cleanerRepo;
    private InMemoryCleaningsRepo cleaningsRepo;

    public List<Option> generateOptions(LocalDate checkIn, LocalDate checkOut, int nrPers){

        return null;
    }
    public String makeReservation(Option option, Coupon coupon){
        //.....
        applyCoupon(coupon);
        return "Reservation created successfully";
    }
    private String applyCoupon(Coupon coupon){
        return "Coupon applied successfully";
        //return "Coupon not found";
    }
    public String deleteReservation(Reservation reservation){
        return "Reservation deleted successfully";
        //return "Reservation not found";
    }
    public List<Reservation> seeAllReservations(){
        return null;
    }

}
