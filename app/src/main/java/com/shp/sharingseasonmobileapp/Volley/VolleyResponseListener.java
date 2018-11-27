package com.shp.sharingseasonmobileapp.Volley;

/**
 * Created by Dewi Oktaviani on 11/27/2018.
 */

public interface VolleyResponseListener {
    void onError(String message);

    void onResponse(String response, Boolean status, String strErrorMsg);
}
