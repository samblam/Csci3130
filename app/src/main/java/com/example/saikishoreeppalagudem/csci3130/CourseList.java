package com.example.saikishoreeppalagudem.csci3130;
/**
 * @author Documented by Sam Barefoot
 */


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
    /**
     * a Reference to Firebase Database, specifically to grab course Data
     */
    DatabaseReference databaseCourses;
    /**
     * listview object that shows list of courses
     */
    ListView listViewCourses;
    /**
     * array list that stores a list of courses
     */
    ArrayList<Course> courseList;
    /**
     * array list that stores course info to be displayed once a course has been specified
     */
    ArrayList<String> courseClickedInfoList;
    @Override
    /**
     * States what objects are to be created, and what is supposed to be displayed on the screen of the phone
     */
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
