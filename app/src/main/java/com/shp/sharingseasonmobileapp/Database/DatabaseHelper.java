package com.shp.sharingseasonmobileapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.shp.sharingseasonmobileapp.Common.Model.clsToken;
import com.shp.sharingseasonmobileapp.Common.Model.mApotek;
import com.shp.sharingseasonmobileapp.Common.Model.mConfig;
import com.shp.sharingseasonmobileapp.Common.Model.mUserLogin;
import com.shp.sharingseasonmobileapp.Common.Model.tResep;

import java.sql.SQLException;

/**
 * Created by ASUS on 11/27/2018.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "DbSHP.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 3;
    // the DAO object we use to access the mConfig table
    protected Dao<mConfig, Integer> mConfigDao;
    protected Dao<mUserLogin, Integer> mUserLoginsDao;
    protected Dao<mApotek, Integer> mApotekDao;
    protected Dao<tResep, Integer> tResepsDao;
    protected Dao<clsToken, Integer> tokenDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, mConfig.class);
            TableUtils.createTableIfNotExists(connectionSource, mApotek.class);
            TableUtils.createTableIfNotExists(connectionSource, tResep.class);

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
            TableUtils.dropTable(connectionSource, mConfig.class, true);
            TableUtils.dropTable(connectionSource, mApotek.class, true);
            TableUtils.dropTable(connectionSource, tResep.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<mConfig, Integer> getmConfigDao() throws SQLException {
        if (mConfigDao == null) {
            mConfigDao = getDao(mConfig.class);
            mConfigDao.setObjectCache(false);
        }
        return mConfigDao;
    }

    public Dao<mUserLogin, Integer> getmUserLoginsDao() throws SQLException {
        if (mUserLoginsDao == null) {
            mUserLoginsDao = getDao(mUserLogin.class);
        }
        return mUserLoginsDao;
    }

    public Dao<mApotek, Integer> getApotekDao() throws SQLException {
        if (mApotekDao == null) {
            mApotekDao = getDao(mApotek.class);
        }
        return mApotekDao;
    }

    public Dao<clsToken, Integer> getTokenDao() throws SQLException {
        if (tokenDao == null) {
            tokenDao = getDao(clsToken.class);
        }
        return tokenDao;
    }

    public Dao<tResep, Integer> getTResepDao() throws SQLException {
        if (tResepsDao == null) {
            tResepsDao = getDao(tResep.class);
        }
        return tResepsDao;
    }

    @Override
    public void close() {
        mConfigDao = null;
        mUserLoginsDao = null;
        mApotekDao = null;
        tResepsDao = null;
        tokenDao = null;
    }
}
