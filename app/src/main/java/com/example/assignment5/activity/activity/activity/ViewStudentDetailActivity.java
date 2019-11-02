package com.example.assignment5.activity.activity.activity;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment5.R;
import com.example.assignment5.activity.activity.model.Student;
import static com.example.assignment5.activity.activity.fragment.StudentListFragment.STUDENT_VIEW;


public class ViewStudentDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton ibSort,ibGrid;
    private TextView tvHeading;
    private EditText etStudentName,etStudentRoll,etStudentClass;
    private Button btnViewBack;
    private Student studentView;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_detail);
        studentView=getIntent().getParcelableExtra(STUDENT_VIEW);
             init();
        etStudentName.setText(studentView.getStudentName());
        etStudentRoll.setText(String.valueOf(studentView.getStudentRoll()));
        etStudentClass.setText(String.valueOf(studentView.getStudentClass()));

        viewDetails();


    }

    public  void init(){
        ibSort=findViewById(R.id.ib_sort);
        ibGrid=findViewById(R.id.ib_grid);
        tvHeading=findViewById(R.id.tv_heading);
        etStudentName=findViewById(R.id.et_student_name);
        etStudentClass=findViewById(R.id.et_student_class);
        etStudentRoll=findViewById(R.id.et_student_roll);
        btnViewBack=findViewById(R.id.add_details);
        btnViewBack.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void viewDetails() {
        ibGrid.setVisibility(View.INVISIBLE);
        ibSort.setVisibility(View.INVISIBLE);
        tvHeading.setText(getResources().getString(R.string.view_student));

        etStudentName.setBackground(getResources().getDrawable(R.drawable.common_border));
        etStudentClass.setBackground(getResources().getDrawable(R.drawable.common_border));
        etStudentRoll.setBackground(getResources().getDrawable(R.drawable.common_border));

        etStudentName.setFocusable(false);
        etStudentClass.setFocusable(false);
        etStudentRoll.setFocusable(false);
        btnViewBack.setText(R.string.back_button);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_details:
                finish();
        }

    }
}
