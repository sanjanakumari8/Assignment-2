package com.example.assignment5.activity.activity.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.assignment5.activity.activity.model.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "STUDENTS";

    // Table columns
    public static final String NAME = "name";
    public static final String CLASS = "class";
    public static final String ROLL_NO = "rollno";

    // Database Information
    static final String DB_NAME = "Student.DB";

    // database version
    static final int DB_VERSION = 2;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ROLL_NO
            + " INTEGER PRIMARY KEY, " + NAME + " TEXT NOT NULL, " + CLASS + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //SQLiteDatabase db=this.getWritableDatabase();
        Log.e("Database creation", "database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    //adding information to async task
    public boolean addInformation(Student studentObj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROLL_NO, studentObj.getStudentRoll());
        contentValues.put(NAME, studentObj.getStudentName());
        contentValues.put(CLASS, studentObj.getStudentClass());
        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.e("Result", "" + result);
        return result != -1;
    }

    //get all studentList from database in recyclerview
    public ArrayList<Student> getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {NAME, CLASS, ROLL_NO};
        Cursor c= db.query(TABLE_NAME, columns, null, null, null, null, null);

        ArrayList<Student> studentsList=new ArrayList<>();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String name = c.getString(0);
                    int studentClass = c.getInt(1);
                    int roll = c.getInt(2);
                    Student st = new Student(name, studentClass, roll,0);
                    studentsList.add(st);
                } while (c.moveToNext());
            }
        }

        return studentsList;

    }
    // delete data from recyclerView;
    public boolean deleteData (int studentRoll) {
        SQLiteDatabase db = this.getWritableDatabase();
       long delete= db.delete(TABLE_NAME,ROLL_NO+"="+studentRoll ,null);
        return delete !=0;
    }

    //update student in database from recyclerView
    public boolean updateData(Student studentObj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROLL_NO,studentObj.getStudentRoll());
        contentValues.put(NAME,studentObj.getStudentName());
        contentValues.put(CLASS,studentObj.getStudentClass());
        long update= db.update(TABLE_NAME, contentValues, ROLL_NO+"="+studentObj.getStudentRoll(),null);
        return update!=-1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
