package com.example.yuxuehai.wallpager.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


/**
 * Created by yuxuehai on 17-11-27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getName();

    protected Context getcontext() {
        return getApplicationContext();
    }

    protected Activity getActivityContext() {
        return this;
    }

    protected abstract int requestLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onBeforeSetContentView();
        if(requestLayoutId() != 0) {
            setContentView(requestLayoutId());
        }
        ButterKnife.bind(this);
        initView();
        initData();
        setupActionBar();
    }

    protected void initData() {

    }

    protected void initView() {

    }


    protected void onBeforeSetContentView(){

    }

    protected void setupActionBar(){

    }
}
