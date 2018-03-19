package com.example.saikishoreeppalagudem.csci3130;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Manojha on 2018-03-16.
 */

public class DeadlineUITest {
    @Rule
    public ActivityTestRule<Activity_deadline> mActivityTestRule = new ActivityTestRule<>(Activity_deadline.class);

    @Test
    public void deadline() {
        onView(withId(R.id.nav_deadline)).perform();
//        onView(withId(R.id.nav_deadline)).check(matches(isDisplayed()));
    }

}
