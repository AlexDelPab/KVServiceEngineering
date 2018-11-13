package main.java.database.entities;

public class Employer extends Person {

    public Employer(String firstName, String lastName, String street, String zip, String city, String country) {
        super(firstName, lastName, street, zip, city, country);
    }

    public Employer(int id, String firstName, String lastName, String street, String zip, String city, String country) {
        super(id, firstName, lastName, street, zip, city, country);
    }
}
