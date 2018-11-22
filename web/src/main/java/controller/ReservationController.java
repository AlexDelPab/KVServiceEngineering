package main.java.controller;

import main.java.database.entities.Guest;
import main.java.database.entities.Reservation;
import main.java.database.entities.Room;
import main.java.database.helpers.GuestDBHelper;
import main.java.database.helpers.ReservationDBHelper;
import main.java.database.helpers.RoomDBHelper;
import main.java.helpers.Const;

import javax.sound.midi.SysexMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationController {

    private List<Reservation> reservations;
    private List<Guest> guests;
    private List<Room> rooms;

    public ReservationController() {
    }

    public List<Reservation> getAllReservations() {
        this.reservations = ReservationDBHelper.findAll();

        return reservations;
    }

    public List<Guest> getAllGuests() {
        this.guests = GuestDBHelper.findAll();

        return guests;
    }

    public List<Room> getAllRooms() {
        this.rooms = RoomDBHelper.findAll();

        return rooms;
    }

    public List<Room> getAllFreeRooms() {
        this.rooms = RoomDBHelper.findAllFree();

        return rooms;
    }

    public List<Room> getReservatedRooms() {
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < this.reservations.size(); i++) {
            if (reservations.get(i).getStatus().equals(Const.STATUS_OPEN)) {
                rooms.add(RoomDBHelper.findById(reservations.get(i).getRoom()));
            }
        }

        return rooms;
    }

    public void checkIn(String roomId) {
        if (roomId == null) {
            return;
        }

        int room = Integer.valueOf(roomId);
        int guest = -1;
        int reservation = -1;

        for (int i = 0; i < this.reservations.size(); i++) {
            if (reservations.get(i).getRoom() == room) {
                if (reservations.get(i).getStatus().equals(Const.STATUS_OPEN)) {
                    if(!RoomDBHelper.findById(reservations.get(i).getRoom()).getOccupiedBool()) {
                        reservation = reservations.get(i).getId();
                        guest = reservations.get(i).getGuest();
                    }
                }
            }
        }

        if (guest == -1) {
            return;
        }

        RoomDBHelper.updateOccupied(room, guest);
        ReservationDBHelper.updateStatus(reservation, Const.STATUS_CHECKD_IN);
    }

    public void create(String from, String to, String roomId, String guestId) {
        if (from == null || to == null || roomId == null || guestId == null) {
            return;
        }

        int room = Integer.valueOf(roomId);
        int guest = Integer.valueOf(guestId);

        boolean r = false;
        boolean u = false;

        for (int i = 0; i < this.rooms.size(); i++) {
            if (rooms.get(i).getId() == room) {
                r = true;
            }
        }

        if (!r) {
            return;
        }

        for (int i = 0; i < this.guests.size(); i++) {
            if (guests.get(i).getId() == guest) {
                u = true;
            }
        }

        if (!u) {
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ReservationDBHelper.insert(new Reservation(formatter.parse(from), formatter.parse(to), room, guest, Const.STATUS_OPEN));
        } catch (ParseException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
