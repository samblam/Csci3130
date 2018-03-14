package com.example.saikishoreeppalagudem.csci3130;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Derived and Inspired by Google's QuickStart FireBase Guide
//https://github.com/firebase/quickstart-js
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

         final String TAG = "EmailPassword";

        private TextView mStatusTextView;
        private TextView mDetailTextView;
         EditText mEmailField;
        EditText mPasswordField;
        DatabaseReference databaseStudentReference;
        Map<String, Object> studentInfoMap = new HashMap<>();
        // [START declare_auth]
        FirebaseAuth mAuth;
        // [END declare_auth]
        public String STUDENT_KEY = "";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Views
            mStatusTextView = findViewById(R.id.textView2);
            mDetailTextView = findViewById(R.id.textView3);
            mEmailField = findViewById(R.id.editText);
            mPasswordField = findViewById(R.id.editText2);
            databaseStudentReference = FirebaseDatabase.getInstance().getReference("Student");
            // Buttons
            findViewById(R.id.button).setOnClickListener(this);
            findViewById(R.id.button2).setOnClickListener(this);
            findViewById(R.id.verify_email_button).setOnClickListener(this);

            // [START initialize_auth]
            mAuth = FirebaseAuth.getInstance();
            // [END initialize_auth]
        }

        // [START on_start_check_user]
        @Override
        public void onStart() {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
        }
        // [END on_start_check_user]

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }



        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser mAuthCurrentUser = mAuth.getCurrentUser();
                        System.out.println(mAuthCurrentUser);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "Account Creation passed.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Account Creation failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }



        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
    }

    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.verify_email_button).setEnabled(false);

        // Send verification email
        //[START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.verify_email_button).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(MainActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        }
        else if(password.length() < 5){
            mPasswordField.setError("Password needs to be at least 5 characters");
        }
        else {
            mPasswordField.setError(null);
        }

        return valid;
    }



    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button2) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
            initializeUserInFirebase();
            goToCourseList();
        } else if (i == R.id.button) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
//ESK
            getUserDetails();
        }// else if (i == R.id.sign_out_button) {
           // signOut(); }
         else if (i == R.id.verify_email_button) {
            sendEmailVerification();
            goToCourseList();
        }
    }
//ESK
 private void getUserDetails(){
            databaseStudentReference.orderByChild("studentID").equalTo(mEmailField.getText().toString()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.e("QUery out", dataSnapshot.getKey());
                    STUDENT_KEY = dataSnapshot.getKey();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }
    public void initializeUserInFirebase(){
        //Method to initialize Student record in Firebase real-time database.
        //Done only once during registration.
        String studentID, studentName, studentCourses;
        studentID = mEmailField.getText().toString();
        studentName = studentID;
        studentCourses ="";
        DatabaseReference pushedStudentRef = databaseStudentReference.push();
        STUDENT_KEY = pushedStudentRef.getKey();
        Log.e("Student_Key", STUDENT_KEY);
        DatabaseReference studentKeyIDRef = databaseStudentReference.child(STUDENT_KEY);
        System.out.println(studentID);
        studentInfoMap.put("studentID", studentID);
        studentInfoMap.put("studentName", studentName);
        studentInfoMap.put("studentCourses", studentCourses);
        studentKeyIDRef.setValue(studentInfoMap);

    }

    public void goToCourseList(){
        //Method to go to the CourseList activity
        Intent intent = new Intent(getApplicationContext(), CourseList.class);
        startActivity(intent);


    }
}
