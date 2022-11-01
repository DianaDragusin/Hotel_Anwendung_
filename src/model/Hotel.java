package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static int id;
    private String name;
    private List<Room> rooms;
    private double singleRPrice;
    private double doubleRPrice;
    private double tripleRPrice;
    private double apartmentRPrice;

    public Hotel(String name, double singleRPrice, double doubleRPrice, double tripleRPrice, double apartmentRPrice) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.singleRPrice = singleRPrice;
        this.doubleRPrice = doubleRPrice;
        this.tripleRPrice = tripleRPrice;
        this.apartmentRPrice = apartmentRPrice;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Hotel.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public double getSingleRPrice() {
        return singleRPrice;
    }

    public void setSingleRPrice(double singleRPrice) {
        this.singleRPrice = singleRPrice;
    }

    public double getDoubleRPrice() {
        return doubleRPrice;
    }

    public void setDoubleRPrice(double doubleRPrice) {
        this.doubleRPrice = doubleRPrice;
    }

    public double getTripleRPrice() {
        return tripleRPrice;
    }

    public void setTripleRPrice(double tripleRPrice) {
        this.tripleRPrice = tripleRPrice;
    }

    public double getApartmentRPrice() {
        return apartmentRPrice;
    }

    public void setApartmentRPrice(double apartmentRPrice) {
        this.apartmentRPrice = apartmentRPrice;
    }

}
