package com.lwlizhe.video.mvp.ui.adapter.entity;

import com.lwlizhe.video.api.entity.DilidiliIndexEntity;

import java.util.List;

/**
 * Created by lizhe on 2019/4/11.
 */

public class VideoMainBannerEntity extends VideoMainAdapterEntity<List<DilidiliIndexEntity.DataBean.CarouselBean>> {

    public VideoMainBannerEntity() {
    }

    public VideoMainBannerEntity(List<DilidiliIndexEntity.DataBean.CarouselBean> carouselData) {
        mData=carouselData;
    }

    @Override
    public int getType() {
        return MULTI_TYPE_BANNER;
    }



}
