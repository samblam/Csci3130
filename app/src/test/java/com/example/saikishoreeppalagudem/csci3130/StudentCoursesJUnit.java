package com.example.saikishoreeppalagudem.csci3130;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by saikishoreeppalagudem on 2018-02-27.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})
public class StudentCoursesJUnit {
    private DatabaseReference mockedDBRef;
    StudentCoursesActivity studentCoursesActivity = new StudentCoursesActivity();
    ArrayList<String> studentCourseList;
    @Before
    public void setUp() {
        studentCourseList = new ArrayList<String>()
        {{
            add("CSCI2110");
            add("CSCI3130");
            add("CSCI5708");
        }};
//        Log.e("stC", studentCoursesActivity.studentInfoList + "");
        mockedDBRef = Mockito.mock(DatabaseReference.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDBRef);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);
    }

    @Test
    public void getSignedInUserProfileTest() {
        when(mockedDBRef.child(anyString())).thenReturn(mockedDBRef);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];
                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
                valueEventListener.onDataChange(mockedDataSnapshot);
                return null;
            }
        }).when(mockedDBRef).addListenerForSingleValueEvent(any(ValueEventListener.class));

    }

    @Test
    public void checkStuCoursesIsInitiallyNull(){
        boolean a;
        if (studentCoursesActivity.listViewStudentCourses == null)
        {
            a = true;
        }
        else
        {
            a = false;
        }
        assertTrue("True" , a);
    }

}
