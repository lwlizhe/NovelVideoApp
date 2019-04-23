package com.lwlizhe.video.mvp.presenter.fragment;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.subscriber.CommonSubscriber;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.basemodule.utils.RxLifecycleUtils;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;
import com.lwlizhe.video.mvp.contract.VideoMainContract;
import com.lwlizhe.video.mvp.ui.adapter.VideoMainAdapter;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainAdapterEntity;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainBannerEntity;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainEditPickEntity;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainUgcPickEntity;
import com.lwlizhe.video.mvp.ui.adapter.entity.VideoMainWeekEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.lwlizhe.video.api.entity.DilidiliIndexEntity.TYPE_BANNER;
import static com.lwlizhe.video.api.entity.DilidiliIndexEntity.TYPE_RECENT;
import static com.lwlizhe.video.mvp.ui.activity.VideoIntroductionActivity.getIntroductionIntent;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class VideoMainPresenter extends BasePresenter<VideoMainContract.Model, VideoMainContract.View> {

    //    private VideoMainAdapter mVideoMainAdapter;
    private VideoMainAdapter mVideoMainAdapter;

    private List<VideoMainAdapterEntity> mMultiItemData;

    @Inject
    public VideoMainPresenter(VideoMainContract.Model model, VideoMainContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);

        mMultiItemData=new ArrayList<>();
        mMultiItemData.add(new VideoMainBannerEntity());
        mMultiItemData.add(new VideoMainWeekEntity());

        initAdapter();

    }

    private void initAdapter() {

        mVideoMainAdapter = new VideoMainAdapter(mMultiItemData);

        mVideoMainAdapter.setOnItemClickListener(new VideoMainAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                switch (viewType) {
                    case TYPE_BANNER:
                        DilidiliIndexEntity.DataBean.CarouselBean targetData = (DilidiliIndexEntity.DataBean.CarouselBean) data;

                        if(targetData==null||targetData.getResource()==null|| TextUtils.isEmpty(targetData.getResource().getResLocation())){
                            return;
                        }

                        mView.launchActivity(getIntroductionIntent(mView.getContext(), Integer.parseInt(targetData.getResource().getResLocation())));

                        break;
                    case TYPE_RECENT:
                        break;
                }
            }
        });

        mView.setRecyclerAdapter(mVideoMainAdapter);

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

                            DilidiliIndexEntity.DataBean indexData = data.getData();

                            mMultiItemData.clear();

                            mMultiItemData.add(new VideoMainBannerEntity(indexData.getCarousel()));
                            mMultiItemData.add(new VideoMainWeekEntity(indexData.getWeekList()));
                            mMultiItemData.add(new VideoMainEditPickEntity(indexData.getEditorPick()));
                            mMultiItemData.add(new VideoMainUgcPickEntity(indexData.getUgcPick()));

                            mVideoMainAdapter.notifyDataSetChanged();
                        }
                    }
                });


    }

}
