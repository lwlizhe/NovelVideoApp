package com.lwlizhe.novelvideoapp.novel.mvp.presenter.fragment;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.adapter.BaseExpandItemEntity;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.basemodule.utils.RxUtils;
import com.lwlizhe.novelvideoapp.common.CommonSubscriber;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.fragment.NovelDetailChapterContract;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.activity.NovelReadActivity;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.adapter.NovelDetailChapterAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import static com.lwlizhe.novelvideoapp.novel.mvp.ui.activity.NovelReadActivity.NOVEL_CHAPTER_ID;
import static com.lwlizhe.novelvideoapp.novel.mvp.ui.activity.NovelReadActivity.NOVEL_CHAPTER_LIST;
import static com.lwlizhe.novelvideoapp.novel.mvp.ui.activity.NovelReadActivity.NOVEL_ID;
import static com.lwlizhe.novelvideoapp.novel.mvp.ui.activity.NovelReadActivity.NOVEL_VOLUME_ID;

/**
 * Created by Administrator on 2018/6/4 0004.
 */

public class NovelDetailChapterPresenter extends BasePresenter<NovelDetailChapterContract.Model, NovelDetailChapterContract.View> {

    private NovelDetailChapterAdapter mChapterAdapter;

    private List<BaseExpandItemEntity> mNovelChapterList;
    private List<NovelChapterEntity> mSrcNovelChapterList;//网络请求拉到的数据源

    private long mNovelId;

    private Gson gson;

    @Inject
    public NovelDetailChapterPresenter(NovelDetailChapterContract.Model model, NovelDetailChapterContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);
        init();
    }

    private void init() {

        mNovelChapterList=new ArrayList<>();
        mSrcNovelChapterList=new ArrayList<>();

        gson=new Gson();

        mChapterAdapter=new NovelDetailChapterAdapter(mNovelChapterList);

        mChapterAdapter.setOnChildItemClickListener(new NovelDetailChapterAdapter.onChildItemClickListener<NovelChapterEntity, NovelChapterEntity.ChaptersBean>() {
            @Override
            public void onItemClick(View view, int viewType, NovelChapterEntity parentData, NovelChapterEntity.ChaptersBean childData, int position) {
                if (mNovelId == 0) {
                    return;
                }

                Intent intent = new Intent(mRootView.getContext(), NovelReadActivity.class);
                intent.putExtra(NOVEL_ID, mNovelId);
                intent.putExtra(NOVEL_CHAPTER_ID, (long)childData.getChapter_id());
                intent.putExtra(NOVEL_VOLUME_ID, (long)parentData.getVolume_id());
                intent.putExtra(NOVEL_CHAPTER_LIST, (Serializable) mSrcNovelChapterList);

                mRootView.launchActivity(intent);
            }
        });


        mRootView.setRecyclerViewAdapter(mChapterAdapter);

    }

    public void getChapterData(long novelId) {

        mNovelId=novelId;

        mModel.getNovelChapter(novelId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<List<NovelChapterEntity>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new CommonSubscriber<List<NovelChapterEntity>>() {
                    @Override
                    public void onFailed(Throwable t) {
                        Log.d("request",t.toString());
                    }

                    @Override
                    public void onNext(List<NovelChapterEntity> novelChapterEntities) {
                        if(novelChapterEntities.size()==0){
                            return;
                        }

                        mSrcNovelChapterList.clear();
                        mSrcNovelChapterList.addAll(novelChapterEntities);

                        mNovelChapterList.clear();
                        mNovelChapterList.addAll(novelChapterEntities);

                        mChapterAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
