package model;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private Integer clientId;
    private LocalDate start;
    private LocalDate end;
    private double price;
    public Reservation(Integer clientId, LocalDate start, LocalDate end, double price) {
        this.clientId = clientId;
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
