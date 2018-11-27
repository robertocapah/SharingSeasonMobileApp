package com.shp.sharingseasonmobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.shp.sharingseasonmobileapp.Volley.VolleyResponseListener;
import com.shp.sharingseasonmobileapp.Volley.VolleyUtill;

public class CreateFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);
        getDataApotek();
    }

    public void getDataApotek(){
        String strLinkAPI = "http://10.171.13.50:8013/api/get_data_apotek";
        new VolleyUtill().volleyLoginGet(CreateFormActivity.this, strLinkAPI, "", "Getting data, please wait !", new VolleyResponseListener() {
            @Override
            public void onError(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG);
            }

            @Override
            public void onResponse(String response, Boolean status, String strErrorMsg) {
                if (response != null) {

                }
            }
        });
    }
}
