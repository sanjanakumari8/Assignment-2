package com.example.assignment5.activity.activity.servicePkg;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.assignment5.activity.activity.database.DatabaseHelper;
import com.example.assignment5.activity.activity.model.Student;

import static com.example.assignment5.activity.activity.fragment.AddStudentFragment.STUDENT_OBJECT;


public class Service extends android.app.Service {
    private Student student;
    public  static final String ADD_BROADCAST="add_broadcast";
    public  static final String ADD_BROADCAST_STUDENT_OBJECT="add_broadcast_student_obj";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         super.onStartCommand(intent, flags, startId);
         student=intent.getParcelableExtra(STUDENT_OBJECT);
        assert student!=null;
        if (student.getInputType() == 1) {
            boolean result=new DatabaseHelper(this).addInformation(student);
            if(result){
                Intent intent1=new Intent(ADD_BROADCAST);
                intent1.putExtra(ADD_BROADCAST_STUDENT_OBJECT,student);
                sendBroadcast(intent1);
            }else if(student.getInputType()==2){
              boolean result1=new DatabaseHelper(getApplicationContext()).updateData(student);
              if(result1){
                  Intent intent1=new Intent(ADD_BROADCAST);
                  intent1.putExtra(ADD_BROADCAST_STUDENT_OBJECT,student);
                  sendBroadcast(intent1);
              }
            }
        }
        return  START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
