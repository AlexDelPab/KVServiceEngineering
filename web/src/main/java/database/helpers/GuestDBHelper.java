package main.java.database.helpers;

import main.java.database.entities.Guest;
import main.java.database.SQLiteJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
      TODO: write following methods: insert, delete, update
*/
public class GuestDBHelper extends SQLiteJDBC {

    private static final String TABLE = "Guest";

    public static void init() {
        Connection con = getConnection();

        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("Daniel", "Putschögl", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));
        guests.add(new Guest("Andrea", "Mair", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));
        guests.add(new Guest("Thomas", "Lichtenauer", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));
        guests.add(new Guest("Matthias", "Ettl", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));
        guests.add(new Guest("Alexander", "Pabinger", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));

        try {
            if (con != null) {
                insertList(guests);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Guest> rows = null;
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

            String sql = "CREATE TABLE " + TABLE +
                    "(" + PersonMeta.personMeta + ");";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("table " + TABLE + " created successfully!");
    }

    public static List<Guest> findAll() throws SQLException {
        Connection con = getConnection();
        List<Guest> rows = new ArrayList<>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from " + TABLE + ";");
        while (rs.next()) {
            rows.add(new Guest(rs.getString("first_name"),rs.getString("last_name"), rs.getString("street"), rs.getString("zip"), rs.getString("city"), rs.getString("country")));
        }
        close(stat);
        close(rs);
        return rows;
    }

    private static void insertList(List<Guest> guests)
            throws SQLException {
        Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement("insert into " + TABLE + " values (?, ?, ?, ?, ?, ?, ?);");
        for (int i = 0; i < guests.size(); i++) {
            prep.setString(1, String.valueOf(i));
            prep.setString(2, guests.get(i).getFirstName());
            prep.setString(3, guests.get(i).getLastName());
            prep.setString(4, guests.get(i).getStreet());
            prep.setString(5, guests.get(i).getZip());
            prep.setString(6, guests.get(i).getCity());
            prep.setString(7, guests.get(i).getCountry());
            prep.addBatch();
        }

        con.setAutoCommit(false);
        prep.executeBatch();
        con.setAutoCommit(true);
        close(prep);
    }
}
