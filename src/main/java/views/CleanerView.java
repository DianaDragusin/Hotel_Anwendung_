package views;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.Cleaner;
import model.Room;
import service.CleanerController;

public class CleanerView {
    private CleanerController cleanercontroller;

    public CleanerView(CleanerController cleanercontroller) {
        this.cleanercontroller = cleanercontroller;
    }

   public boolean registerStatus(String firstName, String lastName, String username, String password)
    {
        boolean status = cleanercontroller.register(firstName, lastName,username,password);
        if (status )
        {
            System.out.println("You registered succsefully\n");
        }
        else {
            System.out.println("Registration failed");
        }
       return status;
    }
    public boolean loginStatus(String username, String password)
    {
        boolean status = cleanercontroller.login(username,password);
        if (status )
        {
            System.out.println("Cleaner " + username + " is logged in" );
        }else {
            System.out.println("Invalid credentials" );
        }
       return status;

    }
    public boolean changePasswordStatus(Integer id,String password)
    {
        return cleanercontroller.changePassword(id,password);
    }
    public  boolean changeDetailsStatus (String newfirstName,String newlastName,Integer id)
    {
        return cleanercontroller.changeDetails(newfirstName, newlastName,id);
    }
    public  boolean cleanroomStatus (Integer id, Integer room, LocalDate date)
    {
        return cleanercontroller.clean_room(id, room,  date);
    }
    public void printRooms()
    {
        List<Room> rooms = cleanercontroller.roomsToClean();
        for (Room r : rooms)
        {
            System.out.println(r.toString()) ;
        }
    }

}
