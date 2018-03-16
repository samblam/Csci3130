package com.example.saikishoreeppalagudem.csci3130;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertTrue;

/**
 * Created by saikishoreeppalagudem on 2018-03-12.
 */

public class CourseTimesJUnit {
    private static ArrayList<String> testInput0 = new ArrayList<>(Arrays.asList("CSCI 3130",  "CSCI 2110"));
    private static ArrayList<String> testScheduleInput0 = new ArrayList<>(Arrays.asList("1035-1155","1425-1555"));
//    private static List<String> testOutput0 = new ArrayList<>((Arrays.asList("CSCI 3130", "CSCI 2110")));
    private static Map<String, String> testOutput0 = new HashMap<>();

    private static Map<String, String> testMap = new HashMap<>();


    private static boolean doTest( ArrayList<String> input, Map<String, String> output ) {
        CourseRegistration courseRegistration = new CourseRegistration();
        testMap.clear();
        testMap.put("CSCI 3130", "1435-1555");
        testMap.put("CSCI 2132", "1235-1325");
        testMap.put("CSCI 2110", "1535-1725");
        Map<String, String> al = courseRegistration.buildSchedule(input, testMap);
        System.out.println( "Input: " );
        System.out.println( input );
        System.out.println( "Generated output" );
        System.out.println( al );
        System.out.println( "Expected output" );
        System.out.println( output );
        System.out.println( "---------------------------------------------------" );
        return al.equals(output);
    }

    @Test
    public void testSuccessfulConv() {
        testOutput0.clear();
        testOutput0.put("CSCI 3130", "1435-1555");
        testOutput0.put("CSCI 2110", "1535-1725");
        assertTrue( "Time conflict test" , doTest( testInput0, testOutput0 ));
    }
}
