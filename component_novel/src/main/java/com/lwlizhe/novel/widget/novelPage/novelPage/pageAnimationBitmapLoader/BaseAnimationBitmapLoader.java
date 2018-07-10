package com.lwlizhe.novel.widget.novelPage.novelPage.pageAnimationBitmapLoader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.novel.widget.novelPage.novelPage.manager.NovelContentManager;
import com.lwlizhe.novel.widget.novelPage.novelPage.manager.NovelPageBitmapManager;
import com.lwlizhe.novel.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novel.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

/**
 * 提供转场bitmap的绘制，是在pageBitmapManager所绘制的bitmap进行二次操作，应该说最后view的onDraw都是由它来实现的，bitmapManager只是创建bitmap，不绘制到canvas。
 * Created by Administrator on 2018/5/18 0018.
 */

public abstract class BaseAnimationBitmapLoader {

    protected Context mContext;

    protected Scroller mScroller;
    protected View mTargetView;

    protected PointF mTouch = new PointF();
    protected PointF mStart = new PointF();

//    protected Bitmap mCurrentBitmap;
//    protected Bitmap mNextBitmap;

    protected boolean isAnimationRunning = false;

    protected NovelPageBitmapManager mBitmapManager;
    protected NovelContentManager mContentManager;
    protected NovelPageStateObserver mStateObserver;

    protected int mScreenWidth;
    protected int mScreenHeight;

    protected boolean isHasPre=false;
    protected boolean isHasNext=false;
    protected boolean isLoading=false;


    public BaseAnimationBitmapLoader(View targetView) {
        this.mScroller = new Scroller(targetView.getContext());

        mTargetView = targetView;

        mContext=targetView.getContext();

        mBitmapManager = NovelPageBitmapManager.instance(mContext);
        mContentManager = NovelContentManager.instance(mContext);
        mStateObserver=NovelPageStateObserver.getInstance();

        mScreenWidth = UiUtils.getScreenWidth();
        mScreenHeight = UiUtils.getScreenHeight();

        mTouch.x = mScreenWidth;
        mTouch.y = mScreenHeight;

        mStateObserver.addListener(new NovelPageStateListener() {
            @Override
            public void onNextDisable() {
                isHasNext=false;
            }

            @Override
            public void onRequestNewChapter(long requestVolumeId, long requestChapterId) {

            }

            @Override
            public void onPreDisable() {
                isHasPre=false;
            }

            @Override
            public void onLoading() {
                isLoading=true;
            }

            @Override
            public void onLoadingFinish() {
                isLoading=false;
                isHasPre=true;
                isHasNext=true;
            }

            @Override
            public void onNormal() {
                isHasPre=true;
                isHasNext=true;
            }
        });

    }

    public void onTouch(MotionEvent event) {
        onPageTouch(event);
    }

    public void onDraw(Canvas canvas) {

        // 如果页面改变的时候，动画没有执行，那么去画静态页面
        if (isAnimationRunning) {
            calTouchPoint();
            drawCurPage(canvas);
            drawNextPage(canvas);
        } else {
            drawStatic(canvas);
        }

    }

    protected abstract void calTouchPoint();

    protected abstract void calcCornerXY(float x, float y);

    protected abstract void drawStatic(Canvas canvas);

    protected abstract void drawNextPage(Canvas canvas);

    protected abstract void drawCurPage(Canvas canvas);

    protected abstract void startAnimation();

    protected abstract void abortAnimation();

    protected abstract void onPageTouch(MotionEvent event);

    public void computeScroll() {

        if (mScroller.computeScrollOffset()) {

            int currY = mScroller.getCurrY();
            int currX = mScroller.getCurrX();

            mTouch.x = currX;
            mTouch.y = currY;

            if(mScroller.getFinalX() == currX && mScroller.getFinalY() == currY){
                isAnimationRunning=false;
            }

            mTargetView.postInvalidate();
        }

    }


}
