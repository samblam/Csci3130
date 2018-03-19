package com.example.saikishoreeppalagudem.csci3130;


import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author saikishoreeppalagudem on 2018-03-10.
 * @author Documented by Sam Barefoot
 */

public class CourseRegistration {

    public Map<String, String> buildSchedule(ArrayList<String> studentCourses, Map<String, String> courseTimes){
        Map<String, String> schedule = new HashMap<>();
        for (String course:
             studentCourses) {
            String courseTime = courseTimes.get(course);
            schedule.put(course, courseTime);
        }
        return schedule;
    }

    public ArrayList<String> getTimeListFromScheduleMap(Map<String, String> schedule){
        ArrayList<String> timingList = new ArrayList<String>(schedule.values());
        return  timingList;
    }

    public String courseToBeRegisteredTiming(String courseToRegister, Map<String, String> courseTimes){
        String newCourseTiming = "";
        newCourseTiming = courseTimes.get(courseToRegister);
        return  newCourseTiming;
    }

    public boolean chkTimeConflict(String courseToRegister, Map<String, String> courseTimes, Map<String, String> schedule){
        String newCourseTiming = courseToBeRegisteredTiming(courseToRegister, courseTimes);
        ArrayList<String> scheduleTimings = getTimeListFromScheduleMap(schedule);
        TimeConflict timeConflict = new TimeConflict();
        Boolean chk = timeConflict.checkTimeConflict(newCourseTiming, scheduleTimings);
        return chk;

    }


    public boolean chkCourseAlreadyRegistered(ArrayList<String> studentCourses, String courseToRegister){
        if (studentCourses.contains(courseToRegister)){
            return true;
        }
        else{
            return  false;
        }
    }


    public void pushCourseRegistration(ArrayList<String> studentCourses, String courseToRegister, String keyStudentID){
        String pushCourses = new String();
        DatabaseReference studentCourseRef = FirebaseDatabase.getInstance().getReference("Student").child(keyStudentID);
        Log.e("studentCourseRef",""+ studentCourseRef);
        Map<String, Object> courseUpdates = new HashMap<>();
        studentCourses.add(courseToRegister);

        for(int i = 0; i < studentCourses.size(); i++){
            if(studentCourses.get(i)!= "null") {
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
        Log.e("pushcourses", ""+pushCourses);
        courseUpdates.put("studentCourses", pushCourses);
        studentCourseRef.updateChildren(courseUpdates);

    }



}
