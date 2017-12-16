package com.example.yuxuehai.wallpager.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.yuxuehai.wallpager.WallPagerApplications;

/**
 * Created by yuxuehai on 17-12-2.
 */

public class GlideUtils {
    private static final String TAG = "GlideUtils";

    public static void loadImgSample(Context context, String url, int placeHolder, int errorDrawable, ImageView imageView){
        Glide.with(context)
                .load(url)
                .crossFade()
                .placeholder(placeHolder)
                .error(errorDrawable)
                .into(imageView);
    }


    /**
     * 高度自适应
     * @param context
     * @param url
     * @param placeHolder
     * @param errorDrawable
     * @param imageView
     */
    public static void loadImgAutoHeight(Context context, String url, int placeHolder, int errorDrawable, final ImageView imageView){
        Glide.with(WallPagerApplications.getContext())
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(placeHolder)
                .error(errorDrawable)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (resource != null){
                            imageView.setImageBitmap(null);
                            imageView.refreshDrawableState();
                            imageView.setImageBitmap(zipBitmap(resource,imageView));
                        }
                    }
                });
    }


    /**
     * 压缩图片
     * @param bitmap
     * @param imageView
     * @return
     */
    private static Bitmap zipBitmap(Bitmap bitmap, ImageView imageView){
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        int reqWid = ScreenUtils.getScreenWidth(WallPagerApplications.getContext());
        int rawWid = bitmap.getWidth();
        float scale = (float)reqWid / (float) rawWid;
        int reqHeight = (int)( bitmap.getHeight() * scale);
        params.width = reqWid;
        params.height = reqHeight;
        imageView.setLayoutParams(params);
        return Bitmap.createScaledBitmap(bitmap,reqWid/2,reqHeight/2,true);
    }

}
