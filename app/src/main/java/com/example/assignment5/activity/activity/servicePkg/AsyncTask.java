package com.example.assignment5.activity.activity.servicePkg;

import android.content.Context;

import com.example.assignment5.activity.activity.database.DatabaseHelper;
import com.example.assignment5.activity.activity.model.Student;


public class AsyncTask extends android.os.AsyncTask<Student, Void, Boolean> {
    private Context context;
    private AsycTaskCallBackListner callBackListner;
    private Student studentObj;


    public AsyncTask(final Context context, final AsycTaskCallBackListner callBack) {
        this.context = context;
        this.callBackListner = callBack;
    }


    @Override
    protected Boolean doInBackground(Student... student) {

        studentObj = student[0];
        if (studentObj.getInputType() == 1) {
            //add code database
            return  new DatabaseHelper(context).addInformation(studentObj);
        } else if (studentObj.getInputType() == 2) {
                return new DatabaseHelper(context).updateData(studentObj);
            //update
        } else if (studentObj.getInputType() == 3) {
            //delete
        }
        return null;

//       //       // databaseHelper.updateData(student1.getStudentName(),student1.getStudentClass(),student1.getStudentRoll());


    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(Boolean isResult) {
        if (isResult) {
            callBackListner.asyncListner(isResult, studentObj);
        } else {
            callBackListner.asyncListner(isResult, null);
        }

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public interface AsycTaskCallBackListner {
        void asyncListner(final boolean isSuccess, final Student student);
    }


}
