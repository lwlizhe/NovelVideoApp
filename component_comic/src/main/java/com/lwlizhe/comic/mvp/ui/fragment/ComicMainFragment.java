package com.lwlizhe.comic.mvp.ui.fragment;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwlizhe.comic.base.CommonFragment;
import com.lwlizhe.comic.di.component.DaggerComicMainComponent;
import com.lwlizhe.comic.di.module.ComicModule;
import com.lwlizhe.comic.mvp.contract.fragment.ComicMainContract;
import com.lwlizhe.comic.mvp.presenter.fragment.ComicMainPresenter;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.library.comic.R;

/**
 * Created by Administrator on 2018/7/10 0010.
 */

public class ComicMainFragment extends CommonFragment<ComicMainPresenter> implements ComicMainContract.View{
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

    }

    @Override
    protected void initView() {

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

    }

    @Override
    public void killMyself() {

    }
}
