package com.example.yuxuehai.wallpager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.wallpager.R;
import com.example.yuxuehai.wallpager.base.BaseRecyclerAdapter;

/**
 * Created by yuxuehai on 17-12-2.
 */

public class PhotoesRecycleAdapter extends BaseRecyclerAdapter<String, PhotoesRecycleAdapter.PhotoesViewHolder> {


    @Override
    public PhotoesViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photoes_item_layout
                , parent, false);

        PhotoesViewHolder holder = new PhotoesViewHolder(view);
        return holder;
    }

    @Override
    public void onBind(PhotoesViewHolder viewHolder, int RealPosition, String data) {
        viewHolder.mItemName.setText(data);
    }


    static class PhotoesViewHolder extends BaseRecyclerAdapter.Holder{

        private ImageView mImageView;
        private TextView mItemName;

        public PhotoesViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_image);
            mItemName = itemView.findViewById(R.id.tv_name);
        }
    }
}
