package database;

import main.java.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDBHelper extends SQLiteJDBC {

    public static final String TABLE = "Hotel";

    public static void init() {
        Connection con = getConnection();

        try {
            createTable(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Hotel Star", "Street 1", "4600", "Wels"));

        try {
            if (con != null) {
                saveAll(con, hotels);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Hotel> rows = null;
        try {
            if (con != null) {
                rows = findAll(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(rows);
        close(con);
    }

    private static void createTable(Connection conn) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();

        try {
            System.out.println("Opened database successfully");

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

    public static List<Hotel> findAll(Connection conn) throws SQLException {
        List<Hotel> rows = new ArrayList<>();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from " + TABLE + ";");
        while (rs.next()) {
            rows.add(new Hotel(rs.getString("name"), rs.getString("street"), rs.getString("zip"), rs.getString("city")));
        }
        close(stat);
        close(rs);
        return rows;
    }

    private static void saveAll(Connection conn, List<Hotel> hotels)
            throws SQLException {
        PreparedStatement prep = conn.prepareStatement("insert into " + TABLE + " values (?, ?, ?, ?, ?);");
        for (int i = 0; i < hotels.size(); i++) {
            prep.setString(1, String.valueOf(i));
            prep.setString(2, hotels.get(i).getName());
            prep.setString(3, hotels.get(i).getStreet());
            prep.setString(4, hotels.get(i).getZip());
            prep.setString(5, hotels.get(i).getCity());
            prep.addBatch();
        }

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);
        close(prep);
    }
}
