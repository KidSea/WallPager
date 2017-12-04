package com.example.yuxuehai.wallpager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by yuxuehai on 17-12-3.
 */

public abstract class MvpBaseFragment<V,T extends BasePresenter<V>> extends BaseFragment {


    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建Presenter
        mPresenter = createPresenter();
        //关联View
        mPresenter.attachView((V) this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mPresenter != null){
            mPresenter.attachView((V)this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }

    protected abstract int requestLayout();
    protected abstract T createPresenter();

}
