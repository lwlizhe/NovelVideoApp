package com.lwlizhe.basemodule.mvp;


import com.lwlizhe.basemodule.event.RxEventBus;

/**
 * Created by jess on 16/4/28.
 */
public class BasePresenter<M extends IModel, V extends BaseView> implements Presenter {
    protected final String TAG = this.getClass().getSimpleName();
//    protected CompositeSubscription mCompositeSubscription;

    protected M mModel;
    protected V mView;

    protected RxEventBus mEventBus;

    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mView = rootView;
        onStart();
    }

    public BasePresenter(V rootView) {
        this.mView = rootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.onDestroy();
            this.mModel = null;
        }
        this.mView = null;
//        this.mCompositeSubscription = null;
    }

    /**
     * 设置RxEventBus
     * @return
     */
    public void setEventBus(RxEventBus mBus) {
        mEventBus=mBus;
    }


//    protected void addSubscribe(Subscription subscription) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        mCompositeSubscription.add(subscription);//将所有subscription放入,集中处理
//    }
//
//    protected void unSubscribe() {
//        if (mCompositeSubscription != null) {
//            mCompositeSubscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
//        }
//    }


}
