package com.example.saikishoreeppalagudem.csci3130;

/**
 * Created by Sowmya Umesh on 4/8/2018.
 */

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by Sowmya and Manojha on 2018-04-08.
 */








public class DeadlineJUnit {

    private static String testInput0 = "04/04/2017";
    private static String testInput1 = "06/04/2024";


    private static boolean doTest( String input ) {

        CourseRegistration courseRegistrationTest = new CourseRegistration();
        Boolean test = courseRegistrationTest.verifyDeadline(input);
        return test;
    }

    @Test
    public void testPass(){
        assertTrue( "Check Deadline pass test" , doTest( testInput0));
    }

    @Test
    public void testFail(){
        assertFalse( "Check Deadline fail test" , doTest( testInput1));
    }

}
