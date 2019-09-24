package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class OtpGeneratorActivity extends AppCompatActivity {
TextView otpTv2,otpTv3;
ImageButton otpBack;
EditText edit1,edit2,edit3,edit4;
Button otpButton,otpButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_generator);
        otpBack=findViewById(R.id.otpBack);
        otpTv2=findViewById(R.id.otpTv2);
        otpTv3=findViewById(R.id.otpTv3);
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        edit3=findViewById(R.id.edit3);
        edit4=findViewById(R.id.edit4);
        otpButton=findViewById(R.id.otpButton);
        otpButton1=findViewById(R.id.otpButton1);
        edit1.requestFocus();
        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edit4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit1.setText("");
                edit2.setText("");
                edit3.setText("");
                edit4.setText("");
                edit1.requestFocus();
            }
        });
        otpButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OtpGeneratorActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        otpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back1=new Intent(OtpGeneratorActivity.this,LoginActivity.class);
                startActivity(back1);
                finish();
            }
        });
    }
}
