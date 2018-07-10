package com.lwlizhe.comic.mvp.presenter.fragment;

import android.app.Application;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.comic.mvp.contract.fragment.ComicMainContract;
import com.lwlizhe.common.cache.manager.CacheManager;
import com.lwlizhe.common.service.manager.ServiceManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/10 0010.
 */

public class ComicMainPresenter extends BasePresenter<ComicMainContract.Model,ComicMainContract.View> {

    private ActivityManager mActivityManager;
    private Application mApplication;

    @Inject
    public ComicMainPresenter(ComicMainContract.Model model, ComicMainContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);

        this.mActivityManager = mActivityManager;
        this.mApplication = mApplication;

    }
}
