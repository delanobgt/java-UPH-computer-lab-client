/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author UPHM
 */
public class LabTransaction {

    private final Integer id;
    private final String nim;
    private final String signIn;
    private final String signOut;
    private final Integer pcNumber;
    private final String labLocation;

    
    public LabTransaction(Integer id, String nim, String signin, String signout, Integer pcnumber, String labLocation) {
        this.id = id;
        this.nim = nim;
        this.signIn = signin;
        this.signOut = signout;
        this.pcNumber = pcnumber;
        this.labLocation = labLocation;
    }
    
    public LabTransaction(Student student, String signIn, Integer pcNumber, String labLocation) {
        this.id = null;
        this.nim = student.getStudentId();
        this.signIn = signIn;
        this.signOut = "1990-10-10 00:00:00";
        this.pcNumber = pcNumber;
        this.labLocation = labLocation;
    }

    public Integer getId() {
        return id;
    }
    
    public String getNim() {
        return nim;
    }

    public String getSignIn() {
        return signIn;
    }

    public String getSignOut() {
        return signOut;
    }

    public Integer getPcNumber() {
        return pcNumber;
    }

    public String getLabLocation() {
        return labLocation;
    }

}
