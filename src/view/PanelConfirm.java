/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.DbLabTransaction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import main.FrameMain;
import model.Student;
import util.Utility;

/**
 *
 * @author irvin
 */
public class PanelConfirm extends javax.swing.JPanel {

    private FrameMain parentFrame;
    private Student student;
    /**
     * Creates new form PanelConfirm
     */
    public PanelConfirm(FrameMain parentFrame, Student student) {
        initComponents();
        this.parentFrame = parentFrame;
        this.student = student;
        
        // init UI
        this.lblLogo.setText(Utility.getStudentAlias(student.getName()));
        this.lblName.setText(Utility.getSentenceCase(student.getName()));
        this.lblStudentID.setText(student.getStudentId());
        this.lblStudyProgram.setText(Utility.getSentenceCase(student.getStudyProgram()));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel() {
            public void paintComponent(Graphics oldG) {
                Graphics2D g = (Graphics2D) oldG;
                g.addRenderingHints(new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON));
            g.setColor(new Color(102, 204, 255));
            g.fillOval(0, 0, getWidth(), getHeight());
            super.paintComponent(oldG);
        }
    };
    lblStudyProgram = new javax.swing.JLabel();
    lblName = new javax.swing.JLabel();
    lblStudentID = new javax.swing.JLabel();
    btnCancel = new javax.swing.JButton();
    btnProceed = new javax.swing.JButton();
    lblBackground = new javax.swing.JLabel();

    setBackground(new java.awt.Color(255, 255, 255));
    setMaximumSize(new java.awt.Dimension(1600, 900));
    setMinimumSize(new java.awt.Dimension(1600, 900));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Is this you?");
    add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 230, 250, 50));

    lblLogo.setFont(new java.awt.Font("Tahoma", 0, 64)); // NOI18N
    lblLogo.setForeground(new java.awt.Color(255, 255, 255));
    lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblLogo.setText("ID");
    add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 300, 150, 150));

    lblStudyProgram.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    lblStudyProgram.setForeground(new java.awt.Color(153, 153, 153));
    lblStudyProgram.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblStudyProgram.setText("[study program]");
    lblStudyProgram.setToolTipText("");
    add(lblStudyProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 520, 300, 30));

    lblName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    lblName.setForeground(new java.awt.Color(121, 205, 205));
    lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblName.setText("[name]");
    add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 460, 300, 30));

    lblStudentID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    lblStudentID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblStudentID.setText("[student ID]");
    lblStudentID.setToolTipText("");
    add(lblStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 490, 300, 30));

    btnCancel.setBackground(new java.awt.Color(255, 255, 255));
    btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    btnCancel.setForeground(new java.awt.Color(255, 102, 102));
    btnCancel.setText("Cancel");
    btnCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102), 2));
    btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            btnCancelMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            btnCancelMouseExited(evt);
        }
    });
    btnCancel.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCancelActionPerformed(evt);
        }
    });
    add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 570, 140, 40));

    btnProceed.setBackground(new java.awt.Color(121, 205, 205));
    btnProceed.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    btnProceed.setForeground(new java.awt.Color(255, 255, 255));
    btnProceed.setText("Proceed");
    btnProceed.setBorder(null);
    btnProceed.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnProceed.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            btnProceedMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            btnProceedMouseExited(evt);
        }
    });
    btnProceed.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnProceedActionPerformed(evt);
        }
    });
    add(btnProceed, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 570, 140, 40));

    lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LAB-plain.jpg"))); // NOI18N
    add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 900));
    }// </editor-fold>//GEN-END:initComponents

    private void btnProceedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceedActionPerformed
        parentFrame.changeSceneTo(FrameMain.PANEL_LOADING);
        
        new Thread(() -> {
            Integer transactionId = DbLabTransaction.createSignIn(student);
            if (transactionId == null) {
                parentFrame.addComponent(
                            FrameMain.PANEL_INFO, 
                            new PanelInfo(parentFrame, "Failed to login", "Somehow we failed to log you in."));
                parentFrame.changeSceneTo(FrameMain.PANEL_INFO);
            } else {
                parentFrame.hideDown();
                parentFrame.showStatusDialog(student, transactionId);
            }
        }).start();
    }//GEN-LAST:event_btnProceedActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        parentFrame.changeSceneTo(FrameMain.PANEL_SIGN_IN);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnProceedMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProceedMouseEntered
        ((JButton)evt.getSource()).setBackground(new Color(80, 180, 180));
    }//GEN-LAST:event_btnProceedMouseEntered

    private void btnProceedMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProceedMouseExited
        ((JButton)evt.getSource()).setBackground(new Color(104, 205, 205));
    }//GEN-LAST:event_btnProceedMouseExited

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        ((JButton)evt.getSource()).setBackground(new Color(255, 102, 102));
        ((JButton)evt.getSource()).setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        ((JButton)evt.getSource()).setBackground(Color.WHITE);
        ((JButton)evt.getSource()).setForeground(new Color(255, 102, 102));
    }//GEN-LAST:event_btnCancelMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnProceed;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblStudyProgram;
    // End of variables declaration//GEN-END:variables
}
