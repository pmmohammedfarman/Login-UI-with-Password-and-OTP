package com.example.myapplicationsz;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class AskPermissions extends AppCompatActivity {
    String[] PERMISSIONS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_permissions);

        PERMISSIONS = new String[]{


                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.INTERNET,

        };

        if (!hasPermissions(AskPermissions.this, PERMISSIONS)) {

            ActivityCompat.requestPermissions(AskPermissions.this, PERMISSIONS, 1);
        }
        else {
            Intent intents = new Intent(AskPermissions.this,MainActivity.class);
            startActivity(intents);
        }
    }

    private boolean hasPermissions(Context context, String... permissions) {


        if (context != null && PERMISSIONS != null) {

            for (String permission : PERMISSIONS) {

                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }

            }
        }


        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "SMS Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Read External Storage Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Write External Storage Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Write External Storage Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Write External Storage Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Media Location Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Media Location Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Internet Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Internet Permission is denied", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(AskPermissions.this,MainActivity.class);
            startActivity(intent);
            finish();


        }
    }
}
