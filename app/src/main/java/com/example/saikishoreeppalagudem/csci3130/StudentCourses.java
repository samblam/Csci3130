package com.example.saikishoreeppalagudem.csci3130;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * @author saikishoreeppalagudem
 */

public class StudentCourses {
    /**
     * Firebase reference to a students course list
     */
    DatabaseReference stuCourseRef = FirebaseDatabase.getInstance().getReference("Student");;

    public void updateCourses(ArrayList<String> changedStuCourses){
        String stuCourses = changedStuCourses.toString();
        Log.e("stuCourses", stuCourses);
    }
}
