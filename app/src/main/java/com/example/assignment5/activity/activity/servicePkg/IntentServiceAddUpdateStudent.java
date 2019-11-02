package com.example.assignment5.activity.activity.servicePkg;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.assignment5.activity.activity.database.DatabaseHelper;
import com.example.assignment5.activity.activity.model.Student;

import static com.example.assignment5.activity.activity.fragment.AddStudentFragment.STUDENT_OBJECT_INTENT_SERVICE;

public class IntentServiceAddUpdateStudent extends IntentService {
    private Student student;
    public static final String INTENT_SERVICE_BROADCAST="INTENT_SERVICE_BROADCAST";
    public static final String INTENT_SERVICE_BROADCAST_OBJECT="INTENT_SERVICE_BROADCAST_OBJECT";
    public IntentServiceAddUpdateStudent(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent!=null;
        student=intent.getParcelableExtra(STUDENT_OBJECT_INTENT_SERVICE);
        assert  student!=null;
        if(student.getInputType()==1){
            boolean isResult=new DatabaseHelper(this).addInformation(student);
            if(isResult){
                intent.setAction(INTENT_SERVICE_BROADCAST);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra(INTENT_SERVICE_BROADCAST_OBJECT,student));
            }
        }
        else if (student.getInputType() == 2) {
            boolean isResult1=new DatabaseHelper(this).updateData(student);
            if(isResult1){
                intent.setAction(INTENT_SERVICE_BROADCAST);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra(INTENT_SERVICE_BROADCAST_OBJECT,student));
            }

        }


    }

}
