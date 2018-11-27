package com.shp.sharingseasonmobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.shp.sharingseasonmobileapp.Common.clsHelper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    LinearLayout ln_create, ln_view, ln_copyDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ln_create = (LinearLayout)findViewById(R.id.ln_create);
        ln_view = (LinearLayout)findViewById(R.id.ln_view);
        ln_copyDb = (LinearLayout) findViewById(R.id.ln_copyDb);
        ln_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateFormActivity.class);
                startActivity(intent);
            }
        });

        ln_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewFormActivity.class);
                startActivity(intent);
            }
        });
        ln_copyDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new clsHelper().copydb(getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
