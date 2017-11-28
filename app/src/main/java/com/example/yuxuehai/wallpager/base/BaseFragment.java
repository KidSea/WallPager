package com.example.yuxuehai.wallpager.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuxuehai.wallpager.ui.SplashActivity;


/**
 * Created by yuxuehai on 17-11-27.
 */

public abstract class BaseFragment extends Fragment{

    protected SplashActivity mActivity;
    private View mMainView = null;

    protected abstract View createView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState);

    protected abstract void initView(View rootView);

    protected abstract void initData();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() instanceof SplashActivity){
            mActivity = (SplashActivity)getActivity();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (mMainView == null || mMainView.getParent() != null) {
            mMainView = createView(inflater, container, savedInstanceState);
            initView(mMainView);
            initData();
        }
        return mMainView;
    }


    protected Intent getIntent(){
        return mActivity.getIntent();
    }
}
