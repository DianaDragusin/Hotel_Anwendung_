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
    public List<Coupon> showCoupons (int id)
    {
        if(clientcontroller.showCoupons(id).size() == 0){
            System.out.println("You don't have any coupon yet. Make more reservations and you'll get some!");
            return null;
        }
        for (Coupon c : clientcontroller.showCoupons(id))
        {
            System.out.println(c.toString());
        }
        return  clientcontroller.showCoupons(id);
    }
    public  List<Option> printOptions (LocalDate checkin, LocalDate checkout, int nrpers)
    {
        List<Option> options = clientcontroller.generateOptions(checkin,checkout,nrpers);
        for (Option option :options)
        {
            System.out.println(option.toString());
        }
        return  options;
    }
    public void  printAllReservedRooms (int id)
    {
        List<Room> rooms =  clientcontroller.seeAllReservedRooms(id);
        System.out.println(id + "has reserved following rooms:\n");
        for (Room room :rooms)
        {
            System.out.println(room.toString());
        }
    }
    public void  printAllReservations (int id)
    {
        List<Reservation> reservations =  clientcontroller.seeAllReservations(id);
        System.out.println("You have following reservations:\n");
        for (Reservation res :reservations)
        {
            System.out.println(res.toString());
        }
    }
    public void makeReservationStatus(Option option, int id, LocalDate start, LocalDate end)
    {
        String status = clientcontroller.makeReservation(option,id, start,end);
        System.out.println(status);
    }
    public void makeReservationWithCouponStatus(Option option, Coupon coupon, int id, LocalDate start, LocalDate end)
    {
        String status = clientcontroller.makeReservationWithCoupon(option,coupon,id, start,end);
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
    public  void changeDetailsStatus (String newfirstName,String newlastName,int id)
    {
        String status = clientcontroller.changeDetails(newfirstName, newlastName,id);
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
    public void changePasswordStatus(int id,String password)
    {
        String status = clientcontroller.changePassword(id,password);
        System.out.println(status);
    }
}
