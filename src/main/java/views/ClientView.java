package views;

import model.Coupon;
import model.Option;
import model.Reservation;
import model.Room;
import service.ClientController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ClientView {
     private  ClientController clientcontroller;

    private void  printOptions (LocalDate checkin, LocalDate checkout, Integer nrpers)
    {
        List<Option> options = clientcontroller.generateOptions(checkin,checkout,nrpers);
        for (Option option :options)
        {
            option.toString();
        }
    }
    private void  printAllReservedRooms (String username)
    {
        List<Room> rooms =  clientcontroller.seeAllReservedRooms(username);
        System.out.println(username + "has reserved following rooms:\n");
        for (Room room :rooms)
        {
            room.toString();
        }
    }
    private void  printAllReservations ( String username)
    {
        List<Reservation> reservations =  clientcontroller.seeAllReservations(username);
        System.out.println(username + "has  following reservations:\n");
        for (Reservation res :reservations)
        {
            res.toString();
        }
    }
    private void makeReservationStatus(Option option, Coupon coupon, String username, LocalDate start, LocalDate end)
    {
        String status = clientcontroller.makeReservation(option,coupon,username, start,end);
        System.out.println(status);
    }
    private void deleteReservationStatus(Reservation reservation)
    {
        String status = clientcontroller.deleteReservation(reservation);
        System.out.println(status);
    }
    private void registerStatus(String firstName,String lastName,String username,String password)
    {
        String status = clientcontroller.register(firstName, lastName,username,password);
        System.out.println(status);
    }
    private  void changeDetailsStatus (String newfirstName,String newlastName,String username)
    {
        String status = clientcontroller.changeDetails(newfirstName, newlastName,username);
        System.out.println(status);
    }
    private void loginStatus(String username, String password)
    {
        boolean status = clientcontroller.login(username,password);
        if (status )
        {
            System.out.println("User " + username + " is logged in" );
        }else {
            System.out.println("Invalid credentials" );
        }


    }
    private void changePasswordStatus(String username,String password)
    {
        String status = clientcontroller.changePassword(username,password);
        System.out.println(status);
    }
}
