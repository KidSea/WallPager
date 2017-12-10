package com.example.yuxuehai.wallpager.ui.fragment;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.MvpBaseFragment;
import com.example.yuxuehai.wallpager.bean.PhotoInfo;
import com.example.yuxuehai.wallpager.bean.UnsplashResult;
import com.example.yuxuehai.wallpager.presenter.PhotoPresenter;
import com.example.yuxuehai.wallpager.utils.GlideUtils;
import com.example.yuxuehai.wallpager.view.PhotoView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxuehai on 17-12-9.
 */

public class PhotoesDetailFragment extends MvpBaseFragment<PhotoView,
        PhotoPresenter> implements PhotoView ,View.OnClickListener{
    private static final String TAG = PhotoesDetailFragment.class.getName();

    @BindView(R.id.container) CoordinatorLayout mContainer;
    @BindView(R.id.iv_photo) ImageView mPhoto;
    @BindView(R.id.iv_arrow_back) ImageView mHomeBack;
    @BindView(R.id.author_photo) CircleImageView mAuthorPhoto;
    @BindView(R.id.tv_author_name) TextView mAuthorName;
    @BindView(R.id.tv_time) TextView mPhotoTime;
    @BindView(R.id.tv_download) TextView mDownLoad;
    @BindView(R.id.tv_share) TextView mShare;
    @BindView(R.id.tv_set_wallpager) TextView mSetWallPager;
    @BindView(R.id.progressbar) ProgressBar mProgressBar;
    @BindView(R.id.tv_attr_size) TextView mPhotoSize;
    @BindView(R.id.tv_attr_exposure) TextView mPhotoExposure;//快门
    @BindView(R.id.tv_attr_aperture) TextView mPhotoAperture;//光圈
    @BindView(R.id.tv_attr_focal) TextView mPhotoFocal;//焦距
    @BindView(R.id.tv_attr_model) TextView mPhotoModel;//器材
    @BindView(R.id.tv_attr_iso) TextView mPhotoIso;//曝光


    UnsplashResult mResult;
    private MaterialDialog mMaterialDialog;


    public PhotoesDetailFragment(UnsplashResult result) {
        mResult = result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mContainer != null){
            mContainer.removeAllViews();
            mContainer = null;
        }
    }

    @Override
    public void showError(String msg) {
        Log.d(TAG, msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPhotoInfo(PhotoInfo photoInfo) {
        PhotoInfo.Exif exif = photoInfo.getExif();
        mPhotoSize.setText("分辨率 : " + photoInfo.getWidth() + " / " + photoInfo.getHeight());
        mPhotoExposure.setText("快门 : " + (exif.getExposure_time() == null ? "Unknow" :
                exif.getExposure_time()));
        mPhotoAperture.setText("光圈 : " + (exif.getAperture() == null ? "Unknow" :
                exif.getAperture()));
        mPhotoFocal.setText("焦距 : " + (exif.getFocal_length() == null ? "Unknow" :
                exif.getFocal_length()));
        mPhotoModel.setText("器材 : " + (exif.getModel() == null ? "Unknow" :
                exif.getModel()));
        mPhotoIso.setText("曝光 : " + (exif.getIso() == 0 ? "Unknow" :
                exif.getIso()));
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void sharePhotoes() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "http://a.app.qq.com/o/simple.jsp?pkgname=com.dingmouren.wallpager");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "分享下载链接到"));
    }

    @Override
    public void setWallPager() {
        String raw = mResult.getUrls().getRaw();
        mPresenter.setWallPager(raw);
    }

    @Override
    public void showDialog() {
        mMaterialDialog.show();
    }

    @Override
    public void dismissDialog() {
        mMaterialDialog.dismiss();
    }

    @Override
    public void setWallPagerSuccess() {
        Toast.makeText(getContext(),"手机壁纸设置成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWallPagerFail() {
        Toast.makeText(getContext(),"手机壁纸设置失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        mPresenter.disPatchClickEvent(view);
    }

    @Override
    protected void beforeSetView() {
        super.beforeSetView();

    }

    @Override
    protected int requestLayout() {
        return R.layout.fragment_photoes_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mMaterialDialog = new MaterialDialog.Builder(getActivity()).build();
        mMaterialDialog.setCancelable(true);
        mMaterialDialog.setContent("正在为您的手机设置壁纸\n客官请骚等...");
    }

    @Override
    protected void initListener() {
        super.initListener();
        mHomeBack.setOnClickListener(this);
        mDownLoad.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mSetWallPager.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        GlideUtils.loadImgAutoHeight(getContext(), mResult.getUrls().getRegular(), 0, 0, mPhoto);
        GlideUtils.loadImgSample(getContext(),mResult.getUser().getProfile_image().getLarge(),0,0
                ,mAuthorPhoto);
        mAuthorName.setText(mResult.getUser().getName());
        mPhotoTime.setText("拍摄于: " +
                mResult.getCreated_at().split("T")[0]);

        mPresenter.getPhotoData(mResult.getId());
    }

    @Override
    protected PhotoPresenter createPresenter() {
        return mPresenter = new PhotoPresenter(getContext());
    }

}