package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager;

import android.content.Context;
import android.view.MotionEvent;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.NovelControlViewStateChangedListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public class NovelMenuManager {

    private Context mContext;

    private static NovelMenuManager mInstance;
    private NovelContentManager mContentManager;

    private List<NovelControlViewStateChangedListener> mControlViewListeners;

    private boolean isControlViewHidden = true;
    private long tempVolumeId = 0;
    private long tempChapterId = 0;
    private int tempPagePos = 0;

    private NovelMenuManager(Context mContext) {
        this.mContext = mContext;

        mContentManager = NovelContentManager.instance(mContext);

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

    public void notifyPageChanged(NovelPageEntity currentPage) {

        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            if (currentPage != null && (currentPage.getVolumeId() != tempVolumeId || currentPage.getChapterId() != tempChapterId || currentPage.getCurrentPagePos() != tempPagePos)) {

                tempVolumeId = currentPage.getVolumeId();
                tempChapterId = currentPage.getChapterId();
                tempPagePos = currentPage.getCurrentPagePos();

                NovelPageInfo pageInfo = new NovelPageInfo();

                pageInfo.setBookId(currentPage.getBookId());
                pageInfo.setVolumeId(tempVolumeId);
                pageInfo.setChapterId(tempChapterId);
                pageInfo.setCurPagePos(tempPagePos);
                pageInfo.setMaxPageCount(currentPage.getMaxPageCount());

                for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                    mControlViewListener.onPageStateChanged(pageInfo);
                }

            }
        }
    }

    public void onTouch(MotionEvent event) {


        if (isControlViewHidden) {
            onOpenControlView();
        } else {
            onHideControlView();
        }

    }

    public void onOpenControlView() {
        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            isControlViewHidden = !isControlViewHidden;
            for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                mControlViewListener.onShowControlView();
            }
        }
    }

    public void onHideControlView() {
        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            isControlViewHidden = !isControlViewHidden;
            for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                mControlViewListener.onHideControlView();
            }
        }
    }

    public void onOpenCatalog(long bookId, long currentVolumeId, long currentChapterId) {
        if (mControlViewListeners != null && mControlViewListeners.size() != 0) {
            for (NovelControlViewStateChangedListener mControlViewListener : mControlViewListeners) {
                mControlViewListener.onOpenCatalog(bookId, currentVolumeId, currentChapterId);
            }
        }
    }

    public void addControlViewListener(NovelControlViewStateChangedListener listener) {
        mControlViewListeners.add(listener);
    }

}
