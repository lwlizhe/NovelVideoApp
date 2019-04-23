package com.lwlizhe.novelvideoapp.main.mvp.presenter.activity;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lwlizhe.GlobeConstance;
import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.base.adapter.BaseFragmentPagerAdapter;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.comic.mvp.ui.fragment.ComicMainFragment;
import com.lwlizhe.novel.mvp.ui.adapter.NovelMainAdapter;
import com.lwlizhe.novel.mvp.ui.fragment.NovelRecommendFragment;
import com.lwlizhe.novelvideoapp.main.mvp.contract.AppMainContract;
import com.lwlizhe.video.mvp.ui.fragment.VideoMainFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class AppMainPresenter extends BasePresenter<AppMainContract.Model, AppMainContract.View> {


    private List<String> mPagerTitles=new ArrayList<>();
    private List<Fragment> mFragments=new ArrayList<>();

    private ActivityManager mActivityManager;
    private Application mApplication;

    private BaseFragmentPagerAdapter mViewPagerAdapter;

    private AppCompatActivity mActivity;

    @Inject
    public AppMainPresenter(AppMainContract.Model model, AppMainContract.View view, ActivityManager activityManager, Application application) {
        super(model, view);
        this.mApplication = application;
        this.mActivityManager = activityManager;

        this.mActivity = mView.getContext();

        initRootView();
        setRootView();
    }

    private void initData() {
//        mFragments.add(new NovelRecommendFragment());
        Fragment recommendFragment = (Fragment) ARouter.getInstance().build(GlobeConstance.CONSTANCE_ROUTER.ROUTER_NOVEL_RECOMMEND).navigation();
        if(recommendFragment!=null){
            mFragments.add(recommendFragment);
        }

        Fragment videoFragment = (Fragment) ARouter.getInstance().build(GlobeConstance.CONSTANCE_ROUTER.ROUTER_VIDEO_MAIN_PAGE).navigation();
        if(videoFragment!=null){
            mFragments.add(videoFragment);
        }
        Fragment comicFragment = (Fragment) ARouter.getInstance().build(GlobeConstance.CONSTANCE_ROUTER.ROUTER_COMIC_MAIN_PAGE).navigation();
        if(comicFragment!=null) {
            mFragments.add(comicFragment);
        }

        mPagerTitles.add("小说");
        mPagerTitles.add("番剧");
        mPagerTitles.add("漫画");
    }

    private void initRootView() {

        initData();

        mViewPagerAdapter = initFragmentPagerAdapter();
//        mCommonNavigator = initIndicator();

    }

    private void setRootView() {

        mView.setFragmentPagerAdapter(mViewPagerAdapter);
//        mView.setIndicator(mCommonNavigator);

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




}
