package com.example.saikishoreeppalagudem.csci3130;
/**
 * @author Documented by Sam Barefoot
 */


import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseList extends AppCompatActivity {
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

    /**
     * button to handle multiple course registration
     */
    Button btnMulReg;

    /**
     * button to handle multiple course registration
     */
    ArrayList<String> selectedCourses;

    /** Hash Map representation of course info data, to be uploaded/retrieved from firebase
     */
    Map<String, String> courseInfoMap = new HashMap<>();


    ArrayList<String> finStudentCourses = new ArrayList<>();

    AppSharedResources appSharedResources;
    String termID;

    @Override
    /**
     * States what objects are to be created, and what is supposed to be displayed on the screen of the phone
     */
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        //AppSharedResources instance
        appSharedResources = AppSharedResources.getInstance();

        courseList = new ArrayList<>();
//        Log.e("StudentID", appSharedResources.STUDENT_ID);
        listViewCourses = findViewById(R.id.listViewCourses);
        btnMulReg = findViewById(R.id.btnMulRegister);
        selectedCourses = new ArrayList<>();

        termID = appSharedResources.TERM_ID;
        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = "" + courseList.get(i);
                Log.e("courseList", courseList+"");
                String[] courseClicked = s.split(",");
                Log.e("S", ""+courseClicked[0]);
                courseClickedInfoList = new ArrayList<>();
                for(int index = 0; index < courseClicked.length; index++){
                    courseClickedInfoList.add(""+courseClicked[index]);
                }
                s = "" + courseClickedInfoList;
                Log.e("courseClicked", s);
                Intent intent = new Intent(getApplicationContext(), CourseInformationActivity.class);
                intent.putExtra("ExtraMsg", courseClickedInfoList);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        databaseCourses.addListenerForSingleValueEvent(new ValueEventListener()
        appSharedResources.courseDbRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseList.clear();
                courseInfoMap.clear();
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()){
                    Course course = courseSnapshot.getValue(Course.class);
                    if (termID == "") Log.e("termID", termID + "termID is blank");
                    else {
                        if (course.getTermID().contains(termID)) {
                            courseList.add(course);
                            Log.e("courseSnapshot: ", course.toString());
                            String courseID = String.valueOf(courseSnapshot.child("courseID").getValue());
                            String courseTimingInfo = String.valueOf(courseSnapshot.child("courseTiming").getValue());
                            courseInfoMap.put(courseID, courseTimingInfo);
                        }
                    }
//                    courseList.add(course);
//                    Log.e("courseSnapshot: ", course.toString() );
//                    String courseID = String.valueOf(courseSnapshot.child("courseID").getValue());
//                    String courseTimingInfo = String.valueOf(courseSnapshot.child("courseTiming").getValue());
//                    courseInfoMap.put(courseID, courseTimingInfo);

                }
                CourseListAdapter adapter = new CourseListAdapter(CourseList.this, courseList);
                adapter.selectedCourses.clear();
                listViewCourses.setAdapter(adapter);
                selectedCourses = adapter.selectedCourses;
                Log.e("courseInfoMap", courseInfoMap + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        appSharedResources.studentDbRef.child(appSharedResources.STUDENT_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                finStudentCourses.clear();
                String stuCourses = dataSnapshot.child("studentCourses").getValue(String.class);
//                Log.e("stuCourses", "" + stuCourses);
                String[] s = stuCourses.split(",");
                for(int i = 0; i < s.length; i++){
                    finStudentCourses.add(s[i]);
                }
                Log.e("finStudentCourses", finStudentCourses + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /** Code to handle button click event. Student can register for multiple courses.
     *
     * */
    public void onClickBtnMulReg(View view) {
        // TODO Write code for multiple registrations
        if (appSharedResources.STUDENT_ID != "3"){
            int selCoursesLen = selectedCourses.size();
            ArrayList<String> studentCourses = new ArrayList<>();
            ArrayList<String> coursesFailedToReg = new ArrayList<>();
            CourseRegistration courseRegistration = new CourseRegistration();
            ArrayList<String> coursesToPush = new ArrayList<>();
            Map<String, String> scheduleMap = new HashMap<>();
            for (int i = 0; i < selCoursesLen; i ++){
                //Get student ID from MainActivity
                Log.e("selectedCourses", "" + selectedCourses);
                studentCourses = finStudentCourses;
                Log.e("StudentCourses", studentCourses + "");
                scheduleMap.clear();
                if (!courseInfoMap.isEmpty()) {
                    scheduleMap = courseRegistration.buildSchedule(studentCourses, courseInfoMap);
                }
                if (courseRegistration.chkCourseAlreadyRegistered(studentCourses, selectedCourses.get(i))){
                    coursesFailedToReg.add(selectedCourses.get(i));
                    Toast.makeText(this, selectedCourses.get(i) + "Already registered!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (courseRegistration.chkTimeConflict(selectedCourses.get(i), courseInfoMap, scheduleMap)) {
                        Toast.makeText(this, "Time conflict!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        studentCourses.add(selectedCourses.get(i));
                        coursesToPush.add(selectedCourses.get(i));
                    }
                }

            }
            if (coursesToPush.equals(selectedCourses)){
                for (int i = 0; i < selCoursesLen; i ++){
                    courseRegistration.pushCourseRegistration(studentCourses, selectedCourses.get(i),  appSharedResources.STUDENT_ID, "register");
                    Toast.makeText(this, "Course registered successfully!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Please register/login!", Toast.LENGTH_SHORT).show();
        }

    }
}
