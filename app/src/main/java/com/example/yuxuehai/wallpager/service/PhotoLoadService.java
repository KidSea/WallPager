package com.example.yuxuehai.wallpager.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.yuxuehai.wallpager.WallPagerApplications;
import com.example.yuxuehai.wallpager.data.NetModel;
import com.example.yuxuehai.wallpager.data.http.DownLoadCallback;
import com.example.yuxuehai.wallpager.ui.interfaces.LoadPhotoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;

import static com.example.yuxuehai.wallpager.utils.Constants.PHOTO_ID;
import static com.example.yuxuehai.wallpager.utils.Constants.PHOTO_LOAD_URL;

/**
 * Created by yuxuehai on 2017-12-11.
 */

public class PhotoLoadService extends IntentService {

    private static final String TAG = PhotoLoadService.class.getName();

    private LoadPhotoEvent mLoadPhotoEvent;
    private NetModel mNetModel;

    public PhotoLoadService() {
        super(TAG);
        EventBus.getDefault().register(this);
        mLoadPhotoEvent = new LoadPhotoEvent();
        mNetModel = NetModel.getNetModel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        String photoLoadUrl = null;
        String photoId = null;
       if (intent != null) {
           photoLoadUrl = intent.getStringExtra(PHOTO_LOAD_URL);
           photoId = intent.getStringExtra(PHOTO_ID);
       }
        File file = new File(Environment.getExternalStorageDirectory()+"/WallPager/"+photoId+".jpg");
        if (file.exists()){
            mLoadPhotoEvent.setPhotoId("exist");
            EventBus.getDefault().post(mLoadPhotoEvent);//发送事件,图片文件已存在
            return;
        }
        String dir = Environment.getExternalStorageDirectory()+"/WallPager/";
        String name = photoId+".jpg";
        String finalPhotoId = photoId;
        Log.e(TAG, photoLoadUrl);
        mNetModel.getHttpHelper().downloadPicFromNet(photoLoadUrl, new DownLoadCallback(dir, name) {
            @Override
            public void progress(long progress, long total) {
                Log.e(TAG, "Progress " + progress +" "+ total);
                mLoadPhotoEvent.setProgress(progress * 100 / total);
                mLoadPhotoEvent.setPhotoId(finalPhotoId);
                EventBus.getDefault().postSticky(mLoadPhotoEvent);//发送事件,更新UI
            }

            @Override
            public void onSuccess(File file) {
                super.onSuccess(file);
                Log.e(TAG, "Success");
                // 最后通知图库更新
                WallPagerApplications.getContext().sendBroadcast(new Intent(Intent
                        .ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" +
                        file.getAbsolutePath())));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error");
                mLoadPhotoEvent.setMessage("error");
                EventBus.getDefault().post(mLoadPhotoEvent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doNothing(LoadPhotoEvent event){

    }
}
