package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Html;
import android.view.MotionEvent;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager.NovelContentManager;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager.NovelPageBitmapManager;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.pageAnimationBitmapLoader.BaseAnimationBitmapLoader;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.pageAnimationBitmapLoader.PageAnimationBitmapLoaderFactory;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.Html.FROM_HTML_MODE_COMPACT;
import static com.lwlizhe.basemodule.utils.StringUtils.getTwoSpaces;

/**
 * 这部分负责的是整体页面逻辑，所有的数据都在这里进行交互处理
 * Created by Administrator on 2018/5/15 0015.
 */

public abstract class BaseNovelLoader implements IPageLoader {

    protected Context mContext;

    protected int mPageHeight, mPageWidth;

    protected NovelPage mTargetPageView;

    // 分页相关
//    private String mContent;

    // 目录
    protected NovelContentCatalogueEntity mCatalogueEntity;

    protected BaseAnimationBitmapLoader mPageAnimationBitmapLoader;

    protected NovelPageBitmapManager mBitmapManager;
    protected NovelContentManager mContentManager;
    protected NovelPageStateObserver mStateObserver;


    protected PageAnimationBitmapLoaderFactory mAnimationFactory;

    public BaseNovelLoader(NovelPage targetPageView) {
        this.mTargetPageView = targetPageView;
        mContext = targetPageView.getContext();

        mBitmapManager = NovelPageBitmapManager.instance(mContext);
        mContentManager = NovelContentManager.instance(mContext);
        mStateObserver= NovelPageStateObserver.getInstance();

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
                if(mTargetPageView!=null){
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
    public void loadNovel(long bookId,long volumeId,long chapterId,String content) {

//        Pattern p_space = Pattern.compile("&nbsp;|<br/>|<br />", Pattern.CASE_INSENSITIVE);
//        Matcher m_space = p_space.matcher(content);
//        content = m_space.replaceAll("\u3000");
//
//        Pattern p_enter1 = Pattern.compile("\\r\\n\\r\\n", Pattern.CASE_INSENSITIVE);
//        Matcher m_enter1 = p_enter1.matcher(content);
//        content = m_enter1.replaceAll("\r\n\r\n" + getTwoSpaces());
//
//        Pattern p_enter2 = Pattern.compile("\\n", Pattern.CASE_INSENSITIVE);
//        Matcher m_enter2 = p_enter2.matcher(content);
//        content = m_enter2.replaceAll("\r\n\r\n" + getTwoSpaces());

        content= Html.fromHtml(content).toString();

        mContentManager.setContent(bookId, volumeId, chapterId, content);

//        mBitmapManager.drawCurrent();
//
//        mTargetPageView.postInvalidate();
    }


    @Override
    public void loadChapter(NovelContentCatalogueEntity catalogueEntity) {
        mCatalogueEntity = catalogueEntity;
        mContentManager.setCatalogue(catalogueEntity);
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

        mBitmapManager.onTouch(event);
        mPageAnimationBitmapLoader.onTouch(event);

    }

    @Override
    public void computeScroll() {
        mPageAnimationBitmapLoader.computeScroll();
    }

    @Override
    public void onDetachedFromWindow() {
        mContentManager.onDestroy();
        mBitmapManager.onDestroy();
        mStateObserver.onDestroy();
    }
}
