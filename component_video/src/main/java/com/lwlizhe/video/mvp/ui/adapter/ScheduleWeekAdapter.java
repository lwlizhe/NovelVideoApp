package com.lwlizhe.video.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.mvp.ui.adapter.holder.ScheduleWeekHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class ScheduleWeekAdapter extends BaseRecyclerViewAdapter<DilidiliIndexEntity.DataBean.WeekListBean> {
    public ScheduleWeekAdapter(List<DilidiliIndexEntity.DataBean.WeekListBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<DilidiliIndexEntity.DataBean.WeekListBean> getHolder(View v, int viewType) {
        return new ScheduleWeekHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_week;
    }
}
