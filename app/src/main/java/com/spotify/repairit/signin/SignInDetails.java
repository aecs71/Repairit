package com.spotify.repairit.signin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.spotify.repairit.YourOrdersActivity;
import com.spotify.repairit.config.Singleton;
import com.spotify.repairit.utils.ApplicationConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anurag-local on 26-Apr-17.
 */

public class SignInDetails {
    private static String userId;
    private static String username;
    private static final String TAG="SignInDetails";
    private static Context ctx;

    public static SharedPreferences getSharedPrefernces(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserId(Context context,String user)
    {
        ctx=context;
        SharedPreferences sp= getSharedPrefernces(context);

        sp.edit().putString(ApplicationConstants.USERNAME,user).apply();
        userId=user;
        Log.i(TAG, "setUserId: "+userId);
        setUsername(context);
    }
    public static void setUsername(Context context)
    {
        SharedPreferences sp= getSharedPrefernces(context);
        getUsernameFromServer(sp);
        Log.i(TAG, "setUsername: "+username);
    }

    private static void getUsernameFromServer(final SharedPreferences  sp) {
        StringRequest stringRequest= new StringRequest(Request.Method.GET,ApplicationConstants.LOGIN_URL+"?userId="+userId,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        username = jsonObject.getString("name");
                        sp.edit().putString(ApplicationConstants.USERFULLNAME,username).apply();
                        System.out.println(username);
                        Log.i(TAG, "onResponsegetusername: "+username);
                    }
                }
                catch (JSONException ex)
                {
                    ex.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getInstance(ctx).addToRequestQueue(stringRequest);
    }

    public static String getUserId(Context context)
    {
        SharedPreferences sp =getSharedPrefernces(context);
        return sp.getString(ApplicationConstants.USERNAME,null);
    }
    public static String getUsername(Context context)
    {
        SharedPreferences sp =getSharedPrefernces(context);
        return sp.getString(ApplicationConstants.USERFULLNAME,null);
    }
}
