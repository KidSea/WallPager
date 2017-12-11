package com.example.yuxuehai.wallpager.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.adapter.PhotoesRecycleAdapter;
import com.example.yuxuehai.wallpager.base.BaseRecyclerAdapter;
import com.example.yuxuehai.wallpager.base.MvpBaseFragment;
import com.example.yuxuehai.wallpager.bean.UnsplashResult;
import com.example.yuxuehai.wallpager.interfaces.OnLoadMoreListener;
import com.example.yuxuehai.wallpager.interfaces.RecyclerViewScrollDetector;
import com.example.yuxuehai.wallpager.presenter.DemoPresenter;
import com.example.yuxuehai.wallpager.ui.PhotoesDetailAcitivity;
import com.example.yuxuehai.wallpager.utils.Constants;
import com.example.yuxuehai.wallpager.utils.LayoutUtils;
import com.example.yuxuehai.wallpager.view.DemoView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by yuxuehai on 17-12-1.
 */

public class DemoFragment extends MvpBaseFragment<DemoView, DemoPresenter> implements DemoView,View.OnClickListener {

    private static final String TAG = DemoFragment.class.getSimpleName();

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
    @BindView(R.id.fb_totop)
    FloatingActionButton mActionButton;

    private int mPage = 1;
    private String mChannel = null;


    private PhotoesRecycleAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private View mDecorView;

    public DemoFragment(String channel){
        mChannel = channel;
    }

    @Override
    public int requestLayout() {
        return R.layout.viewpager_layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRecyclerView != null) mRecyclerView.removeAllViews();

    }

    @Override
    public void showError(String msg) {
        Log.d(TAG, msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        mErrorLayout.setVisibility(View.VISIBLE);
        mEmptyLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mActionButton.setVisibility(View.GONE);
    }

    @Override
    public void setRefresh() {
        mErrorLayout.setVisibility(View.GONE);
        mEmptyLayout.setVisibility(View.GONE);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideRefresh() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshData(List<UnsplashResult> unsplashResults) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        mEmptyLayout.setVisibility(View.GONE);
        mAdapter.addDatas(unsplashResults);
    }

    @Override
    public void addMoreData(List<UnsplashResult> unsplashResults) {
        mAdapter.setLoadMoreData(unsplashResults);
    }

    @Override
    public void setNoDataView() {
        mEmptyLayout.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mActionButton.setVisibility(View.GONE);
    }

    @Override
    public void getData() {
        mPage = 1;
        setRefresh();
        mPresenter.requestDatas(mChannel, mPage);
    }

    @Override
    public void loadMoreError() {
        mAdapter.loadFailed();
    }

    @Override
    public void loadMoreEnd() {
        mAdapter.loadEnd();
    }

    @Override
    public void rollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View view) {
        mPresenter.setClickEvent(view);
    }

    @Override
    protected void initView() {
        super.initView();
        mDecorView = getActivity().getWindow().getDecorView();

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new PhotoesRecycleAdapter(getContext(), true);
        View loadView = LayoutUtils.inflate(getContext(), R.layout.load_loading_layout);
        View loadErrorView = LayoutUtils.inflate(getContext(), R.layout.load_failed_layout);
        View loadEndView = LayoutUtils.inflate(getContext(), R.layout.load_end_layout);
        mAdapter.setLoadingView(loadView);
        mAdapter.setLoadFailedView(loadErrorView);
        mAdapter.setLoadEndView(loadEndView);
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                mPresenter.requestDatas(mChannel, ++mPage);
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data, View view) {
                getIntoPhotoesDetail(position, (UnsplashResult)data, view);
            }
        });
        mRecyclerView.addOnScrollListener(mDetector);
        mRecyclerView.setItemViewCacheSize(100);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.GONE);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        super.initData();
        getData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mActionButton.setOnClickListener(this);
        mErrorLayout.setOnClickListener(this);
    }

    @Override
    protected DemoPresenter createPresenter() {
        return new DemoPresenter(getContext());
    }

    private RecyclerViewScrollDetector mDetector = new RecyclerViewScrollDetector() {
        @Override
        public void onScrollUp() {
            hideSystemUI();
        }

        @Override
        public void onScrollDown() {
            showSystemUI();
        }
    };

    private void showSystemUI() {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void hideSystemUI() {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void getIntoPhotoesDetail(int position, UnsplashResult data, View view){
        Intent intent = new Intent(getContext(), PhotoesDetailAcitivity.class);
        intent.putExtra(Constants.UNSPLASH_RESULT, data);
        if(Build.VERSION.SDK_INT >= 22){
            getActivity().startActivity(intent, ActivityOptions
                    .makeSceneTransitionAnimation(getActivity(), view, getActivity()
                            .getResources().getString(R.string.share_photo)).toBundle());
        }else {
            getActivity().startActivity(intent);
        }

    }

}
