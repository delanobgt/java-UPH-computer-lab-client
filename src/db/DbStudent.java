package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

public class DbStudent {
    
    private static final String JDBC_CLASS = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://35.194.100.112:3306/students";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "psiuph2016";

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_CLASS);
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static Student getStudentByNim(String nim) {
        Connection conn = getConnection();
        if (conn == null) return null;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("select * from studentdata where Student_ID = ?");
            pstmt.setString(1, nim);
            // Execute SQL query
            rs = pstmt.executeQuery();
            // Process result set
            if (!rs.next()) {
                return null;
            } else {
                Student student = new Student(
                        rs.getString("Student_ID"), 
                        rs.getString("Name"), 
                        rs.getString("Study_Program"));
                return student;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbStudent.class.getName()).log(Level.SEVERE, null, ex);
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
}
