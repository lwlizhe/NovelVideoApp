package com.lwlizhe.basemodule.utils;

import com.lwlizhe.basemodule.base.BaseActivity;
import com.lwlizhe.basemodule.base.BaseFragment;
import com.lwlizhe.basemodule.mvp.BaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;



/**
 * Created by jess on 11/10/2016 16:39
 * Contact with jess.yan.effort@gmail.com
 */

public class RxUtils {

    public static <T> LifecycleTransformer<T> bindToLifecycle(BaseView view) {
        if (view instanceof BaseActivity) {
            return ((BaseActivity) view).<T>bindToLifecycle();
        } else if (view instanceof BaseFragment) {
            return ((BaseFragment) view).<T>bindToLifecycle();
        } else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }


    }

}
