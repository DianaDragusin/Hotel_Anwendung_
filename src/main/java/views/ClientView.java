package views;

import model.*;
import service.ClientController;
import utils.CustomIllegalArgument;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
   /// public  void printOptions (LocalDate checkin, LocalDate checkout ,int nrperson )
   // {
     //   List<Option>options = clientcontroller.generateOptions(checkin,checkout,nrperson);


   // }

    public void  printAllReservations (int id)
    {
        List<Reservation> reservations =  clientcontroller.seeAllReservations(id);
        System.out.println("You have following reservations:\n");
        for (Reservation res :reservations)
        {
            System.out.println(res.toString());
        }
        System.out.println('\n');
    }
    public void makeReservationStatus(Option option, int id, LocalDate start, LocalDate end)
    {
        if(clientcontroller.makeReservation(option,id, start,end)!= null){
            System.out.println("Reservation created successfully!");
            return;
        }
        System.out.println("Reservation not created!");
    }
    public void makeReservationWithCouponStatus(Option option, int couponid, int id, LocalDate start, LocalDate end)
    {
        if(clientcontroller.makeReservationWithCoupon(option,couponid,id, start,end)!= null){
            System.out.println("Reservation created successfully!");
            return;
        }
        System.out.println("Reservation not created!");
    }
    public void deleteReservationStatus(int resId, int clientId)  {
        try
        {
            clientcontroller.deleteReservation(resId,clientId);
            System.out.println("Reservation deleted successfully!");
        }catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }


    }
    public boolean registerStatus(String firstName,String lastName,String username,String password)
    {
        try
        {
            clientcontroller.register(firstName, lastName,username,password);
            System.out.println("Client registered successfully!" );
            return true;

        }catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return  false;
        }



    }
    public  boolean changeDetailsStatus (String newfirstName,String newlastName,int id)
    {

        try
        {
            clientcontroller.changeDetails(newfirstName, newlastName,id);
            System.out.println("User has changed its first and last name succsesfully \n" );
            return true;

        }catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return  false;
        }
    }

    public boolean loginStatus(String username, String password)
    {
        try
        {
            clientcontroller.login(username,password);
            System.out.println("User " + username + " is logged in \n" );
            return true;

        }catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return  false;
        }



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
    private void printOptions(List<Option> options)
    {
        System.out.println("options size="+options.size());
        for (Option option :options)
        {
            System.out.println(option.toString());

        }
    }
    public void optionPart (int couponans, LocalDate from, LocalDate to, int people, Client client)
    {
        List<Option> options = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        try
        {
            options = clientcontroller.generateOptions(from,to,people);
            System.out.println("Options:");
            printOptions(options);
            System.out.println("What option do you prefer:");
            try {
                int optionnr = Integer.parseInt(myObj.nextLine());

                if (couponans >-1 && options.size() > 0)
                {

                        // nest another level
                       // Coupon c = clientcontroller.findCouponById(couponans, client.getId());
                        makeReservationWithCouponStatus(options.get(optionnr - 1),couponans, client.getId(),from,to);
                        clientcontroller.removeCoupon(couponans,client.getId());


                }
                else if(couponans == -1 && options.size() > 0)
                {
                    // nest another level
                    makeReservationStatus(options.get(optionnr - 1), client.getId(),from,to);

                    if (clientcontroller.seeAllReservations(client.getId()).size() %2 == 0)
                    {

                        int random_int = (int)Math.floor(Math.random()*(90-10+1)+10)/10;
                        random_int = random_int * 10;

                        Coupon coupon = new Coupon(random_int);
                        clientcontroller.addCoupon(coupon,client.getId());

                    }
                }

            }catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }


        }catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }


    }


}
