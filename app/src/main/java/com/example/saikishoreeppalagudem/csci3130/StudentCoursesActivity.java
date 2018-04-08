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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.saikishoreeppalagudem.csci3130.CourseInformationActivity.keyStudentID;
import static com.example.saikishoreeppalagudem.csci3130.MainActivity.STUDENT_KEY;

/**
 * @author Documented by Sam Barefoot
 */

public class StudentCoursesActivity extends AppCompatActivity {
    /**
     * Reference to FireBase Database using key "Student"
     */
    DatabaseReference databaseStudentCourses;
    /**
     * ListView object showing a particular
     */
    ListView listViewStudentCourses;
    /**
     * Array List object storing a list of courses offered
     */
    ArrayList<Course> courseList;
    /**
     * ArrayList storing a student information
     */
    ArrayList<String> studentInfoList;
    /**
     * String Storing the currently logged in students ID
     */
    String STUDENT_ID;

    /**
     * * Reference to FireBase Database using key "Course"
     */
    DatabaseReference databaseCourses;

    /**
     * An adapter that updates and populates ui
     */
    StudentCourseListAdapter adapter;
    /**
     * An adapter that updates and populates ui
     */
    AppSharedResources appSharedResources;

    /**
     * Dictates what's to be done when the activity is created
     */

    public static final Map<String, String> courseSeatsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_courses);
        courseList = new ArrayList<>();
        studentInfoList = new ArrayList<>();
        listViewStudentCourses = findViewById(R.id.listViewStuCourses);
        databaseStudentCourses = FirebaseDatabase.getInstance().getReference("Student");
        databaseCourses =  FirebaseDatabase.getInstance().getReference("Courses");
        appSharedResources = AppSharedResources.getInstance();

    }

    @Override
    /**
     * Dictates what's to occur when the activity starts
     */
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
                    Log.e("Student_ID", appSharedResources.STUDENT_ID);
                    if (studentID.equals(appSharedResources.STUDENT_ID)){
                        Log.e("studentID", studentID);
                        String[] a = studentCourseInfo.split(",");
                        for(int j = 0; j < a.length ; j++){
                            studentInfoList.add(a[j]);
                        }
                    }
                }
                Log.e("studentInfoList", studentInfoList + "");
                if (!studentInfoList.get(0).toString().equals("")){
                    Log.e("Msg", "Inside if");
                    adapter = new StudentCourseListAdapter(StudentCoursesActivity.this, studentInfoList);
                    listViewStudentCourses.setAdapter(adapter);
                }

                if(studentInfoList.get(0).toString().equals("")){
                    Toast.makeText(StudentCoursesActivity.this, "Please register some courses!", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                    String courseID = String.valueOf(courseSnapshot.child("courseID").getValue());
                    String seatsAvail = String.valueOf(courseSnapshot.child("seatsAvail").getValue());
                    courseSeatsMap.put(courseID, seatsAvail);
                }
                Log.e("seatAvail", "" + courseSeatsMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }





}
