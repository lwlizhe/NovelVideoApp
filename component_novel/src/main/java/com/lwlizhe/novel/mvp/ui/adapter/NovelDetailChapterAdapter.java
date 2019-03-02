package com.lwlizhe.novel.mvp.ui.adapter;

import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseExpandItemEntity;
import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;

import com.lwlizhe.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.library.novel.R;
import com.lwlizhe.novel.mvp.ui.holder.NovelDetailChapterChildHolder;
import com.lwlizhe.novel.mvp.ui.holder.NovelDetailChapterParentHolder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static com.lwlizhe.basemodule.base.adapter.BaseExpandItemEntity.CHILD_ITEM;
import static com.lwlizhe.basemodule.base.adapter.BaseExpandItemEntity.PARENT_ITEM;

/**
 * Created by Administrator on 2018/6/5 0005.
 */

public class NovelDetailChapterAdapter extends BaseRecyclerViewAdapter<BaseExpandItemEntity> {

    private onChildItemClickListener<NovelChapterEntity,NovelChapterEntity.ChaptersBean> mChildItemClickListener;

    public NovelDetailChapterAdapter(List<BaseExpandItemEntity> infos) {
        super(infos);
        init();
    }

    private void init() {

        initFirstLevelLabel();

    }

    private void initFirstLevelLabel() {

        setOnItemClickListener(new OnRecyclerViewItemClickListener<BaseExpandItemEntity>() {
            @Override
            public void onItemClick(final View view, int viewType, final BaseExpandItemEntity data, int position) {

                switch (viewType) {
                    case PARENT_ITEM:

                        NovelChapterEntity chapterEntity= (NovelChapterEntity) data;
                        if(!chapterEntity.isExpand()){
                            List<NovelChapterEntity.ChaptersBean> childDataList =  chapterEntity.getChildData();

                            for(NovelChapterEntity.ChaptersBean chaptersBean:childDataList){
                                chaptersBean.setParent(chapterEntity);
                            }

                            if (childDataList != null && childDataList.size() != 0) {
                                mInfos.addAll(position+1, childDataList);
                            }

                        }else{
                            List<NovelChapterEntity.ChaptersBean> childDataList = chapterEntity.getChildData();

                            if (childDataList != null && childDataList.size() != 0) {
                                HashSet<NovelChapterEntity.ChaptersBean> targetHash = new HashSet<>(childDataList);
                                Iterator<BaseExpandItemEntity> iter = mInfos.iterator();

                                while(iter.hasNext()){
                                    if(targetHash.contains(iter.next())){
                                        iter.remove();
                                    }
                                }
                            }

                        }

                        chapterEntity.setExpand(!chapterEntity.isExpand());
                        notifyDataSetChanged();
                        break;
                    case CHILD_ITEM:

                        NovelChapterEntity.ChaptersBean childEntity= (NovelChapterEntity.ChaptersBean) data;
                        if(mChildItemClickListener!=null){
                            mChildItemClickListener.onItemClick(view,viewType,childEntity.getParent() ,childEntity,position);
                        }

                        break;
                }


            }
        });
    }

    @Override
    public BaseHolder<BaseExpandItemEntity> getHolder(View v, int viewType) {

        BaseHolder result = null;

        switch (viewType) {
            case PARENT_ITEM:
                result = new NovelDetailChapterParentHolder(v);
                break;
            case CHILD_ITEM:
                result = new NovelDetailChapterChildHolder(v);
                break;
        }
        return result;
    }

    @Override
    public int getItemViewType(int position) {
        return mInfos.get(position).getType();
    }

    @Override
    public int getLayoutId(int viewType) {

        int result = 0;

        switch (viewType) {
            case PARENT_ITEM:
                result = R.layout.layout_novel_chapter;
                break;
            case CHILD_ITEM:
                result = R.layout.layout_novel_chapter_child;
                break;
        }
        return result;
    }


    /**
     * 二级项目点击事件
     *
     * @param listener
     */
    public void setOnChildItemClickListener(onChildItemClickListener<NovelChapterEntity,NovelChapterEntity.ChaptersBean> listener) {
        mChildItemClickListener=listener;
    }

    public interface onChildItemClickListener<T,C>{
        void onItemClick(View view, int viewType, T parentData, C childData, int position);
    }
}
