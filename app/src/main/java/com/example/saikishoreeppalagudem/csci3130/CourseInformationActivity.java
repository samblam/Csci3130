package com.example.saikishoreeppalagudem.csci3130;

/**
 * @author Integrated by karthick parameswaran on 2018-03-16
 * @author Documented by Sam Barefoot
 */

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
     * Array List that stores student Info
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

    //kp
    /**
     * ArrayList representation of course details from previous activity, to be uploaded/retrieved from firebase
     */
    public ArrayList<String> message = new ArrayList<>();

    /**
     * Hash Map representation of student waitlist info data, to be uploaded/retrieved from firebase
     */
    Map<String, ArrayList<String>> studentsWaitlistInfoMap = new HashMap<>();
    ArrayList<String> studentWaitCourseInfoList;

    //kp

    Map<String, String> courseSeatsMap = new HashMap<>();
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
        studentWaitCourseInfoList = new ArrayList<>();
        studentCoursesInfo = new ArrayList<>();
        studentIDInfo = new ArrayList<>();
        btnRegister = findViewById(R.id.btnRegister);

        Intent intent = getIntent();
        message = intent.getStringArrayListExtra("ExtraMsg");
        if (message != null) {
            tvCourseInfo.setText(message.get(0));
            tvCourseInfoDesc.setText(getString(R.string.course_id) + message.get(1));
            profname.setText(getString(R.string.prof_name) + message.get(3));
            profmail.setText(getString(R.string.prof_email) + message.get(4));
            seatsAvail.setText(getString(R.string.seatsAvail) + message.get(5));
            chkAndUpdateRegisterButton();
            courseDetail.setText("Course Detail: " + message.get(6));
        }

        courseRegistration = new CourseRegistration();


        appSharedResources = AppSharedResources.getInstance();
        keyStudentID = appSharedResources.STUDENT_ID;
        Log.e("keyStudentID", keyStudentID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        appSharedResources.studentDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    studentInfoList.clear();
                    String studentID = String.valueOf(studentSnapshot.child("studentID").getValue());
                    String studentCourseInfo = String.valueOf(studentSnapshot.child("studentCourses").getValue());
                    String studentWaitListInfo = String.valueOf(studentSnapshot.child("waitlistCourses").getValue());
                    studentIDInfo.add(studentID);
                    String[] a = studentCourseInfo.split(",");
                    for (int j = 0; j < a.length; j++) {
                        studentInfoList.add(a[j]);
                    }
                    studentInfoMap.put(studentID, studentInfoList);

                    studentWaitCourseInfoList.clear();
                    String[] b = studentWaitListInfo.split(",");
                    for (int k = 0; k < b.length; k++) {
                        studentWaitCourseInfoList.add(b[k]);
                    }
                    studentsWaitlistInfoMap.put(studentID, studentWaitCourseInfoList);

                    studentInfoList = new ArrayList<>(studentInfoList);
                    studentWaitCourseInfoList = new ArrayList<>(studentWaitCourseInfoList);
                }
                Log.e("student", "" + studentInfoMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        appSharedResources.courseDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseInfoMap.clear();
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                    String courseID = String.valueOf(courseSnapshot.child("courseID").getValue());
                    String courseTimingInfo = String.valueOf(courseSnapshot.child("courseTiming").getValue());
                    String seatsAvail = String.valueOf(courseSnapshot.child("seatsAvail").getValue());
                    courseInfoMap.put(courseID, courseTimingInfo);
                    courseSeatsMap.put(courseID, seatsAvail);

                }
                Log.e("courses", "" + courseInfoMap);
                Log.e("seatAvail", "" + courseSeatsMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Allows students to register for courses. 3 Possible Outcomes
     * <p>
     * 1: The user is already registered for the course, and they recieve an error message detailing this
     * </p>
     * <p>

     *     2: There is a time conflict, and the user receives an error message detailing this
     * </p>
     * <p>3: The user is successfully registered for the course</p>
     *
     * @param view
     */
    public void registerOnClick(View view) {
        if (keyStudentID == "3"){
            Toast.makeText(this, "Please register/login!", Toast.LENGTH_SHORT).show();
        }
        else{
            String course = message.get(0);
            String seatAvailability = message.get(5);
            String waitListAvailability = message.get(6);
            ArrayList<String> courses = new ArrayList<>();
            ArrayList<String> waitListCourses = new ArrayList<>();

            String courseToRegister = tvCourseInfo.getText().toString();
            // if(!courseInfoMap.isEmpty())
            courses = studentInfoMap.get(keyStudentID);
            waitListCourses = studentsWaitlistInfoMap.get(keyStudentID);
            if ((courses != null) && (!courses.get(0).toString().equals(""))) {
                Log.e("StudentCourses: ", courses + "");
                Map<String, String> scheduleMap = new HashMap<>();
                scheduleMap = courseRegistration.buildSchedule(courses, courseInfoMap);
                Log.e("scheduleMap", scheduleMap + "");
                if (courseRegistration.chkCourseAlreadyRegistered(courses, courseToRegister)) {
                    Toast.makeText(this, "Already registered!", Toast.LENGTH_SHORT).show();
                } else {
                    if (courseRegistration.chkTimeConflict(courseToRegister, courseInfoMap, scheduleMap)) {
                        Toast.makeText(this, "Time conflict!", Toast.LENGTH_SHORT).show();
                    } else if (courseRegistration.chkAndUpdateSeatAvailability(course, seatAvailability, 1)) {
                        courseRegistration.pushCourseRegistration(courses, courseToRegister, keyStudentID, "register");
                        Toast.makeText(this, "Course registered successfully!", Toast.LENGTH_SHORT).show();
                        String updatedSeatAvail = courseSeatsMap.get(courseToRegister);
                        seatsAvail.setText(updatedSeatAvail);

                    }
                    else if (courseRegistration.chkCourseAlreadyRegistered(waitListCourses, courseToRegister)) {
                        Toast.makeText(this, "Already waitlisted!", Toast.LENGTH_SHORT).show();
                    } else if (courseRegistration.chkAndUpdateWaitlistAvailability(course, waitListAvailability, 1)) {
                        courseRegistration.pushCourseRegistration(waitListCourses, courseToRegister, keyStudentID,"waitlist");
                        Toast.makeText(this, "Course waitlisted successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "Course is full!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                if (courseRegistration.chkAndUpdateSeatAvailability(course, seatAvailability, 1)) {
                    courseRegistration.pushCourseRegistration(courseToRegister, keyStudentID, "register");
                    Toast.makeText(this, "Course registered successfully!", Toast.LENGTH_SHORT).show();
                }
                else if (courseRegistration.chkCourseAlreadyRegistered(waitListCourses, courseToRegister))
                        Toast.makeText(this, "Already waitlisted!", Toast.LENGTH_SHORT).show();
                else if (courseRegistration.chkAndUpdateWaitlistAvailability(course, waitListAvailability, 1)) {
                    courseRegistration.pushCourseRegistration(waitListCourses, courseToRegister, keyStudentID,"waitlist");
                    Toast.makeText(this, "Course waitlisted successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Course is full!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void chkAndUpdateRegisterButton() {
        String seatAvail = message.get(5);
        if (seatAvail.equals("0")) {
            btnRegister.setText(R.string.waitList);
        } else
            btnRegister.setText(R.string.Register);

    }

}
