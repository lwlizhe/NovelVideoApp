package com.lwlizhe.comic.base;

import com.lwlizhe.AppApplication;
import com.lwlizhe.basemodule.base.BaseActivity;
import com.lwlizhe.basemodule.event.RxEventBus;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;
import com.lwlizhe.basemodule.mvp.Presenter;
import com.lwlizhe.common.di.component.AppComponent;


/**
 * Created by Administrator on 2018/5/3 0003.
 */

public abstract class CommonActivity<P extends Presenter> extends BaseActivity<P> {

    protected AppApplication mApplication;
    protected AppComponent mAppComponent;
    protected GlideImageLoaderStrategy mImageLoader;
    protected RxEventBus mEventBus;

    @Override
    protected void ComponentInject() {
        mApplication = (AppApplication) getApplication();
        mAppComponent=mApplication.getAppComponent();
        mEventBus=mAppComponent.eventBus();
        mImageLoader=mAppComponent.mGlideImageLoader();
        setupActivityComponent(mApplication.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mApplication = null;
    }
}