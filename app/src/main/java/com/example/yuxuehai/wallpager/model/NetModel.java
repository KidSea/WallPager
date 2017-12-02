package com.example.yuxuehai.wallpager.model;

import com.example.yuxuehai.wallpager.bean.PhotoInfo;
import com.example.yuxuehai.wallpager.bean.UnsplashResult;
import com.example.yuxuehai.wallpager.model.http.HttpHelper;
import com.example.yuxuehai.wallpager.model.http.HttpHelperImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by yuxuehai on 17-12-2.
 */

public class NetModel implements HttpHelper{

    private HttpHelper mHttpHelper;

//    public static NetModel getNetModel(){
//
//        if (mNetModel == null){
//            synchronized (NetModel.class){
//                if (mNetModel == null){
//                    mNetModel = new NetModel();
//                }
//            }
//        }
//        return mNetModel;
//    }

    public NetModel(HttpHelperImpl httpHelper){
        mHttpHelper = httpHelper;
    }

    @Override
    public Observable<List<UnsplashResult>> getRecentPhotos(String clientId, int page, int per_page, String order_by) {
        return mHttpHelper.getRecentPhotos(clientId, page, per_page, order_by);
    }

    @Override
    public Observable<List<UnsplashResult>> getCuratedPhotos(String clientId, int page, int per_page, String order_by) {
        return mHttpHelper.getCuratedPhotos(clientId, page, per_page, order_by);
    }

    @Override
    public Observable<List<UnsplashResult>> getBuildingsPhotos(String clientId, int page, int per_page) {
        return mHttpHelper.getBuildingsPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getFoodsPhotos(String clientId, int page, int per_page) {
        return mHttpHelper.getFoodsPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getNaturePhotos(String clientId, int page, int per_page) {
        return mHttpHelper.getNaturePhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getGoodsPhotos(String clientId, int page, int per_page) {
        return mHttpHelper.getGoodsPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getPersonPhotos(String clientId, int page, int per_page) {
        return mHttpHelper.getPersonPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<List<UnsplashResult>> getTechnologyPhotos(String clientId, int page, int per_page) {
        return mHttpHelper.getTechnologyPhotos(clientId, page, per_page);
    }

    @Override
    public Observable<PhotoInfo> getPhotoInfo(String id, String clienId) {
        return mHttpHelper.getPhotoInfo(id, clienId);
    }
}
