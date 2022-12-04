package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private Integer clientId;
    private LocalDate start;
    private LocalDate end;
    private double price;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private List<Room> rooms;

    public Reservation(Integer clientId, LocalDate start, LocalDate end, double price) {
        this.clientId = clientId;
        this.start = start;
        this.end = end;
        this.price = price;
        this.rooms = new ArrayList<>();
    }

    public Reservation() {

    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Integer getClientId() {
        return clientId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getUsername() {
        return clientId;
    }

    public void setUsername(Integer idUser) {
        this.clientId = idUser;
    }

    public int getId() {
        return id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Reservation: " + id + " Client " + clientId + " checkIn " + start + " checkOut " + end;
    }
}
