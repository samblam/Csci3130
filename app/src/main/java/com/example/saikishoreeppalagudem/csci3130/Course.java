package com.example.saikishoreeppalagudem.csci3130;

/**
 * @author saikishoreeppalagudem
 *  @author Integrated by karthick parameswaran
 *  @author Documented by Sam Barefoot
 */

public class Course {
    /**
     * String That Stores a Course Objects ID number
     */
    String courseID;
    /**
     * String containing a text description of the Course
     */
    String courseDesc;
    /**
     * Long that stores how may seats are currently available for a course
     */

    Long seatsAvail;
    /**
     * String that stores the name of the prof teaching the course
     */
    String courseProf;
    /**
     * String that stores the email of the prof teaching the course
     */
    String profEmail;
    /**
     * String storing the timings for the course
     */
    String courseTimings;
    /**
     * Long storing how many students are on the waiting list
     */
    Long seatWL;

    String courseDetail;
    /**
     * String indicating in which semester the course is taking place
     */
    String termID;

    @Override
    public String toString() {

        return courseID + ","  + courseDesc + "," + courseTimings + "," + courseProf + "," + profEmail + "," + seatsAvail
                + "," + courseDetail + ","+ seatWL+ "," + termID;
    }

    public String getTermID() {
        return termID;
    }

    public void setTermID(String termID) {
        this.termID = termID;
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

    public Course(String courseID, String courseDesc, String courseTimings, String courseProf,String profEmail, Long seatsAvail,
                  String termID, String courseDetail, Long seatWL) {
        this.courseID = courseID;
        this.courseDesc = courseDesc;
        this.profEmail = profEmail;
        this.courseProf = courseProf;
        this.courseTimings = courseTimings;
        this.seatsAvail = seatsAvail;
        this.termID = termID;
        this.seatWL = seatWL;
        this.courseDetail = courseDetail;

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

    public String getCourseDetail() {return courseDetail;}
}
