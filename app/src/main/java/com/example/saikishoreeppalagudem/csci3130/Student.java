package com.example.saikishoreeppalagudem.csci3130;

/**
 * Created by saikishoreeppalagudem on 2018-02-21.
 */

public class Student {
    String studentName;
    String studentID;

    public Student(){

    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentID() {
        return studentID;
    }



    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }


}
