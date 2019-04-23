package com.lwlizhe.novel.mvp.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageConfig;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;
import com.lwlizhe.AppApplication;
import com.lwlizhe.GlobeConstance;
import com.lwlizhe.library.novel.R;
import com.lwlizhe.novel.api.entity.NovelReCommendEntity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelRecommendIBannerHolder extends BaseHolder<NovelReCommendEntity> {

    public Banner mBanner;
//    @BindView(R.id.rgp_select_area)
//    public RadioGroup mRadioGroup;
//    @BindView(R.id.rbt_follow_novel)
//    public RadioButton mRbtFollowNovel;
//    @BindView(R.id.rbt_search_novel)
//    public RadioButton mRbtSearchNovel;
//    @BindView(R.id.rbt_novel_chart)
//    public RadioButton mRbtNovelChart;

//    private ImageLoader mGlideImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final AppApplication mApplication;
    private GlideImageLoaderStrategy mGlideImageLoader;

    private List<NovelReCommendEntity.DataBean> datas;

    public NovelRecommendIBannerHolder(View itemView) {
        super(itemView);

        mBanner=itemView.findViewById(R.id.banner);


        mApplication = (AppApplication) itemView.getContext().getApplicationContext();
        mGlideImageLoader = mApplication.getAppComponent().mGlideImageLoader();

    }

    @Override
    public void setData(NovelReCommendEntity data, int position) {

        setBannerData(data);
    }

    public void setBannerData(NovelReCommendEntity mBannerData){

        ArrayList<String> bannerImages=new ArrayList<>();
        ArrayList<String> bannerTitles=new ArrayList<>();


        datas = mBannerData.getData();

        for(NovelReCommendEntity.DataBean data:datas){
            bannerImages.add(data.getCover());
            bannerTitles.add(data.getTitle());
        }

        setBanner(bannerImages,bannerTitles);

    }

    public void setBanner(ArrayList<String> images, ArrayList<String> titles) {
        mBanner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                mGlideImageLoader.loadImage(mApplication, GlideImageConfig
                        .builder()
                        .url((String) path)
                        .imageView(imageView)
                        .refererUrl(GlobeConstance.CONSTANCE_URL.DMZJ_IMG_REFERER_URL)
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

    public void setBannerClickListener(final OnBannerClickListener listener){
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                listener.OnBannerClick(position,datas.get(position));
            }
        });
    }

    public interface OnBannerClickListener{
        void OnBannerClick(int position, NovelReCommendEntity.DataBean itemData);
    }
}
