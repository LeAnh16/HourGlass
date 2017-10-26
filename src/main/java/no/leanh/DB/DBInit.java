package no.leanh.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInit {
    public DBInit(DBConn dbconn){
        try (Connection conn = dbconn.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE DATABASE hourglass");
            System.out.println("Database created");
        } catch (SQLException e) {
            if(e.getMessage().contains("database exists")){
                System.out.println("Database already created -- continuing");
            } else {
                e.printStackTrace();
            }
        }
    }
}