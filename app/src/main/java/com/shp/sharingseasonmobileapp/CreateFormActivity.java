package com.shp.sharingseasonmobileapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shp.sharingseasonmobileapp.Adapter.AdapterListView;
import com.shp.sharingseasonmobileapp.Adapter.AdapterListViewApotek;
import com.shp.sharingseasonmobileapp.Common.Model.clsProfile;
import com.shp.sharingseasonmobileapp.Common.Model.mApotek;
import com.shp.sharingseasonmobileapp.Common.Repo.mApotekRepo;
import com.shp.sharingseasonmobileapp.Response.dataApotek.DataApotek;
import com.shp.sharingseasonmobileapp.Volley.VolleyResponseListener;
import com.shp.sharingseasonmobileapp.Volley.VolleyUtill;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateFormActivity extends AppCompatActivity {
    ListView listView;
    private static List<clsProfile> itemAdapterList = new ArrayList<>();
    AdapterListViewApotek adapter;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);
        listView = (ListView)findViewById(R.id.lv_infoprogram);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
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
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        DataApotek model = gson.fromJson(jsonObject.toString(), DataApotek.class);
                        boolean txtStatus = model.getResult().isStatus();
                        String txtMessage = model.getResult().getMessage();
                        String txtMethode_name = model.getResult().getMethodName();

                        if (txtStatus==true){
                            if (model.getData().getRecords()!=null){
                                if(model.getData().getRecords().size()>0){
                                    for (int i = 0; i < model.getData().getRecords().size(); i++){
                                        mApotek data = new mApotek();
                                        data.setIntApotekId(model.getData().getRecords().get(i).getCode());
                                        data.setTxtApotekName(model.getData().getRecords().get(i).getName());
                                        new mApotekRepo(getApplicationContext()).createOrUpdate(data);
                                    }
                                }

//                                List<mApotek>
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), txtMessage, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
