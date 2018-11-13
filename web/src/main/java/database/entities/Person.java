package main.java.database.entities;

public abstract class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String street;
    private String zip;
    private String city;
    private String country;

    public Person(String firstName, String lastName, String street, String zip, String city, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public int getId() {
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getAddress() {
        return this.getStreet() + ", " + this.getZip() + " " + this.getCity();
    }
}
