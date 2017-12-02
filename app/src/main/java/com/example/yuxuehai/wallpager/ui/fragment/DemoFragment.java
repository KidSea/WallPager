package com.example.yuxuehai.wallpager.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.adapter.PhotoesRecycleAdapter;
import com.example.yuxuehai.wallpager.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yuxuehai on 17-12-1.
 */

public class DemoFragment extends BaseFragment {

    @BindView(R.id.empty_layout)
    RelativeLayout mEmptyLayout;
    @BindView(R.id.error_layout)
    RelativeLayout mErrorLayout;
    @BindView(R.id.container_layout)
    RelativeLayout mContainerLayout;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle_layout)
    RecyclerView mRecyclerView;


    private PhotoesRecycleAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public int requestLayout() {
        return R.layout.viewpager_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setEnabled(false);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new PhotoesRecycleAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.GONE);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        super.initData();

        getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<String>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getActivity(), "Got the list!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "Something went wrong!",
                                Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                        mErrorLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(ArrayList<String> strings) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mSwipeRefreshLayout.setRefreshing(false);
                        mAdapter.addDatas(strings);
                    }
                });
    }


    private Observable<ArrayList<String>> getData(){
        return Observable.create(new Observable.OnSubscribe<ArrayList<String>>() {

            @Override
            public void call(Subscriber<? super ArrayList<String>> subscriber) {
                ArrayList<String> mArrayList = new ArrayList<String>();
                for (int i = 0; i < 20; i++){
                    String name = "item " + i;
                    mArrayList.add(name);
                }
                subscriber.onNext(mArrayList);
            }
        });
    }
}
