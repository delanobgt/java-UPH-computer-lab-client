package db;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LabTransaction;
import model.Student;

/**
 *
 * @author irvin
 */
public class DbWizard {
    
    private static Thread studentFetchThread = null;
    private static Thread labTransactionSyncThread = null;
    
    public static Student doesStudentExist(String nim) {
        if (studentFetchThread == null || !studentFetchThread.isAlive()) {
            studentFetchThread = new Thread(() -> {
                List<Student> cloudStudentList = DbCloudStudent.getStudentList();
                for (Student student : cloudStudentList) {
                    DbLocalStudent.addStudent(student);
                }
                DbLocalStudent.save();
            });
            studentFetchThread.start();
        }
        
        Student student = DbLocalStudent.getStudentByNim(nim);
        if (student != null) {
            return student;
        } else {
            try {
                studentFetchThread.join();
            } catch (InterruptedException ex) {Logger.getLogger(DbWizard.class.getName()).log(Level.SEVERE, null, ex);}
            student = DbLocalStudent.getStudentByNim(nim);
            if (student != null) return student;
        }
        return null;
    }
    
    public static Integer signIn(Student student) {
        Integer newID = DbLocalLabTransaction.createSignIn(student);
        startLabTransactionSyncThread();
        return newID;
    }
    
    public static boolean signOut(int id) {
        DbLocalLabTransaction.updateSignOut(id);
        startLabTransactionSyncThread();
        return true;
    }
    
    private static void startLabTransactionSyncThread() {
        if (labTransactionSyncThread == null || !labTransactionSyncThread.isAlive()) {
            labTransactionSyncThread = new Thread(() -> {
                Boolean repeat = true;
                while (repeat) {
                    repeat = false;
                    List<LabTransaction> labTransactionList = DbLocalLabTransaction.getLabTransactionList();
                    for (LabTransaction labTransaction : labTransactionList) {
                        if (labTransaction.getCloudID() == null) {
                            repeat = true;
                            int newCloudID = DbCloudLabTransaction.addLabTransaction(labTransaction);
                            labTransaction.setCloudID(newCloudID);
                        } else if (!labTransaction.getSignOut().equals("1990-10-10 00:00:00") && 
                                labTransaction.getCloudID() != null) {
                            repeat = true;
                            if (DbCloudLabTransaction.updateLabTransaction(labTransaction)) {
                                DbLocalLabTransaction.deleteLabTransactionById(labTransaction.getId());
                            }
                        }
                    }
                    DbLocalLabTransaction.save();
                }
            });
            labTransactionSyncThread.start();
        }
    }
}
