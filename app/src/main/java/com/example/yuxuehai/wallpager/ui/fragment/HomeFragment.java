package com.example.yuxuehai.wallpager.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BaseFragment;
import com.example.yuxuehai.wallpager.ui.MainActivity;

import butterknife.BindView;

/**
 * Created by yuxuehai on 17-11-29.
 */

public class HomeFragment extends BaseFragment {



    @Override
    public int requestLayout() {
        return R.layout.fragment_main_layout;
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setActionBar() {
        super.setActionBar();
    }
}
