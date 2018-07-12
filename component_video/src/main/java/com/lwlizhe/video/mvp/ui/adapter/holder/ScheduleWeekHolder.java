package com.lwlizhe.video.mvp.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.common.api.video.entity.jsoup.ScheduleWeek;
import com.lwlizhe.library.video.R;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class ScheduleWeekHolder extends BaseHolder<ScheduleWeek.ScheduleItem> {

    private TextView mVideoName;
    private TextView mVideoLatestEpisode;

    public ScheduleWeekHolder(View itemView) {
        super(itemView);

        mVideoName = itemView.findViewById(R.id.tvw_video_name);
        mVideoLatestEpisode = itemView.findViewById(R.id.tvw_video_episode);

    }

    @Override
    public void setData(ScheduleWeek.ScheduleItem data, int position) {

        if(data==null){
            return;
        }

        mVideoName.setText(data.getName());
        mVideoLatestEpisode.setText(data.getEpisode());
    }
}
