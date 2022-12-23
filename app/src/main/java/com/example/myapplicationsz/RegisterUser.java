package com.example.myapplicationsz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterUser extends AppCompatActivity {
    EditText PhoneNum;
    Button CheckUsers;
    DatabaseReference mRef;

    String phn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        PhoneNum = (EditText) findViewById(R.id.phonenumbers);
        CheckUsers = (Button) findViewById(R.id.Enters);


       CheckUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyuser();
            }
        });
    }

    private void verifyuser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference reference1 = reference.child("login");
        phn = PhoneNum.getText().toString();

        //Query checkUser = reference1.orderByChild("username").equalTo(phn);
        System.out.println(phn);


        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(phn).exists())
                {
                    PhoneNum.setError("User Already Exists");
                    System.out.println(phn);
                }
                else {

                    registeruserdetails(phn);
                    System.out.println(phn);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void registeruserdetails(String aaa){
        Intent intent = new Intent(RegisterUser.this,RegisterUserDetails.class);
        intent.putExtra("Phone",aaa);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(RegisterUser.this,MainActivity.class);
        startActivity(intent);

    }
}