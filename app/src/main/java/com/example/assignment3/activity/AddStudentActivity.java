package com.example.assignment3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.R;
import com.example.assignment3.model.Student;

public class AddStudentActivity extends AppCompatActivity {
    TextView tvStudent, tvHome;
    EditText etStudentName,etStudentClass,etStudentRoll;
    Button addStudentSubmit;
    ImageButton ibGrid,ibSort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        etStudentName=findViewById(R.id.student_name);
        etStudentClass=findViewById(R.id.student_class);
        etStudentRoll=findViewById(R.id.student_roll);
        addStudentSubmit=findViewById(R.id.submit);
        tvHome = findViewById(R.id.tv_home);
        tvHome.setText(R.string.add_student);
        ibGrid=findViewById((R.id.iv_grid));
        ibSort=findViewById((R.id.iv_sort));
        ibGrid.setVisibility(View.INVISIBLE);
        ibGrid.setVisibility(View.INVISIBLE);

        Intent intent=getIntent();
        int result=intent.getIntExtra("result",0);
        Student student=intent.getParcelableExtra("student");

        final int index = intent.getIntExtra("index", 0);
        if(result==11){
            tvHome.setText("View Student");
            etStudentName.setEnabled(false);
            etStudentName.setFocusable(false);
            etStudentRoll.setEnabled(false);
            etStudentRoll.setFocusable(false);
            etStudentClass.setEnabled(false);
            etStudentClass.setFocusable(false);
            addStudentSubmit.setVisibility(View.INVISIBLE);
            etStudentName.setText(student.getStudentName());
            etStudentClass.setText(String.valueOf(student.getStudentClass()));
            etStudentRoll.setText(String.valueOf(student.getStudentRoll()));
        }

        if(result==12){
            tvHome.setText("Update Student");
            etStudentRoll.setEnabled(false);
            etStudentRoll.setFocusable(false);
            etStudentName.setText(student.getStudentName());
            etStudentClass.setText(String.valueOf(student.getStudentClass()));
            etStudentRoll.setText(String.valueOf(student.getStudentRoll()));
            addStudentSubmit.setVisibility(View.VISIBLE);
        }


        addStudentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputStudentName=etStudentName.getText().toString();
                int inputStudentClass= Integer.parseInt(etStudentClass.getText().toString());
                int inputStudentRoll= Integer.parseInt(etStudentRoll.getText().toString());
                String validclass="^([1-9]|1[012])$";
                String validRoll="^\\+?[1-9]\\d*$";

                if(inputStudentName.isEmpty()){
                    Toast.makeText(AddStudentActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
                if(!etStudentClass.getText().toString().matches(validclass)){
                    Toast.makeText(AddStudentActivity.this, "Enter class", Toast.LENGTH_SHORT).show();
                }
                if(!etStudentRoll.getText().toString().matches(validRoll)){
                    Toast.makeText(AddStudentActivity.this, "Enter Roll", Toast.LENGTH_SHORT).show();
                }else{
                    Student st=new Student(inputStudentName,inputStudentClass,inputStudentRoll);
                    Intent i=new Intent(AddStudentActivity.this,HomeActivity.class);
                    i.putExtra("Object",st);
                    i.putExtra("index", index);
                    setResult(RESULT_OK,i);
                    finish();

                }

            }
        });

    }
}
