package com.lwlizhe.comic.mvp.presenter.activity;

import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.comic.mvp.contract.activity.ComicPageContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/7/16 0016.
 */

public class ComicPagePresenter extends BasePresenter<ComicPageContract.Model,ComicPageContract.View> {

    @Inject
    public ComicPagePresenter(ComicPageContract.Model model, ComicPageContract.View rootView) {
        super(model, rootView);
    }
}
