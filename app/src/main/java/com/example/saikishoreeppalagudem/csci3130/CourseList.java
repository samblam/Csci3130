package com.example.saikishoreeppalagudem.csci3130;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {

    DatabaseReference databaseCourses;
    ListView listViewCourses;
    ArrayList<Course> courseList;
    ArrayList<String> courseClickedInfoList;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        courseList = new ArrayList<>();

        databaseCourses = FirebaseDatabase.getInstance().getReference("Courses");
        listViewCourses = findViewById(R.id.listViewCourses);

        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = "" + courseList.get(i);
                String[] courseClicked = s.split(",");
                Log.e("S", ""+courseClicked[0]);
                courseClickedInfoList = new ArrayList<>();
                for(int index = 0; index < courseClicked.length; index++){
                    courseClickedInfoList.add(""+courseClicked[index]);
                }
                s = "" + courseClickedInfoList;
                Log.e("courseClicked", s);
//                Log.e("courseClicked", courseClicked[1]);

               // Toast.makeText(CourseList.this, "" + courseList.get(i), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CourseInformationActivity.class);
                intent.putExtra("ExtraMsg", courseClickedInfoList);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseList.clear();
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()){
                    Course course = courseSnapshot.getValue(Course.class);
                    courseList.add(course);
                    Log.e("courseSnapshot: ", course.toString() );

                }
                CourseListAdapter adapter = new CourseListAdapter(CourseList.this, courseList);
                listViewCourses.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
