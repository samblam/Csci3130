package com.example.saikishoreeppalagudem.csci3130;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by saikishoreeppalagudem on 2018-02-27.
 */

public class CourseInformationUITest {

    @Rule
    public ActivityTestRule<CourseInformationActivity> mActivityRule = new ActivityTestRule<>(
            CourseInformationActivity.class);



    @Test
    public void checkTV(){
        onView(withId(R.id.tvCourseInfo)).check(matches(isDisplayed()));
//        onView(withId(R.id.tvCourseInfoDesc));
//        onView(withId(R.id.btnRegister));

    }
}
