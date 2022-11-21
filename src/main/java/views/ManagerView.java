package views;

import model.*;
import service.ManagerController;

import java.util.List;
import java.util.Scanner;

public class ManagerView {
    public ManagerController managercontroller;

    public ManagerView(ManagerController managercontroller) {
        this.managercontroller = managercontroller;
    }
    
    public void setSalarySatus(String username, int salary)
    {
        String Status  = managercontroller.setSalaryCleaner(username,salary);
        System.out.println(Status);
    }
    public void loginStatus( String password)
    {
        boolean status = managercontroller.login(password);
        if (status )
        {
            System.out.println("Welcome to your designated space\n" );
        }else {
            System.out.println("Invalid password\n" );
        }


    }
    public void  printAllRooms ( )
    {
        List<Room> rooms =  managercontroller.seeAllRooms();
        System.out.println("These rooms are in our system\n");
        for (Room r: rooms)
        {
           r.toString();
        }
    }
    public void  printAllClients ( )
    {
        List<Client> clients =  managercontroller.seeAllClients();
        System.out.println("These clients are in our system\n");
        for (Client c: clients)
        {
            c.toString();
        }
    }
    public void  printAllCleaners ( )
    {
        List<Cleaner> cleaners =  managercontroller.seeAllCleaners();
        System.out.println("These cleaners are in our system\n");
        for (Cleaner c: cleaners)
        {
            c.toString();
        }
    }
    public void  printAllCleanings ( )
    {
        List<Cleaning> cleanings =  managercontroller.seeAllCleanings();
        System.out.println("These cleanings are in our system\n");
        for (Cleaning c: cleanings)
        {
            c.toString();
        }
    }
    public void printAllCleaningsForCleaner(Cleaner cleaner)
    {
        List<Cleaning>cleanings = managercontroller.seeAllCleaningsForCleaner(cleaner);
        System.out.println("These cleanings are in our system for cleaner " + cleaner + "\n");
        for (Cleaning c : cleanings)
        {
            c.toString();
        }
    }
    public void printAllCleaningsForRoom(Room room)
    {
        List<Cleaning>cleanings = managercontroller.seeAllCleaningsForRoom(room);
        System.out.println("These cleanings are made in room " + room + "\n");
        for (Cleaning c : cleanings)
        {
            c.toString();
        }
    }
    public void deleteRoomStatus(String id)
    {
        String status = managercontroller.deleteRoom(id);
        System.out.println(status);
    }
    public void updateRoomStatus(String id, Room room)
    {
        String status = managercontroller.updateRoom(id,room);
        System.out.println(status);
    }
    public void findRoomByIdStatus(String id)
    {
        if (managercontroller.findRoomById(id) == null)
        {
            System.out.println("This room is not in our system\n");
        }
        else
        {
            System.out.println("We have found the room you are looking for\n");
        }
    }
    public void findClientByIdStatus(String username)
    {
        if (managercontroller.findClientByUsername(username) == null)
        {
            System.out.println("This client is not in our system\n");
        }
        else
        {
            System.out.println("We have found the client you are looking for\n");
        }
    }
    public void findClientByUsernameStatus(String username)
    {
        if (managercontroller.findClientByUsername(username) == null)
        {
            System.out.println("This client is not in our system\n");
        }
        else
        {
            System.out.println("We have found the client you are looking for\n");
        }
    }
    public void findCleanerByUsernameStatus(String username)
    {
        if (managercontroller.findCleanerByUsername(username) == null)
        {
            System.out.println("This cleaner is not in our system\n");
        }
        else
        {
            System.out.println("We have found the cleaner you are looking for\n");
        }
    }
    public void findCleanerByIdStatus(String username)
    {
        if (managercontroller.findCleanerByUsername(username) == null)
        {
            System.out.println("This cleaner is not in our system\n");
        }
        else
        {
            System.out.println("We have found the cleaner you are looking for\n");
        }
    }
    public void deleteClientStatus(String username)
    {
        String Status = managercontroller.deleteCleaner(username);
        System.out.println(Status);
    }
}
