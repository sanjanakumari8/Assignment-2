package com.example.assignment5.activity.activity.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.assignment5.R;
import com.example.assignment5.activity.activity.activity.HomeActivity;
import com.example.assignment5.activity.activity.database.DatabaseHelper;
import com.example.assignment5.activity.activity.model.Student;
import com.example.assignment5.activity.activity.servicePkg.AsyncTask;
import com.example.assignment5.activity.activity.servicePkg.Service;

import static com.example.assignment5.activity.activity.servicePkg.IntentServiceAddUpdateStudent.INTENT_SERVICE_BROADCAST;
import static com.example.assignment5.activity.activity.servicePkg.IntentServiceAddUpdateStudent.INTENT_SERVICE_BROADCAST_OBJECT;
import static com.example.assignment5.activity.activity.servicePkg.Service.ADD_BROADCAST;
import static com.example.assignment5.activity.activity.servicePkg.Service.ADD_BROADCAST_STUDENT_OBJECT;

public class AddStudentFragment extends Fragment implements View.OnClickListener, AsyncTask.AsycTaskCallBackListner {

    private EditText etStudentName, etStudentClass, etStudentRoll;
    private Context mContext;
    private Button btnAddDetails, btnView, btnEdit, btnDelete;
    private Student student;
    private int mClickedPosition;
    private boolean isUpdate = true;
    private boolean mBroadCasrReceiver = false;
    public static final String STUDENT_OBJECT = "STUDENT_OBJ";
    public static final String STUDENT_OBJECT_INTENT_SERVICE = "STUDENT_OBJECT_INTENT_SERVICE";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public AddStudentFragment(final boolean isBroadRequired) {
        this.mBroadCasrReceiver = isBroadRequired;
    }

    public AddStudentFragment() {
    }


    @Nullable
    @Override


