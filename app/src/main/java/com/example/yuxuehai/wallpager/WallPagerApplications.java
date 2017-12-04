package com.example.yuxuehai.wallpager;

import android.app.Application;

/**
 * Created by yuxuehai on 17-11-27.
 */

public class WallPagerApplications extends Application {

    private static WallPagerApplications sInstance;

    public static WallPagerApplications getInstance() {
        if (sInstance == null) {
            return new WallPagerApplications();
        } else {
            return sInstance;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (sInstance == null) {
            sInstance = this;
        }
    }
}
