package com.lwlizhe.novelvideoapp.novel.mvp.presenter.activity;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.adapter.BaseFragmentPagerAdapter;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.activity.NovelMainContract;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.adapter.NovelMainAdapter;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.fragment.NovelRecommendFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelMainPresenter extends BasePresenter<NovelMainContract.Model, NovelMainContract.View> {


    private List<String> mPagerTitles=new ArrayList<>();
    private List<Fragment> mFragments=new ArrayList<>();

    private ActivityManager mActivityManager;
    private Application mApplication;

    private BaseFragmentPagerAdapter mViewPagerAdapter;

    private AppCompatActivity mActivity;

    @Inject
    public NovelMainPresenter(NovelMainContract.Model model, NovelMainContract.View view, ActivityManager activityManager, Application application) {
        super(model, view);
        this.mApplication = application;
        this.mActivityManager = activityManager;

        this.mActivity = mRootView.getContext();

        initRootView();
        setRootView();
    }

    private void initData() {
        mFragments.add(new NovelRecommendFragment());
        mFragments.add(new NovelRecommendFragment());
        mFragments.add(new NovelRecommendFragment());

        mPagerTitles.add("推荐");
        mPagerTitles.add("最新");
        mPagerTitles.add("分类");
    }

    private void initRootView() {

        initData();

        mViewPagerAdapter = initFragmentPagerAdapter();
//        mCommonNavigator = initIndicator();

    }

    private void setRootView() {

        mRootView.setFragmentPagerAdapter(mViewPagerAdapter);
//        mRootView.setIndicator(mCommonNavigator);

    }

    /**
     * 初始化FragmentAdapter
     * @return BaseFragmentPagerAdapter
     */
    private BaseFragmentPagerAdapter initFragmentPagerAdapter() {

        NovelMainAdapter novelMainAdapter = new NovelMainAdapter(mActivity.getSupportFragmentManager());

//        novelMainAdapter.bindData(mFragments,  mPagerTitles.toArray(new CharSequence[mPagerTitles.size()]));
        novelMainAdapter.setData(mFragments);
        novelMainAdapter.setTitles(mPagerTitles);
        return novelMainAdapter;
    }

//    /**
//     * 初始化Indicator的CommonNavigator，用于调整MagicIndicator
//     * @return CommonNavigator
//     */
//    private CommonNavigator initIndicator() {
//        CommonNavigator commonNavigator = new CommonNavigator(mRootView.getContext());
//        final CommonNavigatorAdapter commonNavigatorAdapter = new CommonNavigatorAdapter() {
//            @Override
//            public int getCount() {
//                return mViewPagerAdapter.getCount();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int index) {
//                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
//                simplePagerTitleView.setText(TextUtils.isEmpty(mViewPagerAdapter.getPageTitle(index)) ? "" : mViewPagerAdapter.getPageTitle(index).toString());
//                simplePagerTitleView.setTextSize(18);
//                simplePagerTitleView.setNormalColor(Color.GRAY);
//                simplePagerTitleView.setSelectedColor(Color.BLACK);
//                simplePagerTitleView.setGravity(Gravity.CENTER);
//                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mRootView.setCurrentViewPager(index);
//                    }
//                });
//                return simplePagerTitleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
//                indicator.setColors(Color.parseColor("#ff4a42"), Color.parseColor("#fcde64"), Color.parseColor("#73e8f4"), Color.parseColor("#76b0ff"), Color.parseColor("#c683fe"));
//                return indicator;
//            }
//        };
//        commonNavigator.setAdapter(commonNavigatorAdapter);
//
//        return commonNavigator;
//    }
//
//


//
//    /**
//     * 动漫之家搜索
//     * @param bigCatId 0漫画，1轻小说
//     * @param keyWords 关键字
//     * @param pages 页数
//     */
//    public void getSearchResult(long bigCatId, String keyWords, long pages) {
//        mModel.getNovelSearchResult(bigCatId, keyWords, pages)
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Consumer<Subscription>() {
//                    @Override
//                    public void accept(@NonNull Subscription subscription) throws Exception {
//
//                    }
//                }).subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doAfterTerminate(new Action() {
//                    @Override
//                    public void run() throws Exception {
////                        new Action0() {
////                            @Override
////                            public void call() {
////                                if (pullToRefresh)
////                                    mRootView.hideLoading();//隐藏上拉刷新的进度条
////                                else
////                                    mRootView.endLoadMore();//隐藏下拉加载更多的进度条
////                            }
////                        }
//                    }
//                })
//                .compose(RxUtils.<List<NovelSearchResultEntity>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
//                .subscribe(new Subscriber<List<NovelSearchResultEntity>>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        s.request(Long.MAX_VALUE);
//                    }
//
//                    @Override
//                    public void onNext(List<NovelSearchResultEntity> novelCommendEntity) {
//                        mRootView.showMessage(novelCommendEntity.get(0).getTitle());
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        mRootView.showMessage("causeMessage:" + t.getCause().getMessage() + " LocalizeMessage:" + t.getLocalizedMessage() + " message:" + t.getMessage() + " Cause:" + t.getCause().toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mRootView.showMessage("finish");
//                    }
//                });
//    }


}
