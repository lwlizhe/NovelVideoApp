package com.lwlizhe.video.mvp.ui.adapter.entity;

import com.lwlizhe.video.api.entity.DilidiliIndexEntity;

import java.util.List;

public class VideoMainUgcPickEntity extends VideoMainAdapterEntity<List<DilidiliIndexEntity.DataBean.UgcPickBean>> {

    public VideoMainUgcPickEntity() {
    }

    public VideoMainUgcPickEntity(List<DilidiliIndexEntity.DataBean.UgcPickBean> carouselData) {
        mData=carouselData;
    }

    @Override
    public int getType() {
        return MULTI_TYPE_UGC_PICK;
    }
}
