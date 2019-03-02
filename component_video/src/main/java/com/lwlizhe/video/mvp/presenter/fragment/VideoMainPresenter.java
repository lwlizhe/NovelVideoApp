package com.lwlizhe.video.mvp.presenter.fragment;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.subscriber.CommonSubscriber;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.basemodule.utils.RxLifecycleUtils;
import com.lwlizhe.video.api.entity.DilidiliAnimationDetailResponseEntity;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.video.api.entity.DilidiliVideoResourceResponseEntity;
import com.lwlizhe.video.mvp.contract.VideoMainContract;
import com.lwlizhe.video.mvp.ui.activity.VideoIntroductionActivity;
import com.lwlizhe.video.mvp.ui.activity.VideoPlayerActivity;
import com.lwlizhe.video.mvp.ui.adapter.VideoMainAppAdapter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lwlizhe.video.api.entity.jsoup.DilidiliInfo.TYPE_BANNER;
import static com.lwlizhe.video.api.entity.jsoup.DilidiliInfo.TYPE_RECENT;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class VideoMainPresenter extends BasePresenter<VideoMainContract.Model, VideoMainContract.View> {

    //    private VideoMainAdapter mVideoMainAdapter;
    private VideoMainAppAdapter mVideoMainAppAdapter;

    @Inject
    public VideoMainPresenter(VideoMainContract.Model model, VideoMainContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);

        initAdapter();

    }

    private void initAdapter() {

        mVideoMainAppAdapter = new VideoMainAppAdapter();

        mVideoMainAppAdapter.setOnItemClickListener(new VideoMainAppAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                switch (viewType) {
                    case TYPE_BANNER:
                        DilidiliIndexEntity.DataBean.CarouselBean targetData = (DilidiliIndexEntity.DataBean.CarouselBean) data;

                        if(targetData==null||targetData.getResource()==null|| TextUtils.isEmpty(targetData.getResource().getResLocation())){
                            return;
                        }

                        mView.launchActivity(VideoIntroductionActivity.getIntroductionIntent(mView.getContext(), Integer.parseInt(targetData.getResource().getResLocation())));

                        break;
                    case TYPE_RECENT:
                        break;
                }
            }
        });

        mView.setRecyclerAdapter(mVideoMainAppAdapter);

    }

    public void getData() {
        mView.showLoading();
        mModel.getDilidiliAppInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.<DilidiliIndexEntity>bindToLifecycle(mView))
                .subscribe(new CommonSubscriber<DilidiliIndexEntity>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onFailed(Throwable t) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(DilidiliIndexEntity data) {
                        mView.hideLoading();
                        if (data.getErrorCode() == 0) {
                            mVideoMainAppAdapter.setDilidiliInfo(data.getData());
                            mVideoMainAppAdapter.notifyDataSetChanged();
                        }
                    }
                });


