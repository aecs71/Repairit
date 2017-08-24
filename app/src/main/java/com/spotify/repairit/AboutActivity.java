package com.spotify.repairit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.spotify.repairit.navigation.NavigationModel;

/**
 * Created by anurag-local on 12-Apr-17.
 */

public class AboutActivity extends BaseActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        setTitle("About us");
        /*toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("RepairIt");*/
    }
    @Override
    protected NavigationModel.NavigationItemEnum getSelfItem() {
        return NavigationModel.NavigationItemEnum.ABOUT_US ;
    }
}
