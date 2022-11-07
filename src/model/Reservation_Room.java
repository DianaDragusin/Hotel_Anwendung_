package model;

public class Reservation_Room {
    private int reservation;
    private int room;

    public Reservation_Room(int reservation, int room) {
        this.reservation = reservation;
        this.room = room;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
