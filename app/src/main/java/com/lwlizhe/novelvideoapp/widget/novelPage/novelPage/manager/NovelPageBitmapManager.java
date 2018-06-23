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
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    // 绘制内容的画笔
    private Paint mContentPaint;
    // 绘制标题的画笔
    private Paint mTitlePaint;
    // 绘制提示的画笔
    private Paint mTipPaint;
    // 绘制背景颜色的画笔(用来擦除需要重绘的部分)
    private Paint mBgPaint;
    // 绘制页脚的画笔
    private Paint mFooterPaint;

    //-----------------------------------配置相关-------------------------------------------
    private int mContentTextSize;
    private int mContentTextColor;

    private int mContentPadding;
    private int mParagraphMargin;

    private int mPageFooterHeight;

    private int mTitleTextSize;
    private int mTitleTextColor;

    private NovelContentManager mContentManager;
    private NovelPageStateObserver mStateObserver;

    private static NovelPageBitmapManager mInstance;

    private boolean isLoading = false;

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
                isLoading = true;
            }

            @Override
            public void onLoadingFinish() {
                isLoading = false;
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
//            drawBg(mCurrentPageBitmap);
        }

        // 创建nextPageBitmap
        if (mViewWidth != 0 && mViewHeight != 0) {
            mNextPageBitmap = Bitmap.createBitmap(mViewWidth, mViewHeight, Bitmap.Config.RGB_565);
//            drawBg(mNextPageBitmap);
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
        mContentTextSize = UiUtils.sp2px(15);
        mTitleTextSize = UiUtils.sp2px(12);
        mContentPadding = UiUtils.dp2px(3);
        mParagraphMargin = UiUtils.dp2px(3);
        mPageFooterHeight= UiUtils.dp2px(12);
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

        // 页脚
        mFooterPaint=new TextPaint();
        mFooterPaint.setColor(mTitleTextColor);
        mFooterPaint.setTextSize(mPageFooterHeight);
        mFooterPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mFooterPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mFooterPaint.setAntiAlias(true);

//        // 绘制电池的画笔
//        mBatteryPaint = new Paint();
//        mBatteryPaint.setAntiAlias(true);
//        mBatteryPaint.setDither(true);

        mContentManager.setContentPaint(mContentPaint);

        mContentManager.setContentTextSize(mContentTextSize);
        mContentManager.setTitleTextSize(mTitleTextSize);
        mContentManager.setContentPadding(mContentPadding);
        mContentManager.setParagraphMargin(mParagraphMargin);
        mContentManager.setPageFooterHeight(mPageFooterHeight);

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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");// HH:mm:ss
        String currentTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        mCanvas.drawText(currentTime,mViewWidth-mFooterPaint.measureText(currentTime)-mContentPadding,mViewHeight-mContentPadding,mFooterPaint);

    }

    /**
     * 绘制内容页
     *
     * @param mTargetBitmap 目标bitmap
     * @param isCurrent     是否是绘制当前页
     * @return 绘制结果：1表示跳转到下一页，-1表示操作失败，0成功
     */
    private void drawContent(Bitmap mTargetBitmap, boolean isNext, boolean isCurrent) {

        Canvas mCanvas = new Canvas(mTargetBitmap);

        NovelPageEntity novelPageEntity;

        if (isCurrent) {
            novelPageEntity = mContentManager.getCurrentPage();
        } else {
            if (isNext) {
                novelPageEntity = mContentManager.getNextPage();
            } else {
                novelPageEntity = mContentManager.getPrePage();
            }
        }

        // 如果正在加载
        if (isLoading || novelPageEntity == null) {
            mCanvas.drawText("正在加载中", mContentPaint.getTextSize(), mContentPaint.getTextSize() + mContentPadding, mContentPaint);
            if(!isCurrent){
                mStateObserver.setNovelPageState(isNext?NovelPageStateObserver.STATE_NO_NEXT:NovelPageStateObserver.STATE_NO_PRE);
            }

            return;
        }

        // 绘制标题
        mCanvas.drawText(novelPageEntity.getTitleName(), mContentPadding, mTitlePaint.getTextSize() + mParagraphMargin + mContentPadding, mTitlePaint);

        // 如果没有内容
        if (novelPageEntity.getLines() == null || novelPageEntity.getLines().size() == 0) {

            mCanvas.drawText("正在加载中", mContentPaint.getTextSize(), mContentPaint.getTextSize() + mContentPadding, mContentPaint);
            if(!isCurrent){
                mStateObserver.setNovelPageState(isNext?NovelPageStateObserver.STATE_NO_NEXT:NovelPageStateObserver.STATE_NO_PRE);
            }

            return;
        }

        List<String> lines = novelPageEntity.getLines();
        int curTextHeight = 0;

        // 一行行的画文字
        for (String line : lines) {

            mCanvas.drawText(line, mContentPadding, mContentPaint.getTextSize() + mTitleTextSize + 2 * mParagraphMargin + mContentPadding + curTextHeight, mContentPaint);
            curTextHeight += mContentPaint.getTextSize();

            curTextHeight += mParagraphMargin;

        }
        mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);

        drawPageNum(mCanvas,novelPageEntity);
    }

    /**
     * 绘制页脚
     */
    private void drawPageNum(Canvas mCanvas,NovelPageEntity entity) {

        mCanvas.drawText(entity.getCurrentPagePos()+1+"/"+entity.getMaxPageCount(),mContentPadding,mViewHeight-mContentPadding,mFooterPaint);
    }

    /**
     * 绘制当前的静态页面的bitmap
     */
    public void drawCurrent() {

        if (mNextPageBitmap == null || mCurrentPageBitmap == null) {
            return;
        }

        drawBg(mNextPageBitmap);

        drawContent(mNextPageBitmap, false, true);

    }

    /**
     * 绘制下一页的bitmap
     */
    public void drawNext() {

        changePage();
        drawBg(mNextPageBitmap);
        drawContent(mNextPageBitmap, true, false);

    }

    /**
     * 绘制上一页的bitmap
     */
    public void drawPre() {

        changePage();
        drawBg(mNextPageBitmap);

        drawContent(mNextPageBitmap, false, false);

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
