package com.spotify.repairit.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.spotify.repairit.navigation.NavigationModel.NavigationItemEnum;
import com.spotify.repairit.R;
import com.spotify.repairit.signin.SignInDetails;
import com.spotify.repairit.utils.ActivityUtils;
import com.spotify.repairit.utils.SessionManager;

import static com.spotify.repairit.navigation.NavigationModel.NavigationItemEnum.LOGOUT;

/**
 * Created by anurag-local on 29-Mar-17.
 */

public class NavigationDrawer implements NavigationView.OnNavigationItemSelectedListener {
    private Activity mActivity;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Handler mHandler;
    private  static final int NAVDRAWER_LAUNCH_DELAY=250;
    private Runnable mDeferredOnDrawerClosedRunnable;
    private NavigationItemEnum mSelfItem;
    private TextView mUsernameLabel;
    private TextView mEmailLabel;
    private static final String TAG="NavigationDrawer";
    public NavigationDrawer() {
    }

    public void activityReady(Activity activity,NavigationItemEnum item) {
        mActivity = activity;
        mSelfItem=item;
        setUpView();
    }

    protected void setUpView() {
        mHandler=new Handler();
        mDrawerLayout = (DrawerLayout) mActivity.findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) mActivity.findViewById(R.id.nav_view);
       mNavigationView.setNavigationItemSelectedListener(this);
       View mNavHeader = mNavigationView.getHeaderView(0);
         mUsernameLabel= (TextView) mNavHeader.findViewById(R.id.hello_username);
        mEmailLabel= (TextView) mNavHeader.findViewById(R.id.user_email);
        mUsernameLabel.setText("Hello, "+SignInDetails.getUsername(mActivity));
        mEmailLabel.setText(SignInDetails.getUserId(mActivity));
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                if (mDeferredOnDrawerClosedRunnable != null) {
                    mDeferredOnDrawerClosedRunnable.run();
                    mDeferredOnDrawerClosedRunnable = null;
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavigationItemEnum menuItem = NavigationItemEnum.getByID(item.getItemId());
        onNavigationDrawerItemClicked(menuItem);
        return true;
    }


    private void onNavigationDrawerItemClicked(final NavigationItemEnum menuItem) {
        if(menuItem==mSelfItem)
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                itemSelected(menuItem);
            }
        },NAVDRAWER_LAUNCH_DELAY);
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        mNavigationView.getMenu().findItem(menuItem.getId()).setChecked(true);
    }
    public void itemSelected(NavigationItemEnum menuItem)
    {
        switch (menuItem)
        {
            case LOGOUT:
                SessionManager session=new SessionManager(mActivity.getApplicationContext());
                session.logoutUser();

            default:
                if(menuItem.getClassToLaunch()!=null) {
                   ActivityUtils.createBackStack(mActivity,new Intent(mActivity,menuItem.getClassToLaunch()));

                }
                break;
        }
    }
    public boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }
    public void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void openNavDrawer(){
        mDrawerLayout.openDrawer(GravityCompat.START);
    }


}
