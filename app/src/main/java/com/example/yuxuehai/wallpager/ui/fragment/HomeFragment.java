package com.example.yuxuehai.wallpager.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BaseFragment;
import com.example.yuxuehai.wallpager.ui.adapter.HomePagerAdapter;
import com.example.yuxuehai.wallpager.ui.home.MainActivity;

import butterknife.BindView;

/**
 * Created by yuxuehai on 17-11-29.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.app_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_container)
    ViewPager mHomeViewPager;

    private HomePagerAdapter mHomePagerAdapter;

    @Override
    public int requestLayoutId() {
        return R.layout.fragment_main_layout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mHomePagerAdapter = new HomePagerAdapter(getFragmentManager());
        mHomeViewPager.setAdapter(mHomePagerAdapter);
        mTabLayout.setupWithViewPager(mHomeViewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentFactory.getFragmentFactory().clearView();
    }

    @Override
    protected void setActionBar() {
        super.setActionBar();
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setTitleTextColor(Color.WHITE);
        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
        ((MainActivity) getActivity()).setToolBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.img_slide_menu);
        mToolbar.setNavigationOnClickListener(view -> ((MainActivity) getActivity()).toggleDrawer());
    }

}
