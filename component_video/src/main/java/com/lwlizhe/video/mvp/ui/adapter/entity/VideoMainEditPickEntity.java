package com.lwlizhe.video.mvp.ui.adapter.entity;

import com.lwlizhe.video.api.entity.DilidiliIndexEntity;

import java.util.List;

public class VideoMainEditPickEntity extends VideoMainAdapterEntity<List<DilidiliIndexEntity.DataBean.EditorPickBean>> {

    public VideoMainEditPickEntity() {
    }

    public VideoMainEditPickEntity(List<DilidiliIndexEntity.DataBean.EditorPickBean> carouselData) {
        mData=carouselData;
    }

    @Override
    public int getType() {
        return MULTI_TYPE_EDIT_PICK;
    }
}
