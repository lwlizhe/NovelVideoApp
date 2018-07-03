package com.lwlizhe.novelvideoapp.video.mvp.presenter.fragment;

import android.app.Application;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.novelvideoapp.common.CommonSubscriber;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.DilidiliInfo;
import com.lwlizhe.novelvideoapp.video.mvp.contract.VideoMainContract;
import com.lwlizhe.novelvideoapp.video.mvp.ui.adapter.VideoMainAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class VideoMainPresenter extends BasePresenter<VideoMainContract.Model, VideoMainContract.View> {

    private List<DilidiliInfo> mDilidilList=new ArrayList<>();

    @Inject
    public VideoMainPresenter(VideoMainContract.Model model, VideoMainContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);

        initAdapter();

    }

    private void initAdapter() {

        VideoMainAdapter adapter=new VideoMainAdapter(mDilidilList);
    }

    public void getData() {
        mModel.getDilidiliInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new CommonSubscriber<DilidiliInfo>() {
            @Override
            public void onNext(DilidiliInfo dilidiliInfo) {

                dilidiliInfo.getScheudleBanners();

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onFailed(Throwable t) {

            }
        });
    }
}
