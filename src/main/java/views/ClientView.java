package views;

import model.Coupon;
import model.Option;
import model.Reservation;
import model.Room;
import service.ClientController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ClientView {
     private  ClientController clientcontroller;

    public ClientView(ClientController clientcontroller) {
        this.clientcontroller = clientcontroller;
    }
    public List<Coupon> showCoupons (String username)
    {

        for (Coupon c : clientcontroller.showCoupons(username))
        {
            c.toString();
        }
        return  clientcontroller.showCoupons(username);
    }
    public  List<Option> printOptions (LocalDate checkin, LocalDate checkout, Integer nrpers)
    {
        List<Option> options = clientcontroller.generateOptions(checkin,checkout,nrpers);
        for (Option option :options)
        {
            System.out.println(option.toString());
        }
        return  options;
    }
    public void  printAllReservedRooms (String username)
    {
        List<Room> rooms =  clientcontroller.seeAllReservedRooms(username);
        System.out.println(username + "has reserved following rooms:\n");
        for (Room room :rooms)
        {
            System.out.println(room.toString());
        }
    }
    public void  printAllReservations ( String username)
    {
        List<Reservation> reservations =  clientcontroller.seeAllReservations(username);
        System.out.println(username + "has  following reservations:\n");
        for (Reservation res :reservations)
        {
            System.out.println(res.toString());
        }
    }
    public void makeReservationStatus(Option option, String username, LocalDate start, LocalDate end)
    {
        String status = clientcontroller.makeReservation(option,username, start,end);
        System.out.println(status);
    }
    public void makeReservationWithCouponStatus(Option option, Coupon coupon, String username, LocalDate start, LocalDate end)
    {
        String status = clientcontroller.makeReservationWithCoupon(option,coupon,username, start,end);
        System.out.println(status);
    }
    public void deleteReservationStatus(Reservation reservation)
    {
        String status = clientcontroller.deleteReservation(reservation);
        System.out.println(status);
    }
    public boolean registerStatus(String firstName,String lastName,String username,String password)
    {
        boolean status = clientcontroller.register(firstName, lastName,username,password);
        if (status )
        {
            System.out.println("Client registered successfully!\n");
        }
        else
        {
            System.out.println("Something went wrong, client was not registered!\n");
        }
        return status;


    }
    public  void changeDetailsStatus (String newfirstName,String newlastName,String username)
    {
        String status = clientcontroller.changeDetails(newfirstName, newlastName,username);
        System.out.println(status);
    }

    public boolean loginStatus(String username, String password)
    {
        boolean status = clientcontroller.login(username,password);
        if (status )
        {
            System.out.println("User " + username + " is logged in \n" );
        }else {
            System.out.println("Invalid credentials\n" );
        }
        return status;


    }
    public boolean findUserStatus(String username)
    {
        return clientcontroller.findUser(username);
    }
    public void changePasswordStatus(String username,String password)
    {
        String status = clientcontroller.changePassword(username,password);
        System.out.println(status);
    }
}
