package no.leanh.DB;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import no.leanh.Properties;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConn {
    private Connection con;|
    public DBConn() throws SQLException{
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(Properties.properties.getProperty("server"));
        ds.setUser(Properties.properties.getProperty("dbusername"));
        ds.setPassword(Properties.properties.getProperty("dbpassword"));
        con = ds.getConnection();
    }

    public Connection getConnection() {
        return con;
    }
}