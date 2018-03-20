package com.example.saikishoreeppalagudem.csci3130;

/**
 * @author saikishoreeppalagudem on 2018-02-22.
 *  @author Integrated by karthick parameswaran on 2018-03-16
 *  @author Documentation by Sam Barefoot
 */

public class Course {


    /**
     * courseID is an alphanumeric code that is given to each unique course
     */
    String courseID;
    /**
     * courseDesc is a short string describing the course, it's material, etc.
     */
    String courseDesc;
    /**
     * seatsAvail is a number representing how many open spots there are in a particular course
     */
    String seatsAvail;
    /**
     * courseProf is a string storing the name of a prof who is teaching
     */
    String courseProf;
    /**
     * profEmail is a string storing the contact email of a prof for any particular course
     */
    String profEmail;
    /**
     * courseTimings is the timings of each class for a particular course
     */
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

    /**
     * sets how many seats are available
     * @param seatsAvail
     */
    public void setSeatsAvail(String seatsAvail) {
        this.seatsAvail = seatsAvail;
    }

    /**
     * Default Constructor
     */
    public Course(){
    }

    /**
     *  Creates a new Course Object, and populates it with the given parameters
     * @param courseID
     * @param courseDesc
     * @param courseTimings
     * @param courseProf
     * @param profEmail
     * @param seatsAvail
     */
    public Course(String courseID, String courseDesc, String courseTimings, String courseProf,String profEmail, String seatsAvail) {
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

    /**
     *
     * @return seatsAvail
     */
    public String getSeatsAvail() {
        return seatsAvail;
    }
}
