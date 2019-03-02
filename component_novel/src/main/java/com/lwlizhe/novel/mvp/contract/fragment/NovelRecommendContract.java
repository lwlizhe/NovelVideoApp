package com.lwlizhe.novel.mvp.contract.fragment;

import android.content.Context;

import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.basemodule.mvp.IModel;
import com.lwlizhe.novel.api.entity.NovelReCommendEntity;

import java.util.List;

import io.reactivex.Flowable;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public interface NovelRecommendContract {


    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {

//        void startRefresh();
//        void endRefresh();
        //申请权限
//        RxPermissions getRxPermissions();

        Context getContext();

        void setRecyclerViewAdapter(BaseRecyclerViewAdapter mAdapter);

    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Flowable<List<NovelReCommendEntity>> getNovelReCommend(boolean upData);
//        Flowable<List<NovelSearchResultEntity>> getNovelSearchResult(long bigCatId, String keyWords, long pages);

    }
}
