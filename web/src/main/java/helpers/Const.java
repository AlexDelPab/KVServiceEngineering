package main.java.helpers;

public final class Const {

    /**
     * Rooms
     */
    public static final String ROOM_CLASSIC = "classic";
    public static final String ROOM_DELUXE = "deluxe";
    public static final String ROOM_PRESIDENT = "president";

    /**
     * SQLite does not have a separate Boolean storage class. Instead, Boolean values are stored as integers 0 (false) and 1 (true).
     */
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    public static final int NULL = -1;

    /**
     * Useful
     */
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");

    /**
     * Reservation Status
     */
    public static final String STATUS_OPEN = "open";
    public static final String STATUS_CHECKD_IN = "check_in";
    public static final String STATUS_CHECK_OUT = "checked_out";
}
