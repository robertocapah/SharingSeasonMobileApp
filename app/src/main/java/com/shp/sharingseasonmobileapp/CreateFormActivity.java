package com.shp.sharingseasonmobileapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.shp.sharingseasonmobileapp.Adapter.AdapterListView;
import com.shp.sharingseasonmobileapp.Adapter.AdapterListViewApotek;
import com.shp.sharingseasonmobileapp.Common.Model.clsProfile;
import com.shp.sharingseasonmobileapp.Volley.VolleyResponseListener;
import com.shp.sharingseasonmobileapp.Volley.VolleyUtill;

import java.util.ArrayList;
import java.util.List;

public class CreateFormActivity extends AppCompatActivity {
    ListView listView;
    private static List<clsProfile> itemAdapterList = new ArrayList<>();
    AdapterListViewApotek adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);
        listView = (ListView)findViewById(R.id.lv_infoprogram);

        itemAdapterList.clear();

        clsProfile itemAdapter = new clsProfile();
        itemAdapter.setTxtId("1");
        itemAdapter.setTxtSubTittle("hahaha");
        itemAdapter.setTxtTittle("hhah"); //nama dokter substring(0,1)
        itemAdapter.setIntColor(R.color.purple_600);
        itemAdapter.setTxtImgName("s");;

        itemAdapterList.add(itemAdapter);
        adapter = new AdapterListViewApotek(getApplicationContext(), itemAdapterList);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setEmptyView((LinearLayout)findViewById(R.id.ln_emptyMain));


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
