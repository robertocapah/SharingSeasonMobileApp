package com.shp.sharingseasonmobileapp.Common.Helper;

import android.content.Context;

import com.shp.sharingseasonmobileapp.Common.Repo.mConfigRepo;

/**
 * Created by ASUS on 1/10/2019.
 */

public class clsHardCode {
    Context context;
    public String linkToken = new mConfigRepo(context).APIToken + "token";
}
