package com.example.assignment5.activity.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.assignment5.R;
import com.example.assignment5.activity.activity.adapter.ViewPagerAdapter;
import com.example.assignment5.activity.activity.database.DatabaseHelper;
import com.example.assignment5.activity.activity.fragment.AddStudentFragment;
import com.example.assignment5.activity.activity.fragment.StudentListFragment;
import com.example.assignment5.activity.activity.model.Student;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    private StudentListFragment studentListFragment;
    private AddStudentFragment addStudentFragment;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DatabaseHelper db;
    private ImageButton ibSort, ibGrid;
    private boolean isList = true;
    private boolean isSort = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setViewPagerAdapter();
        db = new DatabaseHelper(this);

    }

    public void init() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ibGrid = findViewById(R.id.ib_grid);
        ibSort = findViewById(R.id.ib_sort);

        //list view and grid view
        ibGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isList) {
                    isList = false;
                    ibGrid.setBackground(getResources().getDrawable(R.drawable.ic_list_black_24dp));
                } else {
                    isList = true;
                    ibGrid.setBackground(getResources().getDrawable(R.drawable.ic_squares));
                }
                studentListFragment.toggleView(isList);

            }
        });
        // sorting
        ibSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, ibSort);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.sort_name:
                                isSort = true;
                                studentListFragment.sortName(isSort);
                                return true;
                            case R.id.sort_roll_no:
                                isSort = true;
                                studentListFragment.sortRoll(isSort);
                                return true;
                            default:
                                isSort = false;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    public void setViewPagerAdapter() {
        studentListFragment = new StudentListFragment();
        addStudentFragment = new AddStudentFragment();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(studentListFragment, getString(R.string.student_list));
        viewPagerAdapter.addFragment(addStudentFragment, getString(R.string.add_update_student));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    //getting the data which is send from AddStudentFragment
    public void onAddUpdateStudent(final int clickedPosition, final Student studentObj) {
        studentListFragment.onAddUpdateStudent(clickedPosition, studentObj);

    }

    //here method to switch the tab
    public void switchViewPager() {
        if (viewPager.getCurrentItem() == 0) {
            viewPager.setCurrentItem(1, true);
        } else {
            viewPager.setCurrentItem(0, true);
        }
    }


    public void dialogClick(int clickedPosition, Student student) {
        viewPager.setCurrentItem(1, true);
        addStudentFragment.updateDetail(clickedPosition, student);
    }

    public void onDataUpdated(final int clickedPosition, Student studentobj) {
        studentListFragment.updateStudentDetail(clickedPosition, studentobj);
    }

}
