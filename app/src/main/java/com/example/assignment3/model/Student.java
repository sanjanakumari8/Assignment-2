package com.example.assignment3.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    String studentName;
    int studentClass;
    int studentRoll;

    public Student(String studentName, int studentClass, int studentRoll) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentRoll = studentRoll;
    }

    protected Student(Parcel in) {
        studentName = in.readString();
        studentClass = in.readInt();
        studentRoll = in.readInt();
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

    public String getStudentName() {
        return studentName;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public int getStudentRoll() {
        return studentRoll;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(studentName);
        parcel.writeInt(studentClass);
        parcel.writeInt(studentRoll);
    }
}
