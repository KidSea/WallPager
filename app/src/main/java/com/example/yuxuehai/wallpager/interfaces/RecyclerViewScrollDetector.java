package com.example.yuxuehai.wallpager.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by yuxuehai on 17-12-4.
 */

public abstract class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {

    public abstract void onScrollUp();

    public abstract void onScrollDown();
    
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            onScrollUp();
        } else {
            onScrollDown();
        }
    }
}
