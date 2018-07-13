package com.lwlizhe.video.mvp.presenter.fragment;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.fcannizzaro.jsoup.annotations.JP;
import com.lwlizhe.GlobeConstance;
import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliVideo;
import com.lwlizhe.common.api.video.entity.jsoup.ScheduleVideo;
import com.lwlizhe.common.api.video.entity.jsoup.ScheduleWeek;
import com.lwlizhe.video.base.CommonSubscriber;
import com.lwlizhe.video.mvp.contract.VideoMainContract;
import com.lwlizhe.video.mvp.ui.activity.VideoPlayerActivity;
import com.lwlizhe.video.mvp.ui.activity.VideoWebPlayerActivity;
import com.lwlizhe.video.mvp.ui.adapter.VideoMainAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo.TYPE_BANNER;
import static com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo.TYPE_WEEK;
import static com.lwlizhe.video.mvp.ui.activity.VideoWebPlayerActivity.SCHEDULE_VIDEO_CSS;

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
                    case TYPE_WEEK:
                        animUrl = ((ScheduleWeek.ScheduleItem) data).getEpisodeLink().contains(GlobeConstance.DILIDILI_URL) ? ((ScheduleWeek.ScheduleItem) data).getEpisodeLink() : GlobeConstance.DILIDILI_URL + ((ScheduleWeek.ScheduleItem) data).getEpisodeLink();
                        break;
                }

                if (TextUtils.isEmpty(animUrl)) {
                    return;
                }

                final String finalAnimUrl = animUrl;

                Single.create(new SingleOnSubscribe<DilidiliVideo>() {
                    @Override
                    public void subscribe(SingleEmitter<DilidiliVideo> e) throws Exception {
                        Element html = Jsoup.connect(finalAnimUrl).get();

                        if (html == null) {
                            e.onError(new Throwable("url null"));
                        } else {
                            DilidiliVideo dilidiliVideo = JP.from(html, DilidiliVideo.class);
                            e.onSuccess(dilidiliVideo);
                        }

                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<DilidiliVideo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(DilidiliVideo dilidiliVideo) {
                                String videoUrl = dilidiliVideo.getVideoUrl();

                                if ((!TextUtils.isEmpty(videoUrl))&&videoUrl.endsWith("mp4")) {
                                    Intent videoIntent = new Intent(mRootView.getContext(), VideoPlayerActivity.class);

                                    if (videoUrl.contains("url=")) {
                                        videoUrl = videoUrl.split("url=")[1];
                                    }

                                    videoIntent.putExtra(VideoPlayerActivity.INTENT_VIDEO_PAGE_URL, videoUrl);
                                    mRootView.launchActivity(videoIntent);
                                } else {

                                    Toast.makeText(mRootView.getContext(), "又好像是flash……用网页打开吧", Toast.LENGTH_SHORT).show();

//                                    if(TextUtils.isEmpty(videoUrl)){

                                        StringBuilder scheduleVideoHtmlBuilder = new StringBuilder();
                                        scheduleVideoHtmlBuilder.append(SCHEDULE_VIDEO_CSS);
                                        scheduleVideoHtmlBuilder.append("<div id=\"vedio\">");
                                        scheduleVideoHtmlBuilder.append(videoUrl);
                                        scheduleVideoHtmlBuilder.append("</div>");

                                        videoUrl=scheduleVideoHtmlBuilder.toString();
//                                    }

                                    Intent videoIntent = new Intent(mRootView.getContext(), VideoWebPlayerActivity.class);
                                    videoIntent.putExtra(VideoWebPlayerActivity.INTENT_VIDEO_PAGE_URL, videoUrl);
                                    mRootView.launchActivity(videoIntent);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });


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
