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

/**
 * @author Sam Barefoot
 * @author Documented by Sam Barefoot
 */

// Derived and Inspired by Google's QuickStart FireBase Guide
//https://github.com/firebase/quickstart-js
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * String that is use in using Log
     */
    final String TAG = "EmailPassword";
    /**
     * TextView object that displays the current status
     */
    private TextView mStatusTextView;
    /**
     *TextView object that stores details about the status
     */
    private TextView mDetailTextView;
    /**
     * EditText object where users input email addresses
     */
    EditText mEmailField;
    /**
     * EditText object whereas users input passwords that correspond to the inputted email
     */
    EditText mPasswordField;
    /**
     *  Reference to firebase database using the key "Students"
     */
    DatabaseReference databaseStudentReference;
    /**
     * Hashmap for student info, interacts with FireBase Database
     */
    Map<String, Object> studentInfoMap = new HashMap<>();
    /**
     * Firebase Authentification Object, used to test user credentials
     */
    FirebaseAuth mAuth;
    /**
     * Key used to search for a specific student within a database
     */
    public String STUDENT_KEY = "";

    @Override
    /**
     * Dictates what is to be done once the activity is created
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        mStatusTextView = findViewById(R.id.textView2);
        mDetailTextView = findViewById(R.id.textView3);
        mEmailField = findViewById(R.id.editText);
        mPasswordField = findViewById(R.id.editText2);
        databaseStudentReference = FirebaseDatabase.getInstance().getReference("Student");

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.verify_email_button).setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    /**
     * Check if user is signed in and update UI
     */
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    /**
     * Creates user Account
     * <p>
     *     If successful, the account is created in firebase, and the Ui is updated accordingly
     * </p>
     * <p>
     *     If unsuccessful, the account is not created, and a message is shown stating as such
     * </p>
     * @param email
     * @param password
     */
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }




        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser mAuthCurrentUser = mAuth.getCurrentUser();
                        System.out.println(mAuthCurrentUser);
                        if (task.isSuccessful()) {

                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "Account Creation passed.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Account Creation failed",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }

    /**
     * Allows user to sign in
     * <p>
     *     If Sign in is successful, the UI is updated with the signed-in user's information
     * </p>
     * <p>
     * If sign in fails, a message is displayed to the user.
     * </p>
     * @param email
     * @param password
     */
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }




        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {

                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                        if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }


                    }
                });

    }

    /**
     * Allows user to sign out
     *
     */
    private void signOut() {
        mAuth.signOut();
    }


    /**
     * Sends a verification email to email inputted
     */
    private void sendEmailVerification() {

        findViewById(R.id.verify_email_button).setEnabled(false);


        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

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

                    }
                });

    }

    /**Validates form, make sure there is an email address inputed, a password of a certain length etc.
     *
     * @return returns a boolean value that steates whether the form is valid or not
     */
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





    /**
     * Sets the process for what happens when you press any of the on screen buttons
     *
     * <p>
     *     Pressing the register button creates the account, initializes the user in firebase, and opens the course list
     * </p>
     * <p>
     *     Pressing the Sign In button
     * </p>
     */

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

    /**
     * Retrieves user details from the Firebase Database
     */
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

    /**
     * Creates Student Account within University
     */
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

    /**
     * Method to go to the CourseList activity
     */
    public void goToCourseList(){

        Intent intent = new Intent(getApplicationContext(), CourseList.class);
        startActivity(intent);


    }
}
