package com.example.saikishoreeppalagudem.csci3130;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * @author Sam Barefoot
 * @author Documented by Sam Barefoot
 */
public class RegistrationActivity extends AppCompatActivity {
    EditText editText;
    Button buttonPush;
    DatabaseReference databaseStudent;

    @Override
    /**
     * States what objects are to be created, and what is supposed to be displayed on the screen of the phone
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

        databaseStudent = FirebaseDatabase.getInstance().getReference("Student");

        editText = findViewById(R.id.etName);
        buttonPush = findViewById(R.id.btnPush);


    }

    /**
     * Dictates what is to occur when a button is pressed
     * @param view
     */
    public void btnClick(View view) {

        String name = editText.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            String id = databaseStudent.push().getKey();
            Log.e("id ", id);
        }
        else{
            Toast.makeText(this,"Enter text", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnUpdate(View view) {
    }
}
