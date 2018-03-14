package com.example.saikishoreeppalagudem.csci3130;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by saikishoreeppalagudem on 2018-03-06.
 */

public class TimeConflictTest {
    private static String testInput0 = "1035-1125";
    private static String testInput1 = "0835-0935";
    private static ArrayList<String> testScheduleInput0 = new ArrayList<>(Arrays.asList("1035-1155","1425-1555"));
    private static String testOutput0 = "Time Conflict!";
    private static String testOutput1 = "No time conflict!";


    private static boolean doTest( String input, ArrayList<String> scheduleInput ) {
        TimeConflict timeConflict = new TimeConflict();
        Boolean al = timeConflict.checkTimeConflict(input, scheduleInput);
        System.out.println( "Input: " );
        System.out.println( input );
        System.out.println("Schedule Input");
        System.out.println(scheduleInput);
        System.out.println( "Generated output" );
        System.out.println( al );
//        System.out.println( "Expected output" );
//        System.out.println( output );
//        System.out.println( "---------------------------------------------------" );
        return al;
    }

    @Test
    public void testFail() {
        assertTrue( "Time conflict test" , doTest( testInput0, testScheduleInput0));
    }

    @Test
    public void testPass(){
        assertFalse("No time conflict test", doTest(testInput1, testScheduleInput0));
    }
}
