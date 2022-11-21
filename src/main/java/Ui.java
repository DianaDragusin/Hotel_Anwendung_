import model.*;
import repository.inMemoryRepo.*;
import service.CleanerController;
import service.ClientController;
import service.ManagerController;
import views.CleanerView;
import views.ClientView;
import views.ManagerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {

    List<Cleaner> cleaners;
    List<Client> clients;
    List<Room> rooms;
    List<Reservation> reservations;
    List<Reservation_Room> reservation_rooms;
    List<Cleaning> cleanings;


    InMemoryCleanerRepo inMemoryCleanerRepo;
    InMemoryClientRepo inMemoryClientRepo;
    InMemoryRoomRepo inMemoryRoomRepo;
    InMemoryReservationRepo inMemoryReservationRepo;
    InMemoryCleaningsRepo inMemoryCleaningsRepo;

    ClientController clientController;
    CleanerController cleanerController;
    ManagerController managerController;

    ClientView clientView;
    CleanerView cleanerView;
    ManagerView managerView;

    public Ui() {

        // Cleaner Controller
        this.cleaners = new ArrayList<>();
        this.inMemoryCleanerRepo = new InMemoryCleanerRepo(cleaners);
        this.cleanerController = new CleanerController(inMemoryCleanerRepo);

        // Client Controller
        this.clients = new ArrayList<>();
        this.inMemoryClientRepo = new InMemoryClientRepo(clients);

        this.rooms = new ArrayList<>();
        this.inMemoryRoomRepo = new InMemoryRoomRepo(rooms);

        this.reservations = new ArrayList<>();
        this.reservation_rooms = new ArrayList<>();
        this.inMemoryReservationRepo = new InMemoryReservationRepo(reservations, reservation_rooms);

        this.cleanings = new ArrayList<>();
        this.inMemoryCleaningsRepo = new InMemoryCleaningsRepo(cleanings);

        this.clientController = new ClientController(inMemoryClientRepo, inMemoryRoomRepo, inMemoryReservationRepo, inMemoryCleanerRepo, inMemoryCleaningsRepo);

        // Manager Controller
        this.managerController = new ManagerController(inMemoryRoomRepo, inMemoryClientRepo, inMemoryCleanerRepo, inMemoryCleaningsRepo, inMemoryReservationRepo);
    }


    public void createView(int option){
        if(option == 1){
            ClientView clientView = new ClientView(clientController);
            System.out.println("You are redirected to the Client menu..");
            //clientView.showOptions();
        } else if(option == 2){
            ManagerView managerView = new ManagerView(managerController);
            System.out.println("You are redirected to the Manager menu..");
            //managerView.showOptions();
        } else if (option == 3) {
            CleanerView cleanerView = new CleanerView(cleanerController);
            System.out.println("You are redirected to the Cleaner menu..");
            //cleanerView.showOptions();
        }
    }
    public void showMenu(){
        System.out.println("""
                Hello! Please choose your connect mode!
                1. Client
                2. Manager
                3. Cleaner
                """);
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter your option: ");
        int option = Integer.parseInt(myObj.nextLine());
        System.out.println("Ok let's go!");
        createView(option);
    }

    public void showOptionsManager(){
        System.out.println("""
                You are in Manager mode!
                0. Back
                1. Login""");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter your option: ");
        int option = Integer.parseInt(myObj.nextLine());
        if(option == 0){
            return;
        } else if (option == 1) {
            System.out.println("Please enter your password: ");
            String password = myObj.nextLine();
            managerView.loginStatus(password);
        }
    }

}
