package com.lwlizhe.basemodule.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.lwlizhe.basemodule.mvp.Presenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import javax.inject.Inject;


public abstract class BaseActivity<P extends Presenter> extends RxAppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected BaseApplication mApplication;
    @Inject
    protected P mPresenter;

    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_add_activity_list";//是否加入到activity的list，管理


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;


        return super.onCreateView(name, context, attrs);
    }


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = (BaseApplication) getApplication();

        setContentView(initRootView());
        initView();
        initListener();
        ComponentInject();//依赖注入

        initData();

    }

    /**
     * 依赖注入的入口
     */
    protected abstract void ComponentInject();


    public void FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
        this.mApplication = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    protected abstract View initRootView();
    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
}
