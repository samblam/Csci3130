package com.example.saikishoreeppalagudem.csci3130;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;




public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button register = findViewById(R.id.button2);
        final EditText Password = findViewById(R.id.editText2);
        final Button signin = findViewById(R.id.button);
        final EditText login = findViewById(R.id.editText);
        final TextView message = findViewById(R.id.textView);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User current_user = new User(String.valueOf(login.getText()), String.valueOf(Password.getText()), true);
                message.setText("The User info is " + current_user.getLogin() + " " + current_user.getPass() + " " + current_user.getLogin_state());

            }

            });
    }
}









