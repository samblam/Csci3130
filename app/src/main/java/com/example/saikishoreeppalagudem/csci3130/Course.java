package com.example.saikishoreeppalagudem.csci3130;

/**
 * Created by saikishoreeppalagudem on 2018-02-22.
 */

public class Course {
    String courseID;
    String courseDesc;
    String courseProf;
    String profEmail;

    @Override
    public String toString() {
//        return "Course{" +
//                "courseID='" + courseID + '\'' +
//                ", courseDesc='" + courseDesc + '\'' +
//                '}';
        return courseID + ","  + courseDesc + "," + courseProf + "," + profEmail;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public void setProfname(String courseProf) {
        this.courseProf = courseProf;
    }

    public void setProfmail(String profEmail) {
        this.profEmail = profEmail;
    }

    public Course(){

    }

    public Course(String courseID, String courseDesc,String courseProf,String profEmail) {
        this.courseID = courseID;
        this.courseDesc = courseDesc;
        this.profEmail = profEmail;
        this.courseProf = courseProf;
    }

    public String getCourseProf() {
        return courseProf;
    }

    public String getProfEmail() {
        return profEmail;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseDesc() {
        return courseDesc;
    }
}
