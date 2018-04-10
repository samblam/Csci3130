package com.example.saikishoreeppalagudem.csci3130;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Sowmya and Manojha
 * @author Documented by Sowmya and Manojha
 * p - paragraph
 */

public class TermFilterActivity extends AppCompatActivity {
    Spinner spinnerTerm;
    FirebaseAuth AuthRef;
    /**
     *  Reference to firebase database using the key "term"
     */
    DatabaseReference term;
    /**
     * String that is used in getting the term and deadline
     */
    String termID, deadline;
    /**
     * Hashmap for term info, interacts with FireBase Database
     */
    Map<String, String> termMap;
    /**
     * ArrayAdapter to hold the terms in a spinner
    */
    private ArrayAdapter<String> adapter;
    /**
     * AppSharedResources to initialise termID
     * and also to get firebase database references
     */
    AppSharedResources appSharedResources;


    @Override

    /**
     * Dictates what is to be done once the activity is created
     */
    protected void onCreate(Bundle savedInstanceState) {
        final String[] termIDArray = {"Fall", "Winter", "Summer"};
        super.onCreate(savedInstanceState);
        term = FirebaseDatabase.getInstance().getReference("Terms");
        termMap = new HashMap<>();
        Resources res = getResources();

        setContentView(R.layout.activity_term_filter);
        Spinner spinnerTerm = (Spinner) findViewById(R.id.spinnerTerm);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.terms_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // Specify the layout to use when the list of choices appears
      // Apply the adapter to the spinner
        spinnerTerm.setAdapter(adapter);

        Intent intentNew = getIntent();

        appSharedResources = AppSharedResources.getInstance();

        spinnerTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String termSelected = termIDArray[i-1];
                    String deadline = termMap.get(termSelected);
                    appSharedResources.setTermId(termSelected);
                    appSharedResources.setDEADLINE(deadline);

                    Intent intent = new Intent(getApplicationContext(), CourseList.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    /**
     * Sets the process for what happens when you select the term from spinner
     *
     * <p>
     *     Pressing any term from the spinner dropdown , gets the termID and deadline in firebase
     * </p>
     * <p>
     *     Filters the courses according to the Deadline and terms.
     * </p>
     */

    @Override
    protected void onStart() {
        super.onStart();
        term.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    termID = String.valueOf(studentSnapshot.child("termID").getValue());
                    deadline = String.valueOf(studentSnapshot.child("regDeadline").getValue());
                    termMap.put(termID, deadline);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

}
