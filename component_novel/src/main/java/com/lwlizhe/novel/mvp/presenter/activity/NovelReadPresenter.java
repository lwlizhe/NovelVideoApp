package com.lwlizhe.novel.mvp.presenter.activity;

import com.lwlizhe.basemodule.di.scope.ActivityScope;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.novel.mvp.contract.activity.NovelReadContract;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
@ActivityScope
public class NovelReadPresenter extends BasePresenter<NovelReadContract.Model,NovelReadContract.View> {

    private long mBookId;

    @Inject
    public NovelReadPresenter(NovelReadContract.Model model, NovelReadContract.View rootView) {
        super(model, rootView);
    }

    public void getChapter(long bookId, final long volumeId, final long chapterId){

        mBookId=bookId;

        mModel.getNovelContent(bookId,volumeId,chapterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String s) {
                        initReadPage(volumeId,chapterId,s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        initReadPage(mRootView.getContext().getString(R.string.test));

    }

    private void initReadPage(long volumeId,long chapterId,String content){

        mRootView.setNovelContent(mBookId,volumeId,chapterId,content);

    }

}
