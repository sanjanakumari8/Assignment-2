package com.example.assignment_4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.assignment_4.R;

public class SplashScreenActivity extends AppCompatActivity {
    TextView tvSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvSplashScreen=findViewById(R.id.tv_splash_screen);

        //splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);
    }
}
