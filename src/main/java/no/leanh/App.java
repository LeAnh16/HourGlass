package no.leanh;

import com.sun.org.apache.xpath.internal.SourceTree;
import no.leanh.db.DBHandler;
import no.leanh.db.DBInit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Prop prop = new Prop();
        Scanner sc = new Scanner(System.in);
        try {
            DBInit dbi = new DBInit();
            dbi.initialize();
            System.out.println("Everything is working properly.");
            dbi.parseCSV();
            if (dbi.checkIfDBNotPopulated()) dbi.populate();
            DBHandler dbh = new DBHandler();
            System.out.println("Would you like to read all data from one table, or search for a specific data entry?" +
                    " Enter 1 to read from all tables, 2 to search for a specific data entry");
            int i = sc.nextInt();
            if (i == 1) {
                System.out.println("please enter the name of the table you wish to read from.");
                dbh.printTable(dbh.read(sc.next()));
            } else if (i == 2) {
                System.out.println("Please enter the name of the table");
                String input1 = sc.next();
                System.out.println("Please enter the name of the column you want to filter from");
                String input2 = sc.next();
                System.out.println("Please enter the keyword you want to search with");
                String input3 = sc.next();
                dbh.printTable(dbh.read(input1, input2, input3));
                System.out.println("Presented data from search conditions");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}