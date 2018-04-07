package com.example.saikishoreeppalagudem.csci3130;


import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author saikishoreeppalagudem on 2018-03-10.
 * @author Documented by Sam Barefoot
 */

public class CourseRegistration {
    public ArrayList<String> finStudentCourses = new ArrayList<>();

    
    /**Makes a Students Schedule
     * <p>
     * Takes the list of courses an individual student is registered for , the Map of course times.
     * </p>
     * <p>
     * Creates a new hashmap called Schedule, populates it with the parameters, and returns it
     * </p>
     *
     * @param studentCourses
     * @param courseTimes
     * @return The specified students course schedule
     */
    public Map<String, String> buildSchedule(ArrayList<String> studentCourses, Map<String, String> courseTimes) {
        Map<String, String> schedule = new HashMap<>();
        for (String course :
                studentCourses) {
            String courseTime = courseTimes.get(course);
            schedule.put(course, courseTime);
        }
        return schedule;
    }

    /**
     * Takes student schedule, returns an arraylist of course times
     *
     *
     * @param schedule
     * @return list of course times
     */
    public ArrayList<String> getTimeListFromScheduleMap(Map<String, String> schedule) {
        ArrayList<String> timingList = new ArrayList<String>(schedule.values());
        return timingList;
    }

    /**
     * @param courseToRegister
     * @param courseTimes
     * @return returns time of the course the student is attempting to register
     */
    public String courseToBeRegisteredTiming(String courseToRegister, Map<String, String> courseTimes) {
        String newCourseTiming = "";
        newCourseTiming = courseTimes.get(courseToRegister);
        return newCourseTiming;
    }

    /**
     * Checks to see if there is a time conflict between the course that the user is attempting to register
     * And the courses the student is currently registered for
     *
     * @param courseToRegister
     * @param courseTimes
     * @param schedule
     * @return
     */
    public boolean chkTimeConflict(String courseToRegister, Map<String, String> courseTimes, Map<String, String> schedule) {
        String newCourseTiming = courseToBeRegisteredTiming(courseToRegister, courseTimes);
        ArrayList<String> scheduleTimings = getTimeListFromScheduleMap(schedule);
        TimeConflict timeConflict = new TimeConflict();
        Boolean chk = timeConflict.checkTimeConflict(newCourseTiming, scheduleTimings);
//        Boolean chk = false;
        return chk;

    }

