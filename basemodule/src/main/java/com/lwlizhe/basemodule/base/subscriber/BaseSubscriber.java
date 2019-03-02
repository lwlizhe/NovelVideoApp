package com.lwlizhe.basemodule.base.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Locale;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    private  FlowableEmitter<String> toastEmitter;

    public BaseSubscriber() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                toastEmitter=emitter;
            }
        }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
//                Toast.makeText(BaseApplicationLike.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // http异常时回调（不包含超时异常）
    protected void onHttpError(HttpException e) {

    }

    // 请求超时异常时回调
    protected void onConnectTimeOut(SocketTimeoutException e) {
//        BaseApplicationLike.showToast("连接超时");
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(Throwable t) {

        if (t instanceof SocketTimeoutException) {

            toastEmitter.onNext("连接超时");
        } else if (t instanceof HttpException) {
            HttpException exception = (HttpException) t;

            onHttpError((HttpException) t);

            toastEmitter.onNext("网络异常");

        } else if (t instanceof ConnectException) {
            toastEmitter.onNext("无法连接到服务器");
        } else if (t instanceof OnErrorNotImplementedException) {
            toastEmitter.onNext(t.getMessage());
        }

        onFailed(t);

    }

    public abstract void onFailed(Throwable t);

    public abstract void onSuccess(T data);

}