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


public class TermFilterActivity extends AppCompatActivity {
    Spinner spinnerTerm;
//    private DrawerLayout mDrawerLayout;
//    private ActionBarDrawerToggle mToggle;
    FirebaseAuth AuthRef;
    DatabaseReference term;
    String termID, deadline;
    Map<String, String> termMap;
    private ArrayAdapter<String> adapter;
    AppSharedResources appSharedResources;


    @Override
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


//        mDrawerLayout = findViewById(R.id.drawer_layout);
//        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        // set item as selected to persist highlight
//                        menuItem.setChecked(true);
//                        // close drawer when item is tapped
//                        mDrawerLayout.closeDrawers();
//
//                        switch (menuItem.getItemId()) {
//
//                            case R.id.nav_RegisterLogin:
//                                callIntent(MainActivity.class);
//                                break;
//
//                            case R.id.nav_CourseList:
//                                callIntent(CourseList.class);
//                                break;
//
//                            case R.id.nav_MyCourses:
//                                callIntent(StudentCoursesActivity.class);
//                                break;
//
//                            case R.id.nav_deadline:
//                                callIntent(Activity_deadline.class);
//                                break;
//
//                            case R.id.nav_Logout:
//                                AuthRef.signOut();
//                                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
//                                    Toast.makeText(TermFilterActivity.this, "Sign out Successful",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//
//
////                                callIntent(MainActivity.class);
//                                break;
//
//                        }
//
//                        // Add code here to update the UI based on the item selected
//                        // For example, swap UI fragments here
//
//                        return true;
//                    }
//                });

    }


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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }


//    public void registerOnClick(View view) {
//        callIntent(MainActivity.class);
//    }
//
//    public void courseListOnClick(View view) {
//        callIntent(CourseList.class);
//    }
//
//    public void myCoursesOnClick(View view) {
//        callIntent(StudentCoursesActivity.class);
//    }
//
//    public void callIntent(Class className) {
//        Intent intent = new Intent(getApplicationContext(), className);
//        startActivity(intent);
//    }

}
