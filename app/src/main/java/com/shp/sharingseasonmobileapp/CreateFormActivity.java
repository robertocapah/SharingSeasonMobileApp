package com.shp.sharingseasonmobileapp;

import android.app.Dialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shp.sharingseasonmobileapp.Adapter.AdapterListViewApotek;
import com.shp.sharingseasonmobileapp.Common.Model.clsProfile;
import com.shp.sharingseasonmobileapp.Common.Model.mApotek;
import com.shp.sharingseasonmobileapp.Common.Repo.mApotekRepo;
import com.shp.sharingseasonmobileapp.Response.Apotek;
import com.shp.sharingseasonmobileapp.Volley.VolleyResponseListener;
import com.shp.sharingseasonmobileapp.Volley.VolleyUtill;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);

        listView = (ListView)findViewById(R.id.lv_infoprogram);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        itemAdapterList.clear();


        adapter = new AdapterListViewApotek(getApplicationContext(), itemAdapterList);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setEmptyView((LinearLayout)findViewById(R.id.ln_emptyMain));
        getDataApotek();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefresh.setRefreshing(false);
                getDataApotek();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clsProfile p = (clsProfile) adapter.getItem(position);
                showCustomDialog(p);
            }
        });
    }
    private void showCustomDialog(final clsProfile p) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_light);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.title)).setText(p.getTxtId());
        ((AppCompatButton) dialog.findViewById(R.id.bt_apotek)).setText(p.getTxtSubTittle());
        final LinearLayout lnProgress1 = (LinearLayout) dialog.findViewById(R.id.ln_progress1);
        final LinearLayout lnProgress2 = (LinearLayout) dialog.findViewById(R.id.ln_progress2);
        final LinearLayout lnKeterangan = (LinearLayout) dialog.findViewById(R.id.lnKeterangan);
        final TextView tvKeteragan = (TextView) dialog.findViewById(R.id.tvDescription);

//        ((CircleImageView) dialog.findViewById(R.id.image)).setImageResource(p.image);

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.bt_apotek)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lnProgress1.getVisibility() == View.VISIBLE){
                    lnProgress1.setVisibility(View.GONE);
                    lnProgress2.setVisibility(View.GONE);
                    lnKeterangan.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), p.getTxtSubTittle()+" Hide", Toast.LENGTH_SHORT).show();
                }else{
                    lnProgress1.setVisibility(View.VISIBLE);
                    lnProgress2.setVisibility(View.VISIBLE);
                    lnKeterangan.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), p.getTxtSubTittle()+ " Show", Toast.LENGTH_SHORT).show();
                }
            }
        });

        String txtLorem = getResources().getString(R.string.long_lorem_ipsum_2);
        tvKeteragan.setText(txtLorem);

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public void getDataApotek(){
//        String strLinkAPI = "http://10.171.13.50:8013/api/get_data_apotek";
        String strLinkAPI = "http://api.karsalintasbuwana.com/mae/apotek";
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
                        Apotek model = gson.fromJson(jsonObject.toString(), Apotek.class);
                        boolean txtStatus = model.isStatus();
                        String txtMessage = model.getMessage();
//                        String txtMethode_name = model.getMethodName();

                        if (txtStatus==true){
                            if (model.getData()!=null){
                                if(model.getData().size()>0){
                                    for (int i = 0; i < model.getData().size(); i++){
                                        mApotek data = new mApotek();
                                        data.setIntApotekId(model.getData().get(i).getCode());
                                        data.setTxtApotekName(model.getData().get(i).getName());
                                        new mApotekRepo(getApplicationContext()).createOrUpdate(data);
                                    }
                                }

                                List<mApotek> data = (List<mApotek>) new mApotekRepo(getApplicationContext()).findAll();
                                for (int i = 0; i < data.size(); i++){
                                    clsProfile itemAdapter = new clsProfile();
                                    itemAdapter.setTxtId(data.get(i).getIntApotekId());
                                    itemAdapter.setTxtSubTittle(data.get(i).getTxtApotekName());
//                                    itemAdapter.setTxtTittle("hhah"); //nama dokter substring(0,1)
                                    itemAdapter.setIntColor(R.color.purple_600);
                                    itemAdapter.setTxtImgName((data.get(i).getTxtApotekName().substring(0,1)).toUpperCase());;

                                    itemAdapterList.add(itemAdapter);
                                    adapter.notifyDataSetChanged();
                                    listView.setAdapter(adapter);
                                }
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
