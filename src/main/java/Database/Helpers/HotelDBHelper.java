package main.java.Database.Helpers;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.java.Database.Entities.Hotel;
import main.java.Database.SQLiteJDBC;

/*
      TODO: write following methods: insert, delete, update
*/
public class HotelDBHelper extends SQLiteJDBC {

    public static final String TABLE = "HOTEL";

    public static void init() {
        Connection con = getConnection();

        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Hotel Star", "Street 1", "4600", "Wels"));

        try {
            if (con != null) {
                saveAll(hotels);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Hotel> rows = null;
        try {
            if (con != null) {
                rows = findAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(rows);
        close(con);
    }

    private static void createTable() throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();

        try {
            System.out.println("Opened main.java.Database successfully");

            String sql = "CREATE TABLE " + TABLE +
                    "(ID INT PRIMARY KEY     NOT NULL,\n" +
                    " NAME TEXT    NOT NULL,\n " +
                    " STREET TEXT     NOT NULL,\n " +
                    " ZIP TEXT NOT NULL,\n " +
                    " CITY TEXT NOT NULL);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("table " + TABLE + " created successfully!");
    }

    public static List<Hotel> findAll() throws SQLException {
        Connection con = getConnection();
        List<Hotel> rows = new ArrayList<>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from " + TABLE + ";");
        while (rs.next()) {
            rows.add(new Hotel(rs.getString("name"), rs.getString("street"), rs.getString("zip"), rs.getString("city")));
        }
        close(stat);
        close(rs);
        return rows;
    }

    private static void saveAll(List<Hotel> hotels)
            throws SQLException {
        Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement("insert into " + TABLE + " values (?, ?, ?, ?, ?);");
        for (int i = 0; i < hotels.size(); i++) {
            prep.setString(1, String.valueOf(i));
            prep.setString(2, hotels.get(i).getName());
            prep.setString(3, hotels.get(i).getStreet());
            prep.setString(4, hotels.get(i).getZip());
            prep.setString(5, hotels.get(i).getCity());
            prep.addBatch();
        }

        con.setAutoCommit(false);
        prep.executeBatch();
        con.setAutoCommit(true);
        close(prep);
    }
}
