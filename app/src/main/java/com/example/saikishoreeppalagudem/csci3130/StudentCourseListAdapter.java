package com.example.saikishoreeppalagudem.csci3130;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saikishoreeppalagudem on 2018-02-25.
 * @author Documented by Sam Barefoot
 */

public class StudentCourseListAdapter extends ArrayAdapter<String>{
    private Activity context;
    private ArrayList<String> studentCourseList;
    StudentCourses studentCourses;

    public StudentCourseListAdapter(Activity context, ArrayList<String> studentCourseList){
        super(context, R.layout.student_course_list, studentCourseList);
        this.context = context;
        this.studentCourseList = studentCourseList;

        studentCourses = new StudentCourses();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.e("Msg", "Inside getView of ListViewAdapter");
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.student_course_list, null, true);
        TextView tvStudentCourseID = listViewItem.findViewById(R.id.tvStudentCourseID);
        String course = studentCourseList.get(position);
        Log.e("course", course);
        tvStudentCourseID.setText(course);
        Button b = listViewItem.findViewById(R.id.btnStuCourseDel);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Del", position + "'");
                studentCourseList.remove(position);
                studentCourses.updateCourses(studentCourseList);
                notifyDataSetChanged();
            }
        });
        return listViewItem;

    }
}
