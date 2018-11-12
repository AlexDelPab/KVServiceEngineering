package database;

import java.sql.*;

public class SQLiteJDBC {

    private static final String DEFAULT_DRIVER = "org.sqlite.JDBC";
    private static final String DEFAULT_URL = "jdbc:sqlite:Hotel.db";

    private static Connection CONNECTION = null;

    public static void main(String[] args) {
        init();

        HotelDBHelper.init();
    }

    public static void init(){

        try {
            CONNECTION = createConnection();
            createNewDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection (){
        return CONNECTION;
    }


    private static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(SQLiteJDBC.DEFAULT_DRIVER);
        return DriverManager.getConnection(SQLiteJDBC.DEFAULT_URL);
    }

    private static void createNewDatabase() {

        try {
            Connection conn = DriverManager.getConnection(DEFAULT_URL);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
