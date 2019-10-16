package com.example.assignment_4.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment_4.R;
import com.example.assignment_4.activity.HomeActivity;
import com.example.assignment_4.model.Student;

import java.util.ArrayList;
import java.util.Objects;


public class AddStudentFragment extends Fragment implements View.OnClickListener {
    private EditText etStudentName, etStudentClass, etStudentRoll;
    private Button btnAddDetails;
    private Context mContext;
    private  boolean updateIsClicked=true;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        etStudentName = view.findViewById(R.id.et_student_name);
        etStudentClass = view.findViewById(R.id.et_student_class);
        etStudentRoll = view.findViewById(R.id.et_student_roll);
        btnAddDetails = view.findViewById(R.id.add_details);

        btnAddDetails.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_details:
                String studentName = etStudentName.getText().toString();
                String studentClass = etStudentClass.getText().toString();
                String studentRoll = etStudentRoll.getText().toString();

                //validation

                if(studentName.isEmpty()){
                    etStudentName.setError(getResources().getString(R.string.empty_msg));
                }
                else if(!studentName.matches("[a-zA-Z]+\\.?")){
                    etStudentName.setError(getResources().getString(R.string.valid_name_error_msg));

                }
                else if(studentClass.isEmpty()){
                    etStudentClass.setError(getResources().getString(R.string.empty_msg));
                }
                else if(!studentClass.matches("^[0-9]*$")){
                    etStudentClass.setError(getResources().getString(R.string.class_error_msg));
                }
                else if(Integer.parseInt(studentClass)>12 || Integer.parseInt(studentClass)<1){
                    etStudentClass.setError(getResources().getString(R.string.lessthan12_error_msg));
                }
                else if(studentRoll.isEmpty()){
                    etStudentRoll.setError(getResources().getString(R.string.empty_msg));
                }
                else if(!studentRoll.matches("^[0-9]*$")){
                    etStudentRoll.setError(getResources().getString(R.string.proper_roll_no));
                }
                else{
                Student studentDetails = new Student(studentName, Integer.parseInt(studentClass), Integer.parseInt(studentRoll));
                ((HomeActivity) mContext).addData(studentDetails);
                ((HomeActivity) mContext).switchViewPager();
                }



        }

        //update data

//        public void updateStudentDetail(final int clickedPosition, final Student studentobj,Boolean updateIsClicked ){
//            if(updateIsClicked){
//                etStudentRoll.setInputType(InputType.TYPE_NULL);
//                etStudentRoll.setBackground(getResources().getDrawable(R.drawable.common_border));
//                btnAddDetails.setText("Update");
//
//                etStudentName.setText(studentobj.getStudentName());
//                etStudentRoll.setText(String.valueOf(studentobj.getStudentRoll()));
//                etStudentClass.setText(String.valueOf(studentobj.getStudentClass()));
//
//                code=2;
//
//                btnAddDetails.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String etUpdatedName=etStudentName.getText().toString().trim();
//                        final  int etUpdatedClass=Integer.parseInt(etStudentClass.getText().toString().trim());
//                        int etUpdatedRoll=studentobj.getStudentRoll();
//
//                        final Student updatedStudentObj=new Student(etUpdatedName,etUpdatedClass,etUpdatedRoll);
//                        ((HomeActivity)mContext).onDataUpdated(clickedPosition,updatedStudentObj);
//                        ((HomeActivity)mContext).switchViewPager();
//                    }
//                });
//            }
//        }


    }


}
