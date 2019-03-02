package com.lwlizhe.video.mvp.contract;

import android.content.Context;

import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.basemodule.mvp.IModel;
import com.lwlizhe.video.api.entity.DilidiliAnimationDetailResponseEntity;
import com.lwlizhe.video.api.entity.DilidiliVideoResourceResponseEntity;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public interface VideoIntroductionContract {

    interface View extends BaseView{


         void showMsg(String msg);

         void onGetRefreshData(boolean isSuccess,DilidiliAnimationDetailResponseEntity entity);

         Context getContext();

    }

    interface Model extends IModel{

        Flowable<DilidiliAnimationDetailResponseEntity> getAnimationDetail(int resourceId);

        Flowable<DilidiliVideoResourceResponseEntity> getAnimationVideoResource(String url);
    }

}
