package com.lwlizhe.video.mvp.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.event.message.ActivityMessage;


import com.lwlizhe.common.di.component.AppComponent;


import com.lwlizhe.library.video.R;
import com.lwlizhe.video.di.component.DaggerVideoMainComponent;
import com.lwlizhe.video.di.module.VideoMainModule;
import com.lwlizhe.video.base.CommonFragment;
import com.lwlizhe.video.mvp.contract.VideoMainContract;
import com.lwlizhe.video.mvp.presenter.fragment.VideoMainPresenter;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class VideoMainFragment extends CommonFragment<VideoMainPresenter> implements VideoMainContract.View,SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mVideoMainList;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerVideoMainComponent.builder().appComponent(appComponent).videoMainModule(new VideoMainModule(VideoMainFragment.this)).build().inject(this);
    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_video_main, container, false);
    }

    @Override
    protected void initData() {

        mPresenter.setEventBus(mEventBus);
        mPresenter.getData();

    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = mRootView.findViewById(R.id.srl_refresh_layout);
        mVideoMainList = mRootView.findViewById(R.id.rvw_main);


        mVideoMainList.setLayoutManager(new LinearLayoutManager(getContext()));

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_light,
                android.R.color.holo_red_light, android.R.color.holo_blue_light,
                android.R.color.holo_green_light);
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {
        mEventBus.post(new ActivityMessage<>(ActivityManager.ActivityEventType.START_ACTIVITY_INTENT, intent));
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void onRefresh() {
        mPresenter.getData();
    }

    @Override
    public void setRecyclerAdapter(RecyclerView.Adapter adapter) {
        mVideoMainList.setAdapter(adapter);
    }
}
