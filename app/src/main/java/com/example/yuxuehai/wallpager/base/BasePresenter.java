package com.example.yuxuehai.wallpager.base;

import android.content.Context;

/**
 * Created by yuxuehai on 17-11-27.
 */

public abstract class BasePresenter<V> {


    private final Context mContext;
    protected V mViewRef;

    public BasePresenter(Context context) {
        mContext = context.getApplicationContext();
    }


    public Context getContext() {
        return mContext;
    }


    public void attachView(V view) {
        this.mViewRef = view;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef = null;
        }
    }

    protected V getView() {
        return mViewRef;
    }

}
