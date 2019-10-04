package com.example.assignment3.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.R;
import com.example.assignment3.adapter.StudentAdapter;
import com.example.assignment3.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity implements StudentAdapter.itemClicked {
    ArrayList<Student> studentList=new ArrayList<Student>();
    private RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    private TextView tvNoStudentAddedYetError;
    private Button btnAddStudent;
    private ImageButton ivSort,ivGrid;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final boolean[] isList = {true};

        init();

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new StudentAdapter(this,studentList);
        update();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,AddStudentActivity.class);
                startActivityForResult(intent,3);
            }
        });
        ivSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopup();
            }
        });

        ivGrid.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if (isList[0] ==true){
                    Toast.makeText(HomeActivity.this, "Grid View", Toast.LENGTH_SHORT).show();
                    ivGrid.setBackground(getResources().getDrawable(R.drawable.ic_list_black_24dp));
                    RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    myAdapter.notifyDataSetChanged();
                    isList[0] =false;
                }
                else {
                    Toast.makeText(HomeActivity.this, "List View", Toast.LENGTH_SHORT).show();
                    ivGrid.setBackground(getResources().getDrawable(R.drawable.ic_squares));
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    myAdapter.notifyDataSetChanged();
                    isList[0] =true;
                }
            }
        });
    }
    public void init() {
        recyclerView = findViewById(R.id.recycler_view);
        btnAddStudent = findViewById(R.id.add_student);
        tvNoStudentAddedYetError = findViewById(R.id.tv_student_not_added_error);
        ivSort = findViewById(R.id.iv_sort);
        ivGrid = findViewById(R.id.iv_grid);

    }

    public void openPopup(){
        PopupMenu popupMenu=new PopupMenu(HomeActivity.this,ivSort);
        popupMenu.getMenuInflater().inflate(R.menu.pop_up,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.sort_name:
                        Collections.sort(studentList, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                return o1.getStudentName().compareToIgnoreCase(o2.getStudentName());
                            }
                        });
                        myAdapter.notifyDataSetChanged();
                        return true;
                    case R.id.sort_roll_no:
                        Collections.sort(studentList, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                String rollNo1=String.valueOf(o1.getStudentRoll());
                                String rollNo2=String.valueOf(o2.getStudentRoll());
                                return rollNo1.compareToIgnoreCase(rollNo2);
                            }
                        });
                        myAdapter.notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3){
            if(resultCode==RESULT_OK){
             Student st=data.getParcelableExtra("Object");
             studentList.add(st);
             update();
            }
        }
        if(requestCode==12){
            if(resultCode==RESULT_OK){
                Student st=data.getParcelableExtra("Object");
                int index = data.getIntExtra("index", 0);
                studentList.set(index, st);
                update();
            }
        }
    }
    public void update(){

        recyclerView.setAdapter(myAdapter);
        if(studentList.size()==0){
            recyclerView.setVisibility(View.INVISIBLE);
            tvNoStudentAddedYetError.setVisibility(View.VISIBLE);

        }
        else{
            tvNoStudentAddedYetError.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClicked(final int index) {
      final Dialog dialog=new Dialog(this);
      dialog.setContentView(R.layout.custom_dialog);
      Button btnView=dialog.findViewById(R.id.custom_dialog_view);
      Button btnEdit=dialog.findViewById(R.id.custom_dialog_edit);
      Button btnDelete=dialog.findViewById(R.id.custom_dialog_delete);
        x=index;

      final Student student = studentList.get(index);
      btnView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(HomeActivity.this, AddStudentActivity.class);
              intent.putExtra("result", 11);
              intent.putExtra("student",student);
              startActivity(intent);
          }
      });
      btnDelete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              studentList.remove(x);
              dialog.dismiss();
              myAdapter.notifyDataSetChanged();
              Toast.makeText(HomeActivity.this, "item has been deleted", Toast.LENGTH_SHORT).show();

              if(studentList.size()==0){
                  tvNoStudentAddedYetError.setText(View.VISIBLE);
                  recyclerView.setVisibility(View.INVISIBLE);
              }
          }
      });

      btnEdit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(HomeActivity.this,AddStudentActivity.class);
              intent.putExtra("result",12);
              intent.putExtra("student",student);
              intent.putExtra("index", index);
              startActivityForResult(intent,12);

          }
      });
        dialog.show();
    }


}
