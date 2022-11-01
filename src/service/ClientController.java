package service;

import model.Coupon;
import model.Option;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class ClientController {

    private ClientRepository clientRepository;

    public List<Option> generateOptions(LocalDate checkIn, LocalDate checkOut, int nrPers){

        return null;
    }
    public String makeReservation(Option option, Coupon coupon){
        //.....
        applyCoupon(coupon);
        return "Reservation created successfully"
    }
    private String applyCoupon(Coupon coupon){
        return "Coupon applied successfully";
        //return "Coupon not found";
    }
    public String deleteReservation(Reservation reservation){
        return "Reservation deleted successfully";
        return "Reservation not found";
    }
    public List<Reservation> seeAllReservations(){
        return null;
    }



}
