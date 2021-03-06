package com.shp.sharingseasonmobileapp.Common.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.shp.sharingseasonmobileapp.Common.Model.mApotek;
import com.shp.sharingseasonmobileapp.Database.DatabaseHelper;
import com.shp.sharingseasonmobileapp.Database.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ASUS on 11/27/2018.
 */

public class mApotekRepo implements crud {
    DatabaseHelper helper;
    public mApotekRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }
    @Override
    public int create(Object item) throws SQLException {
        int index = -1;
        mApotek object = (mApotek) item;
        try {
            index = helper.getApotekDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) throws SQLException {
        int index = -1;
        mApotek object = (mApotek) item;
        try {
            Dao.CreateOrUpdateStatus status  = helper.getApotekDao().createOrUpdate(object);
            index = status.getNumLinesChanged();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int update(Object item) throws SQLException {
        int index = -1;
        mApotek object = (mApotek) item;
        try {
            index = helper.getApotekDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int delete(Object item) throws SQLException {
        int index = -1;
        mApotek object = (mApotek) item;
        try {
            index = helper.getApotekDao().update(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public Object findById(int id) throws SQLException {
        mApotek item = null;
        try{
            item = helper.getApotekDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<?> findAll() throws SQLException {
        List<mApotek> items = null;
        try{
            items = helper.getApotekDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}
