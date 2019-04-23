package com.lwlizhe.video.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainEditPickEntity;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoItemEditPickHolder;

import java.util.List;

public class VideoItemEditPickAdapter extends BaseRecyclerViewAdapter<DilidiliIndexEntity.DataBean.EditorPickBean> {
    public VideoItemEditPickAdapter(List<DilidiliIndexEntity.DataBean.EditorPickBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<DilidiliIndexEntity.DataBean.EditorPickBean> getHolder(View v, int viewType) {
        return new VideoItemEditPickHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.video_item_video_edit_pick_item;
    }
}
