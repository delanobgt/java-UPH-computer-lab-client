package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LabTransaction;
import model.Student;
import properties.PropertiesLoader;

public class DbLabTransaction {

    public static final String JDBC_CLASS = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = PropertiesLoader.get("JDBC_URL_LAB");
    public static final String JDBC_USERNAME = PropertiesLoader.get("JDBC_USERNAME");
    public static final String JDBC_PASSWORD = PropertiesLoader.get("JDBC_PASSWORD");

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_CLASS);
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbLabTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    /*
     * return newly generated key
     */
    public static Integer createSignIn(Student student) {
        Connection conn = getConnection();
        if (conn == null) return null;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {    
            LabTransaction labTransaction = new LabTransaction(
                    student, 
                    getCurrentTimeStamp().toString(), 
                    getLabPcNumberFromSystem(), 
                    getLabLocationFromSystem());   
            // Prepare statement
            pstmt = conn.prepareStatement(
                    "insert into lab_transaction(nim, sign_in, pc_number, lab_location) values(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, labTransaction.getNim());
            pstmt.setString(2, labTransaction.getSignIn());
            pstmt.setInt(3, labTransaction.getPcNumber());
            pstmt.setString(4, labTransaction.getLabLocation());
            // Execute SQL query
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            rs.next();
            int newId = rs.getInt(1);
            pstmt.close();
            return newId;
        } catch (SQLException ex) {
            Logger.getLogger(DbLabTransaction.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conn.close();
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean updateSignOut(Integer transactionId) {
        Connection conn = getConnection();
        if (conn == null) return false;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(
                    "update lab_transaction "
                    + "set sign_out = ? "
                    + "where id = ? ");
            pstmt.setTimestamp(1, getCurrentTimeStamp());
            pstmt.setInt(2, transactionId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DbLabTransaction.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                conn.close();
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static Timestamp getCurrentTimeStamp() {
        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        return timestamp;
    }
    private static Integer getLabPcNumberFromSystem() {
        return Integer.valueOf(System.getenv("LAB_PC_NUMBER"));
    }
    private static String getLabLocationFromSystem() {
        return System.getenv("LAB_LOCATION");
    }
}
