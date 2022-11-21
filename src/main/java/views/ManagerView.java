package views;

import model.*;
import service.ClientController;
import service.ManagerController;

import java.util.List;
import java.util.Scanner;

public class ManagerView {
    public ManagerController managercontroller;

    public ManagerView(ManagerController managercontroller) {
        this.managercontroller = managercontroller;
    }

    public boolean loginStatus( String password)
    {
        boolean status = managercontroller.login(password);
        if (status)
        {
            System.out.println("Welcome to your designated space\n" );
            return true;
        }else {
            System.out.println("Invalid password\n" );
            return false;
        }
    }

    // CLIENT

    public void  printAllClients ( )
    {
        List<Client> clients =  managercontroller.seeAllClients();
        System.out.println("These clients are in our system\n");
        for (Client c: clients)
        {
            System.out.println(c.toString());
        }
    }
    public void findClientByUsernameStatus()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the username of the client you want to find:");
        String username = myObj.nextLine();

        Client client = managercontroller.findClientByUsername(username);

        if (client == null)
        {
            System.out.println("Client not found! Check the username and try again!\n");
        }
        else
        {
            System.out.println("The client is:\n"+client+"\n");
        }
    }
    public void deleteClientStatus()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the username of the client you want to delete:");
        String username = myObj.nextLine();

        Client client = managercontroller.findClientByUsername(username);

        if (client == null)
        {
            System.out.println("Client not found! Check the username and try again!\n");
        }
        else
        {
            managercontroller.deleteClient(username);
            System.out.println("Client successfully deleted!\n");
        }
    }

    // ROOM

    public void printAllRooms ( )
    {
        List<Room> rooms =  managercontroller.seeAllRooms();
        System.out.println("These rooms are in our system\n");
        for (Room r: rooms)
        {
            System.out.println(r.toString());
        }
    }
    public void addRoomStatus(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the data of the room you want to create:");
        System.out.println("Enter type (1-SINGLE, 2-DOUBLE, 3-TRIPLE, 4-APARTMENT:");
        int typeint = Integer.parseInt(myObj.nextLine());
        Type type;
        if(typeint == 1){
            type = Type.SINGLE;
        }else if (typeint == 2){
            type = Type.DOUBLE;
        } else if (typeint == 3){
            type = Type.TRIPLE;
        }else {
            type = Type.APARTMENT;
        }

        System.out.println("Enter price:");
        int price = Integer.parseInt(myObj.nextLine());
        System.out.println("Enter capacity (persons):");
        int nrPers = Integer.parseInt(myObj.nextLine());

        managercontroller.addRoom(type,price,nrPers);
        System.out.println("Room successfully added!\n");
    }
    public void deleteRoomStatus()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the id of the room you want to delete:");
        String id = myObj.nextLine();

        if(managercontroller.deleteRoom(id)){
            System.out.println("Room deleted successfully!\n");
        }
        else {
            System.out.println("Room not found! Check the id and try again!\n");
        }
    }
    public void updateRoomStatus()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the id of the room you want to update:");
        String id = myObj.nextLine();
        Room room = managercontroller.findRoomById(id);
        if(room != null) {
            System.out.println("Enter new type (1-SINGLE, 2-DOUBLE, 3-TRIPLE, 4-APARTMENT:");
            int typeint = Integer.parseInt(myObj.nextLine());
            Type type;
            if(typeint == 1){
                type = Type.SINGLE;
            }else if (typeint == 2){
                type = Type.DOUBLE;
            } else if (typeint == 3){
                type = Type.TRIPLE;
            }else {
                type = Type.APARTMENT;
            }

            System.out.println("Enter new price:");
            int price = Integer.parseInt(myObj.nextLine());
            System.out.println("Enter new capacity (persons):");
            int nrPers = Integer.parseInt(myObj.nextLine());

            String status = managercontroller.updateRoom(id, new Room(type, price, nrPers, id));
            System.out.println(status);
        }else {
            System.out.println("Room not found! Check the id and try again!\n");
        }
    }
    public void findRoomByIdStatus()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the id of the room you want to find:");
        String id = myObj.nextLine();

        Room room = managercontroller.findRoomById(id);

        if (room == null)
        {
            System.out.println("Room not found! Check the id and try again!\n");
        }
        else
        {
            System.out.println("The room is:\n"+room+"\n");
        }
    }

    // CLEANER

    public void  printAllCleaners ( )
    {
        List<Cleaner> cleaners =  managercontroller.seeAllCleaners();
        System.out.println("These cleaners are in our system\n");
        for (Cleaner c: cleaners)
        {
            System.out.println(c.toString());
        }
    }
    public void findCleanerByUsernameStatus()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the username of the cleaner you want to find:");
        String username = myObj.nextLine();

        Cleaner cleaner = managercontroller.findCleanerByUsername(username);

        if (cleaner == null)
        {
            System.out.println("Cleaner not found! Please check the username and try again!\n");
        }
        else
        {
            System.out.println("The cleaner is:\n"+cleaner+"\n");
        }
    }
    public void setSalarySatus()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the username of the cleaner you want to change salary:");
        String username = myObj.nextLine();

        Cleaner cleaner = managercontroller.findCleanerByUsername(username);
        if(cleaner == null){
            System.out.println("Cleaner was not found. Check the username and try again!\n");
            return;
        }

        System.out.println("Enter the new salary for "+cleaner);
        int salary = Integer.parseInt(myObj.nextLine());

        if(managercontroller.setSalaryCleaner(username,salary)){
            System.out.println("Salary was successfully changed!\n");
        }
        else{
            System.out.println("Cleaner was not found. Check the username and try again!\n");
        }

    }
    public void deleteCleanerStatus(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the username of the cleaner you want to change salary:");
        String username = myObj.nextLine();

        Cleaner cleaner = managercontroller.findCleanerByUsername(username);
        if(cleaner == null){
            System.out.println("Cleaner was not found. Check the username and try again!\n");
            return;
        }
        managercontroller.deleteCleaner(username);
        System.out.println("Cleaner successfully deleted!\n");
    }

    // CLEANING

    public void  printAllCleanings ( )
    {
        List<Cleaning> cleanings =  managercontroller.seeAllCleanings();
        System.out.println("These cleanings are in our system\n");
        for (Cleaning c: cleanings)
        {
            System.out.println(c.toString());
        }
    }
    public void printAllCleaningsForCleaner()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the username of the cleaner you want to print cleanings for:");
        String username = myObj.nextLine();

        Cleaner cleaner = managercontroller.findCleanerByUsername(username);
        if(cleaner == null){
            System.out.println("Cleaner not found! Check the username and try again!\n");
            return;
        }
        List<Cleaning>cleanings = managercontroller.seeAllCleaningsForCleaner(cleaner);
        System.out.println("These cleanings are in our system for cleaner " + cleaner + "\n");
        for (Cleaning c : cleanings)
        {
            System.out.println(c.toString());
        }
    }
    public void printAllCleaningsForRoom()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the id of the room you want to print cleanings for:");
        String id = myObj.nextLine();

        Room room = managercontroller.findRoomById(id);

        if(room == null){
            System.out.println("Room not found! Check the id and try again!\n");
            return;
        }
        List<Cleaning>cleanings = managercontroller.seeAllCleaningsForRoom(room);
        System.out.println("These cleanings are made in room " + room + ":\n");
        for (Cleaning c : cleanings)
        {
            System.out.println(c.toString());
        }
    }
}
