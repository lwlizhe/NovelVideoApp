package com.lwlizhe.video.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.api.entity.jsoup.DilidiliInfo;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainBannerHolder;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainRecentHolder;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainRecommendHolder;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainWeekHolder;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainAdapter extends RecyclerView.Adapter<BaseHolder> {

    private DilidiliInfo info;

    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;
    protected VideoMainBannerHolder.OnBannerClickListener mBannerItemClickListener = null;

    private final int TYPE_BANNER = 0;
    private final int TYPE_WEEK = 1;
    private final int TYPE_RECENT = 2;
    private final int TYPE_RECOMMEND = 3;

    private int curWeekTagPos=-1;

    public void setDilidiliInfo(DilidiliInfo info) {

        this.info = info;

    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        BaseHolder mHolder = getHolder(view, viewType);
        mHolder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
            @Override
            public void onViewClick(View view, int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, viewType, info, position);
                }
            }
        });
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_BANNER:
                holder.setData(info.getScheduleBanners(), position);
                break;
            case TYPE_WEEK:
                holder.setData(info.getScheduleWeek(), curWeekTagPos);
                break;
            case TYPE_RECENT:
                holder.setData(info.getScheduleRecents(), position);
                break;
            case TYPE_RECOMMEND:
                holder.setData(info.getScheduleRecommends(), position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return info == null ? 0 : 4;
    }

    @Override
    public int getItemViewType(int position) {

        int type = 0;

        switch (position) {
            case 0:
                type = TYPE_BANNER;
                break;
            case 1:
                type = TYPE_WEEK;
                break;
            case 2:
                type = TYPE_RECENT;
                break;
            case 3:
                type = TYPE_RECOMMEND;
                break;
        }


        return type;
    }

    private int getLayoutId(int viewType) {
        int layoutId = 0;

        switch (viewType) {
            case TYPE_BANNER:
                layoutId = R.layout.item_video_banner;
                break;
            case TYPE_WEEK:
                layoutId = R.layout.item_video_week;
                break;
            case TYPE_RECENT:
                layoutId = R.layout.item_video_recent;
                break;
            case TYPE_RECOMMEND:
                layoutId = R.layout.item_video_recommend;
                break;
        }

        return layoutId;

    }

    public BaseHolder getHolder(View v, int viewType) {

        BaseHolder holder = null;

        switch (viewType) {
            case TYPE_BANNER:
                holder = new VideoMainBannerHolder(v);
                ((VideoMainBannerHolder)holder).setBannerClickListener(new VideoMainBannerHolder.OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(View view, DilidiliIndexEntity.DataBean.CarouselBean itemData, int position) {
                        if(mOnItemClickListener!=null){
                            mOnItemClickListener.onItemClick(view,TYPE_BANNER,itemData,position);
                        }
                    }
                });
                break;
            case TYPE_WEEK:
                holder = new VideoMainWeekHolder(v);
                ((VideoMainWeekHolder)holder).setOnTagSelectedChangedListener(new VideoMainWeekHolder.OnTagSelectedChangedListener() {
                    @Override
                    public void onTagSelectedChanged(int currentPos) {
                        curWeekTagPos=currentPos;
                    }
                });
                ((VideoMainWeekHolder)holder).setOnWeekItemClickListener(new VideoMainWeekHolder.OnWeekItemClickListener() {
                    @Override
                    public void onWeekItemClick(View view, DilidiliIndexEntity.DataBean.WeekListBean data, int pos) {
                        if(mOnItemClickListener!=null){
                            mOnItemClickListener.onItemClick(view,TYPE_WEEK,data,pos);
                        }
                    }
                });
                break;
            case TYPE_RECENT:
                holder = new VideoMainRecentHolder(v);
                break;
            case TYPE_RECOMMEND:
                holder = new VideoMainRecommendHolder(v);
                break;
        }

        return holder;
    }


    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int viewType, Object data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnBannerItemClickListener(VideoMainBannerHolder.OnBannerClickListener listener){
        mBannerItemClickListener=listener;
    }

}
