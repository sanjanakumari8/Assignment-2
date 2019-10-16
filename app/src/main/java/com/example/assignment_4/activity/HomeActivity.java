package com.example.assignment_4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_4.Fragment.AddStudentFragment;
import com.example.assignment_4.R;
import com.example.assignment_4.Fragment.StudentListFragment;
import com.example.assignment_4.adapter.StudentRecyleViewAdapter;
import com.example.assignment_4.adapter.ViewPagerAdapter;
import com.example.assignment_4.model.Student;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageButton ibSort,ibGrid;
    private TextView title;
    private StudentListFragment studentListFragment;
    private AddStudentFragment addStudentFragment;
    private boolean isList=true;
    private  boolean isSort=true;
    private ArrayList<Student> studentList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        studentListFragment=new StudentListFragment();
        addStudentFragment=new AddStudentFragment();

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(studentListFragment,getString(R.string.student_list));
        viewPagerAdapter.addFragment(addStudentFragment,getString(R.string.add_update_student));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //list view and grid view

        ibGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isList){
                    isList=false;
                    ibGrid.setBackground(getResources().getDrawable(R.drawable.ic_list_black_24dp));
                }else{
                    isList=true;
                    ibGrid.setBackground(getResources().getDrawable(R.drawable.ic_squares));
                }studentListFragment.toggleView(isList);


            }
        });

        // sorting
        ibSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(HomeActivity.this,ibSort);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.sort_name:
                                isSort=true;
                                studentListFragment.sortName(isSort);
                                return  true;
                            case R.id.sort_roll_no:
                                isSort=true;
                                studentListFragment.sortRoll(isSort);
                                return  true;
                                default:
                                    isSort=false;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    public  void init(){
        viewPager = findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tab_layout);
        ibGrid=findViewById(R.id.ib_grid);
        ibSort=findViewById(R.id.ib_sort);
        title=findViewById(R.id.tv_heading);
    }
    //on click AddStudent btn viewpager Switches to add/update student
    public void switchViewPager(){
        if(viewPager.getCurrentItem()==0){
            viewPager.setCurrentItem(1,true);
        }else {
            viewPager.setCurrentItem(0,true);
        }
    }

    //getting data from fragment sending to  student list
    public void addData(Student studentDetails) {
        studentList.add(studentDetails);

        if(studentListFragment instanceof StudentListFragment){
            studentListFragment.setData(studentList);
        }

    }


    //dialog on click open fragment for edit
//    public void dialogClick(final int clickedPosition,Student studentobj,boolean mUpdateClicked){
//        viewPager.setCurrentItem(1,true);
//        addStudentFragment.updateStudentDetail(clickedPosition,studentobj,mUpdateClicked);
//    }
//    public void onDataUpdated(final  int clickedPosition,Student studentobj){
//        studentListFragment.updatesStudentDetail(clickedPosition,studentobj);
//
//    }




}
