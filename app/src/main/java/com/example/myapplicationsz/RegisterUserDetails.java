package com.example.myapplicationsz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUserDetails extends AppCompatActivity {
    EditText name, password, address , city;
    TextView PhoneNum;
    DatabaseReference mRef;
    Button Register;
    String phn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_details);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        address = (EditText) findViewById(R.id.Location);
        city = (EditText) findViewById(R.id.City);
        PhoneNum = (TextView) findViewById(R.id.phonenumber);
        Register = (Button) findViewById(R.id.Enter);



        Intent i = getIntent();
        phn = i.getStringExtra("Phone");

//        int a = 1000;
  //      phn = Integer.toString(a);

        PhoneNum.setText(phn);

        mRef = FirebaseDatabase.getInstance().getReference();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert("Do you hereby confirm that the details entered are correct , and want to register for the app ?");

            }
        });






    }

    private void alert(String message) {
        AlertDialog dialog = new AlertDialog.Builder(RegisterUserDetails.this).setTitle("Alert").setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                       registerdetailstodatabase();


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();






                    }
                }).create();
        dialog.show();
    }

    private void registerdetailstodatabase() {
        String a = "user";
        //int phones = Integer.parseInt(phn);

        String names = name.getText().toString();
        String passwords = password.getText().toString();
        String  addresses = address.getText().toString();
        String  cities = address.getText().toString();

        mRef.child("login").child(phn).child("username").setValue(phn);
        mRef.child("login").child(phn).child("as").setValue(a);
        mRef.child("login").child(phn).child("name").setValue(names);
        mRef.child("login").child(phn).child("password").setValue(passwords);
        mRef.child("login").child(phn).child("address").setValue(addresses);
        mRef.child("login").child(phn).child("city").setValue(cities);
       // mRef.child("login").child(phn).child("username").setValue(phn);
        //mRef.child("login").child(phn).child("username").setValue(phn);

        Intent intent = new Intent(RegisterUserDetails.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(RegisterUserDetails.this,RegisterUser.class);
        startActivity(intent);

    }


}