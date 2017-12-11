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
    public void requestDatas(String channel, int page) {

        switch (channel){
            case Constants.CHANNLE_NEW:
                mNetModel.getHttpHelper().getRecentPhotos(Constants.UNSPLASH_APP_KEY,page,
                        Constants.NUM_PER_PAGE,Constants.ORDER_BY_LATEST)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UnsplashResult>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (getView() == null) {
                                    return;
                                }
                                if(page == 1){
                                    getView().hideRefresh();
                                    getView().showError(e.getMessage());
                                }else {
                                    getView().loadMoreError();
                                }
                            }

                            @Override
                            public void onNext(List<UnsplashResult> unsplashResults) {
                                if(page == 1){
                                    if(unsplashResults.isEmpty()){
                                        getView().setNoDataView();
                                    }else {
                                        getView().hideRefresh();
                                        getView().refreshData(unsplashResults);
                                    }
                                }else {
                                    if (unsplashResults.isEmpty()){
                                        getView().loadMoreEnd();
                                    }else {
                                        getView().addMoreData(unsplashResults);
                                    }
                                }
                            }
                        });
                break;
            case Constants.CHANNLE_PICK:
                mNetModel.getHttpHelper().getCuratedPhotos(Constants.UNSPLASH_APP_KEY,page,
                        Constants.NUM_PER_PAGE,Constants.ORDER_BY_LATEST)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UnsplashResult>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (getView() == null) {
                                    return;
                                }
                                if(page == 1){
                                    getView().hideRefresh();
                                    getView().showError(e.getMessage());
                                }else {
                                    getView().loadMoreError();
                                }
                            }

                            @Override
                            public void onNext(List<UnsplashResult> unsplashResults) {
                                if(page == 1){
                                    if(unsplashResults.isEmpty()){
                                        getView().setNoDataView();
                                    }else {
                                        getView().hideRefresh();
                                        getView().refreshData(unsplashResults);
                                    }
                                }else {
                                    if (unsplashResults.isEmpty()){
                                        getView().loadMoreEnd();
                                    }else {
                                        getView().addMoreData(unsplashResults);
                                    }
                                }
                            }
                        });
                break;
            case Constants.CHANNLE_ARC:
                mNetModel.getHttpHelper().getBuildingsPhotos(Constants.UNSPLASH_APP_KEY,page,
                        Constants.NUM_PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UnsplashResult>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (getView() == null) {
                                    return;
                                }
                                if(page == 1){
                                    getView().hideRefresh();
                                    getView().showError(e.getMessage());
                                }else {
                                    getView().loadMoreError();
                                }
                            }

                            @Override
                            public void onNext(List<UnsplashResult> unsplashResults) {
                                if(page == 1){
                                    if(unsplashResults.isEmpty()){
                                        getView().setNoDataView();
                                    }else {
                                        getView().hideRefresh();
                                        getView().refreshData(unsplashResults);
                                    }
                                }else {
                                    if (unsplashResults.isEmpty()){
                                        getView().loadMoreEnd();
                                    }else {
                                        getView().addMoreData(unsplashResults);
                                    }
                                }
                            }
                        });
                break;
            case Constants.CHANNLE_FOOD:
                mNetModel.getHttpHelper().getFoodsPhotos(Constants.UNSPLASH_APP_KEY,page,
                        Constants.NUM_PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UnsplashResult>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (getView() == null) {
                                    return;
                                }
                                if(page == 1){
                                    getView().hideRefresh();
                                    getView().showError(e.getMessage());
                                }else {
                                    getView().loadMoreError();
                                }
                            }

                            @Override
                            public void onNext(List<UnsplashResult> unsplashResults) {
                                if(page == 1){
                                    if(unsplashResults.isEmpty()){
                                        getView().setNoDataView();
                                    }else {
                                        getView().hideRefresh();
                                        getView().refreshData(unsplashResults);
                                    }
                                }else {
                                    if (unsplashResults.isEmpty()){
                                        getView().loadMoreEnd();
                                    }else {
                                        getView().addMoreData(unsplashResults);
                                    }
                                }
                            }
                        });
                break;
            case Constants.CHANNLE_NATURE:
                mNetModel.getHttpHelper().getNaturePhotos(Constants.UNSPLASH_APP_KEY,page,
                        Constants.NUM_PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UnsplashResult>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (getView() == null) {
                                    return;
                                }
                                if(page == 1){
                                    getView().hideRefresh();
                                    getView().showError(e.getMessage());
                                }else {
                                    getView().loadMoreError();
                                }
                            }

                            @Override
                            public void onNext(List<UnsplashResult> unsplashResults) {
                                if(page == 1){
                                    if(unsplashResults.isEmpty()){
                                        getView().setNoDataView();
                                    }else {
                                        getView().hideRefresh();
                                        getView().refreshData(unsplashResults);
                                    }
                                }else {
                                    if (unsplashResults.isEmpty()){
                                        getView().loadMoreEnd();
                                    }else {
                                        getView().addMoreData(unsplashResults);
                                    }
                                }
                            }
                        });
                break;
            case Constants.CHANNLE_GOOD:
                mNetModel.getHttpHelper().getGoodsPhotos(Constants.UNSPLASH_APP_KEY,page,
                        Constants.NUM_PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UnsplashResult>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (getView() == null) {
                                    return;
                                }
                                if(page == 1){
                                    getView().hideRefresh();
                                    getView().showError(e.getMessage());
                                }else {
                                    getView().loadMoreError();
                                }
                            }

                            @Override
                            public void onNext(List<UnsplashResult> unsplashResults) {
                                if(page == 1){
                                    if(unsplashResults.isEmpty()){
                                        getView().setNoDataView();
                                    }else {
                                        getView().hideRefresh();
                                        getView().refreshData(unsplashResults);
                                    }
                                }else {
                                    if (unsplashResults.isEmpty()){
                                        getView().loadMoreEnd();
                                    }else {
                                        getView().addMoreData(unsplashResults);
                                    }
                                }
                            }
                        });
                break;
            case Constants.CHANNLE_PERSON:
                mNetModel.getHttpHelper().getPersonPhotos(Constants.UNSPLASH_APP_KEY,page,
                        Constants.NUM_PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UnsplashResult>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (getView() == null) {
                                    return;
                                }
                                if(page == 1){
                                    getView().hideRefresh();
                                    getView().showError(e.getMessage());
                                }else {
                                    getView().loadMoreError();
                                }
                            }

                            @Override
                            public void onNext(List<UnsplashResult> unsplashResults) {
                                if(page == 1){
                                    if(unsplashResults.isEmpty()){
                                        getView().setNoDataView();
                                    }else {
                                        getView().hideRefresh();
                                        getView().refreshData(unsplashResults);
                                    }
                                }else {
                                    if (unsplashResults.isEmpty()){
                                        getView().loadMoreEnd();
                                    }else {
                                        getView().addMoreData(unsplashResults);
                                    }
                                }
                            }
                        });
                break;
            case Constants.CHANNLE_TECH:
                mNetModel.getHttpHelper().getTechnologyPhotos(Constants.UNSPLASH_APP_KEY,page,
                        Constants.NUM_PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UnsplashResult>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (getView() == null) {
                                    return;
                                }
                                if(page == 1){
                                    getView().hideRefresh();
                                    getView().showError(e.getMessage());
                                }else {
                                    getView().loadMoreError();
                                }
                            }

                            @Override
                            public void onNext(List<UnsplashResult> unsplashResults) {
                                if(page == 1){
                                    if(unsplashResults.isEmpty()){
                                        getView().setNoDataView();
                                    }else {
                                        getView().hideRefresh();
                                        getView().refreshData(unsplashResults);
                                    }
                                }else {
                                    if (unsplashResults.isEmpty()){
                                        getView().loadMoreEnd();
                                    }else {
                                        getView().addMoreData(unsplashResults);
                                    }
                                }
                            }
                        });
                break;
        }
    }

    @Override
    public void setClickEvent(View view) {
        int id = view.getId();
        switch (id){
            case R.id.fb_totop:
                getView().rollToTop();
                break;
            case R.id.error_layout:
                getView().getData();
                break;
        }
    }
}
