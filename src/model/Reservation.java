package model;

import java.time.LocalDate;

public class Reservation {
    private static int id;
    private String idUser;
    private LocalDate start;
    private LocalDate end;
    public Reservation(String username, LocalDate start, LocalDate end) {
        id = 1;
        this.idUser = username;
        this.start = start;
        this.end = end;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public static int getId() {
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
        return "Reservation: " + id + " Client " + idUser + " checkIn " + start + " checkOut " + end;
    }
}
