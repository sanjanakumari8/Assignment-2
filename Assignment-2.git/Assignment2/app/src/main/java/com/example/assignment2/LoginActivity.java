package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.modal.Register;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
TextView loginTop,loginBottom;
EditText loginEmail;
EditText loginPassword;
Button loginButton;
 public static ArrayList<Register> userDetails=new ArrayList<Register>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDetails.add(new Register("sajni123@gmail.com","Abcd@1234"));
        loginBottom=findViewById(R.id.loginBottomText);
        loginTop=findViewById(R.id.loginHeading);
        loginEmail=findViewById(R.id.loginEmail);
        loginPassword=findViewById(R.id.loginPassword);
        loginButton=findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputLoginEmail=loginEmail.getText().toString();
                String  inputLoginPassword=loginPassword.getText().toString();
                for (int i = 0; i < userDetails.size(); i++) {
                    if (userDetails.get(i).getEmail().equals(inputLoginEmail) && userDetails.get(i).getPassword().equals(inputLoginPassword)) {
                        Intent back =new Intent(LoginActivity.this,OtpGeneratorActivity.class);
                        startActivity(back);
                        finish();
                    }else{
                        Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"Either username or password is wrong!!",Snackbar.LENGTH_LONG);
                        snackbar.show();

                    }
                }
            }
        });
        String text=getResources().getString(R.string.login_bottom_text);
        SpannableString span=new SpannableString(text);
        ClickableSpan cs =new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        };
        ForegroundColorSpan grey=new ForegroundColorSpan(Color.BLACK);
        ForegroundColorSpan green=new ForegroundColorSpan(Color.GREEN);
        span.setSpan(grey,0,21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(green,22,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(cs,22,30,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginBottom.setText(span);
        loginBottom.setMovementMethod(LinkMovementMethod.getInstance());
            }
}
