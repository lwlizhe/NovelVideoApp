package com.lwlizhe.video.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainAdapterEntity;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainBannerHolder;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainEditPickHolder;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainUgcPickHolder;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainWeekHolder;

import java.util.List;

import static com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainAdapterEntity.MULTI_TYPE_BANNER;
import static com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainAdapterEntity.MULTI_TYPE_EDIT_PICK;
import static com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainAdapterEntity.MULTI_TYPE_UGC_PICK;
import static com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainAdapterEntity.MULTI_TYPE_WEEK;
import static com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainWeekHolder.FUNCTION_TYPE_ITEM_CLICK;
import static com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainWeekHolder.FUNCTION_TYPE_ITEM_DRAMA_CLICK;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainAdapter extends BaseRecyclerViewAdapter<VideoMainAdapterEntity> {

    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;
    protected VideoMainBannerHolder.OnBannerClickListener mBannerItemClickListener = null;

    private int curWeekTagPos=-1;

    public VideoMainAdapter(List<VideoMainAdapterEntity> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<VideoMainAdapterEntity> getHolder(View v, int viewType) {


        BaseHolder<VideoMainAdapterEntity> holder = null;

        switch (viewType) {
            case MULTI_TYPE_BANNER:
                holder = new VideoMainBannerHolder(v);
                ((VideoMainBannerHolder)holder).setBannerClickListener((view, itemData, position) -> {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(view,MULTI_TYPE_BANNER,itemData,position);
                    }
                });
                break;
            case MULTI_TYPE_WEEK:
                holder = new VideoMainWeekHolder(v);
                ((VideoMainWeekHolder)holder).setFunctionClickListener((functionType, data) -> {
                    switch (functionType){
                        case FUNCTION_TYPE_ITEM_CLICK:
                            break;
                        case FUNCTION_TYPE_ITEM_DRAMA_CLICK:
                            break;
                    }
                });
                break;
            case MULTI_TYPE_EDIT_PICK:
                holder = new VideoMainEditPickHolder(v);
                break;
            case MULTI_TYPE_UGC_PICK:
                holder = new VideoMainUgcPickHolder(v);
                break;
        }

        return holder;

    }

    @Override
    public int getLayoutId(int viewType) {
        int layoutId = 0;

        switch (viewType) {
            case MULTI_TYPE_BANNER:
                layoutId = R.layout.video_item_video_banner;
                break;
            case MULTI_TYPE_WEEK:
                layoutId = R.layout.video_item_video_week;
                break;
            case MULTI_TYPE_EDIT_PICK:
                layoutId = R.layout.video_item_video_edit_pick;
                break;
            case MULTI_TYPE_UGC_PICK:
                layoutId = R.layout.video_item_video_ugc_pick;
                break;
        }

        return layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        return mInfos.get(position).getType();
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

    public interface OnItemFunctionClickListener{

        void onItemFunctionEnable(int functionType,Object data);

    }


}
