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

    /** String to hold student id
     **/
    public static String STUDENT_ID = "3";

    /** DatabaseReference for Courses
     * **/
    public DatabaseReference courseDbRef = FirebaseDatabase.getInstance().getReference("Courses");

    /** DatabaseReference for Students
     * **/
    public DatabaseReference studentDbRef = FirebaseDatabase.getInstance().getReference("Student");

    /** DatabaseReference for Terms
     * **/
    public DatabaseReference termDbRef = FirebaseDatabase.getInstance().getReference("Terms");
    /**
     * String to hold Term ID
     */
    public static String TERM_ID = "Fall";

    /**
     * String to hold deadline
     * **/
    public static String DEADLINE = "04/06/2018";

    /**
     * <p>
     *     TermID setter
     * </p>**/
    public void setTermId(String termId) {
        this.TERM_ID = termId;
    }

    /**
     * <p>
     *     Deadline setter
     * </p>**/
    public static void setDEADLINE(String DEADLINE) {
        AppSharedResources.DEADLINE = DEADLINE;
    }


    /**
     * <p>
     *     Method to getInstance of the AppSharedResources class.
     *     Follows the Singleton pattern.
     * </p>**/
    public static AppSharedResources getInstance(){
        if (appSharedResources == null)
            appSharedResources = new AppSharedResources();
        return appSharedResources;
    }

    /**
     * <p>
     *     StudentID setter
     * </p>**/
    public void setStudentId(String studentId){
       this.STUDENT_ID = studentId;
   }
}

