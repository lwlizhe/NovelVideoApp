package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager;

import android.content.Context;
import android.view.MotionEvent;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.NovelControlViewStateChangedListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public class NovelMenuManager {

    private Context mContext;
    
    private static NovelMenuManager mInstance;
    private NovelContentManager mContentManager;

    private NovelControlViewStateChangedListener mControlViewListener;

    private boolean isControlViewHidden=false;
    private long tempVolumeId=0;
    private long tempChapterId=0;
    private int tempPagePos=0;

    private NovelMenuManager(Context mContext) {
        this.mContext=mContext;

        mContentManager=NovelContentManager.instance(mContext);
    }

    public static NovelMenuManager instance(Context mContext){
        if(mInstance==null){
            synchronized (NovelMenuManager.class){
                if(mInstance==null){
                    mInstance=new NovelMenuManager(mContext);
                }
            }
        }
        
        return mInstance;
    }

    public void notifyPageChanged(NovelPageEntity currentPage){

        if(mControlViewListener!=null){
            if(currentPage!=null&&(currentPage.getVolumeId()!=tempVolumeId||currentPage.getChapterId()!=tempChapterId||currentPage.getCurrentPagePos()!=tempPagePos)){

                tempVolumeId=currentPage.getVolumeId();
                tempChapterId=currentPage.getChapterId();
                tempPagePos=currentPage.getCurrentPagePos();

                NovelPageInfo pageInfo=new NovelPageInfo();

                pageInfo.setBookId(currentPage.getBookId());
                pageInfo.setVolumeId(tempVolumeId);
                pageInfo.setChapterId(tempChapterId);
                pageInfo.setCurPagePos(tempPagePos);
                pageInfo.setMaxPageCount(currentPage.getMaxPageCount());

                mControlViewListener.onPageStateChanged(pageInfo);
            }
        }
    }

    public void onTouch(MotionEvent event){

        if(isControlViewHidden){
            onOpenControlView();
        }else {
            onHideControlView();
        }

    }

    public void onOpenControlView(){
        if(mControlViewListener!=null){
            isControlViewHidden=!isControlViewHidden;
            mControlViewListener.onShowControlView();
        }
    }
    public void onHideControlView(){
        if(mControlViewListener!=null){
            isControlViewHidden=!isControlViewHidden;
            mControlViewListener.onHideControlView();
        }
    }

    public void onOpenCatalog(long bookId,long currentVolumeId,long currentChapterId){
        if(mControlViewListener!=null){
            mControlViewListener.onOpenCatalog(bookId,currentVolumeId,currentChapterId);
        }
    }

    public void setControlViewListener(NovelControlViewStateChangedListener listener){
        mControlViewListener=listener;
    }

}
