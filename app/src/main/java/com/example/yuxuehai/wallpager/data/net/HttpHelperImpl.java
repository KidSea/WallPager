package com.example.yuxuehai.wallpager.data.net;

import com.example.yuxuehai.wallpager.data.api.WallPagerApis;
import com.example.yuxuehai.wallpager.data.bean.PhotoInfo;
import com.example.yuxuehai.wallpager.data.bean.UnsplashResult;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by yuxuehai on 17-12-2.
 */

public class HttpHelperImpl {
    //// TODO: 17/12/14
    private WallPagerApis mWallPagerApis;

    public HttpHelperImpl(WallPagerApis apis) {
        this.mWallPagerApis = apis;
    }


    public Observable<List<UnsplashResult>> getRecentPhotos(String clientId, int page, int per_page, String order_by) {
        return mWallPagerApis.getRecentPhotos(clientId, page, per_page, order_by);
    }

    public Observable<List<UnsplashResult>> getCuratedPhotos(String clientId, int page, int per_page, String order_by) {
        return mWallPagerApis.getCuratedPhotos(clientId, page, per_page, order_by);
    }

    public Observable<List<UnsplashResult>> getBuildingsPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getBuildingsPhotos(clientId, page, per_page);
    }

    public Observable<List<UnsplashResult>> getFoodsPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getFoodsPhotos(clientId, page, per_page);
    }

    public Observable<List<UnsplashResult>> getNaturePhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getNaturePhotos(clientId, page, per_page);
    }

    public Observable<List<UnsplashResult>> getGoodsPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getGoodsPhotos(clientId, page, per_page);
    }

    public Observable<List<UnsplashResult>> getPersonPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getPersonPhotos(clientId, page, per_page);
    }

    public Observable<List<UnsplashResult>> getTechnologyPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getTechnologyPhotos(clientId, page, per_page);
    }

    public Observable<PhotoInfo> getPhotoInfo(String id, String clienId) {
        return mWallPagerApis.getPhotoInfo(id, clienId);
    }

    private Call<ResponseBody> downloadPicFromNet(String url) {
        return mWallPagerApis.downloadPicFromNet(url);
    }

    public void downloadPicFromNet(String url, DownLoadCallback callback) {
        Call<ResponseBody> call = downloadPicFromNet(url);
        call.enqueue(callback);

    }
}
