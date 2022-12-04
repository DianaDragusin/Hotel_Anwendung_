package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Cleaner extends Person {
    private int salary;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cleaner_room" ,
            joinColumns = @JoinColumn(name = "cleaner_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private  List<Room> rooms;

    public Cleaner(String firstName, String lastName, String username, String password) {
        super(firstName, lastName, username, password);
        this.salary = 0;
        rooms = new ArrayList<>();
    }



    public Cleaner() {

    }

    public List<Room> getRooms() {
        return rooms;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void addRoomToClean(Room room){
        this.rooms.add(room);
    }

    public void deleteRoomToClean(Room room){
        this.rooms.remove(room);
    }

    @Override
    public String toString() {
        return "Cleaner(" + getUsername() + ", " + getFirstName() + ", " + getLastName() + ", salary: " + getSalary() + " lei)";
    }
}
