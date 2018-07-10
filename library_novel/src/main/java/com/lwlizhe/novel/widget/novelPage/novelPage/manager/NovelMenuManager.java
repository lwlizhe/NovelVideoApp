package com.lwlizhe.novel.widget.novelPage.novelPage.manager;

import android.content.Context;
import android.view.MotionEvent;

import com.lwlizhe.novel.widget.novelPage.novelPage.controlView.NovelControlViewStateChangedListener;
import com.lwlizhe.novel.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novel.widget.novelPage.novelPage.entity.NovelPageEntity;
import com.lwlizhe.novel.widget.novelPage.novelPage.entity.NovelPageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public class NovelMenuManager {

    private Context mContext;

    private static NovelMenuManager mInstance;

    private List<NovelControlViewStateChangedListener> mControlViewListeners;

    private boolean isControlViewHidden = true;
    private boolean isCatalogViewHidden = true;
    private long tempVolumeId = 0;
    private long tempChapterId = 0;
    private int tempPagePos = 0;
    private int tempTextSize = 0;


    private NovelMenuManager(Context mContext) {
        this.mContext = mContext;

        mControlViewListeners = new ArrayList<>();
    }

    public static NovelMenuManager instance(Context mContext) {
        if (mInstance == null) {
            synchronized (NovelMenuManager.class) {
                if (mInstance == null) {
                    mInstance = new NovelMenuManager(mContext);
                }
            }
        }

        return mInstance;
    }

    //判断是页面内容
    public void notifyPageChanged(NovelPageEntity currentPage) {

        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            if (currentPage != null && (currentPage.getVolumeId() != tempVolumeId || currentPage.getChapterId() != tempChapterId || currentPage.getCurrentPagePos() != tempPagePos||currentPage.getCurTextSize() != tempTextSize)) {

                tempVolumeId = currentPage.getVolumeId();
                tempChapterId = currentPage.getChapterId();
                tempPagePos = currentPage.getCurrentPagePos();
                tempTextSize=currentPage.getMaxPageCount();

                NovelPageInfo pageInfo = new NovelPageInfo();

                pageInfo.setBookId(currentPage.getBookId());
                pageInfo.setVolumeId(tempVolumeId);
                pageInfo.setChapterId(tempChapterId);
                pageInfo.setCurPagePos(tempPagePos);
                pageInfo.setPageTextSize(currentPage.getCurTextSize());
                pageInfo.setMaxPageCount(currentPage.getMaxPageCount());

                for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                    mControlViewListener.onPageStateChanged(pageInfo);
                }

            }
        }
    }

    /**
     * 复位，这里是关闭所有界面
     */
    public void reset(){
        hideControlView();
        closeCatalog();
    }

    public void onTouch(MotionEvent event) {

        if (isControlViewHidden) {
            openControlView();
        } else {
            hideControlView();
        }

    }

    public void openControlView() {
        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            isControlViewHidden = false;
            for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                mControlViewListener.onShowControlView();
            }
        }
    }

    public void hideControlView() {
        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            isControlViewHidden = true;
            for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                mControlViewListener.onHideControlView();
            }
        }
    }

    public void onOpenCatalog(NovelCatalogueEntity catalogueEntity,long bookId, long currentVolumeId, long currentChapterId) {
        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            isCatalogViewHidden=false;
            for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                mControlViewListener.onOpenCatalog(catalogueEntity,bookId, currentVolumeId, currentChapterId);
            }
        }
    }
    public void closeCatalog() {
        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            isCatalogViewHidden=true;
            for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                mControlViewListener.onCloseCatalog();
            }
        }
    }

    public void addControlViewListener(NovelControlViewStateChangedListener listener) {
        mControlViewListeners.add(listener);
    }

    public boolean isControlViewOpen(){
        return !isControlViewHidden;
    }
    public boolean isCatalogViewOpen(){
        return !isCatalogViewHidden;
    }

}
