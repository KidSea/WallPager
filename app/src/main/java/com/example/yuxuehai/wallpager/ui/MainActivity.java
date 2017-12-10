package com.example.yuxuehai.wallpager.ui;

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
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
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

    private HomeFragment mHomeFragment;
    private ActionBarDrawerToggle mDrawerToggle;

    private long exitTime;

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
    public void setToolBar(Toolbar toolBar) {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolBar,
                R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.drawer_home:
                //changeFragmentIndex(item,0);
                return true;
            case R.id.drawer_invoke_system:
                Toast.makeText(this, "壁纸设置", Toast.LENGTH_SHORT).show();
                Log.e("mainactivity", "壁纸设置");
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
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().beginTransaction()
                .remove(mHomeFragment);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    protected void onBeforeSetContentView() {
        super.onBeforeSetContentView();
        getWindow().setEnterTransition(new Fade());
    }

    @Override
    protected int requestLayout() {
        return R.layout.activity_main;
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


    private void initFragment() {

        mHomeFragment = new HomeFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, mHomeFragment)
                .show(mHomeFragment)
                .commit();
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }


}
