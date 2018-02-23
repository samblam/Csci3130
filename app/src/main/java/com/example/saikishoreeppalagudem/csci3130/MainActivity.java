package com.example.saikishoreeppalagudem.csci3130;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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




public class MainActivity extends AppCompatActivity {



    private FirebaseAuth auth;
    private EditText login;
    private EditText Password;
    private Button register;
    private Button signin;
    private TextView message;
    private static final String TAG = "MainActivity";






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = findViewById(R.id.button2);
        Password = findViewById(R.id.editText2);
        signin = findViewById(R.id.button);
        login = findViewById(R.id.editText);
        message = findViewById(R.id.textView);
        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm2();
               // User current_user = new User(String.valueOf(login.getText()), String.valueOf(Password.getText()), true);
              //  message.setText("The User info is " + current_user.getLogin() + " " + current_user.getPass() + " " + current_user.getLogin_state());


            }

            });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        String email = login.getText().toString().trim();
        String password = Password.getText().toString().trim();

//        loginInputLayoutEmail.setErrorEnabled(false);
//        loginInputLayoutPassword.setErrorEnabled(false);

        //authenticate user using Firebase
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, Log a message to the LogCat. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // there was an error
                            Toast.makeText(MainActivity.this,"login unsuccessful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this,"login successful", Toast.LENGTH_LONG).show();
                            //Intent intent = new Intent(LoginActivity.this, UserActivity.class); course registration
                            //startActivity(intent);
                            // finish();//
                        }
                    }
                });

    }

    private void submitForm2() {

        String email = login.getText().toString().trim();
        String password = Password.getText().toString().trim();


//        signupInputLayoutEmail.setErrorEnabled(false);
//        signupInputLayoutPassword.setErrorEnabled(false);


        //create Firebase Authenticated user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"createUserWithEmail:onComplete:" + task.isSuccessful());
                        // progressBar.setVisibility(View.GONE);
                        // If sign in fails, Log the message to the LogCat. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(TAG,"Authentication failed." + task.getException());

                        } else {
                            // startActivity(new Intent(SignupActivity.this, UserActivity.class));
                             finish();
                        }
                    }
                });
        Toast.makeText(getApplicationContext(), "You are successfully Registered !!", Toast.LENGTH_SHORT).show();
    }


}









