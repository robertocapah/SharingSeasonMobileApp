package com.shp.sharingseasonmobileapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shp.sharingseasonmobileapp.utils.Tools;


public class LoginActivity extends AppCompatActivity {

    private View parent_view;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        parent_view = findViewById(android.R.id.content);
        Tools.setSystemBarColor(this, R.color.blue_grey_900);

        ((View) findViewById(R.id.forgot_password)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Forgot Password", Snackbar.LENGTH_SHORT).show();
            }
        });
        ((View) findViewById(R.id.sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Sign Up", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    private boolean checkPermission() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("You need to allow access. . .");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && !ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)){
                    ActivityCompat.requestPermissions(LoginActivity.this,
                            new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    dialog.dismiss();

                }else{
                    ActivityCompat.requestPermissions(LoginActivity.this,
                            new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    dialog.dismiss();
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        int hasWriteExternalStoragePermission = ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int hasReadExternalStoragePermission = ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED
                || hasReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED){
            boolean checkPermission = checkPermission();
        }
    }
}
