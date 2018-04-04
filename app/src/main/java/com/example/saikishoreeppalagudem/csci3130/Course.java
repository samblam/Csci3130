package com.example.saikishoreeppalagudem.csci3130;

/**
 * Created by saikishoreeppalagudem on 2018-02-22.
 *  Integrated by karthick parameswaran on 2018-03-16
 */

public class Course {
    String courseID;
    String courseDesc;
    Long seatsAvail;
    String courseProf;
    String profEmail;
    String courseTimings;
    Long seatWL;

    @Override
    public String toString() {

        return courseID + ","  + courseDesc + "," + courseTimings + "," + courseProf + "," + profEmail + "," + seatsAvail + "," + seatWL;
    }

    public String getCourseTimings() {
        return courseTimings;
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
        this.profEmail = profEmail;}

    public void setSeatsAvail(Long seatsAvail) {
        this.seatsAvail = seatsAvail;
    }

    public Course(){
    }

    public Course(String courseID, String courseDesc, String courseTimings, String courseProf,String profEmail, Long seatsAvail, Long seatWL) {
        this.courseID = courseID;
        this.courseDesc = courseDesc;
        this.profEmail = profEmail;
        this.courseProf = courseProf;
        this.courseTimings = courseTimings;
        this.seatsAvail = seatsAvail;
        this.seatWL = seatWL;

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

    public Long getSeatsAvail() {
        return seatsAvail;
    }

    public Long getSeatWL(){return seatWL;}

}
