package com.lwlizhe.novelvideoapp.novel.mvp.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.basemodule.event.message.ActivityMessage;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.common.CommonFragment;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.novelvideoapp.novel.di.component.DaggerNovelDetailChapterComponent;
import com.lwlizhe.novelvideoapp.novel.di.module.NovelDetailChapterModule;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.fragment.NovelDetailChapterContract;
import com.lwlizhe.novelvideoapp.novel.mvp.presenter.fragment.NovelDetailChapterPresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/4 0004.
 */

public class NovelDetailChapterFragment extends CommonFragment<NovelDetailChapterPresenter> implements NovelDetailChapterContract.View {

    private long mNovelId;

    private RecyclerView mRecyclerView;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

        DaggerNovelDetailChapterComponent.builder().appComponent(appComponent).novelDetailChapterModule(new NovelDetailChapterModule(NovelDetailChapterFragment.this)).build().inject(this);
    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_novel_detail_chapter, container, false);
    }

    @Override
    protected void initData() {

        if (mNovelId != 0) {
            mPresenter.getChapterData(mNovelId);
        }

    }

    @Override
    protected void initView() {

        mRecyclerView = mRootView.findViewById(R.id.rvw_chapter_list);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

        mEventBus.post(new ActivityMessage<>(ActivityManager.ActivityEventType.START_ACTIVITY_INTENT,intent));

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void setNovelId(long novelId) {

        mNovelId = novelId;

    }

    @Override
    public void setRecyclerViewAdapter(BaseRecyclerViewAdapter mAdapter) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

    }

    public List<NovelChapterEntity> getChapterList(){
        return mPresenter.getChapterList();
    }
}
