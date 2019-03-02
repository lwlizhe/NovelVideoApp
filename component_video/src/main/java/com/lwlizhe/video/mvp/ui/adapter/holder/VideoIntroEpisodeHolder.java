package com.lwlizhe.video.mvp.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.api.entity.DilidiliAnimationDetailResponseEntity;

public class VideoIntroEpisodeHolder extends BaseHolder<DilidiliAnimationDetailResponseEntity.DataBean.EpisodeListBean> {

        private TextView mTvwEpisodeTextView;

       public VideoIntroEpisodeHolder(View itemView) {
            super(itemView);

            mTvwEpisodeTextView =  itemView.findViewById(R.id.tvw_episode_name);
        }

        @Override
        public void setData(DilidiliAnimationDetailResponseEntity.DataBean.EpisodeListBean data, int position) {
            mTvwEpisodeTextView.setText(data.getName());
        }
    }