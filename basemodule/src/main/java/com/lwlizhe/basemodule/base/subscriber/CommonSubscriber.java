package com.lwlizhe.basemodule.base.subscriber;

/**
 * Created by lwlizhe on 2019/2/14.
 */

public abstract class CommonSubscriber<T> extends BaseSubscriber<T>{

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
}
