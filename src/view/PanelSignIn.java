/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.DbLocalStudent;
import db.DbWizard;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import main.FrameMain;
import model.Student;
import properties.PropertiesLoader;
import util.BrutalForce;

/**
 *
 * @author irvin
 */
public class PanelSignIn extends javax.swing.JPanel {

    private FrameMain parentFrame;
    /**
     * Creates new form PanelSignIn
     */
    public PanelSignIn(FrameMain parentFrame) {
        initComponents();
        this.parentFrame = parentFrame;
        
        ((AbstractDocument) txtStudentID.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
               if (text.length() > 0 && !('0' <= text.charAt(0) && text.charAt(0) <= '9')) text = "";
                super.replace(fb, offset, length, text, attrs);
            }
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                if (text.length() > 0 && !('0' <= text.charAt(0) && text.charAt(0) <= '9')) text = "";
                super.insertString(fb, offset, text, attr);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtStudentID = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1600, 900));
        setMinimumSize(new java.awt.Dimension(1600, 900));
        setPreferredSize(new java.awt.Dimension(1600, 900));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtStudentID.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtStudentID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStudentID.setToolTipText("");
        txtStudentID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(121, 205, 205), 3, true));
        txtStudentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudentIDActionPerformed(evt);
            }
        });
        add(txtStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 430, 370, 70));

        btnLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-round.png"))); // NOI18N
        btnLogin.setBorder(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 530, 270, 70));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LAB-UI-01-text_.jpg"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 900));
    }// </editor-fold>//GEN-END:initComponents

    private void onLoginTriggered() {
        String inputString = txtStudentID.getText().trim();
        this.txtStudentID.setText("");
        
        if (inputString.equals(PropertiesLoader.get("EXIT_KEY"))) {
            BrutalForce.stop();
            System.exit(0);
        } else {
            parentFrame.changeSceneTo(FrameMain.PANEL_LOADING);
            String studentID = inputString;

            new Thread(() -> {    
                Student student = DbWizard.doesStudentExist(studentID);
                if (student == null) {
                    parentFrame.addComponent(
                            FrameMain.PANEL_INFO, 
                            new PanelInfo(parentFrame));
                    parentFrame.changeSceneTo(FrameMain.PANEL_INFO);
                } else {
                    parentFrame.addComponent(
                            FrameMain.PANEL_CONFIRM, 
                            new PanelConfirm(parentFrame, student));
                    parentFrame.changeSceneTo(FrameMain.PANEL_CONFIRM);
                }        
            }).start();
        }
    }
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        onLoginTriggered();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtStudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudentIDActionPerformed
        onLoginTriggered();
    }//GEN-LAST:event_txtStudentIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JTextField txtStudentID;
    // End of variables declaration//GEN-END:variables
}
