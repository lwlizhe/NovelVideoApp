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

    private String mContent;

    private int mCurrentVolumePos = 0;
    private int mCurrentChapterPos = 0;

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

        mPreChapterList.clear();
        mPreChapterList.addAll(mCurChapterList);

        if (mNextChapterList != null && mNextChapterList.size() != 0) {
            mCurChapterList.clear();
            mCurChapterList.addAll(mNextChapterList);
        } else {
            List<NovelContentCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();
            if (mCurrentVolumePos >= volumeList.size() - 1 || mCurrentChapterPos >= volumeList.get(mCurrentVolumePos).getChapterList().size() - 1) {
                mStateObserver.setNovelPageState(1);
            } else {

                currentRequestVolumeId = volumeList.get(mCurrentVolumePos).getVolumeId();
                currentRequestChapterId = volumeList.get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos + 1).getChapterId();

                mStateObserver.setNovelPageState(2, currentRequestVolumeId, currentRequestChapterId);

            }
        }
    }

    /**
     * 计算加载目标章节的内容
     *
     * @return
     */
    private List<NovelPageEntity> transformCurrentContent(List<NovelPageEntity> targetList, String srcContent) {

        if (!TextUtils.isEmpty(srcContent) && mContentPaint != null && mPageHeight > 0 && mPageWidth > 0) {

            String[] paragraphs = srcContent.split("\r\n");

            calPageContent(paragraphs, targetList);
        }

        return targetList;
    }

    public void setContent(long bookId, long volumeId, long chapterId, String content) {
        this.mContent = content;

        mCurrentVolumePos=0;
        mCurrentChapterPos=0;

        for (NovelContentCatalogueEntity.NovelContentVolumeEntity volumeEntity : mCatalogue.getVolumeList()) {
            if (volumeEntity.getVolumeId() == volumeId) {
                break;
            }
            mCurrentVolumePos++;
        }

        List<NovelContentCatalogueEntity.NovelContentChapterEntity> chapters = mCatalogue.getVolumeList().get((int) mCurrentVolumePos).getChapterList();
        for (NovelContentCatalogueEntity.NovelContentChapterEntity entity : chapters) {

            if (entity.getChapterId() == chapterId) {
                break;
            }

            mCurrentChapterPos++;
        }

        transformCurrentContent(mCurChapterList, mContent);
    }

    public void setPreContent(long bookId, long volumeId, long chapterId, String content) {

        transformCurrentContent(mPreChapterList, content);

    }

    public void setNextContent(long bookId, long volumeId, long chapterId, String content) {

        transformCurrentContent(mNextChapterList, content);
    }

    public void setCatalogue(NovelContentCatalogueEntity catalogue) {
        mCatalogue = catalogue;
    }


    public List<NovelPageEntity> getCurChapterList() {
        return mCurChapterList;
    }

    private void calPageContent(String[] sourceContent, List<NovelPageEntity> targetList) {

        int curTextHeight = 0;

        List<String> chapterLines = new ArrayList<>();

        //遍历段落并计算分行
        for (String paragraph : sourceContent) {
            if (TextUtils.isEmpty(paragraph)) continue;

            while (paragraph.length() > 0) {

                // 如果超过一页，那么新建一页，并添加进集合
                if (curTextHeight > mPageHeight - 2 * UiUtils.dp2px(10)) {
                    NovelPageEntity singleEntity = new NovelPageEntity();

                    singleEntity.setLines(new ArrayList<String>(chapterLines));

                    targetList.add(singleEntity);
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

            targetList.add(singleEntity);
            chapterLines.clear();
        }

    }


    public void onDestroy() {
        if(mInstance!=null){
            mInstance = null;
        }

    }
}
