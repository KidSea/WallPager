package com.example.yuxuehai.wallpager.utils;

import com.example.yuxuehai.wallpager.WallPagerApplications;

import java.io.File;

/**
 * Created by yuxuehai on 17-12-1.
 */

public class Constants {
    //path
    public static final String PATH_DATA = WallPagerApplications.getInstance().getCacheDir().
            getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String UNSPLASH_MAIN_URL = "https://api.unsplash.com/";
    public static final String UNSPLASH_APP_KEY = "eb54e3b9dc12b9e0862b028b646085355d20b3442fbdfca4633ca0f7b01ef9a6";
    public static final String ORDER_BY_LATEST = "latest";//最新的

    public static final String[] DEFAULT_CHANNELS = new String[]{"新作","精选","建筑","饮食","自然","物品","人物","科技"};

    public static final int NUM_PER_PAGE = 20;
    public static final long CACHE_SIZE = 50 * 1024 * 1024;


}
