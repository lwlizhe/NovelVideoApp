package com.lwlizhe.video.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.common.api.video.entity.jsoup.ScheduleWeek;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.mvp.ui.adapter.holder.ScheduleWeekHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class ScheduleWeekAdapter extends BaseRecyclerViewAdapter<ScheduleWeek.ScheduleItem> {
    public ScheduleWeekAdapter(List<ScheduleWeek.ScheduleItem> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ScheduleWeek.ScheduleItem> getHolder(View v, int viewType) {
        return new ScheduleWeekHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_week;
    }
}
