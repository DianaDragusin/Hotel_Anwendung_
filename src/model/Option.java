package model;

import java.util.ArrayList;
import java.util.List;

public class Option {
    private static int id;
    private double totalPrice;
    private List<Room> rooms;

    public Option(double totalPrice, List<Room> rooms) {
        id = 1;
        this.totalPrice = totalPrice;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    private List<Room> getRoomsBYType(Type type){
        List<Room> typeRooms = new ArrayList<>();
        for(Room r:rooms){
            if(r.getType().equals(type)){
                typeRooms.add(r);
            }
        }
        return typeRooms;
    }

    private String roomListToString(List<Room> rooms){
        String stringRooms = "";

        List<Room> singleR = getRoomsBYType(Type.SINGLE);
        List<Room> doubleR = getRoomsBYType(Type.DOUBLE);
        List<Room> tripleR = getRoomsBYType(Type.TRIPLE);
        List<Room> apartmentR = getRoomsBYType(Type.APARTMENT);

        if(singleR.size()!=0){
            stringRooms = stringRooms.concat(String.valueOf(singleR.size()));
            stringRooms = stringRooms.concat("SINGLE Room ");
        }
        if(doubleR.size()!=0){
            stringRooms = stringRooms.concat(String.valueOf(doubleR.size()));
            stringRooms = stringRooms.concat(" DOUBLE Room ");
        }
        if(tripleR.size()!=0){
            stringRooms = stringRooms.concat(String.valueOf(tripleR.size()));
            stringRooms = stringRooms.concat(" TRIPLE Room ");
        }
        if(doubleR.size()!=0){
            stringRooms = stringRooms.concat(String.valueOf(apartmentR.size()));
            stringRooms = stringRooms.concat(" APARTMENT ");
        }
        return stringRooms;
    }
    @Override
    public String toString() {
        return "Option: " + id + ", Rooms: " + roomListToString(rooms) + " Price: " + totalPrice;
    }
}
