package com.example.yuxuehai.wallpager.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuxuehai.wallpager.ui.SplashActivity;

import butterknife.ButterKnife;


/**
 * Created by yuxuehai on 17-11-27.
 */

public abstract class BaseFragment extends Fragment{




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(requestLayout(), container, false);
        ButterKnife.bind(this,view);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        setActionBar();
    }

    /**
     * 设置布局前的初始化
     */
    protected void  beforeSetView(){}
    /**
     * 布局
     * @return
     */
    protected abstract int requestLayout();

    /**
     * View需要初始化的
     */
    protected  void initView(){}

    /**
     * 初始化数据
     */
    protected void initData(){}

    /**
     * 初始化ActionBar
     */
    protected void setActionBar(){}

}
