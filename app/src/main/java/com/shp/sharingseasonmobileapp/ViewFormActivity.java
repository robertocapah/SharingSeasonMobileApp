package com.shp.sharingseasonmobileapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shp.sharingseasonmobileapp.Adapter.AdapterListView;
import com.shp.sharingseasonmobileapp.Common.Model.clsProfile;
import com.shp.sharingseasonmobileapp.Common.Model.tResep;
import com.shp.sharingseasonmobileapp.Common.Repo.tResepRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewFormActivity extends AppCompatActivity {

    ListView listView;
    private static List<clsProfile> itemAdapterList = new ArrayList<>();
    AdapterListView adapter;
    Dialog dialogCustom;
    String txtNoDoc;
    FloatingActionButton fab;
    tResep dataResep;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_form);
        listView = (ListView)findViewById(R.id.lv_infoprogram);
        fab = (FloatingActionButton)findViewById(R.id.fab_maintenance);

        itemAdapterList.clear();


        List<tResep> reseps = new ArrayList<>();
        try {
            reseps = new tResepRepo(getApplicationContext()).findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (tResep r :
                reseps) {
            clsProfile itemAdapter = new clsProfile();
            itemAdapter.setTxtId(String.valueOf(r.getIntResepId()));
            itemAdapter.setTxtSubTittle(r.getTxtResep());
//                                    itemAdapter.setTxtTittle("hhah"); //nama dokter substring(0,1)
            itemAdapter.setIntColor(R.color.purple_600);
            itemAdapter.setTxtImgName((r.getTxtResep().substring(0,1)).toUpperCase());;

            itemAdapterList.add(itemAdapter);
        }
        adapter = new AdapterListView(getApplicationContext(), itemAdapterList);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setEmptyView((LinearLayout)findViewById(R.id.ln_emptyMain));

        adapter.setmOnImageDeleteClickListener(new AdapterListView.onImageDeleteClickListener() {
            @Override
            public void onItemClick(View view, clsProfile obj, int position) {
                //delete
                Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();
                int id = Integer.parseInt(obj.getTxtId());
                try {
                    tResep rs = (tResep) new tResepRepo(getApplicationContext()).findById(id);
                    if(rs!=null){
                        new tResepRepo(getApplicationContext()).delete(rs);
                        itemAdapterList.remove(obj);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });

        adapter.setOnItemEditClickListener(new AdapterListView.onItemEditClickListener() {
            @Override
            public void onItemClick(View view, clsProfile obj, int position) {
                //edit
                Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_SHORT).show();
                int id = Integer.parseInt(obj.getTxtId());
                try {
                    isEdit = true;
                    dataResep = (tResep) new tResepRepo(getApplicationContext()).findById(id);
                    showCustomDialog();
//                    if(rs!=null){
//                        rs.setTxtResep(obj.getTxtSubTittle());
//                        new tResepRepo(getApplicationContext()).createOrUpdate(rs);
//                        List<tResep> reseps = new tResepRepo(getApplicationContext()).findAll();
//                        if (itemAdapterList!=null){
//                            itemAdapterList.clear();
//                            for (tResep r :
//                                    reseps) {
//                                clsProfile itemAdapter = new clsProfile();
//                                itemAdapter.setTxtId(String.valueOf(r.getIntResepId()));
//                                itemAdapter.setTxtSubTittle(r.getTxtResep());
////                                    itemAdapter.setTxtTittle("hhah"); //nama dokter substring(0,1)
//                                itemAdapter.setIntColor(R.color.purple_600);
//                                itemAdapter.setTxtImgName((r.getTxtResep().substring(0,1)).toUpperCase());;
//
//                                itemAdapterList.add(itemAdapter);
//                                adapter.notifyDataSetChanged();
//                                listView.setAdapter(adapter);
//                            }
//                        }
//                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit = false;
                showCustomDialog();
            }
        });
    }


    private void showCustomDialog() {
        dialogCustom = new Dialog(ViewFormActivity.this);
        dialogCustom.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialogCustom.setContentView(R.layout.dialog_custom);
        dialogCustom.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogCustom.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView tv_title = (TextView)dialogCustom.findViewById(R.id.cd_title);
        TextView tv_subtitle = (TextView)dialogCustom.findViewById(R.id.cd_subtitle);
        tv_title.setText("Create Order");
        tv_subtitle.setText("Please fill number document");
        final EditText et_userName = (EditText) dialogCustom.findViewById(R.id.et_int_number_realisasi);
        if (isEdit){
            et_userName.setText(dataResep.getTxtResep());
        }else {
            et_userName.setText("");
        }
        et_userName.setHint("AA.2018.07");
//        et_userName.setInputType(InputType.TYPE_CLASS_TEXT);
        char[] chars = {'.'};
        etCapsTextWatcherNoSpaceAtFirst(et_userName, null, chars);
        ((AppCompatButton) dialogCustom.findViewById(R.id.btn_cancel_realisasi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCustom.dismiss();
            }
        });

        ((AppCompatButton) dialogCustom.findViewById(R.id.btn_submit_realisasi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNoDoc = et_userName.getText().toString().trim();
                if (txtNoDoc.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill number document...", Toast.LENGTH_SHORT).show();
//                    ToastCustom.showToasty(getApplicationContext(),"Please fill number document...",4);
                } else {
                    saveData(txtNoDoc);
                    dialogCustom.dismiss();
                }
            }
        });

        dialogCustom.show();
        dialogCustom.getWindow().setAttributes(lp);
    }

    public void saveData(final String txtRes){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ViewFormActivity.this);

        builder.setTitle("Create Order");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                tResep data = new tResep();
                if (isEdit){
                    data = dataResep;
                    data.setTxtResep(txtRes
                    );
                }else {
                    data.setTxtResep(txtRes);
                }

                try {
                    new tResepRepo(getApplicationContext()).createOrUpdate(data);
                    List<tResep> reseps = new tResepRepo(getApplicationContext()).findAll();
                    if (itemAdapterList!=null){
                        itemAdapterList.clear();
                        for (tResep r :
                                reseps) {
                            clsProfile itemAdapter = new clsProfile();
                            itemAdapter.setTxtId(String.valueOf(r.getIntResepId()));
                            itemAdapter.setTxtSubTittle(r.getTxtResep());
//                                    itemAdapter.setTxtTittle("hhah"); //nama dokter substring(0,1)
                            itemAdapter.setIntColor(R.color.purple_600);
                            itemAdapter.setTxtImgName((r.getTxtResep().substring(0,1)).toUpperCase());;

                            itemAdapterList.add(itemAdapter);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        android.app.AlertDialog alert = builder.create();
        alert.show();
    }


    public void etCapsTextWatcherNoSpaceAtFirst(final EditText editText, final Integer length, final char[] chars){
        editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        editText.addTextChangedListener(new TextWatcher() {
            int b = 0;
            @Override
            public void afterTextChanged(Editable editable) {
                String originalText = editable.toString();
                int originalTextLength = originalText.length();
                int textSelection = editText.getSelectionStart();
                int currentSelection;
                if (length!= null){
                    if (textSelection > (length + 1) ){
                        currentSelection = length + 1;
                    } else {
                        currentSelection = editText.getSelectionStart() ;
                    }
                } else {
                    currentSelection = editText.getSelectionStart() ;
                }

                // Create the filtered text
                StringBuilder sb = new StringBuilder();
                boolean hasChanged = false;
//                char[] chars = {'.', ','};
                boolean a = false;
                for (int j = 0 ; j < originalTextLength; j++){
                    char currentChar = originalText.charAt(j);
                    if (currentChar == ' '){
                        b++;
                    } else {
                        break;
                    }
                }
                for (int i = 0; i < originalTextLength; i++) {
                    char currentChar = originalText.charAt(i);
                    if (isAllowed(currentChar, i) || isThereChar(chars, currentChar)) {
                        if (length != null){
                            if (i < length){
                                sb.append(currentChar);
                            } else {
                                hasChanged = true;
                                if (currentSelection >= i) {
                                    currentSelection--;
                                }
                            }
                        }else {
                            sb.append(currentChar);
                        }
                    } else {
                        hasChanged = true;
                        if (currentSelection >= i) {
                            currentSelection--;
                        }
                    }
                }

                // If we filtered something, update the text and the cursor location
                if (hasChanged) {
                    String newText = sb.toString();
                    editText.setText(newText);
                    editText.setSelection(currentSelection);
                }
            }

            private boolean isAllowed(char c, int position) {
                // TODO: Add the filter logic here
                if ((position >= 0 && position <= b) &&  Character.isSpaceChar(c)){
                    return false;
                } else {
                    return Character.isLetterOrDigit(c) || Character.isSpaceChar(c) ;
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do Nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

        });
    }

    private static boolean isThereChar(char[] chaArray, char chr){
        boolean bool = false;
        for(int i=0; i < chaArray.length; i++) {
            if(chr==chaArray[i]){
                bool = true;
            }
        }
        return bool;
    }
}
