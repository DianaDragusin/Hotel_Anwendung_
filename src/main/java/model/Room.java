package model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Type type;
    private double price;
    private int nrPers;
    @ManyToMany(mappedBy = "rooms")
    private List<Cleaner> cleaners;

    public Room( Type type, double price, int nrPers) {
        this.type = type;
        this.price = price;
        this.nrPers = nrPers;
    }

    public Room() {

    }

    public List<Cleaner> getCleaners() {
        return cleaners;
    }

    public void addCleaner(Cleaner cleaner){
        this.cleaners.add(cleaner);
    }

    public void deleteCleaner(Cleaner cleaner){
        this.cleaners.remove(cleaner);
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
        return "Room(" + id + ", Type: " + type + ", " + price +" lei, " + nrPers + " Pers)";
    }
}
