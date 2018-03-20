package com.example.saikishoreeppalagudem.csci3130;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author saikishoreeppalagudem on 2018-02-24.
 * @author Documented by Sam Barefoot
 */

public class CourseListAdapter extends ArrayAdapter<Course>{
    private Activity context;
    private List<Course> courseList;

    public CourseListAdapter(Activity context, List<Course> courseList){
        super(context, R.layout.course_list, courseList);
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    /**
     * Sets Course Data to be displayed
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.course_list, null, true);
        TextView textViewID = listViewItem.findViewById(R.id.tvCourseID);
        TextView textViewDesc = listViewItem.findViewById(R.id.tvCourseDesc);
        TextView textViewseatavail = listViewItem.findViewById(R.id.tvCourseSeatsAvlbl);
        Course course = courseList.get(position);
        textViewID.setText(course.getCourseID());
        textViewDesc.setText(course.getCourseDesc());
        textViewseatavail.setText(course.getSeatsAvail().toString());
        return listViewItem;
    }
}
