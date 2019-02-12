package com.lwlizhe.video.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.offline.FilteringManifestParser;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistParserFactory;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.base.CommonActivity;
import com.lwlizhe.video.di.component.DaggerVideoPlayerComponent;
import com.lwlizhe.video.di.module.VideoPlayerModule;
import com.lwlizhe.video.mvp.contract.VideoPlayerContract;
import com.lwlizhe.video.mvp.presenter.activity.VideoPlayerPresenter;

import java.util.ArrayList;

//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class VideoPlayerActivity extends CommonActivity<VideoPlayerPresenter> implements VideoPlayerContract.View, PlaybackPreparer {

    public static final String INTENT_VIDEO_PAGE_URL = "intent_video_page_url";

    private PlayerView playerView;
    private SimpleExoPlayer player;

    private DefaultTrackSelector trackSelector;
    private DefaultTrackSelector.Parameters trackSelectorParameters;

    private FrameworkMediaDrm mediaDrm;

    private DataSource.Factory dataSourceFactory;

    private String targetUrl;

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

        DefaultTrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory());
        // 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        player.setPlayWhenReady(true);

        playerView.setPlayer(player);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            targetUrl = intent.getStringExtra(INTENT_VIDEO_PAGE_URL);
        }

        targetUrl="https://cdn-3.haku99.com/hls/2019/02/10/y7uiExtI/playlist.m3u8";

        dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,VideoPlayerActivity.class.getSimpleName()));

        initPlayer(targetUrl);

    }

    @Override
    protected void initListener() {

    }

    private void initPlayer(String videoUrl) {
        if (TextUtils.isEmpty(videoUrl)) {
            return;
        }

        MediaSource videoSource = buildMediaSource(Uri.parse(videoUrl),null);

        player.prepare(videoSource);

    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initPlayer(targetUrl);
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || player == null) {
            initPlayer(targetUrl);

            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
        }
    }

    private void releaseMediaDrm() {
        if (mediaDrm != null) {
            mediaDrm.release();
            mediaDrm = null;
        }
    }

    private void releasePlayer() {
        if (player != null) {
            updateTrackSelectorParameters();

            player.release();
            player = null;

            trackSelector = null;
        }
        releaseMediaDrm();
    }

    private void updateTrackSelectorParameters() {
        if (trackSelector != null) {
            trackSelectorParameters = trackSelector.getParameters();
        }
    }



    @SuppressWarnings("unchecked")
    private MediaSource buildMediaSource(Uri uri, @Nullable String overrideExtension) {
        @C.ContentType int type = Util.inferContentType(uri, overrideExtension);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(dataSourceFactory)
                        .setManifestParser(
                                new FilteringManifestParser<>(new DashManifestParser(), new ArrayList<StreamKey>()))
                        .createMediaSource(uri);
            case C.TYPE_SS:
                return new SsMediaSource.Factory(dataSourceFactory)
                        .setManifestParser(
                                new FilteringManifestParser<>(new SsManifestParser(), new ArrayList<StreamKey>()))
                        .createMediaSource(uri);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(dataSourceFactory)
                        .setPlaylistParserFactory(
                                new DefaultHlsPlaylistParserFactory(new ArrayList<StreamKey>()))
                        .createMediaSource(uri);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }


    @Override
    public void preparePlayback() {
        initPlayer(targetUrl);

    }
}
