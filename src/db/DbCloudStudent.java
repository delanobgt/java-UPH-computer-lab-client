/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
import properties.PropertiesLoader;

/**
 *
 * @author irvin
 */
public class DbCloudStudent {
    
    public static final String CLOUD_URL = PropertiesLoader.get("CLOUD_DB_URL_STUDENT");
    public static final String CLOUD_USERNAME = PropertiesLoader.get("CLOUD_DB_USERNAME");
    public static final String CLOUD_PASSWORD = PropertiesLoader.get("CLOUD_DB_PASSWORD");
    
    private static Connection getCloudConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CLOUD_URL, CLOUD_USERNAME, CLOUD_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DbLocalStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static List<Student> getStudentList() {
        final String SQL = "SELECT * FROM studentdata";
        try (Connection conn = getCloudConnection();
             Statement stmt = conn.createStatement()) {
            // Execute SQL query
            ResultSet rs = stmt.executeQuery(SQL);
            // Process result set
            List<Student> studentList = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("Student_ID"), 
                        rs.getString("Name"), 
                        rs.getString("Study_Program"));
                studentList.add(student);
            }
            rs.close();
            return studentList;
        } catch (SQLException ex) {
            Logger.getLogger(DbLocalStudent.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
