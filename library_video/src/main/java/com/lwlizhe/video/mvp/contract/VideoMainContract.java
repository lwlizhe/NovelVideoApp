package com.lwlizhe.video.mvp.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.basemodule.mvp.IModel;
import com.lwlizhe.common.api.video.entity.jsoup.DilidiliInfo;


import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public interface VideoMainContract {

    interface View extends BaseView{

         void setRecyclerAdapter(RecyclerView.Adapter adapter);

         Context getContext();

    }

    interface Model extends IModel{

        Flowable<DilidiliInfo> getDilidiliInfo();

    }

}
