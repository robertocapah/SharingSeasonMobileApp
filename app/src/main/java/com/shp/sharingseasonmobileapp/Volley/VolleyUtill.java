package com.shp.sharingseasonmobileapp.Volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shp.sharingseasonmobileapp.Common.Model.mConfig;
import com.shp.sharingseasonmobileapp.Common.Repo.mConfigRepo;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dewi Oktaviani on 11/27/2018.
 */

public class VolleyUtill {

    public void volleyLogin(final Context context, String strLinkAPI, final String mRequestBody, String progressBarType, final VolleyResponseListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final String[] body = new String[1];
        final String[] message = new String[1];
        final ProgressDialog Dialog = new ProgressDialog(context);
        Dialog.setMessage(progressBarType);
        Dialog.setCancelable(false);
        Dialog.show();

        final ProgressDialog finalDialog = Dialog;
        final ProgressDialog finalDialog1 = Dialog;

        final mConfigRepo configRepo = new mConfigRepo(context);
        try {
            mConfig configDataClient = (mConfig) configRepo.findById(4);
//            clientId = configDataClient.getTxtDefaultValue().toString();
//            dataToken = getDataToken(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.POST, strLinkAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = false;
                String errorMessage = null;
                listener.onResponse(response, status, errorMessage);
                finalDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                String strLinkAPI = new clsHardCode().linkToken;
//                final String refresh_token = dataToken.get(0).txtRefreshToken;
                NetworkResponse networkResponse = error.networkResponse;
                String msg = "";
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    // HTTP Status Code: 401 Unauthorized
                    try {
                        // body for value error response
                        body[0] = new String(error.networkResponse.data,"UTF-8");
                        JSONObject jsonObject = new JSONObject(body[0]);
                        message[0] = jsonObject.getString("Message");
                        //Toast.makeText(context, "Error 401, " + message[0], Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else  if (error instanceof NetworkError) {
                    msg = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    msg = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    msg = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    msg = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    msg = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    msg = "Connection TimeOut! Please check your internet connection.";
                } else {
                    msg = "Error 500, Server Error";
                }

                if (msg!=null||!msg.equals("")){
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//                    ToastCustom.showToasty(context,msg,4);
                    finalDialog1.dismiss();
                }
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                dataToken = getDataToken(context);
//                access_token = dataToken.get(0).getTxtUserToken();
                HashMap<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer " + access_token);

                return headers;
            }
        };
        request.setRetryPolicy(new
                DefaultRetryPolicy(60000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }
    public void volleyDownloadDataKLB(final Context context, String strLinkAPI, String progressBarType, final VolleyResponseListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final String[] body = new String[1];
        final String[] message = new String[1];
        final ProgressDialog Dialog = new ProgressDialog(context);
        Dialog.setMessage(progressBarType);
        Dialog.setCancelable(false);
        Dialog.show();

        final ProgressDialog finalDialog = Dialog;
        final ProgressDialog finalDialog1 = Dialog;

        StringRequest request = new StringRequest(Request.Method.GET, strLinkAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = false;
                String errorMessage = null;
                listener.onResponse(response, status, errorMessage);
                finalDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String msg = "";
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    // HTTP Status Code: 401 Unauthorized
                    try {
                        // body for value error response
                        body[0] = new String(error.networkResponse.data,"UTF-8");
                        JSONObject jsonObject = new JSONObject(body[0]);
                        message[0] = jsonObject.getString("Message");
                        Toast.makeText(context, "Error 401, " + message[0], Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finalDialog1.dismiss();
                } else  if (error instanceof NetworkError) {
                    msg = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    msg = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    msg = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    msg = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    msg = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    msg = "Connection TimeOut! Please check your internet connection.";
                } else {
                    msg = "Error 500, Server Error";
                }

                if (msg!=null||!msg.equals("")){
//                    ToastCustom.showToasty(context,msg,4);
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    finalDialog1.dismiss();
                }
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                mConfigRepo configRepo = new mConfigRepo(context);
                mConfig UserName = null;
                mConfig Passoword = null;
                try {
                    UserName = (mConfig) configRepo.findById(9);
                    Passoword = (mConfig) configRepo.findById(10);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                HashMap<String, String> headers = new HashMap<>();
                // add headers <key,value>
                String credentials = UserName.getTxtDefaultValue().toString() +":"+Passoword.getTxtDefaultValue().toString();
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(),
                        Base64.NO_WRAP);
                headers.put("Authorization", auth);
                return headers;
            }
        };
//        request.setRetryPolicy(new
//                DefaultRetryPolicy(60000,
//                0,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }
}
