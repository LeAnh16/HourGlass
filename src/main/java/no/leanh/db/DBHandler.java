package no.leanh.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DBHandler {
    private ConnProvider d;

    public DBHandler() throws SQLException {
        this.d = new ConnProvider();

    }

    public void insertTableFromList(ArrayList<String> list, String table) {
        try (Connection conn = d.getConnection();
             Statement stmt = conn.createStatement()) {
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
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void read(String table) {
        try (Connection conn = d.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + table)) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            StringBuilder strb = new StringBuilder();
            int highest = 0;

            for (int j = 1; j <= columnsNumber; j++) {
                if (highest < rsmd.getColumnName(j).length()) highest = rsmd.getColumnName(j).length();
                while (rs.next()) {
                    if (highest < rs.getString(j).length()) highest = rs.getString(j).length();
                }
                strb.append("| %-");
                strb.append(highest);
                strb.append("s ");
                highest = 0;
                rs.first();
            }
            String result = strb.toString();
            String leftAlignFormat = result + "|%n";
            List<Object> colNameList = new ArrayList<>();

            for (int i = 1; i <= columnsNumber; i++) {
                colNameList.add(rsmd.getColumnName(i));
            }
            System.out.format(leftAlignFormat, colNameList.toArray());

            while (rs.next()) {
                List<Object> contentList = new ArrayList<>();
                for (int i = 1; i <= columnsNumber; i++) {
                    contentList.add(rs.getString(i));
                }
                System.out.format(leftAlignFormat, contentList.toArray());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




