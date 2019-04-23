package com.lwlizhe.video.mvp.ui.adapter.holder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.video.mvp.ui.adapter.VideoItemEditPickAdapter;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainAdapterEntity;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainEditPickEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainEditPickHolder extends BaseHolder<VideoMainAdapterEntity> {

    private VideoMainEditPickEntity mEditPickEntity;

    private RecyclerView mRvwEditPick;
    private VideoItemEditPickAdapter mEditPickItemAdapter;

    private List<DilidiliIndexEntity.DataBean.EditorPickBean> mItemData;

    public VideoMainEditPickHolder(View itemView) {
        super(itemView);

        mItemData=new ArrayList<>();

        mRvwEditPick = itemView.findViewById(R.id.rvw_edit_pick);

        GridLayoutManager layoutManager = new GridLayoutManager(itemView.getContext(), 2);

//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 2;
//            }
//        });

        mRvwEditPick.setLayoutManager(layoutManager);
        mEditPickItemAdapter=new VideoItemEditPickAdapter(mItemData);
        mRvwEditPick.setAdapter(mEditPickItemAdapter);
    }

    @Override
    public void setData(VideoMainAdapterEntity data, int position) {
        VideoMainEditPickEntity editPickEntity = (VideoMainEditPickEntity) data;
        mEditPickEntity = editPickEntity;

        mItemData.clear();
        mItemData.addAll(mEditPickEntity.getData());
        mEditPickItemAdapter.notifyDataSetChanged();
    }
}
