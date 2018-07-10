package com.lwlizhe.novel.widget.novelPage.novelPage.novelLoader;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.lwlizhe.novel.widget.novelPage.novelPage.controlView.NovelControlViewStateChangedListener;
import com.lwlizhe.novel.widget.novelPage.novelPage.entity.NovelCatalogueEntity;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public interface IPageLoader {

    //***********************************绘制******************************************
    void onTouch(MotionEvent event);
    void onDraw(Canvas mCanvas);

    //***********************************周期******************************************
    void onPageSizeChanged(int w, int h);
    void computeScroll();
    void onDetachedFromWindow();

    //***********************************内容装载***************************************
    void loadNovel(long bookId, long volumeId, long chapterId, String novel);
    void loadLastRead(long bookId);
    void skipToTargetChapter(long bookId, long volumeId, long chapterId);
    void skipToNextChapter();
    void skipToPreChapter();
    void skipToTargetPagePos(int pos);
    void setCatalogue(NovelCatalogueEntity catalogueEntity);

    //**********************************功能和配置***************************************
    void setTextSize(int size);
    void setBgColor(int color);
    void updateTime();
    void updateBattery(int level);

    //**********************************监听器********************************************
    void addControlViewStateListener(NovelControlViewStateChangedListener listener);

}
