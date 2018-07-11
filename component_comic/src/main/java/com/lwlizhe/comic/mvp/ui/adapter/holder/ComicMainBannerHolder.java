package com.lwlizhe.comic.mvp.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lwlizhe.AppApplication;
import com.lwlizhe.GlobeConstance;
import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageConfig;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;
import com.lwlizhe.common.api.comic.entity.ComicRecommendResponse;
import com.lwlizhe.common.api.novel.entity.NovelReCommendEntity;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo;
import com.lwlizhe.library.comic.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/11 0011.
 */

public class ComicMainBannerHolder extends BaseHolder<ComicRecommendResponse> {

    private Banner mBanner;
    private final AppApplication mApplication;
    private GlideImageLoaderStrategy mGlideImageLoader;

    private List<ComicRecommendResponse.DataBean> datas;

    public ComicMainBannerHolder(View itemView) {
        super(itemView);


        mBanner = itemView.findViewById(R.id.banner);
        mApplication = (AppApplication) itemView.getContext().getApplicationContext();
        mGlideImageLoader = mApplication.getAppComponent().mGlideImageLoader();
    }

    @Override
    public void setData(ComicRecommendResponse data, int position) {
        setBannerData(data);
    }

    private void setBannerData(ComicRecommendResponse mBannerData){

        ArrayList<String> bannerImages=new ArrayList<>();
        ArrayList<String> bannerTitles=new ArrayList<>();


        datas = mBannerData.getData();

        for(ComicRecommendResponse.DataBean data:datas){
            bannerImages.add(data.getCover());
            bannerTitles.add(data.getTitle());
        }

        setBanner(bannerImages,bannerTitles);

    }

    private void setBanner(ArrayList<String> images, ArrayList<String> titles) {
        mBanner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                mGlideImageLoader.loadImage(mApplication, GlideImageConfig
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
                listener.OnBannerClick(ComicMainBannerHolder.this.itemView,datas.get(position),position);
            }
        });
    }

    public interface OnBannerClickListener {
        void OnBannerClick(View view, ComicRecommendResponse.DataBean itemData,int position);
    }
}
