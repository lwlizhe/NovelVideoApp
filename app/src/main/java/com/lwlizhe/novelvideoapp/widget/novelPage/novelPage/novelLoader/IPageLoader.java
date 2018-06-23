package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public interface IPageLoader {

    void onTouch(MotionEvent event);

    void onDraw(Canvas mCanvas);

    void onPageSizeChanged(int w,int h);

    void loadNovel(long bookId,long volumeId,long chapterId,String novel);

    void loadLastRead(long bookId);

    void skipToTargetChapter(long bookId,long volumeId,long chapterId);

    void setCatalogue(NovelCatalogueEntity catalogueEntity);

    void computeScroll();

    void updateTime();

    void updateBattery(int level);

    void onDetachedFromWindow();

}
