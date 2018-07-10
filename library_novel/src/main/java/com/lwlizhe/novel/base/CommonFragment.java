package com.lwlizhe.novel.base;


import com.lwlizhe.AppApplication;
import com.lwlizhe.basemodule.base.BaseFragment;
import com.lwlizhe.basemodule.event.RxEventBus;
import com.lwlizhe.basemodule.mvp.Presenter;
import com.lwlizhe.common.di.component.AppComponent;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public abstract class CommonFragment<P extends Presenter> extends BaseFragment<P> {

    protected AppApplication mApplication;
    protected RxEventBus mEventBus;

    @Override
    protected void ComponentInject() {
        mApplication = (AppApplication) getActivity().getApplication();
        mEventBus=mApplication.getAppComponent().eventBus();
        setupFragmentComponent(mApplication.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mApplication = null;
    }
}

