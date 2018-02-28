package com.example.saikishoreeppalagudem.csci3130;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by saikishoreeppalagudem on 2018-02-25.
 */

public class FirebaseEventHandlers {
//    String pushCourses;
//    public void dropCourse(ArrayList<String> courses, String studentID){
//        DatabaseReference databaseStudent = FirebaseDatabase.getInstance().getReference("Student");
//        DatabaseReference studentCourseRef = databaseStudent.child(studentID);
//        Log.e("studentCourseRef",""+ studentCourseRef);
//        Map<String, Object> courseUpdates = new HashMap<>();
//        courses.add(tvCourseInfo.getText().toString());
//
//        for(int i = 0; i < courses.size(); i++){
//            if(courses.get(i)!= "null") {
//                if (i == 0) {
//                    pushCourses = courses.get(i);
//                } else {
//                    pushCourses = pushCourses + courses.get(i);
//                }
//                if (i < courses.size() - 1) {
//                    pushCourses = pushCourses + ",";
//                }
//            }
//        }
//        Log.e("pushcourses", ""+pushCourses);
//        courseUpdates.put("studentCourses", pushCourses);
//        studentCourseRef.updateChildren(courseUpdates);
//            studentCourseRef.updateChildrenAsync(courseUpdates);
//
//        Toast.makeText(this, "Course not yet registered", Toast.LENGTH_SHORT).show();
//    }
}
