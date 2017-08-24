package com.spotify.repairit.config;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by anurag-local on 16-Mar-17.
 */

public class Singleton {
    public static Singleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private Singleton (Context context){
        mCtx=context;
        requestQueue=getRequestQueue();
    }
    public static synchronized Singleton getInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new Singleton(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public<T> void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }
}
