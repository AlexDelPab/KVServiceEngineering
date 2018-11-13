package main.java.database.entities;

public class Guest extends Person {
    public Guest(String firstName, String lastName, String street, String zip, String city, String country) {
        super(firstName, lastName, street, zip, city, country);
    }

    public Guest(int id, String firstName, String lastName, String street, String zip, String city, String country) {
        super(id, firstName, lastName, street, zip, city, country);
    }
}
