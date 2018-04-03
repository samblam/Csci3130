package com.example.saikishoreeppalagudem.csci3130;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by saikishoreeppalagudem on 2018-03-30.
 */

public class CourseListUITest {
    @Rule
    public ActivityTestRule<CourseList> courseListActivityTestRule = new
            ActivityTestRule<>(CourseList.class);

    @Test
    public void checkUIElements(){
        onView(withId(R.id.listViewCourses)).check(matches(isDisplayed()));
//        onView(withId(R.id.))
    }

}
