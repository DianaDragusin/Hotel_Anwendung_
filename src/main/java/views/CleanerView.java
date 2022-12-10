package views;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.Cleaner;
import model.Cleaning;
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
    public void cleanroomStatus (int cleanerid, int roomId, LocalDate date)
    {
        cleanercontroller.cleanRoom(cleanerid, roomId,  date);
        System.out.println("Room cleaned successfully!");
    }
    public void printCleanedRooms(int cleanerId)
    {
        List<Cleaning> cleanings = cleanercontroller.getPersonalCleanings(cleanerId);
        for (Cleaning c : cleanings)
        {
            System.out.println(c.toString()) ;
        }
        System.out.println('\n');
    }

    public void showUserDetails(Cleaner cleaner) {
        System.out.println("These are your personal details:");
        System.out.println("Username: " + cleaner.getUsername());
        System.out.println("Name: " + cleaner.getLastName() + " " + cleaner.getFirstName()+cleaner);
    }

    public void printAllRooms() {
        System.out.println("These rooms are in our system:\n");
        for(Room r : cleanercontroller.getRooms()){
            System.out.println(r);
        }
        System.out.println('\n');
    }
}
