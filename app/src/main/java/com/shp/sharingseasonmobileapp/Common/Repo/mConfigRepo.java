package com.shp.sharingseasonmobileapp.Common.Repo;

import android.content.Context;


import com.shp.sharingseasonmobileapp.Common.Model.mConfig;
import com.shp.sharingseasonmobileapp.Database.DatabaseHelper;
import com.shp.sharingseasonmobileapp.Database.DatabaseManager;

import java.sql.SQLException;
import java.util.List;



public class mConfigRepo {
    DatabaseHelper helper;
    public String API_menu = "http://template.kalbe.com/abc";
    public String APIToken = "http://10.171.13.50:8013/";
    public String APIKLB = "http://api.karsalintasbuwana.com/";

    public mConfigRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    public Object findById(int value) throws SQLException {
        mConfig item = null;
        try{
            item = helper.getmConfigDao().queryForId(value);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    public List<?> findAll() throws SQLException {
        List<mConfig> items = null;
        try{
            items = helper.getmConfigDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    public void InsertDefaultmConfig() throws SQLException {
        mConfig data = new mConfig();
        data.setIntId(1);
        data.setTxtName("android:versionCode");
        data.setTxtValue("5");
        data.setTxtDefaultValue("5");
        data.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data);

        data = new mConfig();
        data.setIntId(2);
        data.setTxtName("API_menu");
        data.setTxtValue(API_menu);
        data.setTxtDefaultValue(API_menu);
        data.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data);

        data = new mConfig();
        data.setIntId(3);
        data.setTxtName("Domain Kalbe");
        data.setTxtValue("ONEKALBE.LOCAL");
        data.setTxtDefaultValue("ONEKALBE.LOCAL");
        data.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data);

        mConfig data6 = new mConfig();
        data6.setIntId(6);
        data6.setTxtName("Text Footer");
        data6.setTxtValue("Copyright &copy; KN IT 2018");
        data6.setTxtDefaultValue("Copyright &copy; KN IT 2018");
        data6.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data6);

        mConfig data7 = new mConfig();
        data7.setIntId(7);
        data7.setTxtName("application_name");
        data7.setTxtValue("SHP Sharing Season");
        data7.setTxtDefaultValue("SHP Sharing Season");
        data7.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data7);

        mConfig data8 = new mConfig();
        data8.setIntId(8);
        data8.setTxtName("Username");
        data8.setTxtValue("mochalatte-mae-stage");
        data8.setTxtDefaultValue("1234567890");
        data8.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data8);

        mConfig data9 = new mConfig();
        data9.setIntId(9);
        data9.setTxtName("Username");
        data9.setTxtValue("mochalatte-mae-stage");
        data9.setTxtDefaultValue("mochalatte-mae-stage");
        data9.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data9);

        mConfig data10 = new mConfig();
        data10.setIntId(10);
        data10.setTxtName("Password");
        data10.setTxtValue("1234567890");
        data10.setTxtDefaultValue("1234567890");
        data10.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data10);
    }
}
