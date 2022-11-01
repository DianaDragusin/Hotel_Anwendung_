package model;

import java.time.LocalDate;

public class Cleaning {
    private Room room;
    private Cleaner cleaner;
    private LocalDate date;

    public Cleaning(Room room, Cleaner cleaner, LocalDate date) {
        this.room = room;
        this.cleaner = cleaner;
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public Cleaner getCleaner() {
        return cleaner;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Cleaning{" +
                "room=" + room +
                ", cleaner=" + cleaner +
                ", date=" + date +
                '}';
    }
}
