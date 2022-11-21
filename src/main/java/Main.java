import model.*;
import repository.fileRepo.FileClientRepo;
import repository.inMemoryRepo.*;
import service.CleanerController;
import service.ClientController;
import service.ManagerController;
import views.ClientView;
import views.ManagerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//      Reservation.state = 0;
//      List<Client>clients = new ArrayList<>();
//      List<Cleaner>cleaners = new ArrayList<>();
//      List<Cleaning>cleanings = new ArrayList<>();
//      List<Reservation>reservations = new ArrayList<>();
//      List<Reservation_Room>res_room = new ArrayList<>();
//        List<Room>rooms = new ArrayList<>();
//      InMemoryClientRepo client_repo = new InMemoryClientRepo(clients);
//      InMemoryCleaningsRepo cleaningsRepo = new InMemoryCleaningsRepo(cleanings);
//      InMemoryCleanerRepo cleanerRepo = new InMemoryCleanerRepo(cleaners);
//      InMemoryRoomRepo roomRepo = new InMemoryRoomRepo(rooms);
//      InMemoryReservationRepo reservationREpo = new InMemoryReservationRepo(reservations,res_room);
//
//      ClientController ccontroller = new ClientController(client_repo,roomRepo,reservationREpo,cleanerRepo,cleaningsRepo);
//      ClientView clientview = new ClientView(ccontroller);
//      ccontroller.makeReservation( ccontroller.generateOptions(LocalDate.now(),LocalDate.now().plusDays(3),2).get(0),"laurgeor",LocalDate.now(),LocalDate.now().plusDays(3));
//      ccontroller.makeReservation( ccontroller.generateOptions(LocalDate.now(),LocalDate.now().plusDays(3),2).get(0),"bobpop",LocalDate.now(),LocalDate.now().plusDays(3));
//    // clientview.printAllReservations(client_repo.findbyID(1).getUsername());
//     // clientview.printAllReservations(client_repo.findbyID(0).getUsername());
//       clientview.printAllReservedRooms(client_repo.findbyID(1).getUsername());
//       clientview.printAllReservedRooms(client_repo.findbyID(0).getUsername());
//      for (Integer res : reservationREpo.returnAllUnAvailableRooms(LocalDate.now(),LocalDate.now().plusDays(3)))
//      {
//        System.out.println(res);
//      }
//        for ( Integer res :reservationREpo.GetAllReservedRoomsForAUser("bobpop")) {
//            System.out.println(res.toString());
//        }
//        for ( Reservation res :reservationREpo.GetAllReservationsForAUser("bobpop")) {
//            System.out.println(res.getId());
//        }
      /*
      Client c = new Client("Ana", "Maria","anamaria20","1234");
      List<Coupon> l = new ArrayList<>();
      Coupon cou = new Coupon(1234,20);
      Coupon cou2 = new Coupon(123434,60);
      Coupon cou3 = new Coupon(12834,90);

      l.add(cou); l.add(cou2); l.add(cou3);
      c.setCouponList(l);
      System.out.println(c);

      FileClientRepo fcr = new FileClientRepo();
      */


        Ui ui = new Ui();
        ui.showMenu();
    }


}