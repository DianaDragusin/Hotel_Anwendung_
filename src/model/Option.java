package model;

import java.util.List;

public class Option {
    private int id;
    private double totalPrice;
    private List<Room> rooms;

    public Option(int id, double totalPrice, List<Room> rooms) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
