package no.leanh;

import no.leanh.DB.DBConn;
import no.leanh.DB.DBInit;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            DBConn dbc = new DBConn();
            System.out.println("Connecting to database.");
            Connection c = dbc.getConnection();
            System.out.println("Connected to database.");
            System.out.println("Creating database schema");
            DBInit dbi = new DBInit(dbc.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database.");
        }
    }
}