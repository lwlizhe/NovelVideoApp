package com.lwlizhe.video.mvp.presenter.activity;

import android.app.Application;
import android.content.Intent;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.subscriber.CommonSubscriber;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.basemodule.utils.RxLifecycleUtils;
import com.lwlizhe.video.api.entity.DilidiliAnimationDetailResponseEntity;
import com.lwlizhe.video.api.entity.DilidiliVideoResourceResponseEntity;
import com.lwlizhe.video.mvp.contract.VideoIntroductionContract;
import com.lwlizhe.video.mvp.ui.activity.VideoPlayerActivity;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class VideoIntroductionPresenter extends BasePresenter<VideoIntroductionContract.Model, VideoIntroductionContract.View> {

    @Inject
    public VideoIntroductionPresenter(VideoIntroductionContract.Model model, VideoIntroductionContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);
    }

    public void getAnimationIntroductionData(int resourceId) {
        mModel.getAnimationDetail(resourceId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new CommonSubscriber<DilidiliAnimationDetailResponseEntity>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onFailed(Throwable t) {
                        mView.onGetRefreshData(false, null);
                        mView.showMsg(t.getMessage());
                    }

                    @Override
                    public void onSuccess(DilidiliAnimationDetailResponseEntity data) {

                        if(data.getErrorCode() == 0){
                            mView.onGetRefreshData(true, data);
                        }else{
                            onFailed(new Throwable(data.getMessage()));
                        }


                    }
                });
    }

    public void getAnimationResourceUrl(String url) {
        mModel.getAnimationVideoResource(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new CommonSubscriber<DilidiliVideoResourceResponseEntity>() {
                    @Override
                    public void onFailed(Throwable t) {

                    }

                    @Override
                    public void onSuccess(DilidiliVideoResourceResponseEntity data) {
                        String playUrl = data.getData().getPlayUrl().get(0);
                        mView.launchActivity(VideoPlayerActivity.getVideoPlayIntent(mView.getContext(),playUrl));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
