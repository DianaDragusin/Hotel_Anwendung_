package model;

public class Reservation_Room {
    private String reservation;
    private String room;

    public Reservation_Room(String reservation, String room) {
        this.reservation = reservation;
        this.room = room;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
