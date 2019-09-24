package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
TextView Assignment;
Thread splashScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Assignment=findViewById(R.id.Assignment);
        splashScreen= new Thread(){
            public void run(){
                try{
                    splashScreen.sleep(3000);
                    Intent i=new Intent(SplashScreenActivity.this,LoginActivity.class);
                    startActivity(i);
                }catch (Exception e){

                }
            }
        };
        splashScreen.start();
    }
}
