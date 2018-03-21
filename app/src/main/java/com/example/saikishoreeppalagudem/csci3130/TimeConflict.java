package com.example.saikishoreeppalagudem.csci3130;

import android.util.Log;

import java.util.ArrayList;

/**
 * @author saikishoreeppalagudem on 2018-03-06.
 * @author Documented by Sam Barefoot
 */

public class TimeConflict {
    /**
     * Function checks with a users current course timings, and whatever new course they are trying to register,
     * to see if there is a time conflict
     * @param newCourseTiming   Takes a string value of the timings of the course that the user wants to register for.
     * @param scheduleTimings   ArrayList containing the timings of courses that the user has already register for.
     * @return boolean value saying if there is a time conflict or not
     */
    public Boolean checkTimeConflict(String newCourseTiming, ArrayList<String> scheduleTimings){
        String[] newCourseTimingList = splitTimingStringtoList(newCourseTiming);
        String[] scheduleList = new String[2];
        int scheduleLen = scheduleTimings.size();
        boolean chk = false;
        for(int i = 0 ; i < scheduleLen ; i++){
            if(chk == false) {
                scheduleList = splitTimingStringtoList(scheduleTimings.get(i).toString());
                System.out.println("chk: "+chk);
                chk = processSchedule(newCourseTimingList, scheduleList);
            }
        }
        return chk;
    }

    /**
     * Splits string of timing into a list
     * @param timing
     * @return
     */
    public String[] splitTimingStringtoList(String timing){
        String [] timingStrList = timing.split("-");
        System.out.println("timingStrList " + timingStrList );
        return timingStrList;
    }

    /**
     * Parses through schedule
     * @param courseTiming
     * @param scheduleTiming
     * @return
     */
    public boolean processSchedule(String[] courseTiming, String[] scheduleTiming){
        if (Integer.parseInt(courseTiming[0]) <= Integer.parseInt(scheduleTiming[0])){
            if(Integer.parseInt(courseTiming[1]) <= Integer.parseInt(scheduleTiming[0])){
                System.out.println("Inside first cond");
                return false;
            }
            else{
                return true;
            }
        }
        else if(Integer.parseInt(courseTiming[0]) >= Integer.parseInt(scheduleTiming[1])){
            if(Integer.parseInt(courseTiming[1]) >= Integer.parseInt(scheduleTiming[1])){
                System.out.println("Inside second cond");
                return false;
            }
            else{return true;}
        }
        return false;
    }
}
