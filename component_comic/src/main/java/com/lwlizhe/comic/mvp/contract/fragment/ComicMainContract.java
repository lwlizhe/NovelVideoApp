package com.lwlizhe.comic.mvp.contract.fragment;

import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.basemodule.mvp.IModel;
import com.lwlizhe.comic.api.entity.ComicRecommendResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/7/10 0010.
 */

public interface ComicMainContract {

    interface View extends BaseView{

        void setRecyclerViewAdapter(BaseRecyclerViewAdapter mAdapter);

    }

    interface Model extends IModel{

        Flowable<List<ComicRecommendResponse>> getComicRecommend();

    }

}
