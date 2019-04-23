package com.lwlizhe.video.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.mvp.ui.adapter.holder.ScheduleWeekHolder;

import java.util.List;

import static com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainWeekHolder.FUNCTION_TYPE_ITEM_CLICK;
import static com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainWeekHolder.FUNCTION_TYPE_ITEM_DRAMA_CLICK;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class ScheduleWeekAdapter extends BaseRecyclerViewAdapter<DilidiliIndexEntity.DataBean.WeekListBean> {

    private OnItemFunctionClickListener mFunctionClickListener;

    public ScheduleWeekAdapter(List<DilidiliIndexEntity.DataBean.WeekListBean> infos) {
        super(infos);
        this.setOnItemClickListener((view, viewType, data, position) -> {
            if(mFunctionClickListener!=null){
                mFunctionClickListener.onFunctionEnable(FUNCTION_TYPE_ITEM_CLICK,data);
            }
        });
    }

    @Override
    public BaseHolder<DilidiliIndexEntity.DataBean.WeekListBean> getHolder(View v, int viewType) {
        return new ScheduleWeekHolder(v, functionData -> {
            if(mFunctionClickListener!=null){
                mFunctionClickListener.onFunctionEnable(FUNCTION_TYPE_ITEM_DRAMA_CLICK,functionData);
            }
        });
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.video_item_video_week_item;
    }

    public void setFunctionEnableListener(OnItemFunctionClickListener listener){
        mFunctionClickListener=listener;
    }

    public interface OnItemFunctionClickListener{
        void onFunctionEnable(int functionType,Object functionData);
    }
}
