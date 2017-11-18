package no.leanh.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBHandler {

    private Connection conn;

    public DBHandler(Connection conn)
    {
        this.conn = conn;

    }

    public DBHandler() {

    }

    public void insertTableFromList(List<String> list, String table) throws SQLException {
        Statement stmt = conn.createStatement();
        for (String i : list) {
            String sql = "INSERT INTO " + table;
            String[] split = i.split(",");
            switch (table) {
                case "Lecturer":
                    sql = "INSERT INTO " + table +
                            " (Name, Email) VALUES ('" + split[0] + "','" + split[1] + "');";
                    break;
                case "Subject":
                    sql = "INSERT INTO " + table +
                            " (Name, Subject_code, Enrolled_Students, Start_Date, End_Date) VALUES ('"
                            + split[0] + "', '" + split[1] + "', '" + split[2] + "', '" + split[3] + "', '" + split[4] + "');";
                    break;
                case "Class":
                    sql = "INSERT INTO " + table +
                            " (Name, Grade, Department) VALUES ('" + split[0] + "', '" + split[1] + "', '" +
                            split[2] + "');";
                    break;
                case "Room":
                    sql = "INSERT INTO " + table +
                            " (Room_Nr, Total_Seats) VALUES ('" + split[0] + "', '" + split[1] + "');";
                    break;
                case "Subject_Lecturer":
                    sql = "INSERT IGNORE INTO " + table +
                            " (Subject_ID, Lecturer_ID) VALUES ('" + split[0] + "', '" + split[1] + "');";
                    break;
                case "Class_Subject":
                    sql = "INSERT IGNORE INTO " + table +
                            " (Subject_ID, Class_ID) VALUES ('" + split[0] + "', '" + split[1] + "');";
                    break;
            }
            stmt.execute(sql);
        }
    }

    public ResultSet read(String table) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + table +";");
        return rs;

    }

    public ResultSet read(String table, String column, String row)throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " WHERE " + column + " = '" + row + "';");
        return rs;



    }

    public void printTable(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        StringBuilder strb = new StringBuilder();
        int highest = 0;

        for (int j = 1; j <= rsmd.getColumnCount(); j++) {
            if (highest < rsmd.getColumnName(j).length()) highest = rsmd.getColumnName(j).length();
            while (rs.next()) {
                if (highest < rs.getString(j).length()){
                    highest = rs.getString(j).length();
                }
            }
            strb.append("| %-");
            strb.append(highest);
            strb.append("s ");
            highest = 0;
            rs.beforeFirst();
        }
        String result = strb.toString();
        String leftAlignFormat = result + "|%n";
        List<Object> colNameList = new ArrayList<>();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            colNameList.add(rsmd.getColumnName(i));
        }
        System.out.format(leftAlignFormat, colNameList.toArray());
        while (rs.next()) {
            List<Object> contentList = new ArrayList<>();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                contentList.add(rs.getString(i));
            }
            System.out.format(leftAlignFormat, contentList.toArray());
        }

    }
}




