package com.example.saikishoreeppalagudem.csci3130;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by saikishoreeppalagudem on 2018-04-04.
 * This class will have all the database references and variables
 * that will be used through out the app
 */


public class AppSharedResources {
    private static AppSharedResources appSharedResources;
    public static String STUDENT_ID = "3";
    public DatabaseReference courseDbRef = FirebaseDatabase.getInstance().getReference("Courses");
    public DatabaseReference studentDbRef = FirebaseDatabase.getInstance().getReference("Student");
    public DatabaseReference termDbRef = FirebaseDatabase.getInstance().getReference("Terms");
    public static String TERM_ID = "Fall";
    public static String DEADLINE = "04/06/2018";

    public static void setTermId(String termId) {
        TERM_ID = termId;
    }

    public static void setDEADLINE(String DEADLINE) {
        AppSharedResources.DEADLINE = DEADLINE;
    }

    public static AppSharedResources getInstance(){
        if (appSharedResources == null)
            appSharedResources = new AppSharedResources();
        return appSharedResources;
    }

   public void setStudentId(String studentId){
       this.STUDENT_ID = studentId;
   }
}

