package com.example.yuxuehai.wallpager.ui.photo;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.transition.ChangeImageTransform;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BaseActivity;
import com.example.yuxuehai.wallpager.data.bean.UnsplashResult;
import com.example.yuxuehai.wallpager.ui.fragment.PhotoesDetailFragment;
import com.example.yuxuehai.wallpager.utils.Constants;

/**
 * Created by yuxuehai on 17-12-9.
 */

public class PhotoesDetailAcitivity extends BaseActivity {


    private PhotoesDetailFragment mPhotoDetailFragment;
    private UnsplashResult mResult;

    @Override
    protected void onBeforeSetContentView() {
        super.onBeforeSetContentView();
        if (Build.VERSION.SDK_INT >= 22) {
            getWindow().setSharedElementExitTransition(new ChangeImageTransform());
            getWindow().setSharedElementReenterTransition(new ChangeImageTransform());
        }
        Intent intent = getIntent();
        if (intent != null){
            mResult = (UnsplashResult) intent.getSerializableExtra(Constants.UNSPLASH_RESULT);
        }
    }

    @Override
    protected int requestLayout() {
        return R.layout.activity_photoes_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        mPhotoDetailFragment = new PhotoesDetailFragment(mResult);
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.container,mPhotoDetailFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().beginTransaction()
                .remove(mPhotoDetailFragment);
    }
}
