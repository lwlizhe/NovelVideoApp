package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.pageAnimationBitmapLoader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.widget.Toast;

import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager.NovelContentManager;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager.NovelPageBitmapManager;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

/**
 * 提供转场bitmap的绘制，是在pageBitmapManager所绘制的bitmap进行二次操作，应该说最后view的onDraw都是由它来实现的，bitmapManager只是创建bitmap，不绘制到canvas。
 * Created by Administrator on 2018/5/18 0018.
 */

public abstract class BaseAnimationBitmapLoader {

    protected Context mContext;

    protected Scroller mScroller;
    protected View mTargetView;

    protected PointF mTouch = new PointF();

//    protected Bitmap mCurrentBitmap;
//    protected Bitmap mNextBitmap;

    protected boolean isAnimationRunning = false;

    protected NovelPageBitmapManager mBitmapManager;
    protected NovelContentManager mContentManager;
    protected NovelPageStateObserver mStateObserver;

    protected int mScreenWidth;
    protected int mScreenHeight;

//    protected boolean isNextEnable=false;
//    protected boolean isPreEnable=false;

    protected static final String STATE_LOADING="state_loading";
    protected static final String STATE_NO_MORE="state_no_more";
    protected static final String STATE_NO_PRE="state_no_pre";
    protected static final String STATE_NORMAL="state_normal";

    protected String mCurrentState=STATE_LOADING;


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
                mCurrentState=STATE_NO_MORE;
            }

            @Override
            public void onRequestNewChapter(long requestVolumeId, long requestChapterId) {
                mCurrentState=STATE_LOADING;
            }

            @Override
            public void onPreDisable() {
                mCurrentState=STATE_NO_PRE;
            }

            @Override
            public void onLoading() {
                mCurrentState=STATE_LOADING;
            }

            @Override
            public void onLoadingFinish() {

            }

            @Override
            public void onNormal() {
                mCurrentState=STATE_NORMAL;
            }
        });

    }

    public void onTouch(MotionEvent event) {
        onPageTouch(event);
    }

//    public void setBitmap(Bitmap mCurrentBitmap,Bitmap mNextBitmap){
//        this.mCurrentBitmap=mCurrentBitmap;
//        this.mNextBitmap=mNextBitmap;
//    }

    public void onDraw(Canvas canvas) {

        // 如果页面改变的时候，动画没有执行，那么去画静态页面
        if (isAnimationRunning) {
            calTouchPoint();
            drawCurPage(canvas);
            drawNextPage(canvas);
        } else {
            drawStatic(canvas);
        }

//        isAnimationRunning = false;
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

//            Logger.d(currX);
//            Logger.d(currY);

            if(mScroller.getFinalX() == currX && mScroller.getFinalY() == currY){
                isAnimationRunning=false;
            }

            mTargetView.postInvalidate();
        }

    }


}
