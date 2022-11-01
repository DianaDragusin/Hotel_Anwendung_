package model;

public class Room {
    private static int id;
    private Type type;
    private double price;
    private int nrPers;

    public Room( Type type, double price, int nrPers) {
        this.id = 1;
        this.type = type;
        this.price = price;
        this.nrPers = nrPers;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNrPers() {
        return nrPers;
    }

    public void setNrPers(int nrPers) {
        this.nrPers = nrPers;
    }

    @Override
    public String toString() {
        return "Room(" + id + ", Type: " + type + ", " + price +" lei, " + nrPers + "Pers)";
    }
}
