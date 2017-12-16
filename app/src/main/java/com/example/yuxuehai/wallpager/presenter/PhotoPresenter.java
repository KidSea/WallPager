package com.example.yuxuehai.wallpager.presenter;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.WallPagerApplications;
import com.example.yuxuehai.wallpager.base.RxBasePresenter;
import com.example.yuxuehai.wallpager.data.NetModel;
import com.example.yuxuehai.wallpager.data.bean.PhotoInfo;
import com.example.yuxuehai.wallpager.ui.view.PhotoView;
import com.example.yuxuehai.wallpager.utils.Constants;
import com.example.yuxuehai.wallpager.utils.ScreenUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yuxuehai on 17-12-10.
 */

public class PhotoPresenter extends RxBasePresenter<PhotoView> {

    private NetModel mNetModel;
    private WallpaperManager mWallpaperManager;

    public PhotoPresenter(Context context) {
        super(context);
        mNetModel = NetModel.getNetModel();
        mWallpaperManager = WallpaperManager.getInstance(WallPagerApplications.getContext());
    }


    public void getPhotoData(String id) {
        mSubscriptions.add(mNetModel.getHttpHelper().getPhotoInfo(id, Constants.UNSPLASH_APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhotoInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() == null){
                            return;
                        }
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(PhotoInfo photoInfo) {
                        if(photoInfo == null){
                            getView().showError("Data Error");
                        }else {
                            getView().setPhotoInfo(photoInfo);
                        }

                    }
                }));
    }

    public void disPatchClickEvent(View view) {
        int id = view.getId();

        switch (id){
            case R.id.iv_arrow_back:
                getView().finish();
                break;
            case R.id.tv_download:
                getView().startDownload();
                break;
            case R.id.tv_share:
                getView().sharePhotoes();
                break;
            case R.id.tv_set_wallpager:
                getView().setWallPager();
                break;
        }
    }

    public void setWallPager(final String raw) {
        getView().showDialog();
        mSubscriptions.add(Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Integer response = getWallPhoto(raw);
                subscriber.onNext(response);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        switch (integer){
                            case 200:
                                getView().dismissDialog();
                                getView().setWallPagerSuccess();
                                break;
                            case 400:
                                getView().dismissDialog();
                                getView().setWallPagerFail();
                                break;
                        }
                    }
                }));
    }

    private Integer getWallPhoto(String raw){
        try {
            URL url = new URL(raw);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setConnectTimeout(10 * 1000);
            connect.setDoInput(true);
            connect.connect();
            int code = connect.getResponseCode();
            if (code == 200){
                InputStream inputStream = connect.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int length = -1;
                byte[] buffer = new byte[1024];
                while((length = inputStream.read(buffer))!= -1){
                    bos.write(buffer,0,length);
                }
                bos.flush();
                bos.close();
                inputStream.close();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bos.toByteArray(),0,bos.toByteArray().length,options);
                int reqWidth = ScreenUtils.getScreenWidth(WallPagerApplications.getContext());
                int reqHeight = ScreenUtils.getScreenHeight(WallPagerApplications.getContext());
                int rawWidth = options.outWidth;
                int rawHeight = options.outHeight;
                int size = 1;
                if (rawHeight > reqHeight || rawWidth > reqWidth){
                    int halfHeight = rawHeight / 2 ;
                    int halfWidth = rawWidth / 2;
                    while ((halfHeight / size) > reqHeight && (halfWidth / size)>reqWidth){
                        size *= 2;
                    }
                }
                options.inSampleSize = size;
                options.inJustDecodeBounds = false;
                Bitmap bitmap = BitmapFactory.decodeByteArray(bos.toByteArray(),0,bos.toByteArray().length,options);
                mWallpaperManager.setBitmap(bitmap);
                return 200;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
        return null;
    }

}
