package com.shp.sharingseasonmobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.shp.sharingseasonmobileapp.Adapter.AdapterListView;
import com.shp.sharingseasonmobileapp.Common.Model.clsProfile;

import java.util.ArrayList;
import java.util.List;

public class ViewFormActivity extends AppCompatActivity {

    ListView listView;
    private static List<clsProfile> itemAdapterList = new ArrayList<>();
    AdapterListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_form);
        listView = (ListView)findViewById(R.id.lv_infoprogram);

        itemAdapterList.clear();

        clsProfile itemAdapter = new clsProfile();
        itemAdapter.setTxtId("1");
        itemAdapter.setTxtSubTittle("hahaha");
        itemAdapter.setTxtTittle("hhah"); //nama dokter substring(0,1)
        itemAdapter.setIntColor(R.color.purple_600);
        itemAdapter.setTxtImgName("s");;

        itemAdapterList.add(itemAdapter);
        adapter = new AdapterListView(getApplicationContext(), itemAdapterList);
        listView.setAdapter(adapter);
        listView.setDivider(null);

        adapter.setmOnImageDeleteClickListener(new AdapterListView.onImageDeleteClickListener() {
            @Override
            public void onItemClick(View view, clsProfile obj, int position) {
                //delete
                Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemEditClickListener(new AdapterListView.onItemEditClickListener() {
            @Override
            public void onItemClick(View view, clsProfile obj, int position) {
                //edit
                Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
