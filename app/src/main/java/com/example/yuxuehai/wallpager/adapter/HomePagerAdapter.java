package com.example.yuxuehai.wallpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuxuehai.wallpager.ui.fragment.DemoFragment;
import com.example.yuxuehai.wallpager.utils.Constains;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-12-1.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {


    private ArrayList<DemoFragment> mDemoFragments;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        mDemoFragments = new ArrayList<>();
        initFrgments();
    }

    @Override
    public Fragment getItem(int position) {

        return mDemoFragments.get(position);
    }

    @Override
    public int getCount() {
        return 8;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return Constains.DEFAULT_CHANNELS[position];
    }

    private void initFrgments() {
        for (int i = 0; i < 8; i++){
            DemoFragment fragment = new DemoFragment();
            mDemoFragments.add(fragment);
        }
    }
}