    //
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        etStudentName = view.findViewById(R.id.et_student_name);
        etStudentClass = view.findViewById(R.id.et_student_class);
        etStudentRoll = view.findViewById(R.id.et_student_roll);
        btnAddDetails = view.findViewById(R.id.add_details);
        init();
        return view;
    }

    //initialization of Ui component
    public void init() {
        btnAddDetails.setOnClickListener(this);

    }


    //sending data to HomeActivity after after adding student on clicklistner event
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_details:

                //dialog open on clicking add button id: add_details
                final Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.dialog);
                btnView = dialog.findViewById(R.id.btn_view);
                btnView.setText("Async Task");
                btnEdit = dialog.findViewById(R.id.btn_edit);
                btnEdit.setText("Service");
                btnDelete = dialog.findViewById(R.id.btn_delete);
                btnDelete.setText("Intent Service");

                //click listner on asynctask button to get input from user

                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (isUpdate == false) {
                            String etUpdatedName = etStudentName.getText().toString().trim();
                            final int etUpdatedClass = Integer.parseInt(etStudentClass.getText().toString().trim());
                            int etUpdatedRoll = student.getStudentRoll();

                            final Student updatedStudentObj = new Student(etUpdatedName, etUpdatedClass, etUpdatedRoll, 2);
                            new AsyncTask(mContext, AddStudentFragment.this).execute(updatedStudentObj);
                            clear();
                        } else {
                            String studentName = etStudentName.getText().toString().trim();
                            int studentClass = Integer.parseInt(etStudentClass.getText().toString().trim());
                            int studentRoll = Integer.parseInt(etStudentRoll.getText().toString().trim());
                            student = new Student(studentName, studentClass, studentRoll, 1);

                            btnAddDetails.setText("ADD");
                            //method call to async method
                            async();
                        }
                        clear();
                        dialog.dismiss();
                    }
                });
                //service click listner
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isUpdate==false){
                            String etUpdatedName = etStudentName.getText().toString().trim();
                            final int etUpdatedClass = Integer.parseInt(etStudentClass.getText().toString().trim());
                            int etUpdatedRoll = student.getStudentRoll();

                            final Student updatedStudentObj = new Student(etUpdatedName, etUpdatedClass, etUpdatedRoll, 2);
                            new AsyncTask(mContext, AddStudentFragment.this).execute(updatedStudentObj);

                        } else {
                            String studentName = etStudentName.getText().toString().trim();
                            int studentClass = Integer.parseInt(etStudentClass.getText().toString().trim());
                            int studentRoll = Integer.parseInt(etStudentRoll.getText().toString().trim());
                            student = new Student(studentName, studentClass, studentRoll, 1);
                             btnAddDetails.setText("Add");
                            Intent serviceIntent = new Intent(mContext, Service.class);
                            serviceIntent.putExtra(STUDENT_OBJECT, student);
                            mContext.startService(serviceIntent);

                        }
                        dialog.dismiss();
                        clear();

                    }
                });
                //IntentService Click Listner
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isUpdate==false){
                            String etUpdatedName = etStudentName.getText().toString().trim();
                            final int etUpdatedClass = Integer.parseInt(etStudentClass.getText().toString().trim());
                            int etUpdatedRoll = student.getStudentRoll();

                            final Student updatedStudentObj = new Student(etUpdatedName, etUpdatedClass, etUpdatedRoll, 2);
                            new AsyncTask(mContext, AddStudentFragment.this).execute(updatedStudentObj);

                        } else {
                            String studentName = etStudentName.getText().toString().trim();
                            int studentClass = Integer.parseInt(etStudentClass.getText().toString().trim());
                            int studentRoll = Integer.parseInt(etStudentRoll.getText().toString().trim());
                            student = new Student(studentName, studentClass, studentRoll, 1);
                            btnAddDetails.setText("Add");
                            Intent serviceIntent = new Intent(mContext, Service.class);
                            serviceIntent.putExtra(STUDENT_OBJECT, student);
                            mContext.startService(serviceIntent);
                            btnAddDetails.setText("Add");


                        }
                        clear();
                        dialog.dismiss();

                    }
                });


                dialog.show();
        }
    }

    private void async() {
        AsyncTask asyncTask = new AsyncTask(mContext, this);
        asyncTask.execute(student);
    }

    @Override
    public void asyncListner(boolean isSuccess, Student student) {
        if (isSuccess) {
            clear();
            ((HomeActivity) mContext).onAddUpdateStudent(mClickedPosition, student);
            ((HomeActivity) mContext).switchViewPager();
        } else {
            Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

    }

    //to clear all text after adding student
    public void clear() {
        etStudentName.setText("");
        etStudentRoll.setText("");
        etStudentClass.setText("");
    }

    public void updateDetail(final int clickedPosition, final Student studentObj) {

        mClickedPosition = clickedPosition;
        isUpdate = false;
        this.student=studentObj;

        etStudentRoll.setBackground(getResources().getDrawable(R.drawable.common_border));
        btnAddDetails.setText("Update");

        etStudentName.setText(studentObj.getStudentName());
        etStudentRoll.setText(String.valueOf(studentObj.getStudentRoll()));
        etStudentClass.setText(String.valueOf(studentObj.getStudentClass()));
        etStudentRoll.setInputType(InputType.TYPE_NULL);
        clear();
    }

    //broadcast receiver
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Student student = intent.getParcelableExtra(ADD_BROADCAST_STUDENT_OBJECT);
            ((HomeActivity) mContext).onAddUpdateStudent(mClickedPosition, student);
            ((HomeActivity) mContext).switchViewPager();
//            clear();
            etStudentRoll.setInputType(InputType.TYPE_CLASS_NUMBER);
            btnAddDetails.setText("ADD");
            etStudentRoll.setBackground(getResources().getDrawable(R.drawable.common_border));
        }
    };

    //register broadcast service
    @Override
    public void onResume() {
        super.onResume();
        if (mBroadCasrReceiver) {
            ((HomeActivity) mContext).registerReceiver(broadcastReceiver, new IntentFilter(ADD_BROADCAST));

        }
    }
//    unregister broadcast service
    @Override
    public void onPause() {
        super.onPause();
        if (mBroadCasrReceiver) {
            ((HomeActivity) mContext).unregisterReceiver(broadcastReceiver);
        }
    }

    //method for register intentService
    public void intentServiceReceiver() {
        IntentServiceBroadCastReceiver intentServiceBroadCastReceiver = new IntentServiceBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INTENT_SERVICE_BROADCAST);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(intentServiceBroadCastReceiver,intentFilter);
    }

    //broadcast receiver for intent service
    public class IntentServiceBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Student student = intent.getParcelableExtra(INTENT_SERVICE_BROADCAST_OBJECT);
            ((HomeActivity) mContext).onAddUpdateStudent(mClickedPosition, student);
            ((HomeActivity) mContext).switchViewPager();
            clear();
            etStudentRoll.setInputType(InputType.TYPE_CLASS_NUMBER);
            btnAddDetails.setText("Add");
            etStudentRoll.setBackground(getResources().getDrawable(R.drawable.common_border));
        }
    }
    //register broadcast receiver IntentService
    @Override
    public void onStart() {
        super.onStart();
        if(mBroadCasrReceiver){
            intentServiceReceiver();
        }
    }
    //unregister broadcast receiver IntentService

    @Override
    public void onStop() {
        super.onStop();
        if(mBroadCasrReceiver){}
    }

}
