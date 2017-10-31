package no.leanh.db;

import no.leanh.Prop;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class DBInit {
    private ArrayList<String> lecturers = new ArrayList<>();
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<String> rooms = new ArrayList<>();
    private ArrayList<String> subjects = new ArrayList<>();
    private ArrayList<String> subjectsToLecturers = new ArrayList<>();
    private ArrayList<String> classesToSubjects = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>(Arrays.asList("subject:", "class:", "room:", "lecturer:", "subject_Lecturer:", "class_Subject:"));

    public DBInit() throws SQLException {

    }

    public void initialize() {
        try (Connection conn = new ConnProvider(1).getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(new File("DBScript.sql")));
             Statement stmt = conn.createStatement()) {
            String l;
            while ((l = reader.readLine()) != null) {
                stmt.execute(l);
            }

            System.out.println("Database created.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parseCSV() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("data.csv")));
        String l;
        int s = 0;
        while ((l = reader.readLine()) != null) {
            boolean broken = false;
            for (String i : titles) {
                if (l.equals(i)) {
                    s++;
                    broken = true;
                    break;
                }
            }
            if (l.equals("--") || broken) {
                continue;
            }
            switch (s) {
                case 1:
                    lecturers.add(l);
                    break;
                case 2:
                    subjects.add(l);
                    break;
                case 3:
                    classes.add(l);
                    break;
                case 4:
                    rooms.add(l);
                    break;
                case 5:
                    subjectsToLecturers.add(l);
                    break;
                case 6:
                    classesToSubjects.add(l);
            }
        }
        System.out.println("Data from CSV added to lists.");
    }

    public void populate() throws SQLException {
        DBHandler dbh = new DBHandler();
        dbh.insertTableFromList(lecturers, "Lecturer");
        dbh = new DBHandler();
        dbh.insertTableFromList(classes, "Class");
        dbh = new DBHandler();
        dbh.insertTableFromList(rooms, "Room");
        dbh = new DBHandler();
        dbh.insertTableFromList(subjects, "Subject");
        dbh = new DBHandler();
        dbh.insertTableFromList(subjectsToLecturers, "Subject_Lecturer");
        dbh = new DBHandler();
        dbh.insertTableFromList(classesToSubjects, "Class_Subject");
        System.out.println("Database populated with data from file.");

        try {
            OutputStream out = new FileOutputStream("config.properties");
            Properties prop = Prop.getProperties();
            Prop.properties.setProperty("dbinitialized", "true");
            prop.store(out, "Some config comments...");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean checkIfDBNotPopulated() {
        return (Prop.properties.getProperty("dbinitialized", "false").equals("false"));

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