package com.lwlizhe.video.mvp.ui.adapter.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.lwlizhe.AppApplication;
import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageConfig;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;

public class VideoItemEditPickHolder extends BaseHolder<DilidiliIndexEntity.DataBean.EditorPickBean> {

    private ImageView mIvwCover;
    private TextView mTvwName;

    private GlideImageLoaderStrategy mImageLoader;

    private Context mContext;

    public VideoItemEditPickHolder(View itemView) {
        super(itemView);

        mContext=itemView.getContext();

        mIvwCover = itemView.findViewById(R.id.ivw_cover);
        mTvwName = itemView.findViewById(R.id.tvw_name);

        AppApplication mApplication = (AppApplication) itemView.getContext().getApplicationContext();

        mImageLoader = mApplication.getAppComponent().mGlideImageLoader();
    }

    @Override
    public void setData(DilidiliIndexEntity.DataBean.EditorPickBean data, int position) {
//        mImageLoader.loadImage(mContext, GlideImageConfig.builder()
//                .url(data.getImgUrl())
//                .centerCrop()
//                .imageView(mIvwCover)
//                .build());
//
        Glide.with(itemView).asBitmap().load(data.getImgUrl()).into(mIvwCover).waitForLayout();

        mIvwCover.post(new Runnable() {
            @Override
            public void run() {
            }
        });
        mTvwName.setText(data.getName());

    }
}
