package com.example.saikishoreeppalagudem.csci3130;

/**
 * @author saikishoreeppalagudem on 2018-02-21.
 * @author Documented by Sam Barefoot
 */

public class Student {
    /**
     * String Storing the Students Name
     */
    String studentName;
    /**
     * String Storing the Student's ID
     */
    String studentID;
    /**
     * String Storing student Courses
     */
    String studentCourses;

    /**
     * Default constructor for Student Object
     */
    public Student(){

    }

    /**
     * Retriever Function for studentName
     * @return String studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Retriever Function for studentID
     * @return String studentID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Retriever Function for studentCourses
     * @return String studentCourses
     */
    public String getStudentCourses(){
        return studentCourses;
    }

    /**
     * Setter Function for studentName
     * @param studentName
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Setter Function for studentID
     * @param studentID
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Setter Function for studentCourses
     * @param studentCourses
     */
    public void setStudentCourses(String studentCourses) {
        this.studentCourses = studentCourses;
    }

    @Override
    /**
     * Concatenates all of a student variables into one string
     */
    public String toString() {
        return studentName + ',' + studentID + ',' + studentCourses;
    }

    /**
     * Constructor for an Instance of a Student Object
     * @param studentID
     * @param studentName
     * @param studentCourses
     */
    public Student(String studentID, String studentName, String studentCourses) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentCourses = studentCourses;
    }


}
