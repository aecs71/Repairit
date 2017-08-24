package com.spotify.repairit.navigation;

import com.spotify.repairit.AboutActivity;
import com.spotify.repairit.ContactusActivity;
import com.spotify.repairit.R;
import com.spotify.repairit.YourOrdersActivity;

/**
 * Created by anurag-local on 30-Mar-17.
 */

public class NavigationModel {

public enum NavigationItemEnum {
    YOUR_ORDER(R.id.your_order,R.string.your_order,R.drawable.ic_menu_yourorder, YourOrdersActivity.class),
    LOGOUT(R.id.nav_logout,R.string.logout,R.drawable.ic_menu_logout,null),
    ABOUT_US(R.id.nav_aboutus,R.string.about_us,R.drawable.ic_menu_aboutus, AboutActivity.class),
    CONTACT_US(R.id.nav_contactus,R.string.contact_us,R.drawable.ic_menu_contactus, ContactusActivity.class),
    INVALID(12,0,0,null);

    private int id;
    private int iconResource;
    private int titleResource;
    private Class classToLaunch;
    NavigationItemEnum(int id,int  titleResource,int iconResource,Class classToLaunch)
    {
        this.id=id;
        this.iconResource=iconResource;
        this.classToLaunch=classToLaunch;
        this.titleResource=titleResource;
    }
    public int getId(){return id;}
    public int getIconResource(){return iconResource;}
    public int getTitleResource(){return titleResource;}
    public Class getClassToLaunch(){return classToLaunch;}

    public static NavigationItemEnum getByID(int id)
    {
     NavigationItemEnum values[]=NavigationItemEnum.values();
        for(NavigationItemEnum val:values)
        {
            if(val.getId()==id)
                return val;
        }

        return INVALID;
    }
}
}
