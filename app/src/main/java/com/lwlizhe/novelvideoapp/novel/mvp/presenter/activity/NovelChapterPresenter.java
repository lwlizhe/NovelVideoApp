package com.lwlizhe.novelvideoapp.novel.mvp.presenter.activity;


import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelDetailEntity;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.activity.NovelChapterContract;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class NovelChapterPresenter extends BasePresenter<NovelChapterContract.Model, NovelChapterContract.View> {

    private ActivityManager mActivityManager;
    private Application mApplication;

    private AppCompatActivity mActivity;


    @Inject
    public NovelChapterPresenter(NovelChapterContract.Model model, NovelChapterContract.View rootView, ActivityManager activityManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mActivityManager = activityManager;

        this.mActivity = mRootView.getContext();

        initRootView();
        setRootView();
    }

    private void setRootView() {

    }

    private void initRootView() {

    }

    public void initData(long bookId) {
        mModel.getNovelChapter(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NovelDetailEntity>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(NovelDetailEntity novelDetailEntity) {

                        mRootView.setData(novelDetailEntity);

                    }

                    @Override
                    public void onError(Throwable t) {

                        Logger.e(t.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
