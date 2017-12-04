package com.example.yuxuehai.wallpager.model.http;

import com.example.yuxuehai.wallpager.bean.PhotoInfo;
import com.example.yuxuehai.wallpager.bean.UnsplashResult;
import com.example.yuxuehai.wallpager.model.api.WallPagerApis;
import com.example.yuxuehai.wallpager.model.dao.HttpHelper;

import java.util.List;

import rx.Observable;

/**
 * Created by yuxuehai on 17-12-2.
 */

public class HttpHelperImpl implements HttpHelper {

    private WallPagerApis mWallPagerApis;

    public HttpHelperImpl(WallPagerApis apis) {
        this.mWallPagerApis = apis;
    }


    @Override
    public Observable<List<UnsplashResult>> getRecentPhotos(String clientId, int page, int per_page, String order_by) {
        return mWallPagerApis.getRecentPhotos(clientId, page, per_page, order_by);
    }

    @Override
    public Observable<List<UnsplashResult>> getCuratedPhotos(String clientId, int page, int per_page, String order_by) {
        return mWallPagerApis.getCuratedPhotos(clientId, page, per_page, order_by);
    }

    @Override
    public Observable<List<UnsplashResult>> getBuildingsPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getBuildingsPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getFoodsPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getFoodsPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getNaturePhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getNaturePhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getGoodsPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getGoodsPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getPersonPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getPersonPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getTechnologyPhotos(String clientId, int page, int per_page) {
        return mWallPagerApis.getTechnologyPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<PhotoInfo> getPhotoInfo(String id, String clienId) {
        return mWallPagerApis.getPhotoInfo(id, clienId);
    }
}
