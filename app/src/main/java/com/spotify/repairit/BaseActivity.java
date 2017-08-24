package com.spotify.repairit;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.spotify.repairit.navigation.NavigationDrawer;
import com.spotify.repairit.navigation.NavigationModel;

/**
 * Created by anurag-local on 23-Mar-17.
 */

public class BaseActivity extends AppCompatActivity  {

    private Toolbar mtoolbar;
    private NavigationDrawer mNavigationDrawer;
      @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        getToolbar();
    }

    private Toolbar getToolbar() {
        if(mtoolbar==null)
        {
            mtoolbar= (Toolbar) findViewById(R.id.toolbar);
            if(mtoolbar!=null)
            {
                setSupportActionBar(mtoolbar);
            }
        }
        return mtoolbar;

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
         mNavigationDrawer =new NavigationDrawer();
        mNavigationDrawer.activityReady(this,getSelfItem());
        setHamIcon();
        loadNavHeader();

         }

    private void loadNavHeader() {

    }

    private void setHamIcon() {
        if(mtoolbar!=null)
        {
            mtoolbar.setNavigationIcon(R.drawable.ic_hamburger);
            mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  mNavigationDrawer.openNavDrawer();
                }
            });
        }
    }

    protected NavigationModel.NavigationItemEnum getSelfItem() {
        return NavigationModel.NavigationItemEnum.INVALID;
    }
    @Override
    public void onBackPressed() {
        if (mNavigationDrawer.isNavDrawerOpen()) {
            mNavigationDrawer.closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

}

