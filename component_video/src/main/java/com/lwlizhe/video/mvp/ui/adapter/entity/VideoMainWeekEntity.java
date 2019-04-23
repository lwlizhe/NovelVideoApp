package com.lwlizhe.video.mvp.ui.adapter.entity;

import com.lwlizhe.video.api.entity.DilidiliIndexEntity;

import java.util.List;

/**
 * Created by lizhe on 2019/4/11.
 */

public class VideoMainWeekEntity extends VideoMainAdapterEntity<List<List<DilidiliIndexEntity.DataBean.WeekListBean>>> {

    public VideoMainWeekEntity() {
    }

    public VideoMainWeekEntity(List<List<DilidiliIndexEntity.DataBean.WeekListBean>> carouselData) {
        mData=carouselData;
    }

    @Override
    public int getType() {
        return MULTI_TYPE_WEEK;
    }



}
