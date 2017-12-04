package com.example.yuxuehai.wallpager.view;

import com.example.yuxuehai.wallpager.base.BaseView;
import com.example.yuxuehai.wallpager.bean.UnsplashResult;

import java.util.List;

/**
 * Created by yuxuehai on 17-12-3.
 */

public interface DemoView extends BaseView {

    void setRefresh();
    void hideRefresh();
    void refreshData(List<UnsplashResult> unsplashResults);
    void addMoreData(List<UnsplashResult> unsplashResults);

}
