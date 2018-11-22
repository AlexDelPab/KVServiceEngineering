package main.java.database.entities;

import java.util.Date;

public class Reservation {
    /**
     * equals reservation Number
     */
    private int id;
    private Date occupiedFrom;
    private Date occupiedTo;
    private int guest;
    private int room;
    private String status;

    public Reservation(int id, int occupiedFrom, int occupiedTo, int guest, int room, String status) {
        this.id = id;
        this.occupiedFrom = new Date(occupiedFrom);
        this.occupiedTo = new Date(occupiedTo);
        this.guest = guest;
        this.room = room;
        this.status = status;
    }

    public Reservation(int id, Date occupiedFrom, Date occupiedTo, int guest, int room, String status) {
        this.id = id;
        this.occupiedFrom = occupiedFrom;
        this.occupiedTo = occupiedTo;
        this.guest = guest;
        this.room = room;
        this.status = status;
    }

    public Reservation(int occupiedFrom, int occupiedTo, int guest, int room, String status) {
        this.occupiedFrom = new Date(occupiedFrom);
        this.occupiedTo = new Date(occupiedTo);
        this.guest = guest;
        this.room = room;
        this.status = status;
    }

    public Reservation(Date occupiedFrom, Date occupiedTo, int guest, int room, String status) {
        this.occupiedFrom = occupiedFrom;
        this.occupiedTo = occupiedTo;
        this.guest = guest;
        this.room = room;
        this.status = status;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOccupiedFrom() {
        return occupiedFrom;
    }

    public void setOccupiedFrom(Date occupiedFrom) {
        this.occupiedFrom = occupiedFrom;
    }

    public Date getOccupiedTo() {
        return occupiedTo;
    }

    public void setOccupiedTo(Date occupiedTo) {
        this.occupiedTo = occupiedTo;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
