package com.shp.sharingseasonmobileapp.Common.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.shp.sharingseasonmobileapp.Common.Model.tResep;
import com.shp.sharingseasonmobileapp.Database.DatabaseHelper;
import com.shp.sharingseasonmobileapp.Database.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ASUS on 11/27/2018.
 */

public class tResepRepo implements crud {
    DatabaseHelper helper;
    public tResepRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }
    @Override
    public int create(Object item) throws SQLException {
        int index = -1;
        tResep object = (tResep) item;
        try {
            index = helper.getTResepDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) throws SQLException {
        int index = -1;
        tResep object = (tResep) item;
        try {
            Dao.CreateOrUpdateStatus status  = helper.getTResepDao().createOrUpdate(object);
            index = status.getNumLinesChanged();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int update(Object item) throws SQLException {
        int index = -1;
        tResep object = (tResep) item;
        try {
            index = helper.getTResepDao().update(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int delete(Object item) throws SQLException {
        int index = -1;
        tResep object = (tResep) item;
        try {
            index = helper.getTResepDao().delete(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public Object findById(int id) throws SQLException {
        tResep item = null;
        try{
            item = helper.getTResepDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<tResep> findAll() throws SQLException {
        List<tResep> items = null;
        try{
            items = helper.getTResepDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}
