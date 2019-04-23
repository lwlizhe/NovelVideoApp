package com.lwlizhe.comic.mvp.ui.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lwlizhe.GlobeConstance;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.comic.base.CommonFragment;
import com.lwlizhe.comic.di.component.DaggerComicMainComponent;
import com.lwlizhe.comic.di.module.ComicModule;
import com.lwlizhe.comic.mvp.contract.fragment.ComicMainContract;
import com.lwlizhe.comic.mvp.presenter.fragment.ComicMainPresenter;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.library.comic.R;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@Route(path = GlobeConstance.CONSTANCE_ROUTER.ROUTER_COMIC_MAIN_PAGE)
public class ComicMainFragment extends CommonFragment<ComicMainPresenter> implements ComicMainContract.View{

    RecyclerView mComicRcv;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

        DaggerComicMainComponent.builder().appComponent(appComponent).comicModule(new ComicModule(ComicMainFragment.this)).build().inject(this);

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_comic_main,container,false);
    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    protected void initView() {

        mComicRcv = mRootView.findViewById(R.id.rvw_main);

        mComicRcv.setLayoutManager(new LinearLayoutManager(getContext()));

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

        Glide.with(this).load("").into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

            }
        });

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void setRecyclerViewAdapter(BaseRecyclerViewAdapter mAdapter) {
        mComicRcv.setAdapter(mAdapter);
    }
}
