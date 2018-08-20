/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.DbWizard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.FrameMain;
import model.Student;
import util.Utility;

/**
 *
 * @author irvin
 */
public class DialogStatus extends javax.swing.JDialog {

    private static final int HIDDEN_HEIGHT = 100;
    private FrameMain parentFrame;
    private Student student;
    private Integer transactionId;
    private volatile boolean isSlidingDown = false;
    private volatile boolean isSlidingUp = false;
    private Thread stopwatchThread = new Thread();
    private Thread slideDownThread = new Thread();
    private Thread slideUpThread = new Thread();
    private Thread onMouseExitThread;
    private Thread logoutThread = null;
    
    /**
     * Creates new form DialogStatus
     */
    public DialogStatus(JFrame parent, boolean modal, Student student, Integer transactionId) {
        super(parent, modal);
        initComponents();
        
        this.parentFrame = (FrameMain)parent;
        this.transactionId = transactionId;
        this.student = student;
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        final int SCREEN_WIDTH = (int) tk.getScreenSize().width;
        final int SCREEN_HEIGHT = (int) tk.getScreenSize().height;
        this.setLocation((SCREEN_WIDTH-getWidth())/2, 0);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.lblAlias.setText(Utility.getStudentAlias(student.getName()));
        this.lblStudentID.setText(student.getStudentId());
        this.lblName.setText(Utility.getSentenceCase(student.getName()));
        this.lblStudyProgram.setText(Utility.getSentenceCase(student.getStudyProgram()));
        
        renewThread(this.stopwatchThread);
        this.stopwatchThread.start();
        
        // onMouseOutsideWindow listener thread
        this.onMouseExitThread = new Thread(() -> {
            while (true) {
                PointerInfo inf = MouseInfo.getPointerInfo();
                Point p = inf.getLocation();
                if (p.x < this.getLocation().x || p.x > this.getLocation().x + this.getWidth() ||
                    p.y < this.getLocation().y || p.y > this.getLocation().y + this.getHeight()) {
                        renewThread(this.slideUpThread);
                        this.slideUpThread.start();
                }
                Utility.sleep(200);
            }
        });
        
        // initial sliding up
        new Thread(() -> {
            Utility.sleep(1500);
            renewThread(this.slideUpThread);
            this.slideUpThread.start();
            this.onMouseExitThread.start();
        }).start();
    }

