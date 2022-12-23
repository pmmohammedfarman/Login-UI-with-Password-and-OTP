package com.example.myapplicationsz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {
    EditText pass,pass1;
    Button Reset;
    String passwords,phone;
    DatabaseReference reference,refer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent i = getIntent();
        phone = i.getStringExtra("PhoneNum");
        pass = (EditText) findViewById(R.id.password);
        pass1 = (EditText) findViewById(R.id.password2);
        Reset = (Button) findViewById(R.id.password3);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifypassword();


            }
        });

    }

    private void verifypassword() {
        String password1 = pass.getText().toString();
        String password2 = pass1.getText().toString();

        int n1 = password1.length();
        int n2 = password2.length();

        reference = FirebaseDatabase.getInstance().getReference();
        refer = reference.child("login");
        if ((n1>=6) && (n2>=6))
        {
            if (password1.equals(password2))
            {
                passwords = password1;
                alert("Confirm password?");
            }
            else {
                Toast.makeText(this, "Password and re-enter password fields must be same.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Please Enter minimum 6 characters for your password.", Toast.LENGTH_SHORT).show();
        }
    }

    private void alert(String message) {

        AlertDialog dialog = new AlertDialog.Builder(ChangePassword.this).setTitle("Alert").setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        changepassword();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                }).create();
        dialog.show();

    }

    private void changepassword() {
        refer.child(phone).child("password").setValue(passwords);
        Intent intent = new Intent(ChangePassword.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}