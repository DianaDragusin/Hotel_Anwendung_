import model.*;

import repository.inMemoryRepo.*;
import service.CleanerController;
import service.ClientController;
import service.ManagerController;
import views.ClientView;
import views.ManagerView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        Client c = new Client("a","a","a","a");
        Cleaner cl = new Cleaner("a","a","b","a");
        cl.setSalary(300);

        manager.getTransaction().begin();

//        manager.persist(cl);
//        manager.persist(c);
        manager.getTransaction().commit();

*/
        Ui ui = new Ui();
        ui.showMenu();
    }


}