package model;

import java.time.LocalDate;

public class Reservation {
    private static int id;

    private int idClient;
    private LocalDate start;
    private LocalDate end;
    public Reservation(LocalDate start, LocalDate end) {
        id = 1;
        this.start = start;
        this.end = end;
    }

    public static int getId() {
        return id;
    }


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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
}
