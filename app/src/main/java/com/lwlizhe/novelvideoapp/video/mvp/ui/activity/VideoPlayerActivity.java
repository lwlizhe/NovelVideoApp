package com.lwlizhe.novelvideoapp.video.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.fcannizzaro.jsoup.annotations.JP;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.lwlizhe.novelvideoapp.GlobeConstance;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.common.CommonActivity;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.DilidiliInfo;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.DilidiliVideo;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.ScheduleVideo;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.ScheduleVideo1;
import com.lwlizhe.novelvideoapp.video.di.component.DaggerVideoPlayerComponent;
import com.lwlizhe.novelvideoapp.video.di.module.VideoPlayerModule;
import com.lwlizhe.novelvideoapp.video.mvp.contract.VideoPlayerContract;
import com.lwlizhe.novelvideoapp.video.mvp.presenter.activity.VideoPlayerPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class VideoPlayerActivity extends CommonActivity<VideoPlayerPresenter> implements VideoPlayerContract.View {

    public static final String INTENT_VIDEO_PAGE_URL = "intent_video_page_url";

    PlayerView playerView;
    SimpleExoPlayer player;


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
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerVideoPlayerComponent.builder().appComponent(appComponent).videoPlayerModule(new VideoPlayerModule(this)).build().inject(this);
    }

    @Override
    protected View initRootView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_video_player, null, false);
    }

    @Override
    protected void initView() {

        playerView = findViewById(R.id.player_view);

        // Measures bandwidth during playback. Can be null if not required.
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
        // 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        playerView.setPlayer(player);


    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        String videoPageUrl = intent.getStringExtra(INTENT_VIDEO_PAGE_URL);

        parsePageInfo(videoPageUrl);

    }

    @Override
    protected void initListener() {

    }

    private void initPlayer(String videoUrl) {
        if (TextUtils.isEmpty(videoUrl)) {
            return;
        }
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        // This is the MediaSource representing the media to be played.

        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUrl));
//        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
        // Prepare the player with the source.
        player.prepare(videoSource);
    }

    private void parsePageInfo(final String srcVideoPage) {

        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {
                Element html = Jsoup.connect(srcVideoPage).get();

                DilidiliVideo scheduleVideo = JP.from(html, DilidiliVideo.class);

                String videoUrl = scheduleVideo.getVideoUrl();

                if(videoUrl.contains("url=")){
                    e.onSuccess(videoUrl.split("url=")[1]);
                }else{
                    e.onSuccess(videoUrl);
                }

//                Element html2 = Jsoup.connect(scheduleVideo.getVideoUrl()).get();
//
//                ScheduleVideo1 scheduleVideo1 = JP.from(html2, ScheduleVideo1.class);
//
//                scheduleVideo1.getVideoUrl();

//                Element player = content.getElementById("player");
//
//                String src = player.attr("src");
//
//                Element html3 = Jsoup.connect(src).get();
//
//                Element dplayer = html3.getElementById("dplayer");
//
//                Element divDplayer = dplayer.getElementsByClass("dplayer-video-wrap").get(0);
//
//                Element videoPlayer = divDplayer.getElementsByClass("dplayer-video dplayer-video-current").get(0);
//
//                String src1 = videoPlayer.attr("src");
//
//                e.onSuccess(src1);

            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                initPlayer(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("test", e.toString());
//                initPlayer(GlobeConstance.DILIDILI_URL+);
            }
        });

    }

}
