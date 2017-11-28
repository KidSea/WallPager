package com.example.yuxuehai.wallpager.ui;

import android.content.Intent;
import android.os.Handler;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BaseActivity;

import butterknife.BindView;


/**
 * create by yuxuehai 2017-11-27
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.rl_container)
    RelativeLayout mContainer;
    @BindView(R.id.iv_logo)
    ImageView mLogo;
    @BindView(R.id.tv_name)
    TextView mName;
    @BindView(R.id.tv_subject)
    TextView mSubject;


    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();

        mContainer.animate()
                .alpha(1)
                .setDuration(800)
                .start();
    }

    @Override
    protected int requestLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                delayFinish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        },1500);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onBeforeSetContentView() {

        Fade fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        super.onBeforeSetContentView();
    }

    private void delayFinish() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.finish();
            }
        }, 1000);
    }
}
