package com.spotify.repairit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.spotify.repairit.LoginActivity;
import com.spotify.repairit.config.Singleton;
import com.spotify.repairit.utils.ApplicationConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.spotify.repairit.utils.ApplicationConstants;

/**
 * Created by anurag-local on 11-Apr-17.
 */

public class VolleyRequest {

    private boolean isAuthorized = false;
    Context context;
    String TAG = VolleyRequest.class.getSimpleName();

    public VolleyRequest(Context context) {


        this.context = context;
    }

    public void isAuthorizedUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,ApplicationConstants.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {Log.i(TAG, "onResponsee: ");
                    System.out.println(isAuthorized+response.toString());
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    if (code.equals("login success")) {
                        isAuthorized = true;
                    }

                } catch (JSONException ex) {
                    ex.printStackTrace();
                    ;
                }
            }
        }

                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();


            }
        }

        )

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences pref = context.getSharedPreferences(ApplicationConstants.PREFERENCES, context.MODE_PRIVATE);
                params.put(ApplicationConstants.USERNAME, pref.getString(ApplicationConstants.USERNAME, ""));
                params.put(ApplicationConstants.PASSWORD, pref.getString(ApplicationConstants.PASSWORD, ""));
                System.out.println(isAuthorized+"auth");
                return params;

            }
        };
        Singleton.getInstance(context).addToRequestQueue(stringRequest);

    }
}
