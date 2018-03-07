package com.example.saikishoreeppalagudem.csci3130;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentCoursesActivity extends AppCompatActivity {
    DatabaseReference databaseStudentCourses;
    ListView listViewStudentCourses;
    ArrayList<Course> courseList;
    ArrayList<String> studentInfoList;
    String STUDENT_ID;
    StudentCourseListAdapter adapter;
//    Button btnCourseDel;
    //    ArrayList<String> courseClickedInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_courses);
        STUDENT_ID = "3";
        courseList = new ArrayList<>();
        studentInfoList = new ArrayList<>();
        listViewStudentCourses = findViewById(R.id.listViewStuCourses);
        databaseStudentCourses = FirebaseDatabase.getInstance().getReference("Student");

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseStudentCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseList.clear();
                for (DataSnapshot studentCourseSnapshot : dataSnapshot.getChildren()){
                    Student student = studentCourseSnapshot.getValue(Student.class);
                    Log.e("courseSnapshot: ", student.toString() );
                    String studentID = String.valueOf(studentCourseSnapshot.child("studentID").getValue());
                    String studentCourseInfo = String.valueOf(studentCourseSnapshot.child("studentCourses").getValue());
                    Log.e("studentID", studentID);
                    Log.e("Student_ID", STUDENT_ID);
                    if (studentID.equals(STUDENT_ID)){
                        Log.e("studentID", studentID);
                        String[] a = studentCourseInfo.split(",");
                        for(int j = 0; j < a.length ; j++){
                            studentInfoList.add(a[j]);
                        }
                    }
                }
                Log.e("studentInfoList", studentInfoList + "");
                if (!studentInfoList.isEmpty()){
                    Log.e("Msg", "Inside if");
                    adapter = new StudentCourseListAdapter(StudentCoursesActivity.this, studentInfoList);
                    listViewStudentCourses.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}