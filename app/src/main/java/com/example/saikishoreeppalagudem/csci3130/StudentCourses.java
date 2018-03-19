package com.example.saikishoreeppalagudem.csci3130;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by saikishoreeppalagudem on 2018-03-19.
 */

public class StudentCourses {
    DatabaseReference stuCourseRef = FirebaseDatabase.getInstance().getReference("Student");;

    public void updateCourses(ArrayList<String> changedStuCourses){
        String stuCourses = changedStuCourses.toString();
        Log.e("stuCourses", stuCourses);
    }
}
