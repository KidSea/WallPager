package com.example.yuxuehai.wallpager.utils;

import com.example.yuxuehai.wallpager.BuildConfig;
import com.example.yuxuehai.wallpager.model.api.WallPagerApis;
import com.example.yuxuehai.wallpager.model.http.CacheInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.yuxuehai.wallpager.utils.Constants.CACHE_SIZE;

/**
 * Created by yuxuehai on 17-12-2.
 */

public class HttpUtils {
    private static final int READ_TIME_OUT = 20;
    private static final int WRITE_TIME_OUT = 20;
    private static final int CONNECT_TIME_OUT = 10;

    public static HttpUtils getHttpUtils() {
        return sHttpUtils;
    }

    private static final HttpUtils sHttpUtils = new HttpUtils();

    private OkHttpClient createOkHttpClien(OkHttpClient.Builder builder){
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        Cache cache = new Cache(new File(Constants.PATH_CACHE), CACHE_SIZE);
        //设置缓存
        builder.addInterceptor(new CacheInterceptor());
        builder.addNetworkInterceptor(new CacheInterceptor());
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        return builder.build();
    }

    public WallPagerApis getWallPagerApis(){
        OkHttpClient okHttpClien = createOkHttpClien(new OkHttpClient.Builder());

        return createRetrofit(new Retrofit.Builder(),okHttpClien, Constants.UNSPLASH_MAIN_URL)
                .create(WallPagerApis.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
