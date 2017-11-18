package no.leanh;

import no.leanh.db.ConnProvider;
import no.leanh.db.DBHandler;
import no.leanh.db.DBInit;
import no.leanh.file.FileHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class Client {

    public Client()throws IOException {
        DBHandler dbh = new DBHandler();
    }


    public void build() throws SQLException {
        DBInit dbi = new DBInit();
        FileHandler file = new FileHandler();
        try {
            if(dbi.checkIfDBNotInitialized()){
                dbi.initialize();
                System.out.println("database initialized.");
            }
            if (dbi.checkIfDBNotPopulated()) {
                dbi.populate(file.parseCSV());
                System.out.println("database populated.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        boolean exitLoop = true;
        while (exitLoop) {
            int input = sc.nextInt();
            printMainMenu();
            switch (input) {
                case 1:
                    System.out.println(help());
                    break;
                case 2:
                    try {
                        build();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Please enter the name of the table.");
                        String in = sc.next();
                        DBHandler.read(in);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Please enter the name of the table.");
                        String in1 = sc.next();
                        System.out.println("Please enter the name of the column you want to filter from");
                        String in2 = sc.next();
                        System.out.println("Please enter the keyword you want to search with");
                        String in3 = sc.next();
                        DBHandler.read(in1, in2, in3);
                        System.out.println("Presented data from search conditions");
                        exitLoop = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.exit(0);
            }


        }
    }


    public String printMainMenu() {
        String mainMenuTable= "+-----------------'''----------------+\n" +
                "|              Hourglass             |\n" +
                "+------------------+-----------------+\n" +
                "| Options:         |1.help           |\n" +
                "|------------------+-----------------|\n" +
                "| please choose an |2.build          |\n" +
                "| option.          |3.display table  |\n" +
                "| Enter 1 for more |4.search table   |\n" +
                "| instructions.    |5.exit           |\n" +
                "+-----------------***----------------|";



        return mainMenuTable;
    }
    public String help(){
        String help = "+-----------------'''----------------+\n" +
                "|              Hourglass             |\n" +
                "+------------------+-----------------+\n" +
                "|              help menu             |\n" +
                "|------------------+-----------------|\n" +
                "|2.build will initialize and populate|\n" +
                "|the database with data.             |\n" +
                "|Use if this is your first time      |\n" +
                "|running the program.                |\n" +
                "|------------------+-----------------|\n" +
                "|3.display table will display all    |\n" +
                "|data from a table of your choice.   |\n" +
                "|------------------+-----------------|\n" +
                "|4.search table will search for a    |\n" +
                "|specific keyword within a column of |\n" +
                "|a table.                            |\n" +
                "|------------------+-----------------|\n" +
                "|5.exit will exit the program.       |\n" +
                "+-----------------***----------------|";
        return help;
    }
}



 /*Prop prop = new Prop();
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
    }*/