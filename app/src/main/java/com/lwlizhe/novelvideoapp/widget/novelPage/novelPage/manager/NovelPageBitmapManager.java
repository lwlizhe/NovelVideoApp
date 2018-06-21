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
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

import java.util.HashMap;
import java.util.List;

import static com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver.STATE_NO_PRE;

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



    private Paint mContentPaint;
    private Paint mTitlePaint;
    // 绘制提示的画笔
    private Paint mTipPaint;
    // 绘制背景颜色的画笔(用来擦除需要重绘的部分)
    private Paint mBgPaint;

    //-----------------------------------配置相关-------------------------------------------
    private int mContentTextSize;
    private int mContentTextColor;

    private int mTitleTextSize;
    private int mTitleTextColor;

    private NovelContentManager mContentManager;
    private NovelPageStateObserver mStateObserver;

    public int mCurrentPagePos = 0;
    private int mMaxPageCount = 0;

    private boolean isOpenNewChapter = false;
    private boolean isCurrentOperateNext = false;

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

            }

            @Override
            public void onLoadingFinish() {

                drawCurrent();
            }

            @Override
            public void onNormal() {

            }
        });

        initColor(mContext);
        initTextSize();
        initPaint();
    }



    /**
     * 重置页面大小
     *
     * @param w 页面宽
     * @param h 页面高
     */
    public void setPageSize(int w, int h) {
        mViewWidth = w;
        mViewHeight = h;

        // 创建currentPageBitmap
        if (mViewWidth != 0 && mViewHeight != 0) {
            mCurrentPageBitmap = Bitmap.createBitmap(mViewWidth, mViewHeight, Bitmap.Config.RGB_565);
            drawBg(mCurrentPageBitmap);
        }

        // 创建nextPageBitmap
        if (mViewWidth != 0 && mViewHeight != 0) {
            mNextPageBitmap = Bitmap.createBitmap(mViewWidth, mViewHeight, Bitmap.Config.RGB_565);
            drawBg(mNextPageBitmap);
        }

        mContentManager.setPageSize(w, h);
    }

    /**
     * 绘制颜色
     *
     * @param mContext
     */
    private void initColor(Context mContext) {

        mContentTextColor = ContextCompat.getColor(mContext, R.color.chapter_title_night);
        mTitleTextColor = ContextCompat.getColor(mContext, R.color.chapter_title_night);

    }

    private void initTextSize() {
        mContentTextSize=UiUtils.sp2px(15);
        mTitleTextSize=UiUtils.sp2px(15);

        mContentManager.setContentTextSize(mContentTextSize);
        mContentManager.setTitleTextSize(mTitleTextSize);
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
        mContentPaint.setTextSize(mContentTextSize);
        mContentPaint.setColor(mContentTextColor);
        mContentPaint.setAntiAlias(true);

        // 绘制标题的画笔
        mTitlePaint = new TextPaint();
        mTitlePaint.setColor(mTitleTextColor);
        mTitlePaint.setTextSize(mTitleTextSize);
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

    /**
     * bitmap的点击事件，用来通知处理bitmap的相关部分（绘制上一页、下一页、交换位置并重置等）
     *
     * @param event 点击事件
     */
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
     * @return 当前展示的页面
     */
    public Bitmap getCurrentPageBitmap() {

        return mCurrentPageBitmap;
    }

    /**
     * 填充背景，默认白色
     *
     * @param mTargetBitmap 目标bitmap
     */
    private void drawBg(Bitmap mTargetBitmap) {

        Canvas mCanvas = new Canvas(mTargetBitmap);
        mCanvas.drawColor(Color.WHITE);

    }

    /**
     * 绘制内容页
     *
     * @param mTargetBitmap 目标bitmap
     * @param pageNum       绘制的章节页码
     * @param isCurrent     是否是绘制当前页
     * @return 绘制结果：1表示跳转到下一页，-1表示操作失败，0成功
     */
    private int drawContent(Bitmap mTargetBitmap, int pageNum, boolean isCurrent) {

        Canvas mCanvas = new Canvas(mTargetBitmap);

        List<NovelPageEntity> mCurChapterList = mContentManager.getCurChapterList();
        // 判断是否还有上一页或者下一页
        if (mCurChapterList == null || mCurChapterList.size() == 0) {
            return -1;
        }
        mMaxPageCount = mCurChapterList.size();

        // 是不是绘制当前页，如果不是，那再判断是不是跳转章节的操作
        if (!isCurrent) {
            if (pageNum > mMaxPageCount - 1) {
                isOpenNewChapter = true;
                mContentManager.skipNextChapter();
                return 1;
            } else if (pageNum < 0) {
                isOpenNewChapter = true;
                mContentManager.skipPreChapter();
                return 1;
            }
        }

        NovelPageEntity novelPageEntity;
        //如果是跳转到不同章节，那么重置页面的位置指针
        if (isOpenNewChapter && !isCurrentOperateNext) {
            novelPageEntity = mCurChapterList.get(mMaxPageCount - 1);
            mCurrentPagePos = mMaxPageCount - 1;
        } else {
            novelPageEntity = mCurChapterList.get(pageNum);
        }

        List<String> lines = novelPageEntity.getLines();
        int curTextHeight = 0;

        // 一行行的画文字(暂定规则：一段后加3dp)
        for (String line : lines) {

            mCanvas.drawText(line, mContentPaint.getTextSize(), mContentPaint.getTextSize()+UiUtils.dp2px(10) + curTextHeight, mContentPaint);
            curTextHeight += mContentPaint.getTextSize();
            if (line.length()<22) {
                curTextHeight += UiUtils.dp2px(3);
            }
        }
        isOpenNewChapter = false;
        return 0;
    }

    /**
     * 绘制当前的静态页面的bitmap
     */
    public void drawCurrent() {

        if (mNextPageBitmap == null || mCurrentPageBitmap == null) {
            return;
        }

        drawBg(mNextPageBitmap);

        drawContent(mNextPageBitmap, mCurrentPagePos, true);

    }

    /**
     * 绘制下一页的bitmap
     */
    public void drawNext() {
        isCurrentOperateNext = true;

        changePage();
        drawBg(mNextPageBitmap);
        int result = drawContent(mNextPageBitmap, mCurrentPagePos + 1, false);

        switch (result) {
            case 1:
                mCurrentPagePos = 0;

                break;
            case 0:
                isOpenNewChapter = false;
                mCurrentPagePos++;
                mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);
                break;
            case -1:
                mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NO_NEXT);
                break;
        }

    }

    /**
     * 绘制上一页的bitmap
     */
    public void drawPre() {
        isCurrentOperateNext = false;

        changePage();
        drawBg(mNextPageBitmap);

        int result = drawContent(mNextPageBitmap, mCurrentPagePos - 1, false);

        switch (result) {
            case 1:
                mCurrentPagePos = 0;

                break;
            case 0:
                isOpenNewChapter = false;
                mCurrentPagePos--;
                mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);
                break;
            case -1:
                mStateObserver.setNovelPageState(STATE_NO_PRE);
                break;
        }
    }

    /**
     * 交换bitmap
     */
    private void changePage() {

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

        result.put("result", 0);
        result.put("bitmap", mNextPageBitmap);

        return result;
    }

    /**
     * 回收
     */
    public void onDestroy() {

        mCurrentPageBitmap.recycle();
        mNextPageBitmap.recycle();
        mCurrentPageBitmap = null;
        mNextPageBitmap = null;

        if (mInstance != null) {
            mInstance = null;
        }
    }

}
