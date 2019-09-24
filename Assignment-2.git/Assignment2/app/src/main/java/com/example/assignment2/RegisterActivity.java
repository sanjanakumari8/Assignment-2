package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment2.modal.Register;
import com.google.android.material.snackbar.Snackbar;

import static android.view.View.Z;
import static com.example.assignment2.LoginActivity.userDetails;

public class RegisterActivity extends AppCompatActivity {
TextView tv1,tv2,tv3;
ImageButton imageBack;
ImageView imageView;
EditText fullName,gender,dateOfBirth,userType,email,password,occupation;
Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageBack=findViewById(R.id.imageBack);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        fullName=findViewById(R.id.fullName);
        imageView=findViewById(R.id.imageView);
        gender=findViewById(R.id.gender);
        dateOfBirth=findViewById(R.id.dateOfBirth);
        userType=findViewById(R.id.userType);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        occupation=findViewById(R.id.occupation);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputFullName=fullName.getText().toString();
                String inputGender=gender.getText().toString();
                String inputDateOfBirth=dateOfBirth.getText().toString();
                String inputUserType=userType.getText().toString();
                String inputEmail=email.getText().toString();
                String validEmail="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String inputPassword=password.getText().toString();
                String validPassword="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})\n";
                String inputOccupation=occupation.getText().toString();
                if(inputFullName.isEmpty()){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter name please",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if(inputGender.isEmpty()){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter gender please",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if(!inputGender.equals("Male") && (!inputGender.equals("Female"))){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter proper gender",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(inputDateOfBirth.isEmpty()){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter date of birth please",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if(inputUserType.isEmpty()){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter user type please",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if(inputOccupation.isEmpty()){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter occupation  please",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if(inputEmail.isEmpty()){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter email",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if(!inputEmail.trim().matches(validEmail)){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter proper email address",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if(inputPassword.isEmpty()){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter password",Snackbar.LENGTH_LONG);
                    snackbar.show();

                }else if(inputPassword.trim().matches(validPassword)){
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"enter valid password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else{
                    userDetails.add(new Register(inputEmail,inputPassword));
                    startActivity(new Intent(RegisterActivity.this,OtpGeneratorActivity.class));
                    Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(),"added successfully",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    finish();
                }
            }
        });

        String text="Sign up with different account?";
        SpannableString span=new SpannableString(text);
        ClickableSpan cs =new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        ForegroundColorSpan green=new ForegroundColorSpan(Color.GREEN);
        span.setSpan(green,0,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(cs,0,31,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv3.setText(span);
        tv3.setMovementMethod(LinkMovementMethod.getInstance());

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(back);
                finish();
            }
        });

    }

}
