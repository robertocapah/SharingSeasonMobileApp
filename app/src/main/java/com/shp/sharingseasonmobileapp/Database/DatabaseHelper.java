package com.shp.sharingseasonmobileapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.shp.sharingseasonmobileapp.Common.Model.mConfigData;

import java.sql.SQLException;

/**
 * Created by ASUS on 11/27/2018.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "DbSHP.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;
    // the DAO object we use to access the mConfigData table
    protected Dao<mConfigData, Integer> mConfigDao;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, mConfigData.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
//            dao = getLoginDao();

//            if (oldVersion < 2) {
//                dao.executeRaw("ALTER TABLE `clsLogin` ADD COLUMN txtRefreshToken TEXT;");
//            }
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, mConfigData.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Dao<mConfigData, Integer> getmConfigDao() throws SQLException {
        if (mConfigDao == null) {
            mConfigDao = getDao(mConfigData.class);
            mConfigDao.setObjectCache(false);
        }
        return mConfigDao;
    }

    @Override
    public void close() {
        mConfigDao = null;
    }
}
