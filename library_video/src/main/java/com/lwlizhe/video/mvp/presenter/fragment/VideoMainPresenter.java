package com.lwlizhe.video.mvp.presenter.fragment;

import android.app.Application;
import android.content.Intent;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.GlobeConstance;
import com.lwlizhe.video.base.CommonSubscriber;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo;
import com.lwlizhe.video.mvp.contract.VideoMainContract;
import com.lwlizhe.video.mvp.ui.activity.VideoPlayerActivity;
import com.lwlizhe.video.mvp.ui.adapter.VideoMainAdapter;
import com.lwlizhe.video.mvp.ui.adapter.holder.VideoMainBannerHolder;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class VideoMainPresenter extends BasePresenter<VideoMainContract.Model, VideoMainContract.View> {

    private VideoMainAdapter mVideoMainAdapter;

    @Inject
    public VideoMainPresenter(VideoMainContract.Model model, VideoMainContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);

        initAdapter();

    }

    private void initAdapter() {

        mVideoMainAdapter=new VideoMainAdapter();

        mVideoMainAdapter.setOnBannerItemClickListener(new VideoMainBannerHolder.OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position, DilidiliInfo.ScheudleBanner itemData) {
                String animeUrl = itemData.getAnimeLink().contains(GlobeConstance.DILIDILI_URL)?itemData.getAnimeLink():GlobeConstance.DILIDILI_URL+itemData.getAnimeLink();
                Intent videoIntent=new Intent(mRootView.getContext(),VideoPlayerActivity.class);
                videoIntent.putExtra(VideoPlayerActivity.INTENT_VIDEO_PAGE_URL,animeUrl);
                mRootView.launchActivity(videoIntent);

            }
        });

        mRootView.setRecyclerAdapter(mVideoMainAdapter);

    }

    public void getData() {
        mModel.getDilidiliInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonSubscriber<DilidiliInfo>() {
                    @Override
                    public void onNext(DilidiliInfo dilidiliInfo) {
                        mVideoMainAdapter.setDilidiliInfo(dilidiliInfo);
                        mVideoMainAdapter.notifyDataSetChanged();
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
