package com.lwlizhe.novelvideoapp.novel.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.common.CommonFragment;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.novel.di.component.DaggerNovelRecommendComponent;
import com.lwlizhe.novelvideoapp.novel.di.module.NovelRecommendModule;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.fragment.NovelRecommendContract;
import com.lwlizhe.novelvideoapp.novel.mvp.presenter.fragment.NovelRecommendPresenter;


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
    public void startLoadMore() {


    }

    @Override
    public void endLoadMore() {

    }

    @Override
    public void startRefresh() {

        mRefreshLayout.setRefreshing(true);

    }

    @Override
    public void endRefresh() {

        mRefreshLayout.setRefreshing(false);

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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void launchActivity(Intent intent) {

        getContext().startActivity(intent);

    }

    @Override
    public void killMyself() {

    }


    @Override
    public void onRefresh() {

        mPresenter.initData(true);

    }
}
