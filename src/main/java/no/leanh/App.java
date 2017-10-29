package no.leanh;

import no.leanh.db.DBHandler;
import no.leanh.db.DBInit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);
        try {
            DBInit dbi = new DBInit();
            dbi.initialize();
            System.out.println("Everything is working properly.");
            dbi.parseCSV();
            dbi.checkIfDBPopulated();
            if(!dbi.checkIfDBPopulated()) dbi.populate();
            DBHandler dbh = new DBHandler();
            System.out.println("please enter the name of the table you wish to read from.");
            //dbh.read(sc.next());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}