package com.example.saikishoreeppalagudem.csci3130;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsAnything.anything;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

@RunWith(AndroidJUnit4.class)

/**
 * Created by saikishoreeppalagudem on 2018-02-27.
 */

public class StudentCoursesUITest {
    @Rule
    public ActivityTestRule<StudentCoursesActivity> mActivityRule = new ActivityTestRule<>(
            StudentCoursesActivity.class);



    @Test
    public void checkUIElements(){
//        onView(withId(R.id.tvCourseInfo)).perform(setTextInTextView("my text"));
//        onView(withId(R.id.tvStudentCourseID)).check(matches(isDisplayed());
//        onData(anything()).inAdapterView(withId(R.id.listViewStuCourses)).
//                perform(isDisplayed()) ; //click());


//        onData(anything())
//                .inAdapterView(withId(R.id.listViewStuCourses))
//                .atPosition(0)
//                .onChildView(withId(R.id.tvCourseInfo))
//                .check(matches(withText(startsWith("CSCI"))));

//        onData(instanceOf(StudentCourseListAdapter.class))
//                .atPosition(0)
//                .check(matches(withText(startsWith("CSCI"))));
    }

    public static ViewAction setTextInTextView(final String value){
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }

}
