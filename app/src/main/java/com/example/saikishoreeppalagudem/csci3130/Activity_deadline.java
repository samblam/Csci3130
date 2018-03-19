package com.example.saikishoreeppalagudem.csci3130;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Manojha
 * @author Documentation by Sam Barefoot
 */

public class Activity_deadline extends AppCompatActivity {
    private TextView txt_deadline;

    @Override
    /**
     * This Activity demonstrates the Deadline for Users to sign up for Courses
     *
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline2);


        txt_deadline = findViewById(R.id.deadline);
        txt_deadline.setText(getString(R.string.deadline_msg));

    }

}
