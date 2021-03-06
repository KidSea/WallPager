package com.example.yuxuehai.wallpager.utils;

import com.example.yuxuehai.wallpager.WallPagerApplications;

import java.io.File;

/**
 * Created by yuxuehai on 17-12-1.
 */

public class Constants {
    //path
    public static final String PATH_DATA = WallPagerApplications.getContext().getCacheDir().
            getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    //api
    public static final String UNSPLASH_MAIN_URL = "https://api.unsplash.com/";
    public static final String UNSPLASH_APP_KEY = "e56930b9f9ed6a12276ba10a4ef5ebf740b399826020fcd9ede41a02b24f49a3";
    public static final String ORDER_BY_LATEST = "latest";//最新的
    //channel
    public static final String CHANNLE_NEW = "new";
    public static final String CHANNLE_PICK = "pick";
    public static final String CHANNLE_ARC = "arc";
    public static final String CHANNLE_FOOD = "food";
    public static final String CHANNLE_NATURE = "nature";
    public static final String CHANNLE_GOOD = "good";
    public static final String CHANNLE_PERSON = "person";
    public static final String CHANNLE_TECH = "tech";
    //图片下载服务常量
    public static final String SERVICE_ACTION = "com.example.yuxuehai.wallpager.intentservice.loadphoto";
    public static final String PHOTO_LOAD_URL = "photo_load_url";
    public static final String PHOTO_ID = "photo_id";

    //下载标志
    public static final String IS_RUNNING = "isrunning";

    public static final String UNSPLASH_RESULT = "unsplash_result";

    //public static final String[] DEFAULT_CHANNELS = new String[]{"新作", "精选", "建筑", "饮食", "自然", "物品", "人物", "科技"};
    public static final String[] DEFAULT_CHANNELS = new String[]{"新作", "精选"};

    public static final int NUM_PER_PAGE = 20;
    public static final long CACHE_SIZE = 50 * 1024 * 1024;


}
