/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author irvin
 */
public class DbCloudStudent {
    
    private static Connection getCloudConnection() {
        return DbCloudConnection.getConnection();
    }
    
    public static List<Student> getStudentList() {
        final String SQL = 
                "SELECT \n" +
                "	S.student_id AS Student_ID, \n" +
                "    S.name AS Name, \n" +
                "    SP.name AS Study_Program\n" +
                "FROM students AS S\n" +
                "	INNER JOIN study_programs AS SP\n" +
                "		ON SP.id = S.study_program_id;";
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
