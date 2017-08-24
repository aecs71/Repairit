package com.spotify.repairit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spotify.repairit.config.Singleton;
import com.spotify.repairit.models.*;
import com.spotify.repairit.utils.ApplicationConstants;
import com.spotify.repairit.utils.SessionManager;
import com.spotify.repairit.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button signinButton;
    TextView signupText;
    EditText mEmailView, mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private UserLoginTask mAuthTask = null;
    private SessionManager session;
    private VolleyRequest volley;
    String login_url = "http://localhost/login.php";
    String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setLogo(R.drawable.ic_build_white_24dp);
     //   toolbar.setTitleMarginStart(50);
        signinButton = (Button) findViewById(R.id.signin);
        session=new SessionManager(getApplicationContext());
        if(session.isLoggedIn() )
        {

            Intent intent = new Intent(LoginActivity.this, LandingActivity.class);
            startActivity(intent);
            finish();
        }
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent =new Intent(LoginActivity.this,LandingActivity.class);
                startActivity(intent);*/
                attemptLogin();


            }
        });
        signupText = (TextView) findViewById(R.id.signUp);
        signupText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        mEmailView = (EditText) findViewById(R.id.Userid);
        mPasswordView = (EditText) findViewById(R.id.Password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString().replaceAll("\\s","");
        System.out.println(email);
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.incorrect_email_format));
            focusView = mEmailView;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.field_required));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel)
            focusView.requestFocus();
        else {
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.processLoginRequest();
        }
    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public class UserLoginTask  {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

       public void processLoginRequest()
        {
            final ResponseContainer responseContainer=new ResponseContainer();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ApplicationConstants.LOGIN_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String code = jsonObject.getString("code");
                        if (code.equals("login success")) {

                            Log.e(TAG, "onResponse:+loged in ");
                            responseContainer.result=true;




                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onResponse:+not loged in ");
                            responseContainer.result=false;

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        responseContainer.result=false;
                    }
                    onPostExecute(responseContainer.result);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    responseContainer.result=false;
                    error.printStackTrace();
                    onPostExecute(responseContainer.result);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", mEmail);
                    params.put("password", mPassword);
                    return params;

                }
            };
            Singleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
        }




        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            if (success) {
              finish();
                session.createLoginSession(mEmail,mPassword);
                    Intent intent = new Intent(LoginActivity.this, LandingActivity.class);
                    startActivity(intent);


                Log.e(TAG, "onPostExecute:true "+success );
            }
            else {
               mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
                Log.e(TAG, "onPostExecute:false "+success );
                Intent intent = new Intent(LoginActivity.this, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        }

        private class ResponseContainer {
            public boolean result;

        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }

    }

}
