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

    private Integer id;
    private final String nim;
    private final String signIn;
    private String signOut;
    private final Integer pcNumber;
    private final String labLocation;
    private Integer cloudID;
    
    public LabTransaction(Integer id, String nim, String signIn, String signOut, Integer pcNumber, String labLocation, Integer cloudID) {
        this.id = id;
        this.nim = nim;
        this.signIn = signIn;
        this.signOut = signOut;
        this.pcNumber = pcNumber;
        this.labLocation = labLocation;
        this.cloudID = cloudID;
    }
    
    public LabTransaction(Student student, String signIn, Integer pcNumber, String labLocation) {
        this.id = null;
        this.nim = student.getStudentId();
        this.signIn = signIn;
        this.signOut = "1990-10-10 00:00:00";
        this.pcNumber = pcNumber;
        this.labLocation = labLocation;
        this.cloudID = null;
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

    public Integer getCloudID() {
        return cloudID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSignOut(String signOut) {
        this.signOut = signOut;
    }

    public void setCloudID(Integer cloudID) {
        this.cloudID = cloudID;
    }
}
