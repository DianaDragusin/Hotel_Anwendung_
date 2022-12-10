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

    public void  printAllClients()
    {
        List<Client> clients =  managercontroller.seeAllClients();
        System.out.println("These clients are in our system\n");
        for (Client c: clients)
        {
            System.out.println(c.toString());
        }
    }
    public void findClientByUsernameStatus(String username)
    {
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
    public void deleteClientStatus(int id)
    {
        Client client = managercontroller.findClientById(id);

        if (client == null)
        {
            System.out.println("Client not found! Check the username and try again!\n");
        }
        else
        {
            managercontroller.deleteClient(id);
            System.out.println("Client successfully deleted!\n");
        }
    }

    // ROOM

    public void printAllRooms ()
    {
        List<Room> rooms =  managercontroller.seeAllRooms();
        System.out.println("These rooms are in our system:\n");
        for (Room r: rooms)
        {
            System.out.println(r.toString());
        }
    }
    public void addRoomStatus(Type type, Double price, int nrPers){
        managercontroller.addRoom(type,price,nrPers);
        System.out.println("Room successfully added!\n");
    }
    public void deleteRoomStatus(int id)
    {

        if(managercontroller.deleteRoom(id)!=null){
            System.out.println("Room deleted successfully!\n");
        }
        else {
            System.out.println("Room not found! Check the id and try again!\n");
        }
    }
    public void updateRoomStatus(int id, Type type, double price, int nrPers)
    {
        Room room = managercontroller.findRoomById(id);
        if(room != null) {

            Room status = managercontroller.updateRoom(id, new Room(type, price, nrPers));
            System.out.println(status);
        }else {
            System.out.println("Room not found! Check the id and try again!\n");
        }
    }
    public void findRoomByIdStatus(int id)
    {
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
    public void findCleanerByUsernameStatus(String username)
    {
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
    public void setSalarySatus(int id, int salary)
    {
        Cleaner cleaner = managercontroller.findCleanerById(id);
        if(cleaner == null){
            System.out.println("Cleaner was not found. Check the username and try again!\n");
            return;
        }

        if(managercontroller.setSalaryCleaner(id,salary)!=null){
            System.out.println("Salary was successfully changed!\n");
        }
        else{
            System.out.println("Cleaner was not found. Check the username and try again!\n");
        }

    }
    public void deleteCleanerStatus(int id){
        Cleaner cleaner = managercontroller.findCleanerById(id);
        if(cleaner == null){
            System.out.println("Cleaner was not found. Check the username and try again!\n");
            return;
        }
        managercontroller.deleteCleaner(id);
        System.out.println("Cleaner successfully deleted!\n");
    }

    // CLEANING

    public void printCleaningsForCleaner(int cleanerId)
    {
        List <Cleaning> cleanings = managercontroller.getCleanerCleanings(cleanerId);
        for (Cleaning cleaning : cleanings)
        {
            System.out.println(cleaning.toString());
        }
        if(cleanings.size()==0){
            System.out.println("There is no cleaned rooms by this cleaner.");
        }
    }
    public void printCleaningsForRoom(int roomid)
    {
        List <Cleaning> cleanings = managercontroller.getRoomCleanings(roomid);
        for (Cleaning cleaning : cleanings)
        {
            System.out.println(cleaning.toString());
        }
        if(cleanings.size()==0){
            System.out.println("There is no cleaning for this room.");
        }
    }
    public void printAllCleanings(){
        System.out.println("These are the cleanings:");
        for(Cleaning c : managercontroller.getCleanings()){
            System.out.println(c);
        }
        System.out.println('\n');
    }

    public void seeAllReservations(){
        List<Reservation> reservations = managercontroller.seeAllReservations();
        for(Reservation r : reservations){
            System.out.println(r);
        }
        System.out.println('\n');
    }

}
