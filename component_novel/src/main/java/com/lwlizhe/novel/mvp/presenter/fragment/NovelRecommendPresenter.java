package com.lwlizhe.novel.mvp.presenter.fragment;

import android.app.Application;
import android.content.Intent;
import android.view.View;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.event.message.ActivityMessage;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.basemodule.utils.RxUtils;
import com.lwlizhe.common.api.novel.entity.NovelReCommendEntity;
import com.lwlizhe.novel.base.CommonSubscriber;
import com.lwlizhe.novel.mvp.contract.fragment.NovelRecommendContract;
import com.lwlizhe.novel.mvp.ui.activity.NovelChapterActivity;
import com.lwlizhe.novel.mvp.ui.adapter.NovelRecommendAdapter;
import com.lwlizhe.novel.mvp.ui.holder.NovelRecommendIBannerHolder;
import com.lwlizhe.novel.mvp.ui.holder.NovelRecommendNormalHolder;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.lwlizhe.novel.mvp.ui.activity.NovelChapterActivity.NOVEL_DATA_ITEM_OBJ_ID;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelRecommendPresenter extends BasePresenter<NovelRecommendContract.Model, NovelRecommendContract.View> {

    private ActivityManager mActivityManager;
    private Application mApplication;

    private NovelRecommendAdapter mAdapter;

    private List<NovelReCommendEntity> mRecommendList = new ArrayList<>();

    /**
     * 单个图片点击事件
     */
    private NovelRecommendNormalHolder.NormalItemImageClickListener normalItemImageClickListener = new NovelRecommendNormalHolder.NormalItemImageClickListener() {
        @Override
        public void onItemImageClickListener(NovelReCommendEntity.DataBean itemData, View view, int position) {

            Intent itemImageIntent=new Intent(mRootView.getContext(), NovelChapterActivity.class);
            itemImageIntent.putExtra(NOVEL_DATA_ITEM_OBJ_ID,itemData.getObj_id());
            mEventBus.post(new ActivityMessage<>(ActivityManager.ActivityEventType.START_ACTIVITY_INTENT,itemImageIntent));

//            Intent itemImageIntent=new Intent(mRootView.getContext(), NovelChapterActivity.class);
//            itemImageIntent.putExtra(NOVEL_DATA_ITEM_OBJ_ID,itemData.getObj_id());
//            mRootView.launchActivity(itemImageIntent);

//            mRootView.showMessage("点击了"+itemData.getCover());
        }
    };

    /**
     * banner图片点击事件
     */
    private NovelRecommendIBannerHolder.OnBannerClickListener listener = new NovelRecommendIBannerHolder.OnBannerClickListener() {
        @Override
        public void OnBannerClick(int position, NovelReCommendEntity.DataBean itemData) {


            Intent itemImageIntent=new Intent(mRootView.getContext(), NovelChapterActivity.class);
            itemImageIntent.putExtra(NOVEL_DATA_ITEM_OBJ_ID,itemData.getObj_id());
            mEventBus.post(new ActivityMessage<>(ActivityManager.ActivityEventType.START_ACTIVITY_INTENT,itemImageIntent));


//            mRootView.launchActivity(itemImageIntent);
        }
    };

    @Inject
    public NovelRecommendPresenter(NovelRecommendContract.Model model, NovelRecommendContract.View view, ActivityManager mActivityManager, Application mApplication) {

        super(model, view);
        this.mActivityManager = mActivityManager;
        this.mApplication = mApplication;

        initView();

    }

    public void initData(boolean shouldRefresh) {

        mRootView.showLoading();

        mModel.getNovelReCommend(shouldRefresh)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {

                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
//                        new Action0() {
//                            @Override
//                            public void call() {
//                                if (pullToRefresh)
//                                    mRootView.hideLoading();//隐藏上拉刷新的进度条
//                                else
//                                    mRootView.endLoadMore();//隐藏下拉加载更多的进度条
//                            }
//                        }
                    }
                })
                .compose(RxUtils.<List<NovelReCommendEntity>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new CommonSubscriber<List<NovelReCommendEntity>>() {
                    @Override
                    public void onNext(List<NovelReCommendEntity> novelReCommendEntity) {

                        mRecommendList.clear();
                        mRecommendList.addAll(novelReCommendEntity);

                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(Throwable t) {
                        mRootView.hideLoading();
                    }

                    @Override
                    public void onComplete() {

                        mRootView.hideLoading();

                    }
                });

    }

    private void initView() {

        mAdapter = new NovelRecommendAdapter(mRecommendList);
        mAdapter.setNormalItemClickListener(normalItemImageClickListener);
        mAdapter.setBannerClickListener(listener);

        mRootView.setRecyclerViewAdapter(mAdapter);

    }


    public void requestCommend(boolean upData) {

//        .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔

        mModel.getNovelReCommend(upData)
                .subscribeOn(Schedulers.io())
//                .retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() {
//                    @Override
//                    public Publisher<?> apply(@NonNull Flowable<Throwable> throwableFlowable) throws Exception {
//                        return null;
//                    }
//                })//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔

                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {

                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
//                        new Action0() {
//                            @Override
//                            public void call() {
//                                if (pullToRefresh)
//                                    mRootView.hideLoading();//隐藏上拉刷新的进度条
//                                else
//                                    mRootView.endLoadMore();//隐藏下拉加载更多的进度条
//                            }
//                        }
                    }
                })
                .compose(RxUtils.<List<NovelReCommendEntity>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new Subscriber<List<NovelReCommendEntity>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<NovelReCommendEntity> novelReCommendEntity) {
                        mRootView.showMessage(novelReCommendEntity.get(0).getData().get(0).getTitle());
                    }

                    @Override
                    public void onError(Throwable t) {
                        mRootView.showMessage("causeMessage:" + t.getCause().getMessage() + " LocalizeMessage:" + t.getLocalizedMessage() + " message:" + t.getMessage() + " Cause:" + t.getCause().toString());
                    }

                    @Override
                    public void onComplete() {
                        mRootView.showMessage("finish");
                    }
                });


//        new ErrorHandleSubscriber<List<User>>(mErrorHandler) {
//            @Override
//            public void onNext(List<User> users) {
//                lastUserId = users.get(users.size() - 1).getId();//记录最后一个id,用于下一次请求
//                if (pullToRefresh) mUsers.clear();//如果是上拉刷新则清空列表
//                for (User user : users) {
//                    mUsers.add(user);
//                }
//                mAdapter.notifyDataSetChanged();//通知更新数据
//            }
//        }
    }
}
