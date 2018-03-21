package com.example.saikishoreeppalagudem.csci3130;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by Karthick Parameswaran on 2018-03-14.
 * This test case register account , goes to course List activity and check
 * the prof name and  mail address from firebase
 *
 */

public class CourseInformationActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    String email = "test1@gmail.com";
    String password = "123456";
    String prof = "PROFESSOR NAME :John D";
    String prof_email = "PROFESSOR EMAIL :johnd@dal.ca";

    // testcase pre-condition: firebase needs to be connected.

    @Test public void ProfdetailTest(){
        onView(withId(R.id.editText)).perform(typeText(email),closeSoftKeyboard()); // types the data in emailaddress fields
        onView(withId(R.id.editText2)).perform(typeText(password),closeSoftKeyboard()); // types the data in password fields
        onView(withId(R.id.button2)).perform(click());// press register button
        onData(anything()).inAdapterView(withId(R.id.listViewCourses)).atPosition(0).perform(click());// Clicks on the first course created in the listview in CourseList
        onView(withId(R.id.profname)).check(matches(withText(prof))); // below test step verifies the prof name from firebase
        onView(withId(R.id.profmail)).check(matches(withText(prof_email))); // below test step verifies the prof email from firebas
    }
}
