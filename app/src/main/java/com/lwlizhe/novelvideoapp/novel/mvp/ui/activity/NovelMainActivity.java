package com.lwlizhe.novelvideoapp.novel.mvp.ui.activity;


import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.lwlizhe.basemodule.base.adapter.BaseFragmentPagerAdapter;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.common.CommonActivity;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.novel.di.component.DaggerNovelMainComponent;
import com.lwlizhe.novelvideoapp.novel.di.module.NovelMainModule;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.activity.NovelMainContract;
import com.lwlizhe.novelvideoapp.novel.mvp.presenter.activity.NovelMainPresenter;



/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelMainActivity extends CommonActivity<NovelMainPresenter> implements NovelMainContract.View {


    ViewPager mViewPager;
    CoordinatorLayout rootView;
    FloatingActionButton mSwitchBtn;

    private boolean isLoadingMore;


    /*************************************************************************************************************
     * 页面初始化部分
     *************************************************************************************************************/


    @Override
    public void setFragmentPagerAdapter(BaseFragmentPagerAdapter adapter) {
        mViewPager.setAdapter(adapter);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerNovelMainComponent.builder().appComponent(appComponent).novelMainModule(new NovelMainModule(this)).build().inject(this);
    }


    @Override
    protected View initRootView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_novel_main, null, false);
    }

    @Override
    protected void initView() {
        mViewPager=findViewById(R.id.vp_novel_main);
        rootView=findViewById(R.id.root_view);
        mSwitchBtn=findViewById(R.id.flb_switch_mode);
    }

    @Override
    protected void initData() {
//        mPresenter.requestCommend(true);
        mPresenter.setEventBus(mEventBus);
    }

    @Override
    protected void initListener() {

    }

    /*************************************************************************************************************
     * View层功能部分
     *************************************************************************************************************/


    @Override
    public void setCurrentViewPager(int index) {
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(NovelMainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public AppCompatActivity getContext() {
        return NovelMainActivity.this;
    }

    public void onViewClicked() {
//        Intent intent=new Intent(NovelMainActivity.this, BilibiliMainActivity.class);
//        launchActivity(intent);
    }

}
