package com.example.yuxuehai.wallpager.data.net;

import com.example.yuxuehai.wallpager.base.RxBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuxuehai on 17-12-17.
 */

public abstract class DownLoadCallback implements Callback<ResponseBody> {

    /**
     * 订阅下载进度
     */
    private CompositeSubscription rxSubscriptions = new CompositeSubscription();
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;

    public DownLoadCallback(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        subscribeLoadProgress();
    }

    public void onSuccess(File file) {
        unsubscribe();
    }

    public void onFail(String error){

    }

    public abstract void progress(long progress, long total);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.code() == 200) {
            try {
                saveFile(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (response.code() == 400) {
            onFail("error");
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        onFail("error");
    }

    /**
     * 保存
     *
     * @param response
     * @return
     * @throws IOException
     */
    public File saveFile(Response<ResponseBody> response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            onSuccess(file);
            return file;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 订阅文件下载进度
     */
//    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    private void subscribeLoadProgress() {
        beforeProgress();
        rxSubscriptions.add(RxBus.getDefault()
                .toObservable(DownLoadEvent.class)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DownLoadEvent>() {
                    @Override
                    public void call(DownLoadEvent fileLoadEvent) {
                        progress(fileLoadEvent.getBytesRead(), fileLoadEvent.getTotalLength());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onFail("error");
                    }
                }));
    }

    protected void beforeProgress() {

    }

    /**
     * 取消订阅，防止内存泄漏
     */
    private void unsubscribe() {
        if (!rxSubscriptions.isUnsubscribed()) {
            rxSubscriptions.unsubscribe();
        }
    }

}
