package com.lwlizhe.novelvideoapp.video.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.DilidiliInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainAdapter extends BaseRecyclerViewAdapter<DilidiliInfo> {

    public VideoMainAdapter(List<DilidiliInfo> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<DilidiliInfo> getHolder(View v, int viewType) {
        return null;
    }

    @Override
    public int getLayoutId(int viewType) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
