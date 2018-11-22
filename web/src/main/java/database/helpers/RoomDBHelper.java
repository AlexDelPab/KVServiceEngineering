package main.java.database.helpers;

import main.java.database.SQLiteJDBC;
import main.java.database.entities.Room;
import main.java.helpers.Const;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
      TODO: write following methods: insert, delete, update
*/
public class RoomDBHelper extends SQLiteJDBC {

    private static final String TABLE = "Room";

    public static void init() {
        Connection con = getConnection();

        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(Const.ROOM_CLASSIC, Const.TRUE, 0));
        rooms.add(new Room(Const.ROOM_CLASSIC, Const.TRUE, 1));
        rooms.add(new Room(Const.ROOM_CLASSIC, Const.TRUE, 2));
        rooms.add(new Room(Const.ROOM_DELUXE, Const.FALSE, Const.NULL));
        rooms.add(new Room(Const.ROOM_DELUXE, Const.FALSE, Const.NULL));
        rooms.add(new Room(Const.ROOM_DELUXE, Const.FALSE, Const.NULL));
        rooms.add(new Room(Const.ROOM_PRESIDENT, Const.FALSE, Const.NULL));
        rooms.add(new Room(Const.ROOM_PRESIDENT, Const.FALSE, Const.NULL));
        rooms.add(new Room(Const.ROOM_PRESIDENT, Const.FALSE, Const.NULL));

        try {
            if (con != null) {
                insertList(rooms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Room> rows = null;
        try {
            if (con != null) {
                rows = findAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(rows);
    }

    private static void createTable() throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();

        try {
            System.out.println("Opened main.java.database successfully");

            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE +
                    "(ID INT PRIMARY KEY NOT NULL,\n" +
                    " TYPE TEXT NOT NULL,\n" +
                    " OCCUPIED INT NOT NULL,\n" +
                    " OCCUPIEDBY INT REFERENCES Guest(id)" +
                    ");";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("table " + TABLE + " created successfully!");
    }

    public static List<Room> findAll() throws SQLException {
        Connection con = getConnection();
        List<Room> rows = new ArrayList<>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from " + TABLE + ";");
        while (rs.next()) {
            rows.add(new Room(rs.getInt("id"), rs.getString("type"), rs.getInt("occupied"), rs.getInt("occupiedBy")));
        }
        close(stat);
        close(rs);
        return rows;
    }

    public static Room findById(int id) throws SQLException {
        Connection con = getConnection();
        Room room = new Room();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from " + TABLE + " r WHERE r.id =" + id + ";");
        boolean onlyOneTime = true;

        while (rs.next() && onlyOneTime) {
            room = new Room(rs.getInt("id"), rs.getString("type"), rs.getInt("occupied"), rs.getInt("occupiedBy"));
            onlyOneTime = false;
        }
        close(stat);
        close(rs);
        return room;
    }

    private static void insertList(List<Room> rooms)
            throws SQLException {
        Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement("insert into " + TABLE + " values (?, ?, ?, ?);");
        for (int i = 0; i < rooms.size(); i++) {
            prep.setString(1, String.valueOf(i));
            prep.setString(2, rooms.get(i).getType());
            prep.setInt(3, rooms.get(i).isOccupied());
            prep.setInt(4, rooms.get(i).getOccupiedBy());
            prep.addBatch();
        }

        con.setAutoCommit(false);
        prep.executeBatch();
        con.setAutoCommit(true);
        close(prep);
    }

    public static void updateOccupied(int id, int guest) {
        try {
            Connection con = getConnection();
            con.setAutoCommit(false);

            PreparedStatement prep = con.prepareStatement("UPDATE " + TABLE + " SET occupied = ?, occupiedby = ? WHERE id = ?");
            prep.setInt(1, Const.TRUE);
            prep.setInt(2, guest);
            prep.setInt(3, id);
            prep.addBatch();

            prep.executeBatch();
            close(prep);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
