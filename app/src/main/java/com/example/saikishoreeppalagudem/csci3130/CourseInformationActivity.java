package com.example.saikishoreeppalagudem.csci3130;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseInformationActivity extends AppCompatActivity {
    TextView tvCourseInfo, tvCourseInfoDesc, seatsAvail;
    Button btnRegister;
    DatabaseReference databaseStudent, databaseCourse;
    ArrayList<String> studentInfoList, studentIDInfo;
    static String keyStudentID;
    Map<String, ArrayList<String>> studentInfoMap = new HashMap<>();
    Map<String, String> courseInfoMap = new HashMap<>();
    public static ArrayList<ArrayList<String>> studentCoursesInfo;
    public CourseRegistration courseRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_information);

        tvCourseInfo = findViewById(R.id.tvCourseInfo);
        tvCourseInfoDesc = findViewById(R.id.tvCourseInfoDesc);
        seatsAvail = findViewById(R.id.seatsAvail);
        studentInfoList = new ArrayList<>();
        studentCoursesInfo = new ArrayList<>();
        studentIDInfo = new ArrayList<>();

        Intent intent = getIntent();
        ArrayList<String> message = intent.getStringArrayListExtra("ExtraMsg");
        if(message!=null) {
            tvCourseInfo.setText(message.get(0));
            tvCourseInfoDesc.setText(message.get(1));
            seatsAvail.setText(message.get(2));
        }

        courseRegistration = new CourseRegistration();

        databaseStudent = FirebaseDatabase.getInstance().getReference("Student");
        databaseCourse = FirebaseDatabase.getInstance().getReference("Courses");
        keyStudentID = "3";


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
                    studentInfoList.clear();
                    String studentID = String.valueOf(studentSnapshot.child("studentID").getValue());
                    String studentCourseInfo = String.valueOf(studentSnapshot.child("studentCourses").getValue());
                    studentIDInfo.add(studentID);
                    String[] a = studentCourseInfo.split(",");
                    for(int j = 0; j < a.length ; j++){
                        studentInfoList.add(a[j]);
                    }

//                    studentCoursesInfo.add(studentInfoList) ;
                    studentInfoMap.put(studentID, studentInfoList);
                    studentInfoList = new ArrayList<>(studentInfoList);
                }
                Log.e("student", "" +   studentInfoMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseCourse.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseInfoMap.clear();
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()){
                    String courseID = String.valueOf(courseSnapshot.child("courseID").getValue());
                    String courseTimingInfo = String.valueOf(courseSnapshot.child("courseTiming").getValue());
                    courseInfoMap.put(courseID, courseTimingInfo);

                }
            Log.e("courses", "" +courseInfoMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void registerOnClick(View view) {
        ArrayList<String> courses = new ArrayList<>();
        String courseToRegister = tvCourseInfo.getText().toString();
        courses = studentInfoMap.get(keyStudentID);
        Log.e("StudentCourses: ", courses + "");
        Map<String, String> scheduleMap = new HashMap<>();
        scheduleMap = courseRegistration.buildSchedule(courses, courseInfoMap);
        Log.e("scheduleMap", scheduleMap + "");
        if (courseRegistration.chkCourseAlreadyRegistered(courses, courseToRegister)){
            Toast.makeText(this, "Already registered!", Toast.LENGTH_SHORT).show();
        }
        else{
            if (courseRegistration.chkTimeConflict(courseToRegister, courseInfoMap, scheduleMap)){
                Toast.makeText(this, "Time conflict!", Toast.LENGTH_SHORT).show();
            }
            else{
                courseRegistration.pushCourseRegistration(courses, courseToRegister, keyStudentID);
                Toast.makeText(this, "Course registered successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
