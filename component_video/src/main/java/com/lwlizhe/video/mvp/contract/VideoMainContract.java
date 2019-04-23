package com.lwlizhe.video.mvp.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.video.api.entity.DilidiliIndexEntity;


import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public interface VideoMainContract {

    interface View extends BaseView{

         void setRecyclerAdapter(RecyclerView.Adapter adapter);

         void showMsg(String msg);

         Context getContext();

    }

    interface Model extends VideoIntroductionContract.Model{

        Flowable<DilidiliIndexEntity> getDilidiliAppInfo();

    }

}
