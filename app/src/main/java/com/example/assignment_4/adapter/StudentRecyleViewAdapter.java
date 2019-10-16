package com.example.assignment_4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_4.Fragment.StudentListFragment;
import com.example.assignment_4.R;
import com.example.assignment_4.model.Student;

import java.util.ArrayList;

public class StudentRecyleViewAdapter extends RecyclerView.Adapter<StudentRecyleViewAdapter.Viewholder> {

    private ArrayList<Student>studentArrayList;
    private ItemClicked itemClicked;
    public StudentRecyleViewAdapter(ItemClicked itemClicked) {
        this.itemClicked=itemClicked;
    }


    public void setAdapterData(ArrayList<Student> studentArrayList){
        this.studentArrayList = studentArrayList;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        //declaration of rv layout field
         TextView rvTvStudentName,rvTvStudentClass,rvTvStudentRoll;
         RelativeLayout rlRv;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            //initialization of rv layout field
            rvTvStudentName=itemView.findViewById(R.id.rv_tv_student_name);
            rvTvStudentClass=itemView.findViewById(R.id.rv_tv_student_class);
            rvTvStudentRoll=itemView.findViewById(R.id.rv_tv_student_roll);
            rlRv=itemView.findViewById(R.id.rl_rv);

        }
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, int position) {
        holder.rvTvStudentName.setText(String.format("Name of the Student:%s", studentArrayList.get(position).getStudentName()));
        holder.rvTvStudentClass.setText(String.format("Class of the Student:%s", studentArrayList.get(position).getStudentClass()));
        holder.rvTvStudentRoll.setText(String.format("Roll no. of the Student:%s", studentArrayList.get(position).getStudentRoll()));
        holder.rlRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClicked.onItemClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentArrayList==null?0:studentArrayList.size();
    }
    public interface ItemClicked{
        void onItemClicked(int position);
    }

}
