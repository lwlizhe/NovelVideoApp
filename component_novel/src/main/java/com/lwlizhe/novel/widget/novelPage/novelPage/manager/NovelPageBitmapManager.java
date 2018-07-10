package com.lwlizhe.novel.widget.novelPage.novelPage.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.widget.Toast;

import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.library.novel.R;
import com.lwlizhe.novel.widget.novelPage.novelPage.entity.NovelPageEntity;
import com.lwlizhe.novel.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novel.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 提供绘制完成的currentBitmap和nextBitmap，所有bitmap的操作都走这里,也是绘制背景、文字的地方
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

    private int mBgColr;

    private int mContentPadding;// 展示的内边距
    private int mParagraphMargin;// 段落边距

    private int mPageModuleMargin;// 各个模块之间距离

    private int mPageFooterTextSize;// 页脚内容文字大小

    private int mTitleTextSize;
    private int mTitleTextColor;
    private int mFooterTextColor;

    private RectF mCenterRect = null;

    private NovelContentManager mContentManager;
    private NovelMenuManager mMenuManager;

    private NovelPageStateObserver mStateObserver;

    private static NovelPageBitmapManager mInstance;

    private boolean isLoading = false;
    private boolean isHasNext = false;
    private boolean isHasPre = false;

    public static NovelPageBitmapManager instance(Context mContext) {

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
        mMenuManager = NovelMenuManager.instance(mContext);

        mStateObserver = NovelPageStateObserver.getInstance();

        mStateObserver.addListener(new NovelPageStateListener() {
            @Override
            public void onNextDisable() {
                isHasNext = false;
            }

            @Override
            public void onRequestNewChapter(long requestVolumeId, long requestChapterId) {

            }

            @Override
            public void onPreDisable() {
                isHasPre = false;
            }

            @Override
            public void onLoading() {
                isLoading = true;
            }

            @Override
            public void onLoadingFinish() {
                isLoading = false;

                isHasNext = true;
                isHasPre = true;


            }

            @Override
            public void onNormal() {
                isHasNext = true;
                isHasPre = true;
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
        mFooterTextColor = ContextCompat.getColor(mContext, R.color.chapter_content_night);

        mBgColr= Color.WHITE;
    }

    private void initTextSize() {
        mContentTextSize = UiUtils.sp2px(16);
        mTitleTextSize = UiUtils.sp2px(13);
        mContentPadding = UiUtils.dp2px(12);
        mParagraphMargin = UiUtils.dp2px(6);
        mPageFooterTextSize = UiUtils.sp2px(13);
        mPageModuleMargin = UiUtils.dp2px(5);
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
        mFooterPaint = new TextPaint();
        mFooterPaint.setColor(mFooterTextColor);
        mFooterPaint.setTextSize(mPageFooterTextSize);
        mFooterPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mFooterPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mFooterPaint.setAntiAlias(true);

//        // 绘制电池的画笔
//        mBatteryPaint = new Paint();
//        mBatteryPaint.setAntiAlias(true);
//        mBatteryPaint.setDither(true);

        mContentManager.setContentPaint(mContentPaint);

        mContentManager.setContentTextSize(mContentTextSize);
        mContentManager.setTitleTextSize(mTitleTextSize);
        mContentManager.setFooterTextSize(mPageFooterTextSize);
        mContentManager.setContentPadding(mContentPadding);
        mContentManager.setParagraphMargin(mParagraphMargin);
        mContentManager.setPageModuleMargin(mPageModuleMargin);

    }

    public void setTouchRect(RectF mCenterRect) {
        this.mCenterRect = mCenterRect;
    }

    public void setBgColor(int bgColor){
        mBgColr=bgColor;
    }
    /**
     * 修改文字大小
     * @param size 文字大小，单位sp
     */
    public void setTextSize(int size){
//        double ratio = (double) size/(double) mContentTextSize;

        mContentTextSize=size;
//        mTitleTextSize= (int) (mTitleTextSize*ratio);
//        mPageFooterTextSize= (int) (mPageFooterTextSize*ratio);
//
//        mPageModuleMargin= (int) (mPageModuleMargin*ratio);
//        mParagraphMargin= (int) (mParagraphMargin*ratio);

        mContentManager.setContentTextSize(mContentTextSize);
//        mContentManager.setParagraphMargin(mParagraphMargin);

        mContentManager.refreshCurrent();

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
        mCanvas.drawColor(mBgColr);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");// HH:mm:ss
        String currentTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        mCanvas.drawText(currentTime, mViewWidth - mFooterPaint.measureText(currentTime) - mContentPadding, mViewHeight - mContentPadding, mFooterPaint);

    }

    /**
     * 绘制内容页
     *
     * @param mTargetBitmap   目标bitmap
     * @param novelPageEntity 绘制页的内容
     * @return 绘制结果：1表示跳转到下一页，-1表示操作失败，0成功
     */
    private void drawContent(Bitmap mTargetBitmap, NovelPageEntity novelPageEntity) {

        Canvas mCanvas = new Canvas(mTargetBitmap);

        if (novelPageEntity == null) {
            return;
        }

        // 绘制标题
        mCanvas.drawText(novelPageEntity.getTitleName(), mContentPadding, mTitlePaint.getTextSize() + mContentPadding, mTitlePaint);

        // 如果没有文字内容，请求新章节
        if (novelPageEntity.getLines() == null || novelPageEntity.getLines().size() == 0) {

            mContentManager.requestNewChapter(novelPageEntity.getVolumeId(), novelPageEntity.getChapterId());

            return;
        }

        List<String> lines = novelPageEntity.getLines();
        int curTextHeight = 0;

        // 一行行的画文字
        for (String line : lines) {

            mCanvas.drawText(line, mContentPadding, mContentPaint.getTextSize() + mParagraphMargin + (mTitleTextSize + mContentPadding + mPageModuleMargin) + curTextHeight, mContentPaint);
            curTextHeight += mContentPaint.getTextSize();

            curTextHeight += mParagraphMargin;

        }
        mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);

        drawPageNum(mCanvas, novelPageEntity);
    }

    /**
     * 绘制页脚
     */
    private void drawPageNum(Canvas mCanvas, NovelPageEntity entity) {

        mCanvas.drawText(entity.getCurrentPagePos() + 1 + "/" + entity.getMaxPageCount(), mContentPadding, mViewHeight - mContentPadding, mFooterPaint);
    }

    /**
     * 绘制当前的静态页面的bitmap
     */
    public void drawCurrent() {

        if (mNextPageBitmap == null || mCurrentPageBitmap == null || mContentManager == null) {
            return;
        }
        NovelPageEntity currentPage = mContentManager.getCurrentPage();

        drawBg(mNextPageBitmap);

        drawContent(mNextPageBitmap, currentPage);

        if (!isLoading) {
            mMenuManager.notifyPageChanged(currentPage);
        }


    }

    /**
     * 绘制下一页的bitmap
     */
    public void drawNext() {

        NovelPageEntity nextPage = mContentManager.getNextPage();

        if (nextPage == null) {
            Toast.makeText(mContext, "没有下一页了", Toast.LENGTH_SHORT).show();
            return;
        }

        changePage();
        drawBg(mNextPageBitmap);
        drawContent(mNextPageBitmap, nextPage);

        if (!isLoading) {

            mMenuManager.notifyPageChanged(nextPage);
        }
    }

    /**
     * 绘制上一页的bitmap
     */
    public void drawPre() {

        NovelPageEntity prePage = mContentManager.getPrePage();

        if (prePage == null) {
            Toast.makeText(mContext, "没有上一页了", Toast.LENGTH_SHORT).show();
            return;
        }

        changePage();
        drawBg(mNextPageBitmap);

        drawContent(mNextPageBitmap, prePage);

        if (!isLoading) {
            mMenuManager.notifyPageChanged(prePage);
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
