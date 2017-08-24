package com.spotify.repairit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.util.Log;

import com.spotify.repairit.LandingActivity;
import com.spotify.repairit.LoginActivity;
import com.spotify.repairit.signin.SignInDetails;

import java.util.HashMap;

/**
 * Created by anurag-local on 10-Apr-17.
 */

public class SessionManager {
    SharedPreferences preferences;
    Editor editor;
    Context context;



    private static final String IS_LOGIN = "isLogin";
private static final String TAG="SessionManager";

    public SessionManager(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(ApplicationConstants.PREFERENCES, context.MODE_PRIVATE);
        this.editor = preferences.edit();

    }

    public void createLoginSession(String user, String password) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(ApplicationConstants.USERNAME, user);
        editor.putString(ApplicationConstants.PASSWORD, password);
        Log.i(TAG, "createLoginSession: "+user);
        SignInDetails.setUserId(context,user);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGIN, false);
    }

    public boolean checkLogin() {
        if (!isLoggedIn()) {
            Intent intent = new Intent(context, LoginActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(intent);
            return false;
        }
        return true;

    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if(Build.VERSION.SDK_INT >= 11) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        context.startActivity(intent);


    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(ApplicationConstants.USERNAME, preferences.getString(ApplicationConstants.USERNAME, ""));
        user.put(ApplicationConstants.PASSWORD, preferences.getString(ApplicationConstants.PASSWORD, ""));

        return user;
    }

}
