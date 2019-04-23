package com.lwlizhe.video.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.api.entity.DilidiliAnimationDetailResponseEntity;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoIntroEpisodeHolder;

import java.util.List;

public class VideoIntroEpisodeAdapter extends BaseRecyclerViewAdapter<DilidiliAnimationDetailResponseEntity.DataBean.EpisodeListBean> {

    public VideoIntroEpisodeAdapter(List<DilidiliAnimationDetailResponseEntity.DataBean.EpisodeListBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<DilidiliAnimationDetailResponseEntity.DataBean.EpisodeListBean> getHolder(View v, int viewType) {

        return new VideoIntroEpisodeHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.video_item_intro_episode_item;
    }
}