package main.java.database;

import main.java.database.helpers.EmployerDBHelper;
import main.java.database.helpers.GuestDBHelper;
import main.java.database.helpers.HotelDBHelper;
import main.java.database.helpers.RoomDBHelper;

import java.sql.*;

public class SQLiteJDBC {

    private static final String DEFAULT_DRIVER = "org.sqlite.JDBC";
    private static final String DEFAULT_URL = "jdbc:sqlite:Hotel.db";

    private static Connection CONNECTION = null;

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        try {
            CONNECTION = createConnection();
            createNewDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        /*
          Clean tables on tomcat with following method
         */
        try {
            cleanAllTables();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HotelDBHelper.init();
        EmployerDBHelper.init();
        GuestDBHelper.init();
        RoomDBHelper.init();
    }

    public static Connection getConnection() {
        if (CONNECTION != null) {
            return CONNECTION;
        }

        init();
        return CONNECTION;
    }


    private static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(SQLiteJDBC.DEFAULT_DRIVER);
        return DriverManager.getConnection(SQLiteJDBC.DEFAULT_URL);
    }

    private static void createNewDatabase() {

        try {
            Connection con = DriverManager.getConnection(DEFAULT_URL);
            System.out.println(con);
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new main.java.database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cleanAllTables() throws Exception {
        Connection con = getConnection();
        Statement stmt = con.createStatement();

        try {
            System.out.println("Clear all tables!");

            String sqlPragmaOpen =
                    "PRAGMA writable_schema = 1;";
            String sqlDelete = "delete from sqlite_master where type in ('table', 'index', 'trigger');";
            String sqlPragmaClose = "PRAGMA writable_schema = 0;";
            String sqlRecoverDeletedSpace = "VACUUM;";
            String sqlCheck = "PRAGMA INTEGRITY_CHECK;";

            stmt.executeUpdate(sqlPragmaOpen);
            stmt.executeUpdate(sqlDelete);
            stmt.executeUpdate(sqlPragmaClose);
            stmt.executeUpdate(sqlRecoverDeletedSpace);
            stmt.executeUpdate(sqlCheck);

            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("all tables successfully cleared!");
    }

    protected static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void close(Statement stat) {
        try {
            if (stat != null) {
                stat.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
