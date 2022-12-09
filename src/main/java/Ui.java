import model.*;
import repository.inMemoryRepo.*;
import service.CleanerController;
import service.ClientController;
import service.ManagerController;
import views.CleanerView;
import views.ClientView;
import views.ManagerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    // nu ar trebui  sa initializam repo cu aceste liste si sa facem constructoarele de la ele goale si pe moment sa le popoulam cu functiile de populate

    List<Cleaner> cleaners;
    List<Client> clients;
    List<Room> rooms;
    List<Reservation> reservations;



    InMemoryCleanerRepo inMemoryCleanerRepo;
    InMemoryClientRepo inMemoryClientRepo;
    InMemoryRoomRepo inMemoryRoomRepo;
    InMemoryReservationRepo inMemoryReservationRepo;


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
        this.cleanerController = new CleanerController(inMemoryCleanerRepo, inMemoryRoomRepo);

        // Client Controller
        this.clients = new ArrayList<>();
        this.inMemoryClientRepo = new InMemoryClientRepo();

        this.rooms = new ArrayList<>();
        this.inMemoryRoomRepo = new InMemoryRoomRepo();

        this.reservations = new ArrayList<>();

        this.inMemoryReservationRepo = new InMemoryReservationRepo();



        this.clientController = new ClientController(inMemoryClientRepo, inMemoryRoomRepo, inMemoryReservationRepo, inMemoryCleanerRepo);
        this.cleanerController = new CleanerController(inMemoryCleanerRepo,inMemoryRoomRepo);
        // Manager Controller
        this.managerController = new ManagerController(inMemoryRoomRepo, inMemoryClientRepo, inMemoryCleanerRepo, inMemoryReservationRepo, "parola1");
    }


    public void createView(int option){
        switch (option) {
            case 1 -> {
                this.clientView = new ClientView(clientController);
                System.out.println("You are redirected to the Client menu..");
                showOptionsClient();
            }
            case 2 -> {
                this.managerView = new ManagerView(managerController);
                System.out.println("You are redirected to the Manager menu..");
                showOptionsManager();
            }
            case 3 -> {
                this.cleanerView = new CleanerView(cleanerController);
                System.out.println("You are redirected to the Cleaner menu..");
                 showOptionsCleaner();
            }
            default -> System.out.println("Not a valid option!");
        }
    }
    public void showMenu(){
        System.out.println("""
                Hello! Please choose your connect mode!
                1. Client
                2. Manager
                3. Cleaner
                Enter your option:""");
        Scanner myObj = new Scanner(System.in);
        int option = Integer.parseInt(myObj.nextLine());
        System.out.println("Ok let's go!");
        createView(option);
    }

    public void showOptionsManager(){
        System.out.println("""
                You are in Manager mode!
                0. Back
                1. Login
                Enter your option:""");
        Scanner myObj = new Scanner(System.in);
        int option = Integer.parseInt(myObj.nextLine());
        if(option == 0){
            showMenu();
        } else if (option == 1) {
            System.out.println("Please enter your password:");
            String password = myObj.nextLine();
            if (managerView.loginStatus(password)) {
                managerMenu();
            }
            else{
                showOptionsManager();
            }
       }
    }
    public void showOptionsClient(){
        System.out.println("""
                You are in Client mode!
                0. Back
                1. Login
                2. Register
                Enter your option:""");
        Scanner myObj = new Scanner(System.in);
        int option = Integer.parseInt(myObj.nextLine());
        if(option == 0){
            showMenu();
        } else if (option == 1) {
            System.out.println("Please enter your username:");
            String username = myObj.nextLine();
            System.out.println("\n");
            System.out.println("Please enter your password:");
            String password = myObj.nextLine();
            System.out.println("\n");
            if (clientView.loginStatus(username,password)) {
                clientMenu(inMemoryClientRepo.findByUsername(username));
            }
            else{
                showOptionsClient();
            }
        }
        else if (option == 2) {
            System.out.println("Please enter your first Name:");
            String firstname = myObj.nextLine();
            System.out.println("\n");
            System.out.println("Please enter your last Name:");
            String lastname = myObj.nextLine();
            System.out.println("\n");
            System.out.println("Please enter your username:");
            String username = myObj.nextLine();
            System.out.println("\n");
            if (clientView.findUserStatus(username))
            {
                while(clientView.findUserStatus(username)) {
                    System.out.println("\n");
                    System.out.println("This username is already Taken.");
                    System.out.println("Please enter your username:");
                     username = myObj.nextLine();
                }

            }
            System.out.println("\n");
            System.out.println("Please enter your password:");
            String password = myObj.nextLine();
            if (clientView.registerStatus(firstname,lastname,username,password)) {
                // adaugam clientul si in lista de clienti actualizata
                clients.add(inMemoryClientRepo.findByUsername(username));

                clientMenu(inMemoryClientRepo.findByUsername(username));

            }
            else{
                showOptionsClient();
            }
        }
    }
    public void showOptionsCleaner(){
        System.out.println("""
                You are in Cleaner mode!
                0. Back
                1. Login
                2. Register
                Enter your option:""");
        Scanner myObj = new Scanner(System.in);
        int option = Integer.parseInt(myObj.nextLine());
        if(option == 0){
            showMenu();
        } else if (option == 1) {
            System.out.println("Please enter your username:");
            String username = myObj.nextLine();
            System.out.println("\n");
            System.out.println("Please enter your password:");
            String password = myObj.nextLine();
            System.out.println("\n");
            if (cleanerView.loginStatus(username,password)) {
                cleanerMenu(inMemoryCleanerRepo.findByUsername(username));
            }
            else{
                showOptionsCleaner();
            }
        }
        else if (option == 2) {
            System.out.println("Please enter your first Name:");
            String firstname = myObj.nextLine();
            System.out.println("Please enter your last Name:");
            String lastname = myObj.nextLine();
            System.out.println("Please enter your username:");
            String username = myObj.nextLine();
            if (cleanerController.findUserByUsername(username)!= null)
            {
                while(cleanerController.findUserByUsername(username)!=null) {
                    System.out.println("This username is already Taken.");
                    System.out.println("Please enter your username:");
                    username = myObj.nextLine();
                }

            }

            System.out.println("Please enter your password:");
            String password = myObj.nextLine();

            if (cleanerView.registerStatus(firstname,lastname,username,password)) {
                cleanerMenu(inMemoryCleanerRepo.findByUsername(username));
            }
            else{
                showOptionsCleaner();
            }
        }
    }
    private void clientMenu(Client client){
        System.out.println("""
                Hello Client!
                Choose an option!
                RESERVATION
                1. Make a reservation
                2. Show all reservations
                3. Show all reserved rooms
                4. Delete a reservation
                5. Show Coupons
                PERSONAL INFO
                6. Change FirstName and Last Name
                7. Change Password
                8. See your details
                EXIT
                0.  Logout
                
                Enter your option:""");
        Scanner myObj = new Scanner(System.in);
        int option = Integer.parseInt(myObj.nextLine());
        if (option == 0)
        {
            showMenu();
        }
        else if (option == 1 )
        {


            System.out.println("When will you be staying with us ?");
            System.out.println("From year = ");
            int year  =  Integer.parseInt(myObj.nextLine());

            System.out.println("month = ");
            int month = Integer.parseInt(myObj.nextLine());

            System.out.println("day = ");
            int day = Integer.parseInt(myObj.nextLine());

            LocalDate from = LocalDate.of(year,month,day);
            System.out.println("To year = ");
            int year2  =  Integer.parseInt(myObj.nextLine());

            System.out.println("month = ");
            int month2 = Integer.parseInt(myObj.nextLine());

            System.out.println("day = ");
            int day2 = Integer.parseInt(myObj.nextLine());

            LocalDate to = LocalDate.of(year2,month2,day2);

            System.out.println("How many will be staying with us ?");
            System.out.println("people = ");
            int people  =  Integer.parseInt(myObj.nextLine());
//            while (clientController.generateOptions(from,to,people).size() == 0)
//            {
//                System.out.println("No rooms are available at this time please try with other dates or number of people");
//                System.out.println("When will you be staying with us ?");
//                System.out.println("From year = ");
//                 year  =  Integer.parseInt(myObj.nextLine());
//
//                System.out.println("month = ");
//                 month = Integer.parseInt(myObj.nextLine());
//
//                System.out.println("day = ");
//                 day = Integer.parseInt(myObj.nextLine());
//
//                from = LocalDate.of(year,month,day);
//                System.out.println("To year = ");
//                 year2  =  Integer.parseInt(myObj.nextLine());
//
//                System.out.println("month = ");
//                 month2 = Integer.parseInt(myObj.nextLine());
//
//                System.out.println("day = ");
//                 day2 = Integer.parseInt(myObj.nextLine());
//
//                 to = LocalDate.of(year2,month2,day2);
//
//                System.out.println("How many will be staying with us ?");
//                System.out.println("people = ");
//                 people  =  Integer.parseInt(myObj.nextLine());
//            }
            System.out.println("These are your coupons: ");
            List<Coupon> couplist = clientController.showCoupons(client.getId());
            clientView.showCoupons(client.getId());
            System.out.println("Which coupon would you like to use? Type -1 if you wont like to use any \n");
            System.out.println("coupon = ");
            int couponans = Integer.parseInt(myObj.nextLine());
            System.out.println("\n");
            List<Option>optionss = clientController.generateOptions(from,to,people);
            System.out.println("Options:");
            clientView.printOptions(optionss);
            System.out.println("What option do you prefer:");
            int optionnr = Integer.parseInt(myObj.nextLine());
            // make something with options plus
            if (couponans >-1 && couponans < couplist.size() && optionss.size() > 0)
            {
                clientView.makeReservationWithCouponStatus(optionss.get(optionnr - 1),couplist.get(couponans), client.getId(),from,to);
                clientController.removeCoupon(clientController.findCouponById(couponans,client.getId()),client.getId());
            }
            else if(couponans == -1 && optionss.size() > 0)
            {
                clientView.makeReservationStatus(optionss.get(optionnr - 1), client.getId(),from,to);
                if (clientController.seeAllReservations(client.getId()).size() %2 == 0)
                {

                    int random_int = (int)Math.floor(Math.random()*(90-10+1)+10)/10;
                    random_int = random_int * 10;

                    Coupon coupon = new Coupon(random_int);
                    clientController.addCoupon(coupon,client.getId());

                }
            }
            clientMenu(client);
        }
        else if (option == 2)
        {
            clientView.printAllReservations(client.getId());
            clientMenu(client);
        }
        else if (option == 3)
        {
            clientView.printAllReservedRooms(client.getId());
            clientMenu(client);
        }
        else if (option == 4)
        {
            System.out.println("\n");
            System.out.println("Which reservation would you like to delete? Ans = ");
            int resnr = Integer.parseInt(myObj.nextLine());
            System.out.println("\n");
            clientView.deleteReservationStatus(inMemoryReservationRepo.getReservations().get(resnr));
            clientMenu(client);
        }
        else if (option == 5)
        {
            System.out.println("These are your coupons \n:");
            clientView.showCoupons(client.getId());
            clientMenu(client);

        }
        else if (option == 6)
        {
            System.out.println("\n");
            System.out.println("Enter your new First Name:");
            String firstname = myObj.nextLine();
            System.out.println("\n");
            System.out.println("Enter your new Last Name:");
            String lastname = myObj.nextLine();
            System.out.println("\n");
            clientView.changeDetailsStatus(firstname,lastname,client.getId());
            clientMenu(client);
        }
        if (option == 7)
        {
            System.out.println("\n");
            System.out.println("Enter your new Password:");
            String password = myObj.nextLine();
            System.out.println("\n");
            clientView.changePasswordStatus(client.getId(),password);
            clientMenu(client);
        }


    }
    private void cleanerMenu(Cleaner cleaner){
        System.out.println("""
                Hello Cleaner!
                Choose an option!
                RESERVATION
                1.Clean Room
                2.Show Rooms to clean
                PERSONAL INFO
                3.Change FirstName and Last Name
                4.Change Password
                5.See your details
                EXIT
                0.  Logout
               
                
                Enter your option:""");
        Scanner myObj = new Scanner(System.in);
        int option = Integer.parseInt(myObj.nextLine());
        if (option == 0 )
        {
            showMenu();
        }
        else if (option == 1 )
        {
            System.out.println("What room would you like to clean?\n");
            int room  =  Integer.parseInt(myObj.nextLine());
            System.out.println("When ? ");
            int year = Integer.parseInt(myObj.nextLine());
            int month = Integer.parseInt(myObj.nextLine());
            int day = Integer.parseInt(myObj.nextLine());
            LocalDate date = LocalDate.of(year,month,day);
            cleanerView.cleanroomStatus(cleaner.getId(),room,date);

            cleanerMenu(cleaner);
        }
        else if (option == 2)
        {
            System.out.println("These rooms must be cleaned:");
            cleanerView.printRooms();
            cleanerMenu(cleaner);
        }
        else if (option == 3)
        {
            System.out.println("Enter your new First Name:");
            String firstname = myObj.nextLine();
            System.out.println("Enter your new Last Name:");
            String lastname = myObj.nextLine();
            cleanerView.changeDetailsStatus(firstname,lastname,cleaner.getId());

            cleanerMenu(cleaner);
        }
        else if (option == 4)
        {
            System.out.println("Enter your new Password:");
            String password = myObj.nextLine();
            cleanerView.changePasswordStatus(cleaner.getId(),password);

            cleanerMenu(cleaner);
        }
        else if (option == 5){
            cleanerView.showUserDetails(cleaner);
            cleanerMenu(cleaner);
        }
    }
    private void managerMenu(){
        System.out.println("""
                Hello Manager!
                Choose an option!
                CLIENTS
                1.  See all clients
                2.  Find client by username
                3.  Delete client
                ROOMS
                4.  See all rooms
                5.  Add room
                6.  Delete room
                7.  Update room details
                8.  Find room by id
                CLEANERS
                9.  See all cleaners
                10. Find cleaner by username
                11. Modify salary for cleaner
                12. Delete cleaner
                CLEANINGS
                13. Add a room to be cleaned by a cleaner
                14. See all rooms that a cleaner has not cleaned yet
                15. See all cleaneaners that have to clean a certain room
                EXIT
                17. See all available rooms
                0.  Logout
                16. Exit
                
                Enter your option:""");
        Scanner myObj = new Scanner(System.in);
        int option = Integer.parseInt(myObj.nextLine());

        switch (option) {
            case 0 -> {
                showMenu();
                return;
            }
            case 1 -> managerView.printAllClients();
            case 2 -> {
                System.out.println("Enter the username of the client you want to find:");
                String username = myObj.nextLine();
                managerView.findClientByUsernameStatus(username);
            }
            case 3 -> {
                System.out.println("Enter the id of the client you want to delete:");
                int id = Integer.parseInt(myObj.nextLine());
                managerView.deleteClientStatus(id);
            }
            case 4 -> managerView.printAllRooms();
            case 5 -> {
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
                double price = myObj.nextDouble();
                System.out.println("Enter capacity (persons):");
                int nrPers = Integer.parseInt(myObj.nextLine());

                managerView.addRoomStatus(type,price,nrPers);
            }
            case 6 -> {
                System.out.println("Enter the id of the room you want to delete:");
                int id = Integer.parseInt(myObj.nextLine());

                managerView.deleteRoomStatus(id);
            }
            case 7 -> {
                System.out.println("Enter the id of the room you want to update:");
                int id = Integer.parseInt(myObj.nextLine());

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

                managerView.updateRoomStatus(id, type, price, nrPers);
            }
            case 8 -> {
                System.out.println("Enter the id of the room you want to find:");
                int id = Integer.parseInt(myObj.nextLine());
                managerView.findRoomByIdStatus(id);
            }
            case 9 -> managerView.printAllCleaners();
            case 10 -> {
                System.out.println("Enter the username of the cleaner you want to find:");
                String username = myObj.nextLine();
                managerView.findCleanerByUsernameStatus(username);
            }
            case 11 -> {
                System.out.println("Enter the username of the cleaner you want to change salary:");
                int id = Integer.parseInt(myObj.nextLine());
                System.out.println("Enter the new salary for cleaner "+id);
                int salary = Integer.parseInt(myObj.nextLine());
                managerView.setSalarySatus(id, salary);
            }
            case 12 -> {
                System.out.println("Enter the username of the cleaner you want to delete");
                int id = Integer.parseInt(myObj.nextLine());
                managerView.deleteCleanerStatus(id);
            }
            case 13 -> {
                System.out.println("Enter the username of the cleaner and the room you want him to tidy. Cleaner username = ");
                String username = myObj.nextLine();
                System.out.println("The room id:");
                int id = Integer.parseInt(myObj.nextLine());
                managerView.addRoomtoCleanerStatus(id,username);
            }
            case 14 -> {
                System.out.println("For what cleaner do you want to see wich rooms he has to clean? Cleaner username :");
                String username = myObj.nextLine();
                managerView.printUncleanedRooms(username);
            }
            case 15 -> {
                System.out.println("For what rooms do you want to see the cleaners? Room id :");
                int id = Integer.parseInt(myObj.nextLine());
                managerView.printCleanersForRoom(id);
            }
            case 16 -> {
                System.out.println("Bye!!!");
                System.exit(1);
            }
            case 17 -> {

                System.out.println("When will you be staying with us ?");
                System.out.println("From year = ");
                int year  =  Integer.parseInt(myObj.nextLine());

                System.out.println("month = ");
                int month = Integer.parseInt(myObj.nextLine());

                System.out.println("day = ");
                int day = Integer.parseInt(myObj.nextLine());

                LocalDate from = LocalDate.of(year,month,day);
                System.out.println("To year = ");
                int year2  =  Integer.parseInt(myObj.nextLine());

                System.out.println("month = ");
                int month2 = Integer.parseInt(myObj.nextLine());

                System.out.println("day = ");
                int day2 = Integer.parseInt(myObj.nextLine());

                LocalDate to = LocalDate.of(year2,month2,day2);
                List<Room> rooms =   clientController.searchAvailableRoom(from,to);
                for (Room r : rooms)
                {
                    System.out.println(r.toString());
                }


            }
            default -> System.out.println("Not a valid option!");
        }

        managerMenu();
    }


}
