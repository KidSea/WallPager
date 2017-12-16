package com.example.yuxuehai.wallpager.base;

import android.content.Context;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuxuehai on 17-12-15.
 */

public class RxBasePresenter<T> extends BasePresenter<T> {

    protected CompositeSubscription mSubscriptions;

    public RxBasePresenter(Context context) {
        super(context);
        mSubscriptions = new CompositeSubscription();
    }

    public void unSubscribe(){
        if (mSubscriptions != null){
            mSubscriptions.clear();
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        unSubscribe();
    }
}
