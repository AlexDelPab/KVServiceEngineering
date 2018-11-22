package main.java.database.helpers;

import java.sql.*;

public class MetaData {
    public static String uid = " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n";

    public static String personMeta =
            uid +
                    " FIRST_NAME TEXT NOT NULL,\n" +
                    " LAST_NAME TEXT NOT NULL,\n" +
                    " STREET TEXT,\n" +
                    " ZIP TEXT,\n" +
                    " CITY TEXT,\n" +
                    " COUNTRY TEXT";

    public static String getValuesClause(int numOfValues) {
        if (numOfValues < 1) {
            return "";
        }

        StringBuilder values = new StringBuilder(" VALUES (");
        for (int i = 0; i < numOfValues - 1; i++) {
            values.append("?,");
        }
        values.append("?) ");

        return values.toString();
    }

    public static PreparedStatement getInsertStatement(Connection con, String table, int numOfValues) throws SQLException {
        return con.prepareStatement("INSERT INTO " + table + getValuesClause(numOfValues) + ";");
    }

    public static ResultSet findById(Statement stmt, String table, int id) throws SQLException {
        return stmt.executeQuery("SELECT * FROM " + table + " x WHERE x.id =" + id + ";");
    }

    public static ResultSet findByDataId(Statement stmt, String table, String data, int id) throws SQLException {
        return stmt.executeQuery("SELECT * FROM " + table + " x WHERE x." + data + " =" + id + ";");
    }
}
