package com.lwlizhe.basemodule.base;

import android.app.Application;
import android.content.Context;

import com.lwlizhe.basemodule.di.component.DaggerBaseComponent;
import com.lwlizhe.basemodule.di.module.AppModule;
import com.lwlizhe.basemodule.di.module.ClientModule;
import com.lwlizhe.basemodule.di.module.GlobeConfigModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

import static com.trello.rxlifecycle2.internal.Preconditions.checkNotNull;

public abstract class BaseApplication extends Application {
    static private BaseApplication mApplication;
    private ClientModule mClientModule;
    private AppModule mAppModule;
    private GlobeConfigModule mGlobeConfigModule;
    //    @Inject
//    public GlideImageLoaderStrategy mGlideImageLoader;
    @Inject
    protected ActivityManager mActivityManager;
    @Inject
    protected ActivityLifecycle mActivityLifecycle;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        this.mAppModule = new AppModule(this);//提供application

        DaggerBaseComponent
                .builder()
                .appModule(mAppModule)
                .build()
                .inject(this);

        this.mClientModule = new ClientModule(mActivityManager);//用于提供okhttp和retrofit的单例
        this.mGlobeConfigModule = checkNotNull(getGlobeConfigModule(), "lobeConfigModule is required");

        Logger.addLogAdapter(new AndroidLogAdapter());
        registerActivityLifecycleCallbacks(mActivityLifecycle);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mClientModule != null)
            this.mClientModule = null;
        if (mAppModule != null)
            this.mAppModule = null;
        if (mActivityLifecycle != null) {
            unregisterActivityLifecycleCallbacks(mActivityLifecycle);
        }
        if (mActivityManager != null) {//释放资源
            this.mActivityManager.release();
            this.mActivityManager = null;
        }
        if (mApplication != null)
            this.mApplication = null;
    }


    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     *
     * @return
     */
    protected abstract GlobeConfigModule getGlobeConfigModule();

    public ClientModule getClientModule() {
        return mClientModule;
    }

    public AppModule getAppModule() {
        return mAppModule;
    }

//    public GlideImageLoaderStrategy getGlideImageLoader(){
//        return mGlideImageLoader;
//    }

    public ActivityManager getAppManager() {
        return mActivityManager;
    }


    /**
     * 返回上下文
     *
     * @return
     */
    public static Context getContext() {
        return mApplication;
    }

}
