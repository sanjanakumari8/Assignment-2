package com.example.assignment5.activity.activity.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
   private String studentName;
    private int studentClass;
   private int studentRoll,inputType;


    public Student(String studentName, int studentClass, int studentRoll,int inputType) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentRoll = studentRoll;
        this.inputType=inputType;
    }

    protected Student(Parcel in) {
        studentName = in.readString();
        studentClass = in.readInt();
        studentRoll = in.readInt();
        inputType = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(studentName);
        parcel.writeInt(studentClass);
        parcel.writeInt(studentRoll);
        parcel.writeInt(inputType);
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public int getStudentRoll() {
        return studentRoll;
    }

    public int getInputType() {
        return inputType;
    }
}
