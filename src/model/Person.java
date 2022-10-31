package model;

public abstract class Person {
    private static int id;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        id = 1;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
