package com.example.saikishoreeppalagudem.csci3130;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {

    DatabaseReference databaseCourses;
    List<Course> a = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        databaseCourses = FirebaseDatabase.getInstance().getReference("Courses");


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                a.clear();
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()){
                    Course course = courseSnapshot.getValue(Course.class);
                    a.add(course);
//                    Log.e("snapshot", a.toString());
                }

//                Log.e
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
