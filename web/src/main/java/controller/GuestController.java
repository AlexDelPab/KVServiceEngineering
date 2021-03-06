package main.java.controller;

import main.java.database.entities.Employer;
import main.java.database.entities.Guest;
import main.java.database.entities.Room;
import main.java.database.helpers.EmployerDBHelper;
import main.java.database.helpers.GuestDBHelper;
import main.java.database.helpers.RoomDBHelper;

import java.sql.SQLException;
import java.util.List;

public class GuestController {

    public GuestController() {
    }

    public List<Guest> getAllGuests() {
        return GuestDBHelper.findAll();
    }

    public Room getRoomById(int id) throws SQLException {
        return RoomDBHelper.findById(id);
    }

    public void create(String firstName, String lastName, String street, String zip, String city, String country, String room) {
        if (firstName == null || firstName.equals("") || lastName == null || lastName.equals("")) {
            return;
        }

        Guest guest = new Guest(firstName, lastName, street, zip, city, country);

        GuestDBHelper.insert(guest);
    }

    // Test functionalities
    public static void main(String[] args) {

        try {
            System.out.println(EmployerDBHelper.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
