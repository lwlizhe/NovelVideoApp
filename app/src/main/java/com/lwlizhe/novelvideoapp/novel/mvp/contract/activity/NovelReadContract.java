package com.lwlizhe.novelvideoapp.novel.mvp.contract.activity;

import android.support.v7.app.AppCompatActivity;

import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.basemodule.mvp.IModel;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public interface NovelReadContract {


    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {

        AppCompatActivity getContext();

        void setNovelContent(long bookId,long volumeId,long chapterId,String content);

    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

        Flowable<String> getNovelContent(long novelId,long volumeId,long chapterId);

    }

}
