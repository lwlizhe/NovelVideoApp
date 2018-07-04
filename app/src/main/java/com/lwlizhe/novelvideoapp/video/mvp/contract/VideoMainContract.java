package com.lwlizhe.novelvideoapp.video.mvp.contract;

import android.support.v7.widget.RecyclerView;

import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.basemodule.mvp.IModel;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.DilidiliInfo;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public interface VideoMainContract {

    interface View extends BaseView{
         void setRecyclerAdapter(RecyclerView.Adapter adapter);

    }

    interface Model extends IModel{

        Flowable<DilidiliInfo> getDilidiliInfo();

    }

}
