package com.example.saikishoreeppalagudem.csci3130;

/**
 * Created by saikishoreeppalagudem on 2018-02-21.
 */

public class StudentCourse {
    String studentID;
    String studentCourse;

    public StudentCourse(String studentID, String studentCourse) {
        this.studentID = studentID;
        this.studentCourse = studentCourse;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public StudentCourse(){}


}
