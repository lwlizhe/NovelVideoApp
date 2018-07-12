package com.lwlizhe.video.mvp.ui.adapter.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.common.api.video.entity.BaseMultiItemData;
import com.lwlizhe.common.api.video.entity.jsoup.ScheduleWeek;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.mvp.ui.adapter.ScheduleWeekAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainWeekHolder extends BaseHolder<List<BaseMultiItemData>> {

    private RadioGroup mRgpWeek;
    private RadioButton mRbtMonday, mRbtTuesday, mRbtWednesday, mRbtThursday, mRbtFriday, mRbtSaturday, mRbtSunday;

    private RecyclerView mRvwScheduleWeek;

    private List<ScheduleWeek.ScheduleItem> mScheduleList;
    private List<BaseMultiItemData> mInfos;

    private OnTagSelectedChangedListener tagListener;
    private OnWeekItemClickListener weekListener;

    ScheduleWeekAdapter adapter;

    public VideoMainWeekHolder(View itemView) {
        super(itemView);

        mScheduleList = new ArrayList<>();
        mInfos=new ArrayList<>();

        adapter = new ScheduleWeekAdapter(mScheduleList);

        mRgpWeek = itemView.findViewById(R.id.rgp_week_group);

        mRbtMonday = itemView.findViewById(R.id.rbt_monday);
        mRbtTuesday = itemView.findViewById(R.id.rbt_tuesday);
        mRbtWednesday = itemView.findViewById(R.id.rbt_wednesday);
        mRbtThursday = itemView.findViewById(R.id.rbt_thursday);
        mRbtFriday = itemView.findViewById(R.id.rbt_friday);
        mRbtSaturday = itemView.findViewById(R.id.rbt_saturday);
        mRbtSunday = itemView.findViewById(R.id.rbt_sunday);

        mRvwScheduleWeek = itemView.findViewById(R.id.rvw_week);

        mRvwScheduleWeek.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        mRvwScheduleWeek.setAdapter(adapter);

        mRgpWeek.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkPos=0;

                if (checkedId == R.id.rbt_monday) {
                    checkPos=0;
                } else if (checkedId == R.id.rbt_tuesday) {
                    checkPos=1;
                } else if (checkedId == R.id.rbt_wednesday) {
                    checkPos=2;
                } else if (checkedId == R.id.rbt_thursday) {
                    checkPos=3;
                } else if (checkedId == R.id.rbt_friday) {
                    checkPos=4;
                } else if (checkedId == R.id.rbt_saturday) {
                    checkPos=5;
                } else if (checkedId == R.id.rbt_sunday) {
                    checkPos=6;
                }

                setData(mInfos,checkPos);

                if(tagListener!=null){
                    tagListener.onTagSelectedChanged(checkPos);
                }
            }
        });

        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<ScheduleWeek.ScheduleItem>() {
            @Override
            public void onItemClick(View view, int viewType, ScheduleWeek.ScheduleItem data, int position) {

                if(weekListener!=null){
                    weekListener.onWeekItemClick(view,data,position);
                }

            }
        });

    }

    @Override
    public void setData(List<BaseMultiItemData> data, int position) {

        mInfos=data;

        int currentTagPos = position == -1 ? calTodayWeekPos() : position;

        List<ScheduleWeek.ScheduleItem> scheduleWeekList;

        ScheduleWeek weekList = (ScheduleWeek) data.get(currentTagPos);

        scheduleWeekList = weekList.getScheduleItems();

        mScheduleList.clear();
        mScheduleList.addAll(scheduleWeekList);

        adapter.notifyDataSetChanged();

    }

    private int calTodayWeekPos() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) -2;
    }

    public void setOnTagSelectedChangedListener(OnTagSelectedChangedListener listener) {
        this.tagListener=listener;
    }

    public void setOnWeekItemClickListener(OnWeekItemClickListener listener){
        this.weekListener=listener;
    }

    public interface OnTagSelectedChangedListener {
        void onTagSelectedChanged(int currentPos);
    }

    public interface  OnWeekItemClickListener{
        void onWeekItemClick(View view,ScheduleWeek.ScheduleItem data,int pos);
    }


}
