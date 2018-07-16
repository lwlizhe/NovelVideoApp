package com.lwlizhe.comic.mvp.ui.activity;

import android.content.Intent;
import android.view.View;

import com.lwlizhe.basemodule.base.BaseActivity;
import com.lwlizhe.comic.mvp.contract.activity.ComicPageContract;
import com.lwlizhe.comic.mvp.presenter.activity.ComicPagePresenter;

/**
 * Created by Administrator on 2018/7/16 0016.
 */

public class ComicDetailActivity extends BaseActivity<ComicPagePresenter> implements ComicPageContract.View {
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

    @Override
    protected void ComponentInject() {

    }

    @Override
    protected View initRootView() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
