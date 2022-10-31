package model;

public class Employee extends Person {
    private Hotel hotel;

    public Employee(String firstName, String lastName, Hotel hotel) {
        super(firstName, lastName);
        this.hotel=hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void cleanRoom(Room room){

    }
}
