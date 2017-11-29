package com.example.yuxuehai.wallpager.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BaseActivity;
import com.example.yuxuehai.wallpager.ui.fragment.HomeFragment;
import com.example.yuxuehai.wallpager.view.MainView;

import butterknife.BindView;

/**
 * Created by yuxuehai on 17-11-28.
 */

public class MainActivity extends BaseActivity implements MainView,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_menu)
    NavigationView mNavigationView;
    @BindView(R.id.app_toolbar)
    Toolbar mToolbar;
    private HomeFragment mHomeFragment;
    private ActionBarDrawerToggle mDrawerToggle;

    private long exitTime;
    private int mDrawerSelectedItem = -1;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 侧滑菜单开关
     */
    @Override
    public void toggleDrawer(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawers();
        }else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.drawer_home:
                //changeFragmentIndex(item,0);
                return true;
            case R.id.drawer_invoke_system:
                Toast.makeText(this, "壁纸设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_photos_loaded:
                Toast.makeText(this, "下载管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_settings:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_about:
                Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
                break;
        }
        mDrawerLayout.closeDrawers();
        return false;
    }

    @Override
    protected int requestLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBeforeSetContentView() {
        super.onBeforeSetContentView();
        getWindow().setEnterTransition(new Fade());
    }

    @Override
    protected void initView() {
        super.initView();

        initFragment();
        initNavigationView();

        getWindow().setSharedElementExitTransition(new ChangeImageTransform());
        getWindow().setSharedElementReenterTransition(new ChangeImageTransform());
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.img_slide_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleDrawer();
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initFragment() {

        mHomeFragment = new HomeFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, mHomeFragment)
                .show(mHomeFragment)
                .commit();
    }



}
