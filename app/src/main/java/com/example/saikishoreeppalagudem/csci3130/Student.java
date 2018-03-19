package com.example.saikishoreeppalagudem.csci3130;

/**
 * @author saikishoreeppalagudem on 2018-02-21.
 * @author Documented by Sam Barefoot
 */

public class Student {
    String studentName;
    String studentID;
    String studentCourses;


    public Student(){

    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentCourses(){
        return studentCourses;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentCourses(String studentCourses) {
        this.studentCourses = studentCourses;
    }

    @Override
    public String toString() {
        return studentName + ',' + studentID + ',' + studentCourses;
    }

    public Student(String studentID, String studentName, String studentCourses) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentCourses = studentCourses;
    }


}
