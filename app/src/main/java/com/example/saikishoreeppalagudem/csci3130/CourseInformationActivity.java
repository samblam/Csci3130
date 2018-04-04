package com.example.saikishoreeppalagudem.csci3130;

/**  @author Integrated by karthick parameswaran on 2018-03-16
 *  @author Documented by Sam Barefoot
 * */

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
    /**
     * Text View Object showing course info
     */
    TextView tvCourseInfo;
    /**
     * TextView Object showing course description info
     */
    TextView tvCourseInfoDesc;
    /**
     * Text View showing the Professor's name who is teaching a course
     */
    TextView profname;
    /**
     * TextView Object showing the teaching professor's email
     */
    TextView profmail;
    /**
     * TextView Object showing how many spots are open in a course
     */
    TextView seatsAvail, courseDetail;
    /**
     * Button Object that registers student for a class
     */
    Button btnRegister;
    /**
     * Reference Object that connects to firebase using the key "Student"
     */
    DatabaseReference databaseStudent;
    /**
     * Reference Object that connects to firebase using the key "Course"
     */
    DatabaseReference databaseCourse;
    /**
     *  Array List that stores student Info
     */
    ArrayList<String> studentInfoList;
    /**
     * Array List that stores Student IDs
     */
    ArrayList<String> studentIDInfo;
    /**
     * String that holds a student ID to be used as a database key
     */
    static String keyStudentID;
    /**
     * Hash Map representation of student info data, to be uploaded/retrieved from firebase
     */
    Map<String, ArrayList<String>> studentInfoMap = new HashMap<>();
    /**
     * Hash Map representation of course info data, to be uploaded/retrieved from firebase
     */
    Map<String, String> courseInfoMap = new HashMap<>();
    /**
     * Array List that stores data about what courses a particular student is signed up for
     */
    public static ArrayList<ArrayList<String>> studentCoursesInfo;
    /**
     * Instance of courseRegistration Object. Refer to that class for more documentation,
     */
    public CourseRegistration courseRegistration;

    AppSharedResources appSharedResources;

    @Override
    /**
     * States what objects are to be created, and what is supposed to be displayed on the screen of the phone
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_information);

        tvCourseInfo = findViewById(R.id.tvCourseInfo);
        tvCourseInfoDesc = findViewById(R.id.tvCourseInfoDesc);
        seatsAvail = findViewById(R.id.seatsAvail);
        profname = findViewById(R.id.profname);
        profmail = findViewById(R.id.profmail);
        courseDetail = findViewById(R.id.tvCourseDetail);
        studentInfoList = new ArrayList<>();
        studentCoursesInfo = new ArrayList<>();
        studentIDInfo = new ArrayList<>();

        Intent intent = getIntent();
        ArrayList<String> message = intent.getStringArrayListExtra("ExtraMsg");
        if(message!=null) {
            tvCourseInfo.setText(message.get(0));
            tvCourseInfoDesc.setText(getString(R.string.course_id) + message.get(1));
            profname.setText(getString(R.string.prof_name) + message.get(3));
            profmail.setText(getString(R.string.prof_email) + message.get(4));
//            tvCourseInfoDesc.setText(message.get(1));
            seatsAvail.setText(getString(R.string.seatsAvail) + message.get(5));
            courseDetail.setText("Course Detail: " + message.get(6));
        }

        courseRegistration = new CourseRegistration();

        databaseStudent = FirebaseDatabase.getInstance().getReference("Student");
        databaseCourse = FirebaseDatabase.getInstance().getReference("Courses");

        appSharedResources = AppSharedResources.getInstance();
        keyStudentID = appSharedResources.STUDENT_ID;
        Log.e("keyStudentID", keyStudentID);


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

    /**Allows students to register for courses. 3 Possible Outcomes
     * <p>
     *     1: The user is already registered for the course, and they recieve an error message detailing this
     * </p>
     * <p>
     *     2: There is a time conflict, and the user receives an error message detailing this
     * </p>
     * <p>3: The user is successfully registered for the course</p>
     *
     * @param view
     */
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
