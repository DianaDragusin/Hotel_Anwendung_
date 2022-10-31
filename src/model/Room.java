package model;

public class Room {
    private int id;
    private Type type;
    private double price;
    private int nrPers;

    public Room(int id, Type type, double price, int nrPers) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.nrPers = nrPers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
