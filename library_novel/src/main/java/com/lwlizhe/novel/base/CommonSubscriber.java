package com.lwlizhe.novel.base;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by Administrator on 2018/6/5 0005.
 */

public abstract class CommonSubscriber<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(Throwable t) {
        onFailed(t);
    }

    public abstract void onFailed(Throwable t);

}
