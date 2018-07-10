package com.lwlizhe.novel.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.basemodule.event.message.ActivityMessage;

import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.library.novel.R;
import com.lwlizhe.novel.base.CommonFragment;
import com.lwlizhe.novel.di.component.DaggerNovelRecommendComponent;
import com.lwlizhe.novel.di.module.NovelRecommendModule;
import com.lwlizhe.novel.mvp.contract.fragment.NovelRecommendContract;
import com.lwlizhe.novel.mvp.presenter.fragment.NovelRecommendPresenter;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelRecommendFragment extends CommonFragment<NovelRecommendPresenter> implements NovelRecommendContract.View, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView mRecommendList;
    SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerNovelRecommendComponent.builder()
                .appComponent(appComponent)
                .novelRecommendModule(new NovelRecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_novel_recommend, container, false);

    }

    @Override
    protected void initData() {

        mPresenter.setEventBus(mEventBus);
        mPresenter.initData(false);

    }


    @Override
    public void initView() {

        mRecommendList=mRootView.findViewById(R.id.rcv_recommend_list);
        mRefreshLayout=mRootView.findViewById(R.id.srl_refresh_layout);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_light,
                android.R.color.holo_red_light, android.R.color.holo_blue_light,
                android.R.color.holo_green_light);

    }

    @Override
    public Context getContext() {
        return getActivity();
    }


    @Override
    public void setRecyclerViewAdapter(BaseRecyclerViewAdapter mAdapter) {

        mRecommendList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecommendList.setAdapter(mAdapter);

    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void launchActivity(Intent intent) {

        mEventBus.post(new ActivityMessage<>(ActivityManager.ActivityEventType.START_ACTIVITY_INTENT,intent));

    }

    @Override
    public void killMyself() {

    }


    @Override
    public void onRefresh() {

        mPresenter.initData(true);

    }
}
