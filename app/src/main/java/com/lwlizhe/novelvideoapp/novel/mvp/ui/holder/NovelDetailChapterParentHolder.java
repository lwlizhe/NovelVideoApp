package com.lwlizhe.novelvideoapp.novel.mvp.ui.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lwlizhe.basemodule.base.adapter.BaseExpandItemEntity;
import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.novelvideoapp.widget.ExpandLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/5 0005.
 */

public class NovelDetailChapterParentHolder extends BaseHolder<BaseExpandItemEntity> {

    private Context mContext;

    public NovelDetailChapterParentHolder(View itemView) {
        super(itemView);
        mContext=itemView.getContext();
    }

    @Override
    public void setData(final BaseExpandItemEntity data, int position) {

        NovelChapterEntity chapterEntity= (NovelChapterEntity) data;

        TextView mTvwChapter = itemView.findViewById(R.id.tvw_chapter);

        mTvwChapter.setText(chapterEntity.getVolume_name());

    }
}
