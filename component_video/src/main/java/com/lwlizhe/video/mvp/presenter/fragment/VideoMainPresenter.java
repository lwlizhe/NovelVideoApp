package com.lwlizhe.video.mvp.presenter.fragment;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lwlizhe.GlobeConstance;
import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo;
import com.lwlizhe.video.base.CommonSubscriber;
import com.lwlizhe.video.mvp.contract.VideoMainContract;
import com.lwlizhe.video.mvp.ui.activity.VideoPlayerActivity;
import com.lwlizhe.video.mvp.ui.adapter.VideoMainAdapter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo.TYPE_BANNER;

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

        mVideoMainAdapter = new VideoMainAdapter();

        mVideoMainAdapter.setOnItemClickListener(new VideoMainAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                String animUrl = null;
                switch (viewType) {
                    case TYPE_BANNER:
                        animUrl = ((DilidiliInfo.ScheudleBanner) data).getAnimeLink().contains(GlobeConstance.DILIDILI_URL) ? ((DilidiliInfo.ScheudleBanner) data).getAnimeLink() : GlobeConstance.DILIDILI_URL + ((DilidiliInfo.ScheudleBanner) data).getAnimeLink();
                        break;
                }

                if(TextUtils.isEmpty(animUrl)){
                    return;
                }

                Intent videoIntent = new Intent(mRootView.getContext(), VideoPlayerActivity.class);
                videoIntent.putExtra(VideoPlayerActivity.INTENT_VIDEO_PAGE_URL, animUrl);
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
