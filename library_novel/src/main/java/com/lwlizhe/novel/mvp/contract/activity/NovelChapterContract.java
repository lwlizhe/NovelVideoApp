package com.lwlizhe.novel.mvp.contract.activity;

import android.support.v7.app.AppCompatActivity;

import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.basemodule.mvp.IModel;
import com.lwlizhe.common.api.novel.entity.NovelDetailEntity;
//import com.lwlizhe.common.api.novel.entity.NovelDetailEntity;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/5/5 0005.
 */

public interface NovelChapterContract {

    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {

        AppCompatActivity getContext();

        void setData(NovelDetailEntity data);

    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
//        Flowable<List<NovelReCommendEntity>> getNovelCommend(boolean upData);
//        Flowable<List<NovelSearchResultEntity>> getNovelSearchResult(long bigCatId, String keyWords, long pages);

        Flowable<NovelDetailEntity> getNovelChapter(long id);

    }

}
