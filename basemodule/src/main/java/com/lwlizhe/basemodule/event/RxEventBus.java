package com.lwlizhe.basemodule.event;


import com.lwlizhe.basemodule.event.message.BaseMessage;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by Administrator on 2018/5/2 0002.
 */
@Singleton
public class RxEventBus {

    private FlowableProcessor<Object> mBus;
    private final Map<Class<?>, Object> mStickyEventMap;

    @Inject
    public RxEventBus() {
        mStickyEventMap = new ConcurrentHashMap<>();
        mBus=PublishProcessor.create().toSerialized();
    }

    /**
     * 发送事件
     */
    public void post(BaseMessage event) {
        mBus.onNext(event);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Flowable<T> toFlowable(final Class<T> eventType) {
        return mBus.ofType(eventType);
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }


    /**
     * Stciky 相关
     */

    /**
     * 发送一个新Sticky事件
     */
    public void postSticky(BaseMessage event) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(event.getClass(), event);
        }
        post(event);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */

//    (Observable.create(new ObservableOnSubscribe<T>() {
//        @Override
//        public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
//            subscriber.onNext(eventType.cast(event));
//        }
//    })

    public <T> Flowable<T> toFlowableSticky(final Class<T> eventType) {
        synchronized (mStickyEventMap) {
            Flowable<T> flowable = mBus.ofType(eventType);
            final Object event = mStickyEventMap.get(eventType);

            if (event != null) {
                return flowable.mergeWith(new Publisher<T>() {
                    @Override
                    public void subscribe(Subscriber<? super T> s) {
                        s.onNext(eventType.cast(event));
                    }
                });
            } else {
                return flowable;
            }
        }
    }

    /**
     * 根据eventType获取Sticky事件
     */
    public <T> T getStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }

    /**
     * 移除指定eventType的Sticky事件
     */
    public <T> T removeStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.remove(eventType));
        }
    }

    /**
     * 移除所有的Sticky事件
     */
    public void removeAllStickyEvents() {
        synchronized (mStickyEventMap) {
            mStickyEventMap.clear();
        }
    }


    /**
     * 普通订阅解绑
     * @param disposable
     */
    public static   void  rxBusUnbund(CompositeDisposable disposable){
        if (null != disposable && !disposable.isDisposed()) {
            disposable.clear();
        }
    }
}
