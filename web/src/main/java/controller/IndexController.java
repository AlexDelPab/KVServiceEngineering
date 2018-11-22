package main.java.controller;

import main.java.database.entities.Reservation;
import main.java.database.helpers.ReservationDBHelper;
import main.java.database.helpers.RoomDBHelper;

import java.util.List;

public class IndexController {

    private List<Reservation> reservations;

    public IndexController() {
    }

    public List<Reservation> getAllReservations () {
        this.reservations = ReservationDBHelper.findAll();

        return reservations;
    }

    public void checkIn (String roomId) {
        if (roomId == null ) {
            return;
        }

        int room = Integer.valueOf(roomId);
        int guest = -1;

        for (int i = 0; i < this.reservations.size(); i++) {
            if (reservations.get(i).getRoom() == room) {
                guest = reservations.get(i).getGuest();
            }
        }

        if (guest == -1) {
            return;
        }

        RoomDBHelper.updateOccupied(room, guest);
    }
}
