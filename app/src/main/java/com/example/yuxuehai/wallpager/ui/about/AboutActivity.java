package com.example.yuxuehai.wallpager.ui.about;

import android.support.v7.widget.Toolbar;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by yuxuehai on 17-12-18.
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.app_toolbar)
    Toolbar mToolbar;

    @Override
    protected int requestLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar.setTitle(R.string.about_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }


}
