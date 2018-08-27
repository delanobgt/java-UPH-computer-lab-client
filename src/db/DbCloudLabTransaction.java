/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LabTransaction;

/**
 *
 * @author irvin
 */
public class DbCloudLabTransaction {
 
    private static Connection getCloudConnection() {
        return DbCloudConnection.getConnection();
    }
    
    public static Integer addLabTransaction(LabTransaction labTransaction) {
        final String SQL = "INSERT INTO "
                + "lab_transactions (student_id, sign_in, sign_out, pc_number, lab_location) "
                + "VALUES((SELECT S.id FROM students AS S WHERE S.student_id = ?), ?, ?, ?, ?) ";
        try (Connection conn = getCloudConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, labTransaction.getStudentID());
            pstmt.setString(2, labTransaction.getSignIn());
            pstmt.setString(3, labTransaction.getSignOut());
            pstmt.setInt(4, labTransaction.getPcNumber());
            pstmt.setString(5, labTransaction.getLabLocation());
            
            // Execute SQL query
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int newId = rs.getInt(1);
            rs.close();
            return newId;
        } catch (SQLException ex) {
            Logger.getLogger(DbLocalStudent.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean updateLabTransaction(LabTransaction labTransaction) {
        final String SQL = "UPDATE lab_transactions "
                + "SET sign_out = ?  "
                + "WHERE id = ? ";
        try (Connection conn = getCloudConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, labTransaction.getSignOut());
            pstmt.setInt(2, labTransaction.getCloudID());
            // Execute SQL query
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DbLocalStudent.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
