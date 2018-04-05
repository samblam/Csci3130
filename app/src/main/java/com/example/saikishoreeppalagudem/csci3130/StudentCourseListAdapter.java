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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author saikishoreeppalagudem on 2018-02-25.
 * @author Documented by Sam Barefoot
 */

public class StudentCourseListAdapter extends ArrayAdapter<String>{
    private Activity context;
    private ArrayList<String> studentCourseList;
    CourseRegistration courseRegistration;

    public StudentCourseListAdapter(Activity context, ArrayList<String> studentCourseList){
        super(context, R.layout.student_course_list, studentCourseList);
        this.context = context;
        this.studentCourseList = studentCourseList;

        courseRegistration = new CourseRegistration();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.e("Msg", "Inside getView of ListViewAdapter");
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.student_course_list, null, true);
        TextView tvStudentCourseID = listViewItem.findViewById(R.id.tvStudentCourseID);
        final String course = studentCourseList.get(position);
        Log.e("course", course);
        tvStudentCourseID.setText(course);
        final Map<String, Object> selectedCourseSeatsMap = new HashMap<>();
        Button b = listViewItem.findViewById(R.id.btnStuCourseDel);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Del", position + "'");
                String selectedCourse = studentCourseList.get(position);
                long seatsAvail = Long.parseLong(StudentCoursesActivity.courseSeatsMap.get(selectedCourse)) + 1;
                studentCourseList.remove(position);
                courseRegistration.pushCourseRegistration(studentCourseList, "", "3","register");
                selectedCourseSeatsMap.put("seatsAvail", seatsAvail);
                selectedCourse = selectedCourse.replaceAll("\\s+", "");
                FirebaseDatabase.getInstance().getReference("Courses").child(selectedCourse).updateChildren(selectedCourseSeatsMap);                notifyDataSetChanged();
                studentCourseList.clear();
            }

        });
        return listViewItem;
    }
}
