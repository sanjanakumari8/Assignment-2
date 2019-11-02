package com.example.assignment5.activity.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.assignment5.R;
import com.example.assignment5.activity.activity.model.Student;
import java.util.ArrayList;


public class StudentAdapterClass extends RecyclerView.Adapter<StudentAdapterClass.ViewHolder> {
    private Context mContext;
    private ArrayList<Student>studentArrayList;
    private ItemClicked itemClicked;


    //constructor for item clicked
    public StudentAdapterClass( ItemClicked itemClicked) {
        this.itemClicked = itemClicked;
    }


    //view holder class to initialize all Ui component in rv_layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rvTvStudentName,rvTvStudentClass,rvTvStudentRoll;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvTvStudentName=itemView.findViewById(R.id.rv_tv_student_name);
            rvTvStudentClass=itemView.findViewById(R.id.rv_tv_student_class);
            rvTvStudentRoll=itemView.findViewById(R.id.rv_tv_student_roll);
         relativeLayout=   itemView.findViewById(R.id.rl_rv);
        }
    }
//method to setAdapterData
    public void setAdapterData(ArrayList<Student>studentArrayList){
        this.studentArrayList=studentArrayList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public StudentAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext=parent.getContext();
        View view= LayoutInflater.from(mContext).inflate(R.layout.rv_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentAdapterClass.ViewHolder holder, int position) {
        holder.rvTvStudentName.setText(studentArrayList.get(position).getStudentName());
        holder.rvTvStudentClass.setText(String.valueOf(studentArrayList.get(position).getStudentClass()));
        holder.rvTvStudentRoll.setText(String.valueOf(studentArrayList.get(position).getStudentRoll()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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

    //interface for item clicked
    public interface ItemClicked{
        void onItemClicked(int position);
    }
}


