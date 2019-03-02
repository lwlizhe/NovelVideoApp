package com.lwlizhe.basemodule.utils;



import com.lwlizhe.basemodule.base.BaseActivity;
import com.lwlizhe.basemodule.base.BaseFragment;
import com.lwlizhe.basemodule.mvp.BaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.annotations.NonNull;

public class RxLifecycleUtils {

    private RxLifecycleUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull BaseView view) {

        if (view instanceof BaseActivity) {
            return ((BaseActivity) view).<T>bindToLifecycle();
        } else if (view instanceof BaseFragment) {
            return ((BaseFragment) view).<T>bindToLifecycle();
        } else {
            throw new IllegalArgumentException("Lifecycleable not match");
        }
    }
    public static <T> LifecycleTransformer<T> bindEventToActivityLifecycle(@NonNull BaseView view, ActivityEvent event) {

        if (view instanceof BaseActivity) {
            return ((BaseActivity) view).<T>bindUntilEvent(event);
        } else {
            throw new IllegalArgumentException("Lifecycleable not match");
        }
    }
    public static <T> LifecycleTransformer<T> bindEventToFragmentLifecycle(@NonNull BaseView view, FragmentEvent event) {

        if (view instanceof BaseFragment) {
            return ((BaseFragment) view).<T>bindUntilEvent(event);
        }  else {
            throw new IllegalArgumentException("Lifecycleable not match");
        }
    }

    public static <T> LifecycleTransformer<T> bindToActivityLifecycle(@NonNull BaseActivity view, ActivityEvent event) {
        if(view instanceof BaseActivity){
            return view.<T>bindUntilEvent(event);
        }else{
            throw new IllegalArgumentException("Lifecycleable not match");
        }
    }

    public static <T> LifecycleTransformer<T> bindToActivityLifecycle(@NonNull BaseActivity view) {
        return ((BaseActivity) view).<T>bindToLifecycle();
    }
}