package com.example.yuxuehai.wallpager.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by yuxuehai on 17-12-3.
 */

public class LayoutUtils {

    public static View inflate(Context context, int layoutId){
        if (layoutId <= 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(layoutId, null);
    }
}
