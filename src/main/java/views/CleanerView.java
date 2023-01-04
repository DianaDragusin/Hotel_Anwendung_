package views;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.Cleaner;
import model.Cleaning;
import model.Client;
import model.Room;
import service.CleanerController;

public class CleanerView {
    private CleanerController cleanercontroller;
    private Scanner myObj;

    public CleanerView(CleanerController cleanercontroller) {
        this.cleanercontroller = cleanercontroller;
        this.myObj = new Scanner(System.in);
    }

   public Cleaner registerStatus()
    {
        System.out.println("Please enter your first Name:");
        String firstName = myObj.nextLine();
        System.out.println("Please enter your last Name:");
        String lastName = myObj.nextLine();
        System.out.println("Please enter your username:");
        String username = myObj.nextLine();
        if (cleanercontroller.findUserByUsername(username)!= null)
        {
            while(cleanercontroller.findUserByUsername(username)!=null) {
                System.out.println("This username is already Taken.");
                System.out.println("Please enter your username:");
                username = myObj.nextLine();
            }

        }

        System.out.println("Please enter your password:");
        String password = myObj.nextLine();

        try
        {
            cleanercontroller.register(firstName, lastName,username,password);
            System.out.println("Client registered successfully!" );
            return cleanercontroller.findUserByUsername(username);

        }catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return null;
        }
    }
    public Cleaner loginStatus()
    {
        System.out.println("Please enter your username:");
        String username = myObj.nextLine();
        System.out.println("\n");
        System.out.println("Please enter your password:");
        String password = myObj.nextLine();
        System.out.println("\n");
        try
        {
            cleanercontroller.login(username,password);
            System.out.println("User " + username + " is logged in \n" );
            return cleanercontroller.findUserByUsername(username);

        }catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return  null;
        }

    }
    public boolean changePasswordStatus(Integer id)
    {
        System.out.println("Enter your new Password:");
        String password = myObj.nextLine();
        return cleanercontroller.changePassword(id,password);
    }
    public  boolean changeDetailsStatus (Integer id)
    {
        System.out.println("Enter your new First Name:");
        String newFirstName = myObj.nextLine();
        System.out.println("Enter your new Last Name:");
        String newLastName = myObj.nextLine();
        return cleanercontroller.changeDetails(newFirstName, newLastName,id);
    }
    public void cleanroomStatus (int cleanerid)
    {
        System.out.println("What room would you like to clean?\n");
        int roomId  =  Integer.parseInt(myObj.nextLine());
        System.out.println("When ? ");
        System.out.print("year = ");
        int year  =  Integer.parseInt(myObj.nextLine());
        System.out.print("month = ");
        int month = Integer.parseInt(myObj.nextLine());
        System.out.print("day = ");
        int day = Integer.parseInt(myObj.nextLine());
        LocalDate date = LocalDate.of(year,month,day);
        cleanercontroller.cleanRoom(cleanerid, roomId,  date);
        System.out.println("Room cleaned successfully!");
    }
    public void printCleanedRooms(int cleanerId)
    {
        System.out.println("These rooms have been cleaned by you:");
        List<Cleaning> cleanings = cleanercontroller.getPersonalCleanings(cleanerId);
        for (Cleaning c : cleanings)
        {
            System.out.println(c.toString()) ;
        }
        System.out.println('\n');
    }

    public void showUserDetails(String username) {
        Cleaner cleaner = cleanercontroller.findUserByUsername(username);
        System.out.println("These are your personal details:");
        System.out.println("Name: " + cleaner.getLastName() + " " + cleaner.getFirstName());
        System.out.println("Username: " + cleaner.getUsername());
        System.out.println("Password: " + cleaner.getPassword());
        System.out.println("Salary: " + cleaner.getSalary());
    }

    public void printAllRooms() {
        System.out.println("These rooms are in our system:\n");
        for(Room r : cleanercontroller.getRooms()){
            System.out.println(r);
        }
        System.out.println('\n');
    }

    public Cleaner findCleanerByUsername(String username) {
        return cleanercontroller.findUserByUsername(username);
    }
}
