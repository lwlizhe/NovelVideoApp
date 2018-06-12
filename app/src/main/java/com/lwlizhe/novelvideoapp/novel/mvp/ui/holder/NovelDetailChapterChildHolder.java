package com.lwlizhe.novelvideoapp.novel.mvp.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lwlizhe.basemodule.base.adapter.BaseExpandItemEntity;
import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelChapterEntity;

/**
 * Created by Administrator on 2018/6/5 0005.
 */

public class NovelDetailChapterChildHolder extends BaseHolder<BaseExpandItemEntity> {

    private Context mContext;

    public NovelDetailChapterChildHolder(View itemView) {
        super(itemView);
        mContext=itemView.getContext();
    }

    @Override
    public void setData(final BaseExpandItemEntity data, int position) {

        NovelChapterEntity.ChaptersBean chapterEntity= (NovelChapterEntity.ChaptersBean) data;

        TextView mTvwChapter = itemView.findViewById(R.id.tvw_child_chapter);

        mTvwChapter.setText(chapterEntity.getChapter_name());

    }
}
