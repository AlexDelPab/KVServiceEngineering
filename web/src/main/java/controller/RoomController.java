package main.java.controller;

import main.java.database.entities.Guest;
import main.java.database.entities.Room;
import main.java.database.helpers.GuestDBHelper;
import main.java.database.helpers.RoomDBHelper;

import java.sql.SQLException;
import java.util.List;

public class RoomController {

    public RoomController() {
    }

    public List<Room> getAllRooms() {
        try {
            return RoomDBHelper.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Guest getGuestById(int id) throws SQLException {
        return GuestDBHelper.findById(id);
    }

//    public Employer createEmployer(String firstName, String lastName, String street, String zip, String city, String country) {
//        Employer employer = new Employer(firstName, lastName, street, zip, city, country);
//
//        try {
//            EmployerDBHelper.insertEntity(employer);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return employer;
//    }

    // Test functionalities
    public static void main(String[] args) {

        try {
            Guest guest = GuestDBHelper.findById(0);
            System.out.println(guest.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