//        mModel.getDilidiliInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CommonSubscriber<DilidiliInfo>() {
//                    @Override
//                    public void onNext(DilidiliInfo dilidiliInfo) {
//                        mVideoMainAdapter.setDilidiliInfo(dilidiliInfo);
//                        mVideoMainAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onFailed(Throwable t) {
//
//                    }
//                });
//
//        parseHtmlContent("http://www.fjisu.com/acg/27281/6.html");
    }

    public void getAnimationDetailUrl(int id) {

        mModel.getAnimationDetail(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new CommonSubscriber<DilidiliAnimationDetailResponseEntity>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onFailed(Throwable t) {

                    }

                    @Override
                    public void onSuccess(DilidiliAnimationDetailResponseEntity data) {

                        String putUrl = data.getData().get(0).getEpisodeList().get(0).getStreams().getPutUrl();


                        getAnimationVideoResource(putUrl);

                    }
                });
    }

    public void getAnimationVideoResource(String putUrl) {
        mModel.getAnimationVideoResource(putUrl)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new CommonSubscriber<DilidiliVideoResourceResponseEntity>() {
                    @Override
                    public void onFailed(Throwable t) {

                    }

                    @Override
                    public void onSuccess(DilidiliVideoResourceResponseEntity data) {

                        String playUrl = data.getData().getPlayUrl().get(0);

                        Intent videoIntent = new Intent(mView.getContext(), VideoPlayerActivity.class);

                        videoIntent.putExtra(VideoPlayerActivity.INTENT_VIDEO_PAGE_URL, playUrl);
                        mView.launchActivity(videoIntent);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 解析html网页，获取播放地址
     */
    private void parseHtmlContent(String url) {


        url = "http://www.fjisu.com/acg/";
//        url = "http://www.fjisu.com/acg/27281/6.html";

        String finalUrl = url;
        Single.create(new SingleOnSubscribe<List<String>>() {
            @Override
            public void subscribe(SingleEmitter<List<String>> e) throws Exception {

                Connection connect = Jsoup.connect(finalUrl);
                Map<String, String> header = new HashMap<String, String>();
                header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
//                header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//                header.put("Accept-Language", "zh-cn,zh;q=0.5");
//                header.put("Host","op.mtyee.com");
//                header.put("Referer",finalUrl);
                connect = connect.headers(header);

                Element html = connect.get().body();

                if (html == null) {
                    e.onError(new Throwable("url null"));
                } else {

                    Elements scripts = html.getElementsByTag("script");

                    List<String> srcs = scripts.eachAttr("src");

                    Element psBody = Jsoup.connect(srcs.get(0)).ignoreContentType(true).ignoreHttpErrors(true).get().body();
                    Element tyBody = Jsoup.connect(srcs.get(1)).ignoreContentType(true).ignoreHttpErrors(true).get().body();

                    List<String> matchers = getMatchers("[a-zA-z]+://[^\\,]*", tyBody.html());

//                    Element scriptTy = Jsoup.connect(srcs.get(1)).get();

                }

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<String> targetVideoContent) {

                        String videoUrl = targetVideoContent.get(0);

                        Intent videoIntent = new Intent(mView.getContext(), VideoPlayerActivity.class);

                        if (videoUrl.contains("url=")) {
                            videoUrl = videoUrl.split("url=")[1];
                        }

                        videoIntent.putExtra(VideoPlayerActivity.INTENT_VIDEO_PAGE_URL, videoUrl);
                        mView.launchActivity(videoIntent);

//                        String videoUrl = dilidiliVideo.getVideoUrl();
//
//                        if ((!TextUtils.isEmpty(videoUrl)) && videoUrl.endsWith("mp4")) {
//                            Intent videoIntent = new Intent(mView.getContext(), VideoPlayerActivity.class);
//
//                            if (videoUrl.contains("url=")) {
//                                videoUrl = videoUrl.split("url=")[1];
//                            }
//
//                            videoIntent.putExtra(VideoPlayerActivity.INTENT_VIDEO_PAGE_URL, videoUrl);
//                            mView.launchActivity(videoIntent);
//                        } else {
//
//                            Toast.makeText(mView.getContext(), "又好像是flash……用网页打开吧", Toast.LENGTH_SHORT).show();
//
////                                    if(TextUtils.isEmpty(videoUrl)){
//
//                            StringBuilder scheduleVideoHtmlBuilder = new StringBuilder();
//                            scheduleVideoHtmlBuilder.append(SCHEDULE_VIDEO_CSS);
//                            scheduleVideoHtmlBuilder.append("<div id=\"vedio\">");
//                            scheduleVideoHtmlBuilder.append(videoUrl);
//                            scheduleVideoHtmlBuilder.append("</div>");
//
//                            videoUrl = scheduleVideoHtmlBuilder.toString();
////                                    }
//
//                            Intent videoIntent = new Intent(mView.getContext(), VideoWebPlayerActivity.class);
//                            videoIntent.putExtra(VideoWebPlayerActivity.INTENT_VIDEO_PAGE_URL, videoUrl);
//                            mView.launchActivity(videoIntent);
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public List<String> getMatchers(String regex, String source) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

}
