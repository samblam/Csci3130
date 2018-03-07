package com.example.saikishoreeppalagudem.csci3130;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by saikishoreeppalagudem on 2018-03-06.
 */

public class TimeConflict {

    public String checkTimeConflict(String newCourseTiming, ArrayList<String> scheduleTimings){
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

        String output = "";
        if(chk == true) output = "Time Conflict!";
        if(chk == false) output = "No time conflict!";
        return output;
    }

    public String[] splitTimingStringtoList(String timing){
        String [] timingStrList = timing.split("-");
        System.out.println("timingStrList " + timingStrList );
        return timingStrList;
    }

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
