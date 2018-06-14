package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;

import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader.entity.NovelPageEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver.STATE_LOADING;
import static com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver.STATE_LOADING_FINISH;
import static com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver.STATE_NO_PRE;
import static com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver.STATE_REQUEST_CHAPTER;

/**
 * 小说文字处理，负责读取、存储、分割计算
 * Created by Administrator on 2018/5/21 0021.
 */

public class NovelContentManager {

    private Paint mContentPaint;

    private NovelContentCatalogueEntity mCatalogue;

//    private String[] paragraphs;

    protected List<NovelPageEntity> mPreChapterList = new ArrayList<>();
    protected List<NovelPageEntity> mCurChapterList = new ArrayList<>();
    protected List<NovelPageEntity> mNextChapterList = new ArrayList<>();

    private int mPageWidth;
    private int mPageHeight;

    private static NovelContentManager mInstance;
    protected NovelPageStateObserver mStateObserver;

    private Context mContext;

    private Disposable mPreLoadDisp;

    private String mContent;

    private int mCurrentVolumePos = -1;
    private int mCurrentChapterPos = -1;

    private long currentRequestVolumeId;
    private long currentRequestChapterId;

    public static synchronized NovelContentManager instance(Context mContext) {

        if (mInstance == null) {
            synchronized (NovelContentManager.class) {
                if (mInstance == null) {
                    mInstance = new NovelContentManager(mContext);
                }
            }

        }

        return mInstance;
    }

    private NovelContentManager(Context context) {

        this.mContext = context;
        mStateObserver = NovelPageStateObserver.getInstance();

    }

    public void setPageSize(int w, int h) {
        mPageWidth = w;
        mPageHeight = h;

        transformCurrentContent(mCurChapterList, mContent);
    }

    public void setContentPaint(Paint mTextContentPaint) {
        mContentPaint = mTextContentPaint;
    }

