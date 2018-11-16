package main.java.database.helpers;

import main.java.database.entities.Employer;
import main.java.database.SQLiteJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
      TODO: write following methods: insert, delete, update
*/
public class EmployerDBHelper extends SQLiteJDBC {

    private static final String TABLE = "Employer";


    public static void init() {
        Connection con = getConnection();

        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Employer> employees = new ArrayList<>();
        employees.add(new Employer(0, "Daniel", "Putschögl", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));
        employees.add(new Employer(1, "Andrea", "Mair", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));
        employees.add(new Employer(2, "Thomas", "Lichtenauer", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));
        employees.add(new Employer(3, "Matthias", "Ettl", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));
        employees.add(new Employer(4, "Alexander", "Pabinger", "Raab-Heim-Straße 1", "4040", "Linz", "Austria"));

        try {
            if (con != null) {
                insertList(employees);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Employer> rows = null;
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
                    "(" + PersonMeta.personMeta + ");";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("table " + TABLE + " created successfully!");
    }

    public static List<Employer> findAll() throws SQLException {
        Connection con = getConnection();
        List<Employer> rows = new ArrayList<>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from " + TABLE + ";");
        while (rs.next()) {
            rows.add(new Employer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("street"), rs.getString("zip"), rs.getString("city"), rs.getString("country")));
        }
        close(stat);
        close(rs);
        return rows;
    }

    private static void insertList(List<Employer> employees)
            throws SQLException {
        Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement("insert into " + TABLE + " values (?, ?, ?, ?, ?, ?, ?);");
        for (int i = 0; i < employees.size(); i++) {
            prep.setInt(1, i);
            prep.setString(2, employees.get(i).getFirstName());
            prep.setString(3, employees.get(i).getLastName());
            prep.setString(4, employees.get(i).getStreet());
            prep.setString(5, employees.get(i).getZip());
            prep.setString(6, employees.get(i).getCity());
            prep.setString(7, employees.get(i).getCountry());
            prep.addBatch();
        }

        con.setAutoCommit(false);
        prep.executeBatch();
        con.setAutoCommit(true);
        close(prep);
    }

    public static void insertEntity(Employer employer) throws SQLException {
        Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement("insert into " + TABLE + " values (?, ?, ?, ?, ?, ?, ?);");
        prep.setInt(1, getNextId());
        prep.setString(2, employer.getFirstName());
        prep.setString(3, employer.getLastName());
        prep.setString(4, employer.getStreet());
        prep.setString(5, employer.getZip());
        prep.setString(6, employer.getCity());
        prep.setString(7, employer.getCountry());
        prep.addBatch();

        con.setAutoCommit(false);
        prep.executeBatch();
        con.setAutoCommit(true);
        close(prep);
    }

    public static int getNextId() throws SQLException {
        Connection con = getConnection();
        int lastId = 0;
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from " + TABLE + ";");

        while (rs.next()) {
            lastId++;
        }

        close(stat);
        close(rs);

        return lastId + 1;
    }
}
