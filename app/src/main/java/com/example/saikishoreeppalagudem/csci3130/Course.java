package com.example.saikishoreeppalagudem.csci3130;

/**
 * @author saikishoreeppalagudem on 2018-02-22.
 *  @author Integrated by karthick parameswaran on 2018-03-16
 *  @author Documentation by Sam Barefoot
 */

public class Course {
    /**
     * courseID is an alphanumeric code that is given to each unique course
     * courseDesc is a short string describing the course, it's material, etc.
     * seatsAvail is a number representing how many open spots there are in a particular course
     * courseProf is a string storing the name of a prof who is teaching
     * profEmail is a string storing the contact email of a prof for any particular course
     * courseTimings is the timings of each class for a particular course
     *
     * Note: See here for all method parameter Descriptions
     */
    String courseID;
    String courseDesc;
    Long seatsAvail;
    String courseProf;
    String profEmail;
    String courseTimings;

    @Override

    /**
     * Concatenates all of the Course Variables into one string
     * @return a string contraining all course variables
     */
    public String toString() {

        return courseID + ","  + courseDesc + "," + courseTimings + "," + courseProf + "," + profEmail + "," + seatsAvail;
    }

    /**
     *
     * @return returns course times
     */
    public String getCourseTimings() {
        return courseTimings;
    }

    /**
     * Sets course ID
     * @param courseID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * Sets course description
     * @param courseDesc
     */
    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    /**
     * Sets Prof name
     * @param courseProf
     */
    public void setProfname(String courseProf) {
        this.courseProf = courseProf;
    }

    /**
     * sets Prof email address
     * @param profEmail
     */
    public void setProfmail(String profEmail) {
        this.profEmail = profEmail;
    }

    public void setSeatsAvail(Long seatsAvail) {
        this.seatsAvail = seatsAvail;
    }

    /**
     * Default Constructor
     */
    public Course(){
    }

    public Course(String courseID, String courseDesc, String courseTimings, String courseProf,String profEmail, Long seatsAvail) {
        this.courseID = courseID;
        this.courseDesc = courseDesc;
        this.profEmail = profEmail;
        this.courseProf = courseProf;
        this.courseTimings = courseTimings;
        this.seatsAvail = seatsAvail;

    }

    /**
     *
     * @return courseProf
     */
    public String getCourseProf() {
        return courseProf;
    }

    /**
     *
     * @return profEmail
     */

    public String getProfEmail() {
        return profEmail;
    }

    /**
     *
     * @return courseID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     *
     * @return courseDesc
     */

    public String getCourseDesc() {
        return courseDesc;
    }

    public Long getSeatsAvail() {
        return seatsAvail;
    }
}
