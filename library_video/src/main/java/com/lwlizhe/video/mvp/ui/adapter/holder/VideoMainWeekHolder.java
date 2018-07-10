package com.lwlizhe.video.mvp.ui.adapter.holder;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo;
import com.lwlizhe.common.api.video.entity.jsoup.ScheduleWeek;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainWeekHolder extends BaseHolder<List<DilidiliInfo.BaseMultiItemData>> {

    public VideoMainWeekHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(List<DilidiliInfo.BaseMultiItemData> data, int position) {

        for(DilidiliInfo.BaseMultiItemData itemData:data){
            ScheduleWeek week= (ScheduleWeek) itemData;
            week.getScheduleItems();
        }

    }


}
