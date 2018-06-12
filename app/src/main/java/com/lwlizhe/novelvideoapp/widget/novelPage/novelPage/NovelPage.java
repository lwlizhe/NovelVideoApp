package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader.IPageLoader;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader.PageLoaderFactory;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

/**
 * Created by Administrator on 2018/5/10 0010.
 * TODO：1、缓存（数据库）；2、页面跳转和章节跳转；3、优化
 */

public class NovelPage extends View {

    private Paint mTextPaint;

    private Context mContext;

    private IPageLoader mPageLoader;

    private OnPageStateChangedListener mListener;
    protected NovelPageStateObserver mStateObserver;

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

    /**
     * 初始化
     */
    private void init(Context mContext) {

        this.mContext = mContext;

        mStateObserver= NovelPageStateObserver.getInstance();
        mStateObserver.addListener(new NovelPageStateListener() {
            @Override
            public void onNextDisable() {

            }

            @Override
            public void onRequestNewChapter(long requestVolumeId, long requestChapterId) {
                if(mListener!=null){
                    mListener.onRequestNewChapter(requestVolumeId, requestChapterId);
                }
            }

            @Override
            public void onPreDisable() {

            }

            @Override
            public void onNormal() {

            }
        });

        initPageLoader();

        mTextPaint = new Paint();
        mTextPaint.setColor(ContextCompat.getColor(mContext, R.color.white));
        mTextPaint.setTextSize(UiUtils.sp2px(15));
        mTextPaint.setAntiAlias(true);

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
    public void setContent(long bookId, long volumeId,long chapterId, String content) {

        mPageLoader.loadNovel(bookId,volumeId,chapterId,content);

    }

    public void setChapterData(NovelContentCatalogueEntity catalogueEntity){

        mPageLoader.loadChapter(catalogueEntity);

    }

    public void setPageStateListener(OnPageStateChangedListener listener){

        this.mListener=listener;

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
        super.onDetachedFromWindow();

    }

    public interface OnPageStateChangedListener{

        void onRequestNewChapter(long requestVolumeId,long requestChapterId);

        void onOpenChapterList(long currentVolumeId,long currentChapterId);

    }

}
