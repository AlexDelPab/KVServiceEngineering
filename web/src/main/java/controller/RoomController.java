package main.java.controller;

import main.java.database.entities.Guest;
import main.java.database.entities.Reservation;
import main.java.database.entities.Room;
import main.java.database.helpers.GuestDBHelper;
import main.java.database.helpers.ReservationDBHelper;
import main.java.database.helpers.RoomDBHelper;

import java.sql.SQLException;
import java.util.List;

public class RoomController {

    public RoomController() {
    }

    public List<Room> getAllRooms() {
        return RoomDBHelper.findAll();
    }

    public Guest getGuestById(int id) throws SQLException {
        return GuestDBHelper.findById(id);
    }

    public Reservation getReservationByGuestId(int id) {
        return ReservationDBHelper.findByRoomId(id);
    }


    public String doPost(String type, String roomId, String guestId) {
        if (roomId == null) {
            return null;
        }

        System.out.println("doPost with params type " + type + " roomId " + roomId + " guestId " + guestId);
        System.out.println("==============================");

        if (type.equals("checkOut")) {
            System.out.println("room as string: " + roomId);
            int room = Integer.valueOf(roomId);
            System.out.println("room as int: " + room);

            RoomDBHelper.updateOccupied(room, -1);

            return "Successfully checked-out RoomId: " + room;
        } else if (type.equals("checkIn")) {
            if (guestId == null) {
                return null;
            }

            int room = Integer.valueOf(roomId);
            int guest = Integer.valueOf(guestId);

            RoomDBHelper.updateOccupied(room, guest);

            return "Successfully checkedIn RoomId: " + room + " and Guest " + guest;
        }

        if (type.isEmpty() && roomId.isEmpty() && guestId.isEmpty()) {
            return "";
        }

        return "Nothing happened with type " + type + " - " + roomId + " - " + guestId;
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
