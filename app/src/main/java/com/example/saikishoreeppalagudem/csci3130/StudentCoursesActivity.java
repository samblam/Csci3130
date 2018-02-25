package com.example.saikishoreeppalagudem.csci3130;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentCoursesActivity extends AppCompatActivity {
    DatabaseReference databaseStudentCourses;
    ListView listViewStudentCourses;
    ArrayList<Course> courseList;
//    ArrayList<String> courseClickedInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_courses);

        courseList = new ArrayList<>();

        databaseStudentCourses = FirebaseDatabase.getInstance().getReference("Courses");
    }
}
