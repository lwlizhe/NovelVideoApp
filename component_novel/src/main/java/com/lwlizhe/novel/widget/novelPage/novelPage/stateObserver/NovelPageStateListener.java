package com.lwlizhe.novel.widget.novelPage.novelPage.stateObserver;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public interface NovelPageStateListener {

    void onNextDisable();

    void onRequestNewChapter(long requestVolumeId, long requestChapterId);

    void onPreDisable();

    void onLoading();

    void onLoadingFinish();

    void onNormal();
}
