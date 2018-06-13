package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.view.MotionEvent;

import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader.entity.NovelPageEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

import java.util.HashMap;
import java.util.List;

/**
 * 提供绘制完成的currentBitmap和nextBitmap，所有bitmap的操作都走这里
 * Created by Administrator on 2018/5/18 0018.
 */

public class NovelPageBitmapManager {

    private Context mContext;

    private Bitmap mCurrentPageBitmap;
    private Bitmap mNextPageBitmap;

    private int mViewWidth;
    private int mViewHeight;

    private int mContentTextSize;
    private int mContentTextColor;

    private Paint mContentPaint;
    private Paint mTitlePaint;
    // 绘制提示的画笔
    private Paint mTipPaint;
    // 绘制背景颜色的画笔(用来擦除需要重绘的部分)
    private Paint mBgPaint;

    private NovelContentManager mContentManager;
    private NovelPageStateObserver mStateObserver;

    public int mCurrentPagePos = 0;
    private int mTempPagePos = 0;

    private int mCurrentState = 0;//-1没有上一页，1没有下一页，0正常

    private static NovelPageBitmapManager mInstance;

    public static synchronized NovelPageBitmapManager instance(Context mContext) {

        if (mInstance == null) {
            synchronized (NovelPageBitmapManager.class) {
                if (mInstance == null) {
                    mInstance = new NovelPageBitmapManager(mContext);
                }
            }

        }

        return mInstance;
    }

    private NovelPageBitmapManager(Context mContext) {

        this.mContext = mContext;

        mContentManager = NovelContentManager.instance(mContext);
        mStateObserver = NovelPageStateObserver.getInstance();

        initColor(mContext);
        initPaint();
    }

    public void setPageSize(int w, int h) {
        mViewWidth = w;
        mViewHeight = h;

        if (mViewWidth != 0 && mViewHeight != 0) {
            mCurrentPageBitmap = Bitmap.createBitmap(mViewWidth, mViewHeight, Bitmap.Config.RGB_565);
            drawBg(mCurrentPageBitmap);
        }

        if (mViewWidth != 0 && mViewHeight != 0) {
            mNextPageBitmap = Bitmap.createBitmap(mViewWidth, mViewHeight, Bitmap.Config.RGB_565);
            drawBg(mNextPageBitmap);
        }

        mContentManager.setPageSize(w, h);
    }

    private void initColor(Context mContext) {

        mContentTextColor = ContextCompat.getColor(mContext, R.color.chapter_title_night);

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        // 绘制提示的画笔
        mTipPaint = new Paint();
//        mTipPaint.setColor(mTitleTextColor);
        mTipPaint.setTextAlign(Paint.Align.LEFT); // 绘制的起始点
        mTipPaint.setTextSize(UiUtils.sp2px(12)); // Tip默认的字体大小
        mTipPaint.setAntiAlias(true);
        mTipPaint.setSubpixelText(true);

        // 绘制页面内容的画笔
        mContentPaint = new TextPaint();
        mContentPaint.setTextSize(UiUtils.sp2px(15));
        mContentPaint.setColor(mContentTextColor);
        mContentPaint.setAntiAlias(true);

        // 绘制标题的画笔
        mTitlePaint = new TextPaint();
//        mTitlePaint.setColor(mTitleTextColor);
//        mTitlePaint.setTextSize(mTitleTextSize);
        mTitlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTitlePaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTitlePaint.setAntiAlias(true);

        // 绘制背景的画笔
        mBgPaint = new Paint();
        mBgPaint.setColor(Color.WHITE);

//        // 绘制电池的画笔
//        mBatteryPaint = new Paint();
//        mBatteryPaint.setAntiAlias(true);
//        mBatteryPaint.setDither(true);

        mContentManager.setContentPaint(mContentPaint);
    }

    public void onTouch(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (event.getX() > mViewWidth / 2) {
                    drawNext();
                } else if (event.getX() < mViewWidth / 2) {
                    drawPre();
                }


                break;
            case MotionEvent.ACTION_MOVE:


                break;
            case MotionEvent.ACTION_UP:


                break;
            case MotionEvent.ACTION_CANCEL:


                break;
        }


    }

    /**
     * 创建当前页
     *
     * @return
     */
    public Bitmap getCurrentPageBitmap() {

        return mCurrentPageBitmap;
    }

    private void drawBg(Bitmap mTargetBitmap) {

        Canvas mCanvas = new Canvas(mTargetBitmap);
        mCanvas.drawColor(Color.WHITE);

    }

    private int drawContent(Bitmap mTargetBitmap, int pageNum) {

        Canvas mCanvas = new Canvas(mTargetBitmap);

        List<NovelPageEntity> mCurChapterList = mContentManager.getCurChapterList();

        if (mCurChapterList == null || mCurChapterList.size() == 0 ) {
            return -1;
        }

        if(pageNum > mCurChapterList.size()-1){
            mContentManager.skipNextChapter();
            return 1;
        }else if(pageNum<0){
            mContentManager.skipPreChapter();
            return 1;
        }

        NovelPageEntity novelPageEntity = mCurChapterList.get(pageNum);

        List<String> lines = novelPageEntity.getLines();
        int curTextHeight = 0;

        for (String line : lines) {

            mCanvas.drawText(line, UiUtils.dp2px(12), UiUtils.dp2px(12) + curTextHeight, mContentPaint);
            curTextHeight += mContentPaint.getTextSize();
            if (line.endsWith("\n")) {
                curTextHeight += UiUtils.dp2px(3);
            }
        }

        return 0;
    }

    public void drawCurrent() {

        if (mNextPageBitmap == null || mCurrentPageBitmap == null) {
            return;
        }

        drawBg(mNextPageBitmap);
//        drawBg(mNextPageBitmap);
        drawContent(mNextPageBitmap, mCurrentPagePos);
//        drawContent(mNextPageBitmap);

//        mNextPageBitmap = mCurrentPageBitmap;
    }

    public void drawNext() {

        changePage();
        drawBg(mNextPageBitmap);
        int result = drawContent(mNextPageBitmap, mCurrentPagePos+1);

        switch (result){
            case 1:
                resetPos();
                break;
            case 0:
                mCurrentPagePos++;
                mStateObserver.setNovelPageState(0);
                break;
            case -1:
                mStateObserver.setNovelPageState(1);
                break;
        }

    }

    /**
     * 重置指针
     */
    private void resetPos() {
        mCurrentPagePos=0;
    }


    public void drawPre() {

        changePage();
        drawBg(mNextPageBitmap);

        int result = drawContent(mNextPageBitmap, mCurrentPagePos-1);

        switch (result){
            case 1:
                resetPos();
                break;
            case 0:
                mCurrentPagePos--;
                mStateObserver.setNovelPageState(0);
                break;
            case -1:
                mStateObserver.setNovelPageState(-1);
                break;
        }
    }

    private void changePage() {
        /**
         * 两个bitmap轮流使用，节省一下资源，另外省的recycle的时候漏了
         */
        Bitmap temp = mCurrentPageBitmap;
        mCurrentPageBitmap = mNextPageBitmap;
        mNextPageBitmap = temp;
    }

    /**
     * 创建下一页
     *
     * @return
     */
    public HashMap<String, Object> getNextPageBitmap() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("result", mCurrentState);
        result.put("bitmap", mNextPageBitmap);

        return result;
    }

    public void onDestroy() {

        mCurrentPageBitmap.recycle();
        mNextPageBitmap.recycle();
        mCurrentPageBitmap = null;
        mNextPageBitmap = null;

        if(mInstance!=null) {
            mInstance = null;
        }
    }

}
