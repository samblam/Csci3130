package com.example.saikishoreeppalagudem.csci3130;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void registerOnClick(View view) {
        callIntent(MainActivity.class);
    }

    public void courseListOnClick(View view) {
        callIntent(CourseList.class);
    }

    public void myCoursesOnClick(View view) {
        callIntent(StudentCoursesActivity.class);
    }

    public void callIntent(Class className){
        Intent intent = new Intent(getApplicationContext(), className);
        startActivity(intent);
    }
}
