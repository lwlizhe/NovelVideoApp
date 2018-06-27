package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.text.Html;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.NovelControlViewStateChangedListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager.NovelContentManager;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager.NovelMenuManager;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager.NovelPageBitmapManager;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.pageAnimationBitmapLoader.BaseAnimationBitmapLoader;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.pageAnimationBitmapLoader.PageAnimationBitmapLoaderFactory;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

/**
 * 这部分负责的是整体页面逻辑，所有的数据都在这里进行交互处理
 * Created by Administrator on 2018/5/15 0015.
 */

public abstract class BaseNovelLoader implements IPageLoader {

    protected Context mContext;

    protected int mPageHeight, mPageWidth;

    private RectF mCenterRect = null;

    protected NovelPage mTargetPageView;
    // 目录
    protected NovelCatalogueEntity mCatalogueEntity;

    protected BaseAnimationBitmapLoader mPageAnimationBitmapLoader;

    protected NovelPageBitmapManager mBitmapManager;
    protected NovelContentManager mContentManager;
    protected NovelMenuManager mMenuManager;
    protected NovelPageStateObserver mStateObserver;

    protected PageAnimationBitmapLoaderFactory mAnimationFactory;

    protected boolean isLoading = false;
    protected boolean isMoving = false;

    protected float mStartX;
    protected float mStartY;

    public BaseNovelLoader(NovelPage targetPageView) {
        this.mTargetPageView = targetPageView;
        mContext = targetPageView.getContext();

        mBitmapManager = NovelPageBitmapManager.instance(mContext);
        mContentManager = NovelContentManager.instance(mContext);
        mMenuManager = NovelMenuManager.instance(mContext);

        mStateObserver = NovelPageStateObserver.getInstance();

        mStateObserver.addListener(new NovelPageStateListener() {
            @Override
            public void onNextDisable() {

            }

            @Override
            public void onRequestNewChapter(long requestVolumeId, long requestChapterId) {

            }

            @Override
            public void onPreDisable() {

            }

            @Override
            public void onLoading() {
                isLoading = true;
            }

            @Override
            public void onLoadingFinish() {
                isLoading = false;
                if (mTargetPageView != null) {
                    mBitmapManager.drawCurrent();
                    mTargetPageView.postInvalidate();
                }
            }

            @Override
            public void onNormal() {

            }
        });

        mAnimationFactory = new PageAnimationBitmapLoaderFactory(targetPageView);

        initPageAnimation();

    }

    private void initPageAnimation() {
        mPageAnimationBitmapLoader = mAnimationFactory.createNovelLoader(0);
    }


    @Override
    public void loadNovel(long bookId, long volumeId, long chapterId, String content) {


        content = Html.fromHtml(content).toString();


        mContentManager.setContent(bookId, volumeId, chapterId, content);

    }

    @Override
    public void loadLastRead(long bookId) {
        mContentManager.loadLastRead(bookId);
        mBitmapManager.drawCurrent();
        mTargetPageView.postInvalidate();
    }

    @Override
    public void skipToTargetChapter(long bookId, long volumeId, long chapterId) {
        mContentManager.loadTargetChapter(bookId, volumeId, chapterId);
        mBitmapManager.drawCurrent();
        mTargetPageView.postInvalidate();
    }

    @Override
    public void skipToNextChapter(){
        mContentManager.loadNextChapter();
        mBitmapManager.drawCurrent();
        mTargetPageView.postInvalidate();
    }

    @Override
    public void skipToPreChapter(){
        mContentManager.loadPreChapter();
        mBitmapManager.drawCurrent();
        mTargetPageView.postInvalidate();
    }

    @Override
    public void skipToTargetPagePos(int pos){
        mContentManager.loadTargetPagePos(pos);
        mBitmapManager.drawCurrent();
        mTargetPageView.postInvalidate();
    }

    @Override
    public void setCatalogue(NovelCatalogueEntity catalogueEntity) {
        mCatalogueEntity = catalogueEntity;
        mContentManager.setCatalogue(catalogueEntity);
    }

    @Override
    public void setControlViewStateListener(NovelControlViewStateChangedListener listener) {
        mMenuManager.setControlViewListener(listener);
    }

    @Override
    public void onPageSizeChanged(int w, int h) {
        mPageWidth = w;
        mPageHeight = h;

        notifyPageSizeChanged(w, h);

    }

    private void notifyPageSizeChanged(int w, int h) {

        mBitmapManager.setPageSize(w, h);

//        if(mContentManager.getCurChapterList().size()!=0){
        mBitmapManager.drawCurrent();
//        }

//        mPageAnimationBitmapLoader.setBitmap(mBitmapManager.getCurrentPageBitmap(), mBitmapManager.getNextPageBitmap());


    }

    @Override
    public void onDraw(Canvas mTargetPageCanvas) {

        mPageAnimationBitmapLoader.onDraw(mTargetPageCanvas);

//        mTargetPageCanvas.drawBitmap(mBitmapManager.getCurrentPageBitmap(), 0, 0, null);

    }

    @Override
    public void onTouch(MotionEvent event) {

//        if (isLoading) {
//            return;
//        }

        if (mCenterRect == null) {
            mCenterRect = new RectF(mPageWidth / 5, mPageHeight / 3,
                    mPageWidth * 4 / 5, mPageHeight * 2 / 3);
            mBitmapManager.setTouchRect(mCenterRect);
        }
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                isMoving = false;
                onPageTouch(event);
                break;

            case MotionEvent.ACTION_MOVE:
                int slop = ViewConfiguration.get(mTargetPageView.getContext()).getScaledTouchSlop();
                if (!isMoving) {
                    isMoving = Math.abs(mStartX - event.getX()) > slop || Math.abs(mStartY - event.getY()) > slop;
                }

                if (isMoving) {
                    onPageTouch(event);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (!isMoving) {
                    //设置中间区域范围

                    //是否点击了中间
                    if (mCenterRect.contains(event.getX(), event.getY())) {
                        mMenuManager.onTouch(event);
                        return;
                    }

                }
                onPageTouch(event);
                break;
        }

//        onPageTouch(event);

    }

    protected abstract void onPageTouch(MotionEvent event);

    @Override
    public void computeScroll() {
        mPageAnimationBitmapLoader.computeScroll();
    }

    @Override
    public void updateBattery(int level) {

    }

    @Override
    public void updateTime() {
        mBitmapManager.drawCurrent();
        mTargetPageView.postInvalidate();
    }

    @Override
    public void onDetachedFromWindow() {
        mContentManager.onDestroy();
        mBitmapManager.onDestroy();
        mStateObserver.onDestroy();
    }
}
