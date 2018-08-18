/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lab04
 */
public class Student {
    private final String studentId;
    private final String name;
    private final String studyProgram;

    public Student(String studentId, String name, String studyProgram) {
        this.studentId = studentId;
        this.name = name;
        this.studyProgram = studyProgram;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudyProgram() {
        return studyProgram;
    }
        
}