    private void renewThread(Thread thread) {
        if (thread == this.slideUpThread) {
            this.slideUpThread = new Thread(() -> {
                isSlidingUp = true;
                if (isSlidingDown) {
                    try {
                        this.slideDownThread.join();
                    } catch (InterruptedException ex) {Logger.getLogger(DialogStatus.class.getName()).log(Level.SEVERE, null, ex);}
                }
                int delay = 3;
                while (this.getLocation().y > -HIDDEN_HEIGHT) {
                    if (isSlidingDown) break;
                    this.setLocation(
                            this.getLocation().x,
                            this.getLocation().y-1);
                    Utility.sleep(delay);
                }
                isSlidingUp = false;
            });
        } else if (thread == this.slideDownThread) {
            this.slideDownThread = new Thread(() -> {
                isSlidingDown = true;
                if (isSlidingUp) {
                    try {
                        this.slideUpThread.join();
                    } catch (InterruptedException ex) {Logger.getLogger(DialogStatus.class.getName()).log(Level.SEVERE, null, ex);}
                }
                int delay = 3;
                while (this.getLocation().y < 0) {
                    if (isSlidingUp) break;
                    this.setLocation(
                            this.getLocation().x,
                            this.getLocation().y+1);
                    Utility.sleep(delay);
                }
                isSlidingDown = false;
            });
        } else if (thread == stopwatchThread) {
            stopwatchThread = new Thread(() -> {
                int currentSecond = 0;
                while (true) {
                    Utility.sleep(1000);
                    currentSecond += 1;
                    this.lblStopwatchText.setText(String.format("%02d : %02d : %02d", 
                            currentSecond / 3600,
                            (currentSecond % 3600) / 60,
                            currentSecond % 60
                    ));
                }
            });
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelParent = new javax.swing.JPanel();
        panelBottom = new javax.swing.JPanel();
        lblStopwatchIcon = new javax.swing.JLabel();
        lblStopwatchText = new javax.swing.JLabel();
        lblAlias = new javax.swing.JLabel() {
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
    lblStudentID = new javax.swing.JLabel();
    lblName = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    btnLogout = new javax.swing.JButton();
    lblErrorMsg = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setAlwaysOnTop(true);
    setBackground(new java.awt.Color(255, 255, 255));
    setMaximumSize(new java.awt.Dimension(250, 120));
    setMinimumSize(new java.awt.Dimension(250, 120));
    setUndecorated(true);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelParent.setBackground(new java.awt.Color(255, 255, 255));
    panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelBottom.setBackground(new java.awt.Color(121, 205, 205));
    panelBottom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    panelBottom.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            panelBottomMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            panelBottomMouseEntered(evt);
        }
    });
    panelBottom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    lblStopwatchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/timer.png"))); // NOI18N
    panelBottom.add(lblStopwatchIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 0, 20, 20));

    lblStopwatchText.setBackground(new java.awt.Color(121, 205, 205));
    lblStopwatchText.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    lblStopwatchText.setForeground(new java.awt.Color(255, 255, 255));
    lblStopwatchText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblStopwatchText.setText("00 : 00 : 00");
    panelBottom.add(lblStopwatchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, 70, 20));

    panelParent.add(panelBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 250, 20));

    lblAlias.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    lblAlias.setForeground(new java.awt.Color(255, 255, 255));
    lblAlias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblAlias.setText("jLabel1");
    panelParent.add(lblAlias, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 55, 55));

    lblStudyProgram.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
    lblStudyProgram.setForeground(new java.awt.Color(153, 153, 153));
    lblStudyProgram.setText("[study program]");
    lblStudyProgram.setToolTipText("");
    panelParent.add(lblStudyProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 150, -1));

    lblStudentID.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
    lblStudentID.setText("[student ID]");
    lblStudentID.setToolTipText("");
    panelParent.add(lblStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 33, 150, -1));

    lblName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    lblName.setForeground(new java.awt.Color(121, 205, 205));
    lblName.setText("[name]");
    lblName.setToolTipText("");
    panelParent.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 150, -1));

    jLabel1.setBackground(new java.awt.Color(220, 220, 220));
    jLabel1.setOpaque(true);
    panelParent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 10, 2, 60));

    btnLogout.setBackground(new java.awt.Color(255, 255, 255));
    btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
    btnLogout.setForeground(new java.awt.Color(255, 102, 102));
    btnLogout.setText("Logout");
    btnLogout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102), 2));
    btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnLogout.setFocusPainted(false);
    btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            btnLogoutMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            btnLogoutMouseExited(evt);
        }
    });
    btnLogout.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnLogoutActionPerformed(evt);
        }
    });
    panelParent.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 73, 80, 22));

    lblErrorMsg.setForeground(new java.awt.Color(255, 51, 51));
    panelParent.add(lblErrorMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 75, 70, -1));

    getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 120));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBottomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBottomMouseClicked
        renewThread(this.slideDownThread);
        this.slideDownThread.start();
    }//GEN-LAST:event_panelBottomMouseClicked

    private void panelBottomMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBottomMouseEntered
        renewThread(this.slideDownThread);
        this.slideDownThread.start();
    }//GEN-LAST:event_panelBottomMouseEntered

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int promptResp = JOptionPane.showConfirmDialog(this, "Logout now?", "Logout", JOptionPane.YES_NO_OPTION);
        if (promptResp == JOptionPane.NO_OPTION) return;
        
        if (this.logoutThread == null || !this.logoutThread.isAlive()) {
            this.logoutThread = new Thread(() -> {
                boolean dbResp = DbWizard.signOut(this.transactionId);
                if (!dbResp) {
                    this.lblErrorMsg.setText("Can't logout!");
                } else {
                    parentFrame.changeSceneTo(FrameMain.PANEL_SIGN_IN);
                    parentFrame.showUp();
                    this.setVisible(false);
                    this.dispose();
                }
            });
            this.logoutThread.start();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        ((JButton)evt.getSource()).setBackground(new Color(255, 102, 102));
        ((JButton)evt.getSource()).setForeground(Color.WHITE);
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        ((JButton)evt.getSource()).setBackground(Color.WHITE);
        ((JButton)evt.getSource()).setForeground(new Color(255, 102, 102));
    }//GEN-LAST:event_btnLogoutMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAlias;
    private javax.swing.JLabel lblErrorMsg;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStopwatchIcon;
    private javax.swing.JLabel lblStopwatchText;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblStudyProgram;
    private javax.swing.JPanel panelBottom;
    private javax.swing.JPanel panelParent;
    // End of variables declaration//GEN-END:variables
}
