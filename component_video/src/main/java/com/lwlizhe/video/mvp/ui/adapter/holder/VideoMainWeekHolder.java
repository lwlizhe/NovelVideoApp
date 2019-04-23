package com.lwlizhe.video.mvp.ui.adapter.holder;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.video.mvp.ui.adapter.ScheduleWeekAdapter;
import com.lwlizhe.video.mvp.ui.adapter.VideoMainAdapter;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainAdapterEntity;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainWeekEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainWeekHolder extends BaseHolder<VideoMainAdapterEntity> {

    public static final int FUNCTION_TYPE_ITEM_CLICK = 0;
    public static final int FUNCTION_TYPE_ITEM_DRAMA_CLICK = 1;

    private TabLayout mWeekTab;
    private RecyclerView mRvwWeekData;

    private ScheduleWeekAdapter currentScheduleAdapter;

    private int currentSelectedPos = 0;

    private VideoMainWeekEntity mCurrentWeekEntity;
    private List<DilidiliIndexEntity.DataBean.WeekListBean> currentWeekBeansList;

    private VideoMainAdapter.OnItemFunctionClickListener mFunctionClickListener;

    public VideoMainWeekHolder(View itemView) {
        super(itemView);

        currentWeekBeansList = new ArrayList<>();

        mWeekTab = itemView.findViewById(R.id.tab_week);
        mRvwWeekData = itemView.findViewById(R.id.rvw_week);

        mWeekTab.setTabMode(MODE_SCROLLABLE);
        mWeekTab.removeAllTabs();
        mWeekTab.addTab(mWeekTab.newTab().setText("星期一"));
        mWeekTab.addTab(mWeekTab.newTab().setText("星期二"));
        mWeekTab.addTab(mWeekTab.newTab().setText("星期三"));
        mWeekTab.addTab(mWeekTab.newTab().setText("星期四"));
        mWeekTab.addTab(mWeekTab.newTab().setText("星期五"));
        mWeekTab.addTab(mWeekTab.newTab().setText("星期六"));
        mWeekTab.addTab(mWeekTab.newTab().setText("星期天"));

        mRvwWeekData.setLayoutManager(new LinearLayoutManager(itemView.getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        currentScheduleAdapter = new ScheduleWeekAdapter(currentWeekBeansList);
        mRvwWeekData.setAdapter(currentScheduleAdapter);

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if (dayOfWeek >= 0){
            currentSelectedPos=dayOfWeek;
        }

        mWeekTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != currentSelectedPos) {
                    currentSelectedPos = tab.getPosition();
                    currentWeekBeansList.clear();
                    currentWeekBeansList.addAll(mCurrentWeekEntity.getData().get(currentSelectedPos));

                    currentScheduleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        currentScheduleAdapter.setFunctionEnableListener((functionType, functionData) -> {
            switch
            (functionType) {
                case FUNCTION_TYPE_ITEM_CLICK:
                    if (mFunctionClickListener != null) {
                        mFunctionClickListener.onItemFunctionEnable(FUNCTION_TYPE_ITEM_CLICK, functionData);
                    }
                    break;
                case FUNCTION_TYPE_ITEM_DRAMA_CLICK:
                    if (mFunctionClickListener != null) {
                        mFunctionClickListener.onItemFunctionEnable(FUNCTION_TYPE_ITEM_DRAMA_CLICK, functionData);
                    }
                    break;
            }
        });

    }

    @Override
    public void setData(VideoMainAdapterEntity data, int position) {

        VideoMainWeekEntity weekEntity = (VideoMainWeekEntity) data;
        mCurrentWeekEntity = weekEntity;

        if (weekEntity.getData() != null && weekEntity.getData().get(currentSelectedPos) != null) {
            currentWeekBeansList.clear();
            currentWeekBeansList.addAll(weekEntity.getData().get(currentSelectedPos));
            currentScheduleAdapter.notifyDataSetChanged();
        }

    }

    public void setFunctionClickListener(VideoMainAdapter.OnItemFunctionClickListener listener) {
        this.mFunctionClickListener = listener;
    }

}
