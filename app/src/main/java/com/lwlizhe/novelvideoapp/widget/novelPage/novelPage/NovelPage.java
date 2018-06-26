package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.NovelControlViewStateChangedListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader.IPageLoader;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader.PageLoaderFactory;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class NovelPage extends View {

//    private Paint mTextPaint;

    private Context mContext;

    private IPageLoader mPageLoader;

    private OnPageStateChangedListener mListener;
    private OnTipStateChangedListener mTipListener;

    protected NovelPageStateObserver mStateObserver;

    // 接收电池信息和时间更新的广播
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                int level = intent.getIntExtra("level", 0);
                mPageLoader.updateBattery(level);
            }
            // 监听分钟的变化
            else if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
                mPageLoader.updateTime();
            }
        }
    };

    public NovelPage(Context context) {
        super(context);
        init(context);
    }

    public NovelPage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NovelPage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private NovelPageStateListener mStateListener = new NovelPageStateListener() {
        @Override
        public void onNextDisable() {

        }

        @Override
        public void onRequestNewChapter(long requestVolumeId, long requestChapterId) {
            if (mListener != null) {
                mListener.onRequestNewChapter(requestVolumeId, requestChapterId);
            }
        }

        @Override
        public void onPreDisable() {

        }

        @Override
        public void onLoading() {
            if(mTipListener!=null){
                mTipListener.onLoading();
            }
        }

        @Override
        public void onLoadingFinish() {
            if(mTipListener!=null){
                mTipListener.onLoadingFinish();
            }
        }

        @Override
        public void onNormal() {

        }
    };

    /**
     * 初始化
     */
    private void init(Context mContext) {

        this.mContext = mContext;

        mStateObserver = NovelPageStateObserver.getInstance();
        mStateObserver.addListener(mStateListener);

        initPageLoader();

        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        mContext.registerReceiver(mReceiver, intentFilter);

    }

    private void initPageLoader() {

        mPageLoader = (new PageLoaderFactory(this, 0)).getNovelLoader();

    }

    /**
     * 设置当前展示页面的章节内容
     *
     * @param bookId    书籍id
     * @param chapterId 当前章节id
     * @param content   章节内容
     */
    public void setContent(long bookId, long volumeId, long chapterId, String content) {

        mPageLoader.loadNovel(bookId, volumeId, chapterId, content);

    }

    /**
     * 设置目录
     *
     * @param catalogueEntity 目录
     */
    public void setCatalogue(NovelCatalogueEntity catalogueEntity) {

        mPageLoader.setCatalogue(catalogueEntity);

    }

    public void loadLastRead(long bookId) {

        mPageLoader.loadLastRead(bookId);
    }

    public void skipToTargetChapter(long novelId, long volumeId, long chapterId) {

        mPageLoader.skipToTargetChapter(novelId, volumeId, chapterId);
    }
    public void skipToNextChapter() {

        mPageLoader.skipToNextChapter();
    }
    public void skipToPreChapter() {

        mPageLoader.skipToPreChapter();
    }

    public void skipToTargetPagePos(int pos){
        mPageLoader.skipToTargetPagePos(pos);
    }

    public void setPageStateListener(OnPageStateChangedListener listener) {

        this.mListener = listener;

    }

    public void setTipsStateListener(OnTipStateChangedListener tipListener) {
        mTipListener = tipListener;
    }

    public void setControlViewStateListener(NovelControlViewStateChangedListener listener) {
        if (listener != null) {
            mPageLoader.setControlViewStateListener(listener);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mPageLoader.onTouch(event);
        return true;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPageLoader.onPageSizeChanged(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPageLoader.onDraw(canvas);
    }

    @Override
    public void computeScroll() {
        mPageLoader.computeScroll();
        super.computeScroll();

    }

    @Override
    protected void onDetachedFromWindow() {
        mPageLoader.onDetachedFromWindow();
        mContext.unregisterReceiver(mReceiver);
        super.onDetachedFromWindow();

    }

    public interface OnPageStateChangedListener {

        void onRequestNewChapter(long requestVolumeId, long requestChapterId);

    }

    public interface OnTipStateChangedListener {
        void onLoading();

        void onLoadingFinish();

        void onLoadingError(int reason);
    }

}
