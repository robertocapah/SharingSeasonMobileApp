package com.shp.sharingseasonmobileapp.Common.Repo;

import com.shp.sharingseasonmobileapp.Common.Model.mApotek;
import com.shp.sharingseasonmobileapp.Database.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ASUS on 11/27/2018.
 */

public class mApotekRepo implements crud {
    DatabaseHelper helper;
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
        return 0;
    }

    @Override
    public int update(Object item) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Object item) throws SQLException {
        return 0;
    }

    @Override
    public Object findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<?> findAll() throws SQLException {
        return null;
    }
}
