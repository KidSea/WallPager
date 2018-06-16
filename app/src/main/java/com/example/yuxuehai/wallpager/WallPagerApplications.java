package com.example.yuxuehai.wallpager;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuxuehai on 17-11-27.
 */

public class WallPagerApplications extends Application {
    //// TODO: 17/12/14  
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mContext == null) {
            mContext = getApplicationContext();
        }
    }
}
