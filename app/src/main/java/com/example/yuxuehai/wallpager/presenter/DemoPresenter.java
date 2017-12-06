package com.example.yuxuehai.wallpager.presenter;

import android.content.Context;
import android.view.View;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BasePresenter;
import com.example.yuxuehai.wallpager.bean.UnsplashResult;
import com.example.yuxuehai.wallpager.model.NetModel;
import com.example.yuxuehai.wallpager.presenter.dao.DemoPresenterDao;
import com.example.yuxuehai.wallpager.utils.Constants;
import com.example.yuxuehai.wallpager.utils.NetUtils;
import com.example.yuxuehai.wallpager.view.DemoView;

import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yuxuehai on 17-12-3.
 */

public class DemoPresenter extends BasePresenter<DemoView> implements DemoPresenterDao{

    private NetModel mNetModel;
    private int mPage = 1;

    public DemoPresenter(Context context) {
        super(context);
        mNetModel = NetModel.getNetModel();
    }


    @Override
    public boolean hasNetWork() {
        if (NetUtils.isNetworkAvailable(getContext())) {
            if (NetUtils.isWiFi(getContext())){
                return true;
            }
        }else {
            return false;
        }

        return false;
    }

    @Override
    public void getRecentPhotos() {
        mNetModel.getHttpHelper().getRecentPhotos(Constants.UNSPLASH_APP_KEY,1,
                Constants.NUM_PER_PAGE,Constants.ORDER_BY_LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UnsplashResult>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        getView().setRefresh();
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() == null) {
                            return;
                        }
                        getView().hideRefresh();
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<UnsplashResult> unsplashResults) {
                        mPage = 1;
                        if(unsplashResults.isEmpty()){
                            getView().setNoDataView();
                        }else {
                            getView().hideRefresh();
                            getView().refreshData(unsplashResults);
                        }
                    }
                });
    }

    @Override
    public void loadMorePhotoes() {
        mNetModel.getHttpHelper().getRecentPhotos(Constants.UNSPLASH_APP_KEY,++mPage,
                Constants.NUM_PER_PAGE,Constants.ORDER_BY_LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UnsplashResult>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() == null) {
                            return;
                        }
                        getView().loadMoreError();
                    }

                    @Override
                    public void onNext(List<UnsplashResult> unsplashResults) {
                        if (unsplashResults.isEmpty()){
                            getView().loadMoreEnd();
                        }else {
                            getView().addMoreData(unsplashResults);
                        }
                    }
                });
    }

    @Override
    public void setClickEvent(View view) {
        int id = view.getId();
        switch (id){
            case R.id.fb_totop:
                getView().rollToTop();
                break;
        }
    }
}
