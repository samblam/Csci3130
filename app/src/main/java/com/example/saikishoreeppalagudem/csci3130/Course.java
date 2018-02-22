package com.example.saikishoreeppalagudem.csci3130;

/**
 * Created by saikishoreeppalagudem on 2018-02-22.
 */

public class Course {
    String courseID;
    String courseDesc;

    public Course(){

    }

    public Course(String courseID, String courseDesc) {
        this.courseID = courseID;
        this.courseDesc = courseDesc;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseDesc() {
        return courseDesc;
    }
}
