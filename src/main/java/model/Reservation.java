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

    private LocalDate startDate;
    private LocalDate endDate;
    private int clientid;
    private int price;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private List<Room> rooms;

    public Reservation(int clientId, LocalDate start, LocalDate end, int price) {
        this.clientid = clientId;
        this.startDate = start;
        this.endDate = end;
        this.price = price;
        this.rooms = new ArrayList<>();
    }

    public Reservation() {

    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public int getId() {
        return id;
    }

    public LocalDate getStart() {
        return startDate;
    }

    public void setStart(LocalDate start) {
        this.startDate = start;
    }

    public LocalDate getEnd() {
        return endDate;
    }

    public void setEnd(LocalDate end) {
        this.endDate = end;
    }

    @Override
    public String toString() {
        return "Reservation: " + id + " checkIn " + startDate + " checkOut " + endDate;
    }
}
