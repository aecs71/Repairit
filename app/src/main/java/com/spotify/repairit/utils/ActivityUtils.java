package com.spotify.repairit.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by anurag-local on 31-Mar-17.
 */

public class ActivityUtils {

    /**
     * Enables back navigation for activities that are launched from the NavBar. See {@code
     * AndroidManifest.xml} to find out the parent activity names for each activity.
     *
     * @param intent
     */
    public static void createBackStack(Activity activity, Intent intent) {
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder builder = TaskStackBuilder.create(activity);
            builder.addNextIntentWithParentStack(intent);
            builder.startActivities();

        } else */{
            activity.startActivity(intent);
      //      activity.finish();

        }
    }


}
