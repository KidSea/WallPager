package com.example.yuxuehai.wallpager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BaseRecyclerAdapter;
import com.example.yuxuehai.wallpager.data.bean.UnsplashResult;
import com.example.yuxuehai.wallpager.utils.GlideUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxuehai on 17-12-2.
 */

public class PhotoesRecycleAdapter extends BaseRecyclerAdapter<UnsplashResult, PhotoesRecycleAdapter.PhotoesViewHolder> {

    public PhotoesRecycleAdapter(Context context, boolean loadMore) {
        mContext = context;
        mOpenLoadMore = loadMore;
    }

    @Override
    public PhotoesViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photoes_item_layout
                , parent, false);

        return new PhotoesViewHolder(view);
    }

    @Override
    public void onBind(PhotoesViewHolder viewHolder, int RealPosition, UnsplashResult data) {
        setAuthorInfor(viewHolder);

        if (viewHolder.mImagePager.getDrawable() != null) {
            viewHolder.mImagePager.setImageBitmap(null);
            viewHolder.mImagePager.refreshDrawableState();
        }

        if (data != null) {
            GlideUtils.loadImgSample(mContext, data.getUser().getProfile_image().getMedium()
                    , R.drawable.user_icon, 0, viewHolder.mAuthorHeader);
            GlideUtils.loadImgAutoHeight(mContext, data.getUrls().getRegular(),
                    R.drawable.imgtest, 0, viewHolder.mImagePager);
            viewHolder.mImagePager.setTag(data.getUrls().getRegular());
            viewHolder.mAuthorName.setText(data.getUser().getName());
            viewHolder.mAuthorLocation.setText(data.getUser().getLocation());
            viewHolder.mLikes.setText(String.valueOf(data.getUser().getTotal_likes()));
        }
    }

    private void setAuthorInfor(final PhotoesViewHolder viewHolder) {
        if (viewHolder.mImagePager.getMeasuredHeight() < 300) {
            viewHolder.mAuthorInfo.setVisibility(View.INVISIBLE);
            viewHolder.mLikes.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.mAuthorInfo.setVisibility(View.VISIBLE);
            viewHolder.mLikes.setVisibility(View.VISIBLE);
        }

        viewHolder.mCantainer.getViewTreeObserver().
                addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (viewHolder.mImagePager.getMeasuredHeight() > 300) {
                            viewHolder.mAuthorInfo.setVisibility(View.VISIBLE);
                            viewHolder.mLikes.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }


    static class PhotoesViewHolder extends BaseRecyclerAdapter.Holder {
        private CardView mCantainer;
        private ImageView mImagePager;
        private RelativeLayout mAuthorInfo;
        private CircleImageView mAuthorHeader;
        private TextView mAuthorName;
        private TextView mAuthorLocation;
        private TextView mLikes;

        public PhotoesViewHolder(View itemView) {
            super(itemView);
            mCantainer = (CardView) itemView.findViewById(R.id.container);
            mImagePager = (ImageView) itemView.findViewById(R.id.img_pager);
            mAuthorInfo = (RelativeLayout) itemView.findViewById(R.id.author_info);
            mAuthorHeader = (CircleImageView) itemView.findViewById(R.id.author_header);
            mAuthorName = (TextView) itemView.findViewById(R.id.author_name);
            mAuthorLocation = (TextView) itemView.findViewById(R.id.author_location);
            mLikes = (TextView) itemView.findViewById(R.id.tv_likes);
        }
    }
}
