package main.java.Database.Helpers;

import main.java.Database.SQLiteJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
      TODO: write following methods: insert, delete, update
*/
public class EmployerDBHelper extends SQLiteJDBC {

    private static final String TABLE = "EMPLOYER";

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
