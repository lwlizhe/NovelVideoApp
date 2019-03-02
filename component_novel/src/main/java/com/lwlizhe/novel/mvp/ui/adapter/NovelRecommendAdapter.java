package com.lwlizhe.novel.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;

import com.lwlizhe.novel.api.entity.NovelReCommendEntity;
import com.lwlizhe.library.novel.R;
import com.lwlizhe.novel.mvp.ui.holder.NovelRecommendIBannerHolder;
import com.lwlizhe.novel.mvp.ui.holder.NovelRecommendNormalHolder;

import java.util.List;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelRecommendAdapter extends BaseRecyclerViewAdapter<NovelReCommendEntity> {

    private final int VIEW_TYPE_BANNER=1;
    private final int VIEW_TYPE_NORMAL=0;

    private NovelRecommendNormalHolder.NormalItemImageClickListener mNormalItemClickListener;
    private NovelRecommendIBannerHolder.OnBannerClickListener mBannerListener;


    public NovelRecommendAdapter(List<NovelReCommendEntity> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<NovelReCommendEntity> getHolder(View v, int viewType) {

        BaseHolder<NovelReCommendEntity> holder = null;

        switch (viewType){
            case VIEW_TYPE_BANNER:
                holder=new NovelRecommendIBannerHolder(v);

                if(mBannerListener!=null){
                    ((NovelRecommendIBannerHolder)holder).setBannerClickListener(mBannerListener);
                }

                break;
            case VIEW_TYPE_NORMAL:
            default:

                holder=new NovelRecommendNormalHolder(v);
                ((NovelRecommendNormalHolder)holder).setOnNormalItemImageClickListener(mNormalItemClickListener);

                break;
        }
        return holder;
    }

    @Override
    public int getLayoutId(int viewType) {

        int viewId=0;

        switch (viewType){
            case VIEW_TYPE_BANNER:
                viewId= R.layout.layout_novel_recommend_banner;
                break;
            case VIEW_TYPE_NORMAL:
            default:
                viewId= R.layout.layout_novel_recommend_normal;
                break;
        }
        return viewId;
    }

    @Override
    public int getItemViewType(int position) {

        int type=0;
        switch (position){
            case 0:
                type=VIEW_TYPE_BANNER;
                break;
            default:
                type=VIEW_TYPE_NORMAL;
                break;
        }

        return type;
    }

    /**
     * 设置NormalItem点击事件
     */
    public void setNormalItemClickListener(NovelRecommendNormalHolder.NormalItemImageClickListener listener){

        this.mNormalItemClickListener=listener;

    }

    public void setBannerClickListener(NovelRecommendIBannerHolder.OnBannerClickListener listener){
        this.mBannerListener=listener;
    }



}
