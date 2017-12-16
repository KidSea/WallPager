package com.example.yuxuehai.wallpager.ui.view;

import com.example.yuxuehai.wallpager.base.BaseView;
import com.example.yuxuehai.wallpager.data.bean.PhotoInfo;

/**
 * Created by yuxuehai on 17-12-10.
 */

public interface PhotoView extends BaseView{
    void setPhotoInfo(PhotoInfo photoInfo);

    void finish();

    void sharePhotoes();

    void setWallPager();

    void showDialog();

    void dismissDialog();

    void setWallPagerSuccess();

    void setWallPagerFail();

    void startDownload();
}
