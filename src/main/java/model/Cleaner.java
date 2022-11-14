package model;

import java.time.LocalDate;

public class Cleaner extends Person {
    private int salary;

    public Cleaner(String firstName, String lastName, String username, String password) {
        super(firstName, lastName, username, password);
        this.salary = 0;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Cleaning cleanRoom(Room room, LocalDate date){
        return new Cleaning(room,this, date);
    }

    @Override
    public String toString() {
        return "Cleaner(" + getFirstName() + ", " + getLastName() + ", salary: " + getSalary() + " lei)";
    }
}
