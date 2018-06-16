package com.example.yuxuehai.wallpager.ui.view;

import com.example.yuxuehai.wallpager.base.BaseView;
import com.example.yuxuehai.wallpager.data.bean.UnsplashResult;

import java.util.List;

/**
 * Created by yuxuehai on 17-12-3.
 */

public interface DemoView extends BaseView {

    void showLoadView();

    void hideLoadView();

    void refreshData(List<UnsplashResult> unsplashResults);

    void addMoreData(List<UnsplashResult> unsplashResults);

    void showNoDataView();

    void getData();

    void loadMoreError();

    void loadMoreEnd();

    void rollToTop();
}
