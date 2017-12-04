package com.example.yuxuehai.wallpager.model.dao;

import com.example.yuxuehai.wallpager.bean.PhotoInfo;
import com.example.yuxuehai.wallpager.bean.UnsplashResult;

import java.util.List;

import rx.Observable;

/**
 * Created by yuxuehai on 17-12-2.
 */

public interface HttpHelper {
    //最新的作品
    Observable<List<UnsplashResult>> getRecentPhotos(String clientId, int page, int per_page, String order_by);
    //精选
    Observable<List<UnsplashResult>> getCuratedPhotos(String clientId, int page, int per_page, String order_by);
    //建筑
    Observable<List<UnsplashResult>> getBuildingsPhotos(String clientId, int page, int per_page);
    //饮食
    Observable<List<UnsplashResult>> getFoodsPhotos(String clientId, int page, int per_page);
    //自然
    Observable<List<UnsplashResult>> getNaturePhotos(String clientId, int page, int per_page);
    //物品
    Observable<List<UnsplashResult>> getGoodsPhotos(String clientId, int page, int per_page);
    //人物
    Observable<List<UnsplashResult>> getPersonPhotos(String clientId, int page, int per_page);
    //科技
    Observable<List<UnsplashResult>> getTechnologyPhotos(String clientId, int page, int per_page);
    //照片的信息
    Observable<PhotoInfo> getPhotoInfo(String id, String clienId);

}
