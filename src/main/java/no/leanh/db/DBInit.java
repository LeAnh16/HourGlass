package no.leanh.db;

import no.leanh.file.Prop;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBInit {
    private Properties prop;
    public DBInit() throws SQLException {
        Prop p = new Prop();
        prop = p.getProperties();

    }

    public void initialize() {
        try (Connection conn = new ConnProvider(1).getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(new File("DBScript.sql")));
             Statement stmt = conn.createStatement()) {
            String l;
            while ((l = reader.readLine()) != null) {
                stmt.execute(l);
            }
            OutputStream out = new FileOutputStream("DBconfig.properties");
            prop.setProperty("db-initialized", "true");
            prop.store(out, "Some config comments...");
            out.close();
            System.out.println("Database created.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();

        }


    }



    public void populate(Map<String, List<String>> map) throws SQLException {
       try(Connection conn = new ConnProvider().getConnection()) {
           DBHandler dbh = new DBHandler(conn);
           dbh.insertTableFromList(map.get("lecturers"), "Lecturer");
           dbh.insertTableFromList(map.get("classes"), "Class");
           dbh.insertTableFromList(map.get("rooms"), "Room");
           dbh.insertTableFromList(map.get("subjects"), "Subject");
           dbh.insertTableFromList(map.get("subjectToLecturers"), "Subject_Lecturer");
           dbh.insertTableFromList(map.get("classToSubjects"), "Class_Subject");
           System.out.println("Database populated with data from file.");
           OutputStream out = new FileOutputStream("DBconfig.properties");
           prop.setProperty("db-populated", "true");
           prop.store(out, "Some config comments...");
           out.close();
       }
       catch(SQLException | IOException e){
           e.printStackTrace();
        }

    }
    public boolean checkIfDBNotInitialized(){
        return (prop.getProperty("db-initialized", "false").equals("false"));
    }
    public boolean checkIfDBNotPopulated() {
        return (prop.getProperty("db-populated", "false").equals("false"));

        /*(Connection conn = new ConnProvider().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("(SELECT * FROM  Subject LIMIT 1)\n" +
                     "UNION (SELECT * FROM  Class LIMIT 1)\n" +
                     "UNION (SELECT * FROM  Room LIMIT 1)\n" +
                     "UNION (SELECT * FROM  Lecturer LIMIT 1)\n" +
                     "UNION (SELECT * FROM  Subject_Lecturer LIMIT 1)\n" +
                     "UNION (SELECT * FROM  Class_Subject LIMIT 1);")){
        rs.last();
        return (rs.getRow() >= 6)*/
    }
}