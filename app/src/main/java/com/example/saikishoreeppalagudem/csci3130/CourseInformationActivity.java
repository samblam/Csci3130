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
    TextView tvCourseInfo, tvCourseInfoDesc;
    Button btnRegister;
    DatabaseReference databaseStudent;
    ArrayList<String> studentInfoList, studentIDInfo;
    static String keyStudentID;
    Map<String, ArrayList<String>> studentInfoMap = new HashMap<>();
    public static ArrayList<ArrayList<String>> studentCoursesInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_information);

        tvCourseInfo = findViewById(R.id.tvCourseInfo);
        tvCourseInfoDesc = findViewById(R.id.tvCourseInfoDesc);
        studentInfoList = new ArrayList<>();
        studentCoursesInfo = new ArrayList<>();
        studentIDInfo = new ArrayList<>();

        Intent intent = getIntent();
        ArrayList<String> message = intent.getStringArrayListExtra("ExtraMsg");

        tvCourseInfo.setText(message.get(0));
        tvCourseInfoDesc.setText(message.get(1));

        databaseStudent = FirebaseDatabase.getInstance().getReference("Student");
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
    }

    public void registerOnClick(View view) {
        ArrayList<String> courses = new ArrayList<>();
        String pushCourses = new String();
        courses = studentInfoMap.get(keyStudentID);
        if (courses.contains(tvCourseInfo.getText())){
            Toast.makeText(this, "Already registered!", Toast.LENGTH_SHORT).show();

        }
        else{
//            String firebaseKey = databaseStudent.getKey();
//            Log.e("firebasekey", firebaseKey);
            DatabaseReference studentCourseRef = databaseStudent.child(keyStudentID);
            Log.e("studentCourseRef",""+ studentCourseRef);
            Map<String, Object> courseUpdates = new HashMap<>();
            courses.add(tvCourseInfo.getText().toString());

            for(int i = 0; i < courses.size(); i++){
                if(courses.get(i)!= "null") {
                    if (i == 0) {
                        pushCourses = courses.get(i);
                    } else {
                        pushCourses = pushCourses + courses.get(i);
                    }
                    if (i < courses.size() - 1) {
                        pushCourses = pushCourses + ",";
                    }
                }
            }
            Log.e("pushcourses", ""+pushCourses);
            courseUpdates.put("studentCourses", pushCourses);
            studentCourseRef.updateChildren(courseUpdates);
//            studentCourseRef.updateChildrenAsync(courseUpdates);

            Toast.makeText(this, "Course not yet registered", Toast.LENGTH_SHORT).show();
        }
    }
}
