package com.example.saikishoreeppalagudem.csci3130;

/**
 * Created by saikishoreeppalagudem on 2018-02-22.
 */

public class Course {
    String courseID;
    String courseDesc;
    Long seatsAvail;

    @Override
    public String toString() {
//        return "Course{" +
//                "courseID='" + courseID + '\'' +
//                ", courseDesc='" + courseDesc + '\'' +
//                '}';
        return courseID + ","  + courseDesc + "," + seatsAvail;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public void setSeatsAvail(Long seatsAvail) {

        this.seatsAvail = seatsAvail;
    }

    public Course(){

    }

    public Course(String courseID, String courseDesc, Long seatsAvail) {
        this.courseID = courseID;
        this.courseDesc = courseDesc;
        this.seatsAvail = seatsAvail;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public Long getSeatsAvail() {
        return seatsAvail;
    }
}
