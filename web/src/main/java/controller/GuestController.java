package main.java.controller;

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
        try {
            return GuestDBHelper.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Room getRoomById(int id) throws SQLException {
        return RoomDBHelper.findById(id);
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
