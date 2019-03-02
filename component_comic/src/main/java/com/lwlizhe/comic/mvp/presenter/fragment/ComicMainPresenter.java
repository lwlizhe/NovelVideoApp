package com.lwlizhe.comic.mvp.presenter.fragment;

import android.app.Application;
import android.util.Log;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.comic.base.CommonSubscriber;
import com.lwlizhe.comic.mvp.contract.fragment.ComicMainContract;
import com.lwlizhe.comic.mvp.ui.adapter.ComicMainAdapter;
import com.lwlizhe.comic.api.entity.ComicRecommendResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/10 0010.
 */

public class ComicMainPresenter extends BasePresenter<ComicMainContract.Model, ComicMainContract.View> {

    private ActivityManager mActivityManager;
    private Application mApplication;

    private ComicMainAdapter adapter;

    private List<ComicRecommendResponse> mRecommendData;

    @Inject
    public ComicMainPresenter(ComicMainContract.Model model, ComicMainContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);

        this.mActivityManager = mActivityManager;
        this.mApplication = mApplication;

        mRecommendData=new ArrayList<>();

        initAdapter();

    }

    private void initAdapter() {

        adapter = new ComicMainAdapter(mRecommendData);
        mView.setRecyclerViewAdapter(adapter);

    }

    public void initData() {

        mModel.getComicRecommend().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonSubscriber<List<ComicRecommendResponse>>() {
            @Override
            public void onFailed(Throwable t) {
                Log.d("test","failed");
            }

            @Override
            public void onNext(List<ComicRecommendResponse> comicRecommendResponses) {

                Log.d("test",comicRecommendResponses.toString());
                mRecommendData.clear();
                mRecommendData.addAll(comicRecommendResponses);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
