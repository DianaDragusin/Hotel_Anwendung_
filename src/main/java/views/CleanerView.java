package views;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;

import model.Cleaner;
import model.Room;
import service.CleanerController;

public class CleanerView {
    private CleanerController cleanercontroller;

    public CleanerView(CleanerController cleanercontroller) {
        this.cleanercontroller = cleanercontroller;
    }
    /*public void showOptionsCleaner() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Please select an action you want to perform!\n");
        System.out.println("0.Back \n");
        System.out.println("1.Login \n");
        System.out.println("2.Register \n");
        System.out.println("Action :");
        int action = keyboard.nextInt();
        System.out.println("\n");
        if (action == 1)
        {
            System.out.println("Please enter your username and your password\n");
            System.out.println("Username :");
            String username = reader.readLine();
            System.out.println("\n");
            System.out.println("Password:");
            String password = reader.readLine();
            System.out.println("\n");
            loginStatus(username,password);

            else
            {

                System.out.println("Please choose one of the following options\n");
                System.out.println("0. Back\n");
                System.out.println("1. Change Details\n");
                System.out.println("2. Change Password\n");
                System.out.println("Option: ");
                int action2 = keyboard.nextInt();
                System.out.println("\n");
                if (action2 == 0)
                {
                    //return
                }
                else if (action2 == 1)
                {
                    changeDetailsStatus();
                }
                System.out.println("Password:");
                String password = reader.readLine();
                System.out.println("\n");
            }
        }

    }
*/
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
        for (Room r : cleanercontroller.roomsToClean())
        {
           r.toString();
        }
    }

}
