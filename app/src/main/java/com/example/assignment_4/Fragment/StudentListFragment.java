package com.example.assignment_4.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment_4.R;
import com.example.assignment_4.activity.HomeActivity;
import com.example.assignment_4.activity.ViewStudentDetailActivity;
import com.example.assignment_4.adapter.StudentRecyleViewAdapter;
import com.example.assignment_4.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListFragment extends Fragment implements StudentRecyleViewAdapter.ItemClicked {
    private RecyclerView recyclerView;
    private TextView errorMsg;
    private Button btnAddStudent;
    private StudentRecyleViewAdapter myAdapter;
    private Context mContext;
    private Button btnView, btnEdit, btnDelete;
    private ArrayList<Student> studentList1 = new ArrayList<>();
    public static final String STUDENT_VIEW = "view";
    private  boolean updateClicked=true;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        errorMsg = view.findViewById(R.id.tv_student_not_added_error);
        btnAddStudent = view.findViewById(R.id.add_student);


        //recycler view
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new StudentRecyleViewAdapter(this);
        recyclerView.setAdapter(myAdapter);

        //on click AddStudent btn viewpager Switches to add/update student

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) mContext).switchViewPager();
            }
        });

        return view;
    }
    //list view and grid view

    public void toggleView(final boolean isSquares) {
        if (isSquares) {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

    }

    //sort by name

    public void sortName(final boolean mIsSortData) {
        if (mIsSortData) {
            Collections.sort(studentList1, new Comparator<Student>() {
                @Override
                public int compare(Student studentDetails3, Student studentDetails4) {
                    String name1 = String.valueOf(studentDetails3.getStudentName());
                    String name2 = String.valueOf(studentDetails4.getStudentName());
                    return name1.compareToIgnoreCase(name2);
                }
            });
        }
        myAdapter.setAdapterData(studentList1);
    }

    //sorting data as roll no
    public void sortRoll(final boolean mIsSortData) {
        if (mIsSortData) {
            Collections.sort(studentList1, new Comparator<Student>() {
                @Override
                public int compare(Student studentDetails3, Student studentDetails4) {
                    String roll1 = String.valueOf(studentDetails3.getStudentRoll());
                    String roll2 = String.valueOf(studentDetails4.getStudentRoll());
                    return roll1.compareToIgnoreCase(roll2);
                }
            });
        }
        myAdapter.setAdapterData(studentList1);
    }

    // updated data
    public  void updateStudentDetail(final int clickedPosition,Student studentbject){
        studentList1.set(clickedPosition,studentbject);
        myAdapter.setAdapterData(studentList1);
    }



    //getting object as we pass in activity for adding student
    public void setData(ArrayList<Student> studentData) {
        studentList1.addAll(studentData);
        myAdapter.setAdapterData(studentData);
        check(studentData);
    }

    //code to check whether there is any student in list or not.
    public void check(ArrayList<Student> studentArrayList) {
        if (studentArrayList.size() == 0) {
            errorMsg.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            errorMsg.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClicked(final int position) {
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog);

        Button btnView = dialog.findViewById(R.id.btn_view);
        Button btnEdit = dialog.findViewById(R.id.btn_edit);
        Button btnDelete = dialog.findViewById(R.id.btn_delete);


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ViewStudentDetailActivity.class);
                intent.putExtra(STUDENT_VIEW, studentList1.get(position));
                startActivity(intent);
                dialog.dismiss();
            }

        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentList1.remove(position);
                myAdapter.setAdapterData(studentList1);
                dialog.dismiss();
                if (studentList1.size() == 0) {
                    errorMsg.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }
            }
        });
        //edit

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)mContext).dialogClick(position,studentList1.get(position),updateClicked);
                dialog.dismiss();

            }
        });
        dialog.show();

    }
}
