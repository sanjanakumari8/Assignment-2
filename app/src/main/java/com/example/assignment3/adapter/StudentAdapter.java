package com.example.assignment3.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.R;
import com.example.assignment3.model.Student;

import java.util.ArrayList;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.Viewholder> {
    itemClicked activity;
    public interface  itemClicked{
        void onItemClicked(int index);
    }
    private ArrayList<Student>studentList;

    public StudentAdapter(Context context, ArrayList<Student> studentList) {
        this.studentList = studentList;
        activity=(itemClicked) context;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView rvTvStudentName,rvTvStudentClass;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            rvTvStudentName=itemView.findViewById(R.id.rv_tv_student_name);
            rvTvStudentClass=itemView.findViewById(R.id.rv_tv_student_class);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(studentList.indexOf((Student)view.getTag()));
                }
            });

        }
    }

    @NonNull
    @Override
    public StudentAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.Viewholder holder, int position) {
       holder.itemView.setTag(studentList.get(position));
       holder.rvTvStudentName.setText(String.format("Student Name:%s", studentList.get(position).getStudentName()));
       holder.rvTvStudentClass.setText(String.format("Student Class:%s", String.valueOf(studentList.get(position).getStudentClass())));

    }

    @Override
    public int getItemCount() {
       return studentList.size();
    }


}
