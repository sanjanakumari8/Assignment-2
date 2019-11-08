package com.example.assignment6.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.assignment6.R;
import com.example.assignment6.fragment.UserDetailsFragment;
import com.example.assignment6.fragment.UserListFragment;
import com.example.assignment6.modal.User;

public class MainActivity extends AppCompatActivity {
    private UserDetailsFragment userDetailsFragment;
    private UserListFragment userListFragment;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment();

    }
    public  void setFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        userListFragment=new UserListFragment();
        fragmentTransaction.add(R.id.user_list_fragment,userListFragment);
        userDetailsFragment=new UserDetailsFragment();
        fragmentTransaction.add(R.id.user_details_fragment,userDetailsFragment);
        fragmentTransaction.commit();
    }

    public void sendUserData(User user) {
     userDetailsFragment.showResult(user);
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
            linearLayout=findViewById(R.id.linear_layout_activity);
            if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            }else if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
                linearLayout.setOrientation(LinearLayout.VERTICAL);
            }


    }
}
