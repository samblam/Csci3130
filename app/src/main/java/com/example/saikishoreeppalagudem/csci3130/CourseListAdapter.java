package com.example.saikishoreeppalagudem.csci3130;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saikishoreeppalagudem on 2018-02-24.
 * @author Documented by Sam Barefoot
 */

public class  CourseListAdapter extends ArrayAdapter<Course>{
    private Activity context;
    private List<Course> courseList;
    public ArrayList<String> selectedCourses = new ArrayList<>();

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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.course_list, null, true);
        TextView textViewID = listViewItem.findViewById(R.id.tvCourseID);
        TextView textViewDesc = listViewItem.findViewById(R.id.tvCourseDesc);
        TextView textViewseatavail = listViewItem.findViewById(R.id.tvCourseSeatsAvlbl);
        CheckBox checkBox = listViewItem.findViewById(R.id.cbCourse);
        Course course = courseList.get(position);
        textViewID.setText(course.getCourseID());
        textViewDesc.setText(course.getCourseDesc());
        textViewseatavail.setText(course.getSeatsAvail().toString());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) checkBox.isChecked() = true;
                if(isChecked){
                    if(!selectedCourses.contains(courseList.get(position).getCourseID()));
                    selectedCourses.add(courseList.get(position).getCourseID());
                }
                else{
                    if(selectedCourses.contains(courseList.get(position).getCourseID()));
                    selectedCourses.remove(courseList.get(position).getCourseID());
                }
                Log.e("selectedCourses", selectedCourses + "");
            }
        });
        return listViewItem;
    }

}
