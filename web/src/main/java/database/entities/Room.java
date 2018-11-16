package main.java.database.entities;

import main.java.helpers.Const;
import main.java.helpers.JAVAUtils;

public class Room {

    /**
     * equals room Number
     */
    private int id;

    /**
     * deluxe, classic, president
     */
    private String type;

    private int occupied;

    /**
     * Guest
     */
    private int occupiedBy;

    public Room(int id, String type, int occupied, int occupiedBy) {
        this.id = id;
        this.type = type;
        this.occupied = occupied;
        this.occupiedBy = occupiedBy;
    }

    public Room(String type, int occupied) {
        this.type = type;
        this.occupied = Const.FALSE;
    }

    public Room(){}

    public Room(String type, int occupied, int occupiedBy) {
        this.type = type;
        this.occupied = occupied;
        this.occupiedBy = occupiedBy;
    }

    /**
     * Helper
     */
    public boolean getOccupiedBool() {
        return JAVAUtils.getBool(this.occupied);
    }

    /**
     * Getter
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int isOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public int getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(int occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
}