    public void skipNextChapter() {

        if (mNextChapterList != null && mNextChapterList.size() != 0) {

            mPreChapterList.clear();
            mPreChapterList.addAll(mCurChapterList);

            mCurChapterList.clear();
            mCurChapterList.addAll(mNextChapterList);

            mNextChapterList.clear();

            mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);
        } else {
            List<NovelContentCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();

            int chapterSize = volumeList.get(mCurrentVolumePos).getChapterList().size();

            if (mCurrentVolumePos >= volumeList.size() - 1 && mCurrentChapterPos >= chapterSize - 1) {
                mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NO_NEXT);
            } else {
                mCurChapterList.clear();

                if (mCurrentChapterPos >= chapterSize - 1) {
                    mCurrentChapterPos = 0;
                    mCurrentVolumePos += 1;

                } else {
                    mCurrentChapterPos += 1;

                }

                currentRequestChapterId = volumeList.get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();
                currentRequestVolumeId = volumeList.get(mCurrentVolumePos).getVolumeId();

                mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, currentRequestVolumeId, currentRequestChapterId);

            }
        }
    }

    public void skipPreChapter() {

        if (mPreChapterList != null && mPreChapterList.size() != 0) {

            mNextChapterList.clear();
            mNextChapterList.addAll(mCurChapterList);

            mCurChapterList.clear();

            mCurChapterList.addAll(mPreChapterList);

            mPreChapterList.clear();

            mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);
        } else {

            if (mCurrentVolumePos == 0 && mCurrentChapterPos == 0) {
                mStateObserver.setNovelPageState(STATE_NO_PRE);
            } else {

                mCurChapterList.clear();

                List<NovelContentCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();

                if (mCurrentChapterPos == 0) {
                    mCurrentVolumePos -= 1;
                    mCurrentChapterPos = volumeList.get(mCurrentVolumePos).getChapterList().size() - 1;

                } else {
                    mCurrentChapterPos -= 1;

                }
                currentRequestChapterId = volumeList.get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();
                currentRequestVolumeId = volumeList.get(mCurrentVolumePos).getVolumeId();

                mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, currentRequestVolumeId, currentRequestChapterId);
            }

        }
    }

    /**
     * 计算加载目标章节的内容
     *
     * @return
     */
    private void transformCurrentContent(List<NovelPageEntity> targetList, final String srcContent) {

        mStateObserver.setNovelPageState(STATE_LOADING);

        if (!TextUtils.isEmpty(srcContent) && mContentPaint != null && mPageHeight > 0 && mPageWidth > 0) {

            //调用异步进行加载
            Single.create(new SingleOnSubscribe<List<NovelPageEntity>>() {
                @Override
                public void subscribe(SingleEmitter<List<NovelPageEntity>> e) throws Exception {
                    e.onSuccess(calPageContent(srcContent));
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<NovelPageEntity>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mPreLoadDisp = d;
                        }

                        @Override
                        public void onSuccess(List<NovelPageEntity> pages) {
                            mCurChapterList = pages;
                            mStateObserver.setNovelPageState(STATE_LOADING_FINISH);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //无视错误
                        }
                    });

//            targetList.clear();
//            targetList.addAll(calPageContent(srcContent));

        }


    }

    public void setContent(long bookId, long volumeId, long chapterId, String content) {
        this.mContent = content;

        if (mCurrentVolumePos == -1) {
            mCurrentVolumePos = 0;
            for (NovelContentCatalogueEntity.NovelContentVolumeEntity volumeEntity : mCatalogue.getVolumeList()) {
                if (volumeEntity.getVolumeId() == volumeId) {
                    break;
                }
                mCurrentVolumePos++;
            }
        }

        if (mCurrentChapterPos == -1) {
            mCurrentChapterPos = 0;
            List<NovelContentCatalogueEntity.NovelContentChapterEntity> chapters = mCatalogue.getVolumeList().get((int) mCurrentVolumePos).getChapterList();
            for (NovelContentCatalogueEntity.NovelContentChapterEntity entity : chapters) {

                if (entity.getChapterId() == chapterId) {
                    break;
                }

                mCurrentChapterPos++;
            }
        }

        transformCurrentContent(mCurChapterList, mContent);
    }

    public void setCatalogue(NovelContentCatalogueEntity catalogue) {
        mCatalogue = catalogue;
    }


    public List<NovelPageEntity> getCurChapterList() {
        return mCurChapterList;
    }

    private List<NovelPageEntity> calPageContent(String srcContent) {

        int curTextHeight = 0;

        String[] paragraphs = srcContent.split("\n");

        List<NovelPageEntity> result = new ArrayList<>();
        List<String> chapterLines = new ArrayList<>();

        //遍历段落并计算分行
        for (String paragraph : paragraphs) {
            if (TextUtils.isEmpty(paragraph)) continue;

            while (paragraph.length() > 0) {

                // 如果超过一页，那么新建一页，并添加进集合
                if (curTextHeight > mPageHeight - 2 * UiUtils.dp2px(10)) {
                    NovelPageEntity singleEntity = new NovelPageEntity();

                    singleEntity.setLines(new ArrayList<String>(chapterLines));

                    result.add(singleEntity);
                    chapterLines.clear();

                    curTextHeight = 0;
                    curTextHeight += UiUtils.dp2px(3);
                    continue;
                } else {
                    //如果没超过一页，那么继续往下加
                    curTextHeight += mContentPaint.getTextSize();
                }
                //计算单行多少个字
                int singleLineWordCount = mContentPaint.breakText(paragraph, true, mPageWidth - 2 * UiUtils.dp2px(10), null);

                // 添加行并重置段落
                String line = paragraph.substring(0, singleLineWordCount);
                chapterLines.add(line);
                paragraph = paragraph.substring(singleLineWordCount);

            }
            curTextHeight += UiUtils.dp2px(3);
        }
        // 遍历完成，把最后一页的加上
        if (chapterLines.size() != 0) {
            NovelPageEntity singleEntity = new NovelPageEntity();


            singleEntity.setLines(new ArrayList<String>(chapterLines));

            result.add(singleEntity);
            chapterLines.clear();
        }

        return result;
    }


    public void onDestroy() {
        if (mInstance != null) {
            mInstance = null;
        }

    }
}
