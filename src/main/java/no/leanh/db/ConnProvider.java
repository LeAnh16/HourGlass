package no.leanh.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import no.leanh.Prop;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnProvider {
    private Connection con;
    public ConnProvider() throws SQLException{
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(Prop.properties.getProperty("server"));
        ds.setUser(Prop.properties.getProperty("dbusername"));
        ds.setPassword(Prop.properties.getProperty("dbpassword"));
        ds.setDatabaseName("hourglass");
        con = ds.getConnection();
    }

    public ConnProvider(int i) throws SQLException{
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(Prop.properties.getProperty("server"));
        ds.setUser(Prop.properties.getProperty("dbusername"));
        ds.setPassword(Prop.properties.getProperty("dbpassword"));
        con = ds.getConnection();
    }

    public Connection getConnection() {
        return con;
    }
}