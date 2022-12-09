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

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();


        manager.getTransaction().begin();

        manager.getTransaction().commit();


        Ui ui = new Ui();
        ui.showMenu();
    }


}