package com.spotify.repairit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.spotify.repairit.navigation.NavigationModel;


/**
 * Created by anurag-local on 12-Apr-17.
 */

public class ContactusActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        setTitle("Contact Us");
       // toolbar.setTitleMarginStart(50);
    }

    @Override
    protected NavigationModel.NavigationItemEnum getSelfItem() {
        return NavigationModel.NavigationItemEnum.CONTACT_US ;
    }
}
