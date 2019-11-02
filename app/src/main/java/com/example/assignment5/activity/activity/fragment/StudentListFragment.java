package com.example.assignment5.activity.activity.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment5.R;
import com.example.assignment5.activity.activity.activity.HomeActivity;
import com.example.assignment5.activity.activity.activity.ViewStudentDetailActivity;
import com.example.assignment5.activity.activity.adapter.StudentAdapterClass;
import com.example.assignment5.activity.activity.database.DatabaseHelper;
import com.example.assignment5.activity.activity.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentListFragment extends Fragment implements View.OnClickListener, StudentAdapterClass.ItemClicked {
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView tvErrorMsg;
    private EditText etStudentRoll;
    private StudentAdapterClass studentAdapterClass;
    private Context mContext;
    private Button btnAddStudent, btnView, btnEdit, btnDelete;
    private Cursor c;
    private DatabaseHelper databaseHelper;
    public static final String STUDENT_VIEW = "view";


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        tvErrorMsg = view.findViewById(R.id.tv_student_not_added_error);
        recyclerView = view.findViewById(R.id.recycler_view);
        btnAddStudent = view.findViewById(R.id.add_student);
        etStudentRoll = view.findViewById(R.id.et_student_roll);
        init();
        setRecyclerView();
        showDataInRecyclerView();
        return view;
    }

    //method to init recyclerView
    public void init() {

        btnAddStudent.setOnClickListener(this);
    }

    //method to set recyclerView
    public void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        studentAdapterClass = new StudentAdapterClass(this);
        recyclerView.setAdapter(studentAdapterClass);
    }

    //getting result in the from of data from HomeActivity
    public void onAddUpdateStudent(final int clickedPosition, final Student studentObj) {
        if (studentObj.getInputType() == 1) {
            studentArrayList.add(studentObj);
        } else if (studentObj.getInputType() == 2) {
            studentArrayList.set(clickedPosition, studentObj);
        }

        setDataToList();

    }

    private void setDataToList() {
        if (studentArrayList.size() == 0) {
            tvErrorMsg.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            tvErrorMsg.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            studentAdapterClass.setAdapterData(studentArrayList);
        }
    }

    //onclick to switch the tab from studentList to add/updateStudent via clicking button AddStudent
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_student:
                ((HomeActivity) mContext).switchViewPager();
        }
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
            Collections.sort(studentArrayList, new Comparator<Student>() {
                @Override
                public int compare(Student studentDetails3, Student studentDetails4) {
                    String name1 = String.valueOf(studentDetails3.getStudentName());
                    String name2 = String.valueOf(studentDetails4.getStudentName());
                    return name1.compareToIgnoreCase(name2);
                }
            });
        }
        studentAdapterClass.setAdapterData(studentArrayList);
    }

    //sorting data as roll no
    public void sortRoll(final boolean mIsSortData) {
        if (mIsSortData) {
            Collections.sort(studentArrayList, new Comparator<Student>() {
                @Override
                public int compare(Student studentDetails3, Student studentDetails4) {
                    String roll1 = String.valueOf(studentDetails3.getStudentRoll());
                    String roll2 = String.valueOf(studentDetails4.getStudentRoll());
                    return roll1.compareToIgnoreCase(roll2);
                }
            });
        }
        studentAdapterClass.setAdapterData(studentArrayList);
    }


    //to show data of sqLite in recyclerView
    public void showDataInRecyclerView() {
        studentArrayList = new DatabaseHelper(mContext).getAllStudents();

        setDataToList();
    }

    //updated student detail
    public void updateStudentDetail(final int clickedPosition, Student studentbject) {
        studentArrayList.set(clickedPosition, studentbject);
        studentAdapterClass.setAdapterData(studentArrayList);
    }

    @Override
    public void onItemClicked(final int position) {
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog);

        btnView = dialog.findViewById(R.id.btn_view);
        btnEdit = dialog.findViewById(R.id.btn_edit);
        btnDelete = dialog.findViewById(R.id.btn_delete);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ViewStudentDetailActivity.class);

                intent.putExtra(STUDENT_VIEW, studentArrayList.get(position));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) mContext).dialogClick(position, studentArrayList.get(position));
                dialog.dismiss();


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDeleteSuccess = new DatabaseHelper(mContext).deleteData(studentArrayList.get(position).getStudentRoll());
                if (isDeleteSuccess) {
                    studentArrayList.remove(position);
                    setDataToList();
                    dialog.dismiss();
                    Toast.makeText(mContext, "Data Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "Data not Deleted", Toast.LENGTH_LONG).show();
                }
            }

        });

        dialog.show();


    }
}