    /**
     * Checks to see if the Student is currently registered to the course they are attempting to register
     *
     * @param studentCourses
     * @param courseToRegister
     * @return returns true if student is already registered, otherwise returns false
     */
    public boolean chkCourseAlreadyRegistered(ArrayList<String> studentCourses, String courseToRegister) {
        if (studentCourses.contains(courseToRegister)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates Firebase with new Registration Data
     *
     * @param studentCourses
     * @param courseToRegister
     * @param keyStudentID
     * @param action
     */
    public void pushCourseRegistration(ArrayList<String> studentCourses, String courseToRegister, String keyStudentID, String action) {
        String pushCourses = new String();
        DatabaseReference studentCourseRef = FirebaseDatabase.getInstance().getReference("Student").child(keyStudentID);
        Log.e("studentCourseRef", "" + studentCourseRef);
        Map<String, Object> courseUpdates = new HashMap<>();
        if (!courseToRegister.isEmpty()) {
            studentCourses.add(courseToRegister);
        }
        for (int i = 0; i < studentCourses.size(); i++) {
            if (studentCourses.get(i) != "null") {
                if (i == 0) {
                    pushCourses = studentCourses.get(i);
                } else {
                    pushCourses = pushCourses + studentCourses.get(i);
                }
                if (i < studentCourses.size() - 1) {
                    pushCourses = pushCourses + ",";
                }
            }
        }
        Log.e("pushcourses", "" + pushCourses);
        if (action.equals("register")) {
            courseUpdates.put("studentCourses", pushCourses);
        } else {
            courseUpdates.put("waitlistCourses", pushCourses);
        }
        studentCourseRef.updateChildren(courseUpdates);

    }


    /**
     * Updates Firebase with new Registration Data
     *
     * @param courseToRegister
     * @param keyStudentID
     * @param action
     */
    public void pushCourseRegistration(String courseToRegister, String keyStudentID, String action) {
        String pushCourses = new String();
        Map<String, Object> courseUpdates = new HashMap<>();
        DatabaseReference studentCourseRef = FirebaseDatabase.getInstance().getReference("Student").child(keyStudentID);
        pushCourses = courseToRegister;
        Log.e("pushcourses", "" + pushCourses);
        if (action.equals("register")) {
            courseUpdates.put("studentCourses", pushCourses);
        } else {
            courseUpdates.put("waitlistCourses", pushCourses);
        }        studentCourseRef.updateChildren(courseUpdates);

    }

    /**
     * Checks and updates seat availability
     *
     * @param courseID
     * @param seatAvailabilty
     * @param a
     */
    public boolean chkAndUpdateSeatAvailability(String courseID, String seatAvailabilty, long a) {
        courseID = courseID.replaceAll("\\s+", "");
        long seats = Long.valueOf(seatAvailabilty) - a;
        String updatedSeat = String.valueOf(seats - 1);
        if (seats >= 0) {
            DatabaseReference courseRef = FirebaseDatabase.getInstance().getReference("Courses").child(courseID);
            Map<String, Object> courseInfoUpdate = new HashMap<>();
            courseInfoUpdate.put("seatsAvail", seats);
            courseRef.updateChildren(courseInfoUpdate);
            return true;
        } else
            return false;
    }


    /**
     * Checks and updates waitlist
     *
     * @param courseID
     * @param waitListAvailability
     * @param a
     */
    public boolean chkAndUpdateWaitlistAvailability(String courseID, String waitListAvailability, long a) {
        courseID = courseID.replaceAll("\\s+", "");
        long seats = Long.valueOf(waitListAvailability) - a;
        String updatedSeat = String.valueOf(seats - 1);
        if (seats >= 0) {
            DatabaseReference courseRef = FirebaseDatabase.getInstance().getReference("Courses").child(courseID);
            Map<String, Object> courseInfoUpdate = new HashMap<>();
            courseInfoUpdate.put("seatWL", seats);
            courseRef.updateChildren(courseInfoUpdate);
            return true;
        } else
            return false;
    }

    /**
     * Performs a series of checks
     *
     * @param courses
     * @param waitListCourses
     * @param courseTimes
     * @param courseTimes
     * @param schedule
     * @param courseToRegister
     * @param keyStudentID
     * @param seatAvailability
     * @param waitListAvailability
     * @param activity
     */
    public void registrationHandler(ArrayList<String> courses, ArrayList<String> waitListCourses, Map<String, String> courseTimes,
                                    Map<String, String> schedule, String courseToRegister, String keyStudentID, String seatAvailability,
                                    String waitListAvailability, Activity activity){
        if (chkCourseAlreadyRegistered(courses, courseToRegister)) {
            Toast.makeText(activity, "Already registered!", Toast.LENGTH_SHORT).show();
        } else {
            if (chkTimeConflict(courseToRegister, courseTimes, schedule)) {
                Toast.makeText(activity, "Time conflict!", Toast.LENGTH_SHORT).show();
            } else if (chkAndUpdateSeatAvailability(courseToRegister, seatAvailability, 1)) {
                pushCourseRegistration(courses, courseToRegister, keyStudentID, "register");
                Toast.makeText(activity, "Course registered successfully!", Toast.LENGTH_SHORT).show();
            }
            else if (chkCourseAlreadyRegistered(waitListCourses, courseToRegister)) {
                Toast.makeText(activity, "Already waitlisted!", Toast.LENGTH_SHORT).show();
            } else if (chkAndUpdateWaitlistAvailability(courseToRegister, waitListAvailability, 1)) {
                pushCourseRegistration(waitListCourses, courseToRegister, keyStudentID,"waitlist");
                Toast.makeText(activity, "Course waitlisted successfully!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(activity, "Course is full!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
