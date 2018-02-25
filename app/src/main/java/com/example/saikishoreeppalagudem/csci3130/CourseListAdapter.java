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
 * Created by saikishoreeppalagudem on 2018-02-24.
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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.course_list, null, true);
        TextView textViewID = (TextView) listViewItem.findViewById(R.id.tvCourseID);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.tvCourseDesc);
        Course course = courseList.get(position);
        textViewID.setText(course.getCourseID());
        textViewDesc.setText(course.getCourseDesc());
        return listViewItem;
    }
}
