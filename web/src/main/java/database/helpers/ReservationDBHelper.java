package main.java.database.helpers;

import main.java.database.SQLiteJDBC;
import main.java.database.entities.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDBHelper extends SQLiteJDBC {
    private static final String TABLE = "RESERVATION";

    public static void init() {
        createTable();

        insert(new Reservation(new Date(), new Date(), 1, 0));
        insert(new Reservation(new Date(), new Date(), 2, 1));
        insert(new Reservation(new Date(), new Date(), 3, 2));

        System.out.println(findAll());
    }

    private static void createTable() {
        Connection con = getConnection();

        try {
            Statement stmt = con.createStatement();
            System.out.println("Opened main.java.database successfully");

            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE +
                    "(" +
                    MetaData.uid +
                    " OCCUPIED_FROM INT NOT NULL,\n" +
                    " OCCUPIED_TO INT NOT NULL,\n" +
                    " GUEST INT REFERENCES Guest(id),\n" +
                    " ROOM INT REFERENCES Room(id)" +
                    ");";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("table " + TABLE + " created successfully!");
    }

    public static List<Reservation> findAll() {
        Connection con = getConnection();
        List<Reservation> rows = new ArrayList<>();

        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM " + TABLE + ";");

            while (rs.next()) {
                rows.add(new Reservation(rs.getInt("id"), rs.getInt("occupied_from"), rs.getInt("occupied_to"), rs.getInt("guest"), rs.getInt("room")));
            }

            close(stat);
            close(rs);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return rows;
    }

    public static Reservation findById(int id) {
        Connection con = getConnection();
        Reservation reservation = new Reservation();

        try {
            Statement stat = con.createStatement();
            ResultSet rs = MetaData.findById(stat, TABLE, id);
            boolean onlyOneTime = true;

            while (rs.next() && onlyOneTime) {
                reservation = new Reservation(rs.getInt("id"), rs.getInt("occupied_from"), rs.getInt("occupied_to"), rs.getInt("guest"), rs.getInt("room"));
                onlyOneTime = false;
            }
            close(stat);
            close(rs);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return reservation;
    }

    private static void insertList(List<Reservation> reservations) {

        try {
            Connection con = getConnection();
            con.setAutoCommit(false);

            PreparedStatement prep = MetaData.getInsertStatement(con, TABLE, 5);
            for (int i = 0; i < reservations.size(); i++) {
                prep.setObject(1, null);
                prep.setLong(2, reservations.get(i).getOccupiedFrom().getTime());
                prep.setLong(3, reservations.get(i).getOccupiedTo().getTime());
                prep.setInt(4, reservations.get(i).getGuest());
                prep.setInt(4, reservations.get(i).getRoom());
                prep.addBatch();
            }

            prep.executeBatch();
            con.setAutoCommit(true);
            close(prep);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void insert(Reservation reservations) {

        try {
            Connection con = getConnection();
            con.setAutoCommit(false);

            PreparedStatement prep = MetaData.getInsertStatement(con, TABLE, 5);
            prep.setObject(1, null);
            prep.setLong(2, reservations.getOccupiedFrom().getTime());
            prep.setLong(3, reservations.getOccupiedTo().getTime());
            prep.setInt(4, reservations.getGuest());
            prep.setInt(5, reservations.getRoom());
            prep.addBatch();

            prep.executeBatch();
            close(prep);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static Reservation findByRoomId(int id) {
        Connection con = getConnection();
        Reservation reservation = new Reservation();

        try {
            Statement stat = con.createStatement();
            ResultSet rs = MetaData.findByDataId(stat, TABLE,"room", id);
            boolean onlyOneTime = true;

            while (rs.next() && onlyOneTime) {
                reservation = new Reservation(rs.getInt("id"), rs.getInt("occupied_from"), rs.getInt("occupied_to"), rs.getInt("guest"), rs.getInt("room"));
                onlyOneTime = false;
            }
            close(stat);
            close(rs);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return reservation;
    }
}
