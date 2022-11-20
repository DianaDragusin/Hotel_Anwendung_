package model;

import java.time.LocalDate;

public class Reservation {
    public static int state;
    private  int id;
    private String username;
    private LocalDate start;
    private LocalDate end;
    private double price;
    public Reservation(String username, LocalDate start, LocalDate end, double price) {
        state++;
        id = state;
        this.username = username;
        this.start = start;
        this.end = end;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String idUser) {
        this.username = idUser;
    }

    public  int getId() {
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
        return "Reservation: " + id + " Client " + username + " checkIn " + start + " checkOut " + end;
    }
}
