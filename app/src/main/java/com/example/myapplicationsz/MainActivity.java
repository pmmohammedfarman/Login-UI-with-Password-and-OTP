package com.example.myapplicationsz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText username,
            password;
    private Button login;
    Switch active;
    TextView Forgotpassword,RegisterUser;
    int counter = 0;
    int a=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        active = findViewById(R.id.active);
        Forgotpassword = findViewById(R.id.ForgotPassword);
        RegisterUser = findViewById(R.id.RegisterUser);

        Forgotpassword.setPaintFlags(Forgotpassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        RegisterUser.setPaintFlags(RegisterUser.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ForgotPassword();

            }
        });

        RegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = username.getText().toString();
                        String input2 = password.getText().toString();

                        if (dataSnapshot.child(input1).exists()) {
                            if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                if (active.isChecked()) {
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(MainActivity.this, true);
                                        preferences.setDataAs(MainActivity.this, "admin");
                                        startActivity(new Intent(MainActivity.this, AdminActivity.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")){
                                        preferences.setDataLogin(MainActivity.this, true);
                                        preferences.setDataAs(MainActivity.this, "user");
                                        startActivity(new Intent(MainActivity.this, UserActivity.class));
                                    }
                                } else {
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(MainActivity.this, false);
                                        startActivity(new Intent(MainActivity.this, AdminActivity.class));

                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")){
                                        preferences.setDataLogin(MainActivity.this, false);
                                        startActivity(new Intent(MainActivity.this, UserActivity.class));
                                    }
                                }

                            } else {
                                Toast.makeText(MainActivity.this, "Kata sandi salah", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Data belum terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void ForgotPassword()
    {
        Intent i = new Intent(this,ForgotPassword.class);
        startActivity(i);
        finish();
    }
    public void RegisterUser()
    {
        Intent i1 = new Intent(this,RegisterUser.class);
        startActivity(i1);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(this, AdminActivity.class));
                finish();
            } else {
                startActivity(new Intent(this, UserActivity.class));
                finish();
            }
        }
    }


    @Override
    public void onBackPressed()
    {
        counter++;
        if(counter == 1)
        {
            alert("Do you want to exit the application??");


        }
        else
        {
            if (counter == 2)
            {
                super.onBackPressed();
                finish();
            }
        }

    }
    public void alert(String message)
    {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setTitle("Alert").setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        onBackPressed();


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        counter=0;
                    }
                }).create();
        dialog.show();
    }

/*    public void calc(int a)
    {
        if (a==0)
        {
            counter=-1;
            onBackPressed();
        }

    }*/
}