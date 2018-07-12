package com.lwlizhe.video.mvp.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageConfig;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;
import com.lwlizhe.AppApplication;
import com.lwlizhe.GlobeConstance;

import com.lwlizhe.common.api.video.entity.BaseMultiItemData;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo;
import com.lwlizhe.library.video.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainBannerHolder extends BaseHolder<List<BaseMultiItemData>> {

    private Banner mBanner;
    private Context mContext;

    private List<BaseMultiItemData> mBannerData;

    private GlideImageLoaderStrategy mImageLoader;

    public VideoMainBannerHolder(View itemView) {
        super(itemView);

        mContext = itemView.getContext();
        mBannerData = new ArrayList<>();

        mImageLoader = ((AppApplication) mContext.getApplicationContext()).getAppComponent().mGlideImageLoader();

        mBanner = itemView.findViewById(R.id.banner);

    }

    @Override
    public void setData(List<BaseMultiItemData> data, int position) {
        mBannerData.clear();
        mBannerData.addAll(data);

        setBannerData(mBannerData);
    }

    public void setBannerData(List<BaseMultiItemData> mBannerData) {

        ArrayList<String> bannerImages = new ArrayList<>();
        ArrayList<String> bannerTitles = new ArrayList<>();


        for (BaseMultiItemData data : mBannerData) {
            DilidiliInfo.ScheudleBanner bannerData= (DilidiliInfo.ScheudleBanner) data;
            bannerImages.add(bannerData.getImgUrl());
            bannerTitles.add(bannerData.getName());
        }

        setBanner(bannerImages, bannerTitles);

    }

    public void setBanner(ArrayList<String> images, ArrayList<String> titles) {
        mBanner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                mImageLoader.loadImage(mContext, GlideImageConfig
                        .builder()
                        .url((String) path)
                        .imageView(imageView)
                        .refererUrl(GlobeConstance.DMZJ_IMG_REFERER_URL)
                        .build());
            }
        });

        mBanner.setImages(images);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setBannerAnimation(Transformer.Default);
        mBanner.setBannerTitles(titles);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);
        mBanner.start();
    }

    public void setBannerClickListener(final OnBannerClickListener listener) {
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                listener.OnBannerClick(VideoMainBannerHolder.this.itemView,(DilidiliInfo.ScheudleBanner) mBannerData.get(position),position);
            }
        });
    }

    public interface OnBannerClickListener {
        void OnBannerClick(View view, DilidiliInfo.ScheudleBanner itemData,int position);
    }


}
