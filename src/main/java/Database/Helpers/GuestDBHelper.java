package main.java.Database.Helpers;

import main.java.Database.SQLiteJDBC;

import java.sql.*;

/*
      TODO: write following methods: insert, delete, update
*/
public class GuestDBHelper extends SQLiteJDBC {

    private static final String TABLE = "GUEST";

    private static void createTable() throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();

        try {
            System.out.println("Opened main.java.Database successfully");

            String sql = "CREATE TABLE " + TABLE +
                    "(" + PersonMeta.personMeta + ");";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("table " + TABLE + " created successfully!");
    }
}
