package model;

import java.time.LocalDate;

public class Reservation {
    private static int id;
    private String username;
    private LocalDate start;
    private LocalDate end;
    public Reservation(String username, LocalDate start, LocalDate end) {
        id = 1;
        this.username = username;
        this.start = start;
        this.end = end;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "Reservation: " + id + " Client " + username + " checkIn " + start + " checkOut " + end;
    }
}
