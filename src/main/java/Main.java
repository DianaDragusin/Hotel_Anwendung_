import model.*;

import org.hibernate.Session;
import repository.inMemoryRepo.*;
import service.CleanerController;
import service.ClientController;
import service.ManagerController;
import views.ClientView;
import views.ManagerView;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        Room r  = new Room(Type.TRIPLE,2000,3);
        Cleaner c = new Cleaner("a", "a", "a" ,"a");
        c.setSalary(200);
        Client client = new Client("a", "a", "b" ,"b");
        Reservation res = new Reservation(LocalDate.of(2002,2,1), LocalDate.of(2002,2,3),2000);
        Reservation res2 = new Reservation(LocalDate.of(2003,2,1), LocalDate.of(2003,2,3),2000);

        client.addReservation(res);
        Room  room = new Room(Type.DOUBLE,1000,2);
        Room  room1 = new Room(Type.DOUBLE,1000,2);
        Room  room2 = new Room(Type.SINGLE,1000,1);

        Client client2 = new Client("a", "a", "c" ,"b");
        Client client3 = new Client("b", "b", "b" ,"b");

        Reservation res3 = new Reservation(LocalDate.of(2003,2,1), LocalDate.of(2003,2,3),2000);
        client2.addReservation(res3);
        Reservation res4 = new Reservation(LocalDate.of(2003,2,1), LocalDate.of(2009,2,3),2000);



        manager.getTransaction().begin();
        // manager.persist(r);
        // manager.persist(room2);
      //  manager.persist(client3);
       //
       // Query query = manager.createNativeQuery("SELECT * FROM Client",Client.class);
      ////  List<Client> clients = query.getResultList();
     ////   for(Client cl : clients){
       //     System.out.println(cl);
      //  }

      //  manager.persist(client2);
       // manager.persist(room1);
      //  manager.persist(res4);
        manager.getTransaction().commit();


        /* Client client22 = (Client) query2.getSingleResult();
        Reservation res5 = new Reservation(LocalDate.of(2006,2,1), LocalDate.of(2006,2,3),2000);
        client22.addReservation(res5);
        manager.persist(res5);


        Query query =  manager.createNativeQuery("select * from Reservation where id=:reservationNr ",Reservation.class);
        query.setParameter("reservationNr","6");
       Reservation foundCourse = (Reservation) query.getSingleResult();
//        System.out.println(foundCourse.getClientid());
        manager.getTransaction().commit();



     EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        // Room r  = new Room(Type.TRIPLE,2050,3);
       // Client c = new Client("ana", "maria", "anaa" ,"mariaa");
       // Query query2 =  manager.createNativeQuery("select * from Client where username=:reservationNr ",Client.class);
        //query2.setParameter("reservationNr","anaa");
        //Client foundCourse = (Client) query2.getSingleResult();
        //System.out.println(foundCourse);

      int clientId = 9;

        Query query = manager.createNativeQuery("SELECT  FROM Client WHERE username=:idCl", Client.class);
        query.setParameter("idCl", "e");
        int nr = (int) query.getSingleResult();
        System.out.println(nr);
 EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        Client c = new Client("b", "b", "b" ,"b");
        Coupon coupon = new Coupon(30);



       // manager.getTransaction().begin();
        List<Room>rooms;
        Query query = manager.createNativeQuery("SELECT * FROM Room", Room.class);
        rooms = (List<Room>) query.getResultList();
        System.out.println(rooms);
       // manager.persist(c);

        //Client cl =  manager.find(Client.class,22);


        //cl.addCoupon(coupon);
        //manager.persist(coupon);
        //cl.setCouponList(new ArrayList<>());
       // manager.remove(cl);
        //Coupon coupon2  = manager.find(Coupon.class,10);
        //cl.removeCoupon(coupon2);
        //manager.remove(coupon2);
        //System.out.println(cl);
        //System.out.println(cl.getCouponList());
        //System.out.println(coupon2);
        //manager.remove(coupon2);
        //manager.persist(c);

        //Coupon cou = manager.find(Coupon.class,2);
       // manager.remove(coupon);
        //System.out.println(coupon);
       // c.setFirstName("test");
        //manager.merge(c);

        //manager.detach(cl);
        //cl.setFirstName("test");
        //

       // Client cl =  manager.find(Client.class,10);
       // manager.remove(cl);
       // manager.detach(cl);
      //  System.out.println(cl);
     //   manager.getTransaction().commit();
      //  c.setId(1);

 */

      //  EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
       // EntityManager manager = factory.createEntityManager();

        //Client c = manager.find(Client.class,5);
        //System.out.println(c);

        boolean memory = false;
        Ui ui = new Ui(memory);
        ui.showMenu();



    }


}