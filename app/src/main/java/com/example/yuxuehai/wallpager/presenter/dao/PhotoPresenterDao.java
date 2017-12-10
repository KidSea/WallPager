package com.example.yuxuehai.wallpager.presenter.dao;

import android.view.View;

/**
 * Created by yuxuehai on 17-12-3.
 */

public interface PhotoPresenterDao {


    void getPhotoData(String id);

    void disPatchClickEvent(View view);

    void setWallPager(String raw);
}
