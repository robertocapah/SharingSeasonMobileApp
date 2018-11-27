package com.shp.sharingseasonmobileapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.shp.sharingseasonmobileapp.Common.Model.mConfig;
import com.shp.sharingseasonmobileapp.Common.Repo.mConfigRepo;
import com.shp.sharingseasonmobileapp.Database.DatabaseManager;
import com.shp.sharingseasonmobileapp.Volley.VolleyResponseListener;
import com.shp.sharingseasonmobileapp.Volley.VolleyUtill;
import com.shp.sharingseasonmobileapp.utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class LoginActivity extends AppCompatActivity {

    private View parent_view;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    TextInputEditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.init(getApplicationContext());
        try {
            new mConfigRepo(getApplicationContext()).InsertDefaultmConfig();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        etUsername = (TextInputEditText) findViewById(R.id.etUsername);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);
        parent_view = findViewById(android.R.id.content);
        Tools.setSystemBarColor(this, R.color.blue_grey_900);

        ((View) findViewById(R.id.forgot_password)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Forgot Password", Snackbar.LENGTH_SHORT).show();
            }
        });
        ((View) findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= etUsername.getText().toString();
                String pas = etPassword.getText().toString();
                login(user,pas);
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
    public void login(String username,String password){
        String strLinkAPI = "http://10.171.13.50:8013/api/user_login";
        JSONObject userData = new JSONObject();
        JSONObject resJson = new JSONObject();
        try {
            userData.put("username",username);
            userData.put("password",password);
            resJson.put("data", userData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = resJson.toString();
        new VolleyUtill().volleyLogin(LoginActivity.this, strLinkAPI, mRequestBody, "Checking in please wait !", new VolleyResponseListener() {
            @Override
            public void onError(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG);
            }

            @Override
            public void onResponse(String response, Boolean status, String strErrorMsg) {
                if (response != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
