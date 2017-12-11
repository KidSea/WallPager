package com.example.yuxuehai.wallpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuxuehai.wallpager.ui.fragment.FragmentFactory;
import com.example.yuxuehai.wallpager.utils.Constants;

/**
 * Created by yuxuehai on 17-12-1.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {


    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return Constants.DEFAULT_CHANNELS.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.DEFAULT_CHANNELS[position];
    }

}
