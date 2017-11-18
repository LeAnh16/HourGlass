package no.leanh.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import no.leanh.file.Prop;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnProvider {
    Properties prop;
    private Connection con;
    public ConnProvider() throws SQLException{
        Prop p = new Prop();
        prop = p.getProperties();
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(prop.getProperty("server"));
        ds.setUser(prop.getProperty("dbusername"));
        ds.setPassword(prop.getProperty("dbpassword"));
        ds.setDatabaseName("hourglass");
        con = ds.getConnection();
    }

    public ConnProvider(int i) throws SQLException{
        Prop p = new Prop();
        prop = p.getProperties();
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(prop.getProperty("server"));
        ds.setUser(prop.getProperty("dbusername"));
        ds.setPassword(prop.getProperty("dbpassword"));
        con = ds.getConnection();
    }

    public Connection getConnection() {
        return con;
    }
}