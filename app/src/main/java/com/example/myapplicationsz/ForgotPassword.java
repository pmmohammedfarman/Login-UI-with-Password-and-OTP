package com.example.myapplicationsz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {
    EditText username;
    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        username = findViewById(R.id.username);
        phone = username.getText().toString();
        Intent intent = new Intent(ForgotPassword.this,OTP.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ForgotPassword.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}