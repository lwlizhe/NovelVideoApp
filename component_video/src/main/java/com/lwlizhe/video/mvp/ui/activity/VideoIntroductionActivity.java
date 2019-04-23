package com.lwlizhe.video.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.event.message.ActivityMessage;
import com.lwlizhe.common.di.component.AppComponent;
import com.lwlizhe.library.video.R;
import com.lwlizhe.video.api.entity.DilidiliAnimationDetailResponseEntity;
import com.lwlizhe.video.base.CommonActivity;
import com.lwlizhe.video.di.component.DaggerVideoIntroductionComponent;
import com.lwlizhe.video.di.module.VideoIntroductionModule;
import com.lwlizhe.video.mvp.contract.VideoIntroductionContract;
import com.lwlizhe.video.mvp.presenter.activity.VideoIntroductionPresenter;
import com.lwlizhe.video.mvp.ui.adapter.VideoIntroEpisodeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import static android.support.v7.widget.RecyclerView.HORIZONTAL;

/**
 * Created by lizhe on 2019/2/20.
 */

public class VideoIntroductionActivity extends CommonActivity<VideoIntroductionPresenter> implements VideoIntroductionContract.View {

    public static final String INTENT_VIDEO_RES_ID = "intent_video_res_id";

    private int resId = 0;

    private SmartRefreshLayout mRefreshLayout;

    private RecyclerView mRcvEpisodeList;
    private VideoIntroEpisodeAdapter mEpisodeAdapter;

    private List<DilidiliAnimationDetailResponseEntity.DataBean.EpisodeListBean> mEpisodeList = new ArrayList<>();

    public static Intent getIntroductionIntent(Context context, int resId) {
        Intent intent = new Intent(context, VideoIntroductionActivity.class);
        intent.putExtra(INTENT_VIDEO_RES_ID, resId);

        return intent;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerVideoIntroductionComponent.builder().appComponent(appComponent).videoIntroductionModule(new VideoIntroductionModule(VideoIntroductionActivity.this)).build().inject(this);
    }

    @Override
    protected View initRootView() {
        return LayoutInflater.from(VideoIntroductionActivity.this).inflate(R.layout.video_activity_video_introduction, null, false);
    }

    @Override
    protected void initView() {

        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRcvEpisodeList = findViewById(R.id.rcv_episode_list);

        mEpisodeAdapter = new VideoIntroEpisodeAdapter(mEpisodeList);
        mRcvEpisodeList.setLayoutManager(new LinearLayoutManager(VideoIntroductionActivity.this, HORIZONTAL, false));
        mRcvEpisodeList.setAdapter(mEpisodeAdapter);

        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.autoRefresh();

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (resId == 0) {
                return;
            }
            mPresenter.getAnimationIntroductionData(resId);
        });

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();

        resId = intent.getIntExtra(INTENT_VIDEO_RES_ID, 0);

        if (resId == 0) {
            return;
        }

        mPresenter.getAnimationIntroductionData(resId);

    }

    @Override
    protected void initListener() {
        mEpisodeAdapter.setOnItemClickListener((view, viewType, data, position) -> {
            DilidiliAnimationDetailResponseEntity.DataBean.EpisodeListBean currentClickedItem = mEpisodeList.get(position);

            String putUrl = currentClickedItem.getStreams().getPutUrl();

            mPresenter.getAnimationResourceUrl(putUrl);
        });
    }

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
        mEventBus.post(new ActivityMessage<>(ActivityManager.ActivityEventType.START_ACTIVITY_INTENT, intent));
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void onGetRefreshData(boolean isSuccess, DilidiliAnimationDetailResponseEntity entity) {

        mRefreshLayout.finishRefresh(isSuccess);

        if (isSuccess) {

            if(mEpisodeList==null){
                mEpisodeList=new ArrayList<>();
            }

            mEpisodeList.clear();
            mEpisodeList.addAll(entity.getData().get(0).getEpisodeList());
            mEpisodeAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public Context getContext() {
        return VideoIntroductionActivity.this;
    }
}
