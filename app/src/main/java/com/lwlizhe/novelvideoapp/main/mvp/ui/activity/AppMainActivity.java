package com.lwlizhe.novelvideoapp.main.mvp.ui.activity;


import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.lwlizhe.basemodule.base.adapter.BaseFragmentPagerAdapter;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.common.CommonActivity;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.main.di.component.DaggerAppMainComponent;
import com.lwlizhe.novelvideoapp.main.di.module.AppMainModule;

import com.lwlizhe.novelvideoapp.main.mvp.contract.AppMainContract;
import com.lwlizhe.novelvideoapp.main.mvp.presenter.activity.AppMainPresenter;



/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class AppMainActivity extends CommonActivity<AppMainPresenter> implements AppMainContract.View {


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
        DaggerAppMainComponent.builder().appComponent(appComponent).appMainModule(new AppMainModule(this)).build().inject(this);

    }

    @Override
    protected View initRootView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_novel_main, null, false);
    }

    @Override
    protected void initView() {
        mViewPager=findViewById(R.id.vp_novel_main);
        rootView=findViewById(R.id.root_view);
        mSwitchBtn=findViewById(R.id.flb_switch_mode);
    }

    @Override
    protected void initData() {
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
        Toast.makeText(AppMainActivity.this.getContext(), message, Toast.LENGTH_SHORT).show();
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
        return this;
    }

    public void onViewClicked() {
//        Intent intent=new Intent(AppMainActivity.this, BilibiliMainActivity.class);
//        launchActivity(intent);
    }

}
