package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import properties.PropertiesLoader;

/**
 *
 * @author irvin
 */
public class DbCloudConnection {

    public static final String CLOUD_URL = PropertiesLoader.get("CLOUD_DB_URL_LAB");
    public static final String CLOUD_USERNAME = PropertiesLoader.get("CLOUD_DB_USERNAME");
    public static final String CLOUD_PASSWORD = PropertiesLoader.get("CLOUD_DB_PASSWORD");
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CLOUD_URL, CLOUD_USERNAME, CLOUD_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DbLocalStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}
