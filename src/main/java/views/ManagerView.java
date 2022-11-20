package views;

import model.*;
import service.ManagerController;

import java.util.List;

public class ManagerView {
    private ManagerController managercontroller;

    public ManagerView(ManagerController managercontroller) {
        this.managercontroller = managercontroller;
    }

    public void setSalarySatus(String username, int salary)
    {
        String Status  = managercontroller.setSalaryCleaner(username,salary);
        System.out.println(Status);
    }
    private void loginStatus( String password)
    {
        boolean status = managercontroller.login(password);
        if (status )
        {
            System.out.println("Welcome to your designated space\n" );
        }else {
            System.out.println("Invalid password\n" );
        }


    }
    private void  printAllRooms ( )
    {
        List<Room> rooms =  managercontroller.seeAllRooms();
        System.out.println("These rooms are in our system\n");
        for (Room r: rooms)
        {
           r.toString();
        }
    }
    private void  printAllClients ( )
    {
        List<Client> clients =  managercontroller.seeAllClients();
        System.out.println("These clients are in our system\n");
        for (Client c: clients)
        {
            c.toString();
        }
    }
    private void  printAllCleaners ( )
    {
        List<Cleaner> cleaners =  managercontroller.seeAllCleaners();
        System.out.println("These cleaners are in our system\n");
        for (Cleaner c: cleaners)
        {
            c.toString();
        }
    }
    private void  printAllCleanings ( )
    {
        List<Cleaning> cleanings =  managercontroller.seeAllCleanings();
        System.out.println("These cleanings are in our system\n");
        for (Cleaning c: cleanings)
        {
            c.toString();
        }
    }
    private void printAllCleaningsForCleaner(Cleaner cleaner)
    {
        List<Cleaning>cleanings = managercontroller.seeAllCleaningsForCleaner(cleaner);
        System.out.println("These cleanings are in our system for cleaner " + cleaner + "\n");
        for (Cleaning c : cleanings)
        {
            c.toString();
        }
    }
    private void printAllCleaningsForRoom(Room room)
    {
        List<Cleaning>cleanings = managercontroller.seeAllCleaningsForRoom(room);
        System.out.println("These cleanings are made in room " + room + "\n");
        for (Cleaning c : cleanings)
        {
            c.toString();
        }
    }
    private void deleteRoomStatus(int id)
    {
        String status = managercontroller.deleteRoom(id);
        System.out.println(status);
    }
    private void updateRoomStatus(int id, Room room)
    {
        String status = managercontroller.updateRoom(id,room);
        System.out.println(status);
    }
    private void findRoomByIdStatus(int id)
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
    private void findClientByIdStatus(int id)
    {
        if (managercontroller.findClientById(id) == null)
        {
            System.out.println("This client is not in our system\n");
        }
        else
        {
            System.out.println("We have found the client you are looking for\n");
        }
    }
    private void findClientByUsernameStatus(String username)
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
    private void findCleanerByUsernameStatus(String username)
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
    private void findCleanerByIdStatus(int id)
    {
        if (managercontroller.findCleanerById(id) == null)
        {
            System.out.println("This cleaner is not in our system\n");
        }
        else
        {
            System.out.println("We have found the cleaner you are looking for\n");
        }
    }
    private void deleteClientStatus(int id)
    {
        String Status = managercontroller.deleteCleaner(id);
        System.out.println(Status);
    }
}
