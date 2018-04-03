package com.example.saikishoreeppalagudem.csci3130;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_Filter extends Activity  {

    DatabaseReference fDatabaseRoot;
    //private Spinner spinner;
    //private static final String[] paths = {"item 1", "item 2", "item 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__filter);

        fDatabaseRoot.child("Terms").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                 List<String> term = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    Terms termID = areaSnapshot.child("termID").getValue(Terms.class);
                    term.add(termID.toString());
                }

                Spinner termSpinner = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<String> termAdapter = new ArrayAdapter<String>(Activity_Filter.this, android.R.layout.simple_spinner_item, term);
                termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                termSpinner.setAdapter(termAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}