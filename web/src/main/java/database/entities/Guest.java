package main.java.database.entities;

public class Guest extends Person {

    /**
     * Room
     */
    private int occupiesRoom;

    public Guest() {
    }

    public Guest(String firstName, String lastName, String street, String zip, String city, String country) {
        super(firstName, lastName, street, zip, city, country);
    }

    public Guest(String firstName, String lastName, String street, String zip, String city, String country, int occupiesRoom) {
        super(firstName, lastName, street, zip, city, country);
        this.occupiesRoom = occupiesRoom;
    }

    public Guest(int id, String first_name, String last_name, String street, String zip, String city, String country, int occupiesRoom) {
        super(id, first_name, last_name, street, zip, city, country);
        this.occupiesRoom = occupiesRoom;
    }

    public int getOccupiesRoom() {
        return occupiesRoom;
    }

    public void setOccupiesRoom(int occupiesRoom) {
        this.occupiesRoom = occupiesRoom;
    }
}
