package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;

import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.novelvideoapp.AppApplication;
import com.lwlizhe.novelvideoapp.common.CommonSubscriber;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.DaoSession;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentEntityDao;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelInfoEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelInfoEntityDao;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader.entity.NovelPageEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.stateObserver.NovelPageStateObserver;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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

    private NovelContentEntityDao mNovelDao;
    private NovelInfoEntityDao mInfoDao;

    private Paint mContentPaint;

    private NovelCatalogueEntity mCatalogue;

//    private String[] paragraphs;

    protected List<NovelPageEntity> mPreChapterList = new ArrayList<>();
    protected List<NovelPageEntity> mCurChapterList = new ArrayList<>();
    protected List<NovelPageEntity> mNextChapterList = new ArrayList<>();

    private NovelContentEntity mPreCacheEntity;
    private NovelContentEntity mNextCacheEntity;

    private int mPageWidth;
    private int mPageHeight;

    private int mContentTextSize;
    private int mTitleTextSize;

    private int mParagraphMargin;
    private int mContentPadding;

    private static NovelContentManager mInstance;
    protected NovelPageStateObserver mStateObserver;

    private Context mContext;

    private Disposable mPreLoadDisp;

    private String mContent;

    private int mCurrentVolumePos = -1;
    private int mCurrentChapterPos = -1;
    private int mCurrentPagePos = -1;

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

        DaoSession mDataDaoSession = ((AppApplication) (context.getApplicationContext())).getDaoSession();

        mNovelDao = mDataDaoSession.getNovelContentEntityDao();
        mInfoDao = mDataDaoSession.getNovelInfoEntityDao();

        mContentPadding = UiUtils.dp2px(10);
        mParagraphMargin = UiUtils.dp2px(3);
    }

    /**
     * 设置改变后的页面规格
     *
     * @param w 页面宽
     * @param h 页面高
     */
    public void setPageSize(int w, int h) {
        mPageWidth = w;
        mPageHeight = h;

        transformCurrentContent(mContent);
        if (mNextCacheEntity != null) {
            loadNextCache(mNextCacheEntity);
        }
        if (mPreCacheEntity != null) {
            loadPreCache(mPreCacheEntity);
        }

    }

    public void setContentTextSize(int contentTextSize) {

        mContentTextSize = contentTextSize;

    }

    public void setTitleTextSize(int titleTextSize) {

        mTitleTextSize = titleTextSize;

    }

    public void setParagraphMargin(int margin) {
        mParagraphMargin = margin;
    }

    public void setContentPaint(Paint mTextContentPaint) {
        mContentPaint = mTextContentPaint;
    }

    /**
     * 跳转到下一章
     */
    public void skipNextChapter() {

        if (mNextChapterList != null && mNextChapterList.size() != 0) {

            mPreChapterList.clear();
            mPreChapterList.addAll(mCurChapterList);

            mCurChapterList.clear();
            mCurChapterList.addAll(mNextChapterList);

            mNextChapterList.clear();

            mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);
        } else {
            List<NovelCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();

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

    /**
     * 跳转到上一章
     */
    public void skipPreChapter() {


    }

    /**
     * 计算加载目标章节的内容
     */
    private void transformCurrentContent(final String srcContent) {

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

    /**
     * 设置目标章节的内容.并保存到数据库
     *
     * @param bookId    书籍id
     * @param volumeId  卷id
     * @param chapterId 章节id
     * @param content   章节内容
     */
    public void setContent(final long bookId, final long volumeId, final long chapterId, final String content) {

        // 保存到数据库
        final NovelContentEntity contentEntity = new NovelContentEntity(bookId, volumeId, chapterId, content);

        Flowable.create(new FlowableOnSubscribe<NovelContentEntity>() {
            @Override
            public void subscribe(FlowableEmitter<NovelContentEntity> e) throws Exception {
                e.onNext(contentEntity);
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .concatMap(new Function<NovelContentEntity, Publisher<NovelContentEntity>>() {
                    @Override
                    public Publisher<NovelContentEntity> apply(NovelContentEntity novelContentEntity) throws Exception {
                        return saveBook(novelContentEntity);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonSubscriber<NovelContentEntity>() {
                    @Override
                    public void onFailed(Throwable t) {

                    }

                    @Override
                    public void onNext(NovelContentEntity novelContentEntity) {
                        mContent = novelContentEntity.getNovelContent();
                        NovelInfoEntity infoEntity = new NovelInfoEntity(bookId, volumeId, chapterId, 0);
                        mInfoDao.insertOrReplace(infoEntity);

                        transformCurrentContent(mContent);
                        mStateObserver.setNovelPageState(STATE_LOADING_FINISH);
                        checkCacheChapter();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    /**
     * 加载上次阅读位置
     *
     * @param bookId
     */
    public void loadLastRead(long bookId) {

        NovelInfoEntity load = mInfoDao.load(bookId);

        if (load == null) {
            mCurrentVolumePos = 0;
            mCurrentChapterPos = 0;
            mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, mCatalogue.getVolumeList().get(0).getVolumeId(), mCatalogue.getVolumeList().get(0).getChapterList().get(0).getChapterId());
        } else {
            long lastReadBookId = load.getBookId();
            long lastReadVolumeId = load.getLastReadVolumeId();
            long lastReadChapterId = load.getLastReadChapterId();
            mCurrentPagePos = load.getLastReadPageNum();

            initPos(lastReadVolumeId, lastReadChapterId);
            checkCacheChapter();

            NovelContentEntity targetContent = mNovelDao.queryBuilder().where(NovelContentEntityDao.Properties.BookId.eq(lastReadBookId), NovelContentEntityDao.Properties.VolumeId.eq(lastReadVolumeId), NovelContentEntityDao.Properties.ChapterId.eq(lastReadChapterId)).unique();

            mContent = targetContent.getNovelContent();

            transformCurrentContent(mContent);
            mStateObserver.setNovelPageState(STATE_LOADING_FINISH);

        }


    }

    /**
     * 加载目标章节
     *
     * @param bookId
     * @param volumeId
     * @param chapterId
     */
    public void loadTargetChapter(long bookId, long volumeId, long chapterId) {

        NovelContentEntity targetContent = mNovelDao.queryBuilder().where(NovelContentEntityDao.Properties.BookId.eq(bookId), NovelContentEntityDao.Properties.VolumeId.eq(volumeId), NovelContentEntityDao.Properties.ChapterId.eq(chapterId)).unique();

        if (targetContent != null) {
            mCurrentPagePos=0;
            initPos(volumeId, chapterId);
            checkCacheChapter();

            NovelContentEntity skipContent = mNovelDao.queryBuilder().where(NovelContentEntityDao.Properties.BookId.eq(bookId), NovelContentEntityDao.Properties.VolumeId.eq(volumeId), NovelContentEntityDao.Properties.ChapterId.eq(chapterId)).unique();

            mContent = skipContent.getNovelContent();

            NovelInfoEntity infoEntity = new NovelInfoEntity(bookId, volumeId, chapterId, 0);
            mInfoDao.insertOrReplace(infoEntity);

            transformCurrentContent(mContent);
            mStateObserver.setNovelPageState(STATE_LOADING_FINISH);

        } else {
            initPos(volumeId, chapterId);
            mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, volumeId, chapterId);
        }

    }

    /**
     * 初始化在目录中的指针位置
     */
    private void initPos(long volumeId, long chapterId) {
        // 初始化
        if (mCurrentVolumePos == -1) {
            mCurrentVolumePos = 0;
            for (NovelCatalogueEntity.NovelContentVolumeEntity volumeEntity : mCatalogue.getVolumeList()) {
                if (volumeEntity.getVolumeId() == volumeId) {
                    break;
                }
                mCurrentVolumePos++;
            }
        }

        if (mCurrentChapterPos == -1) {
            mCurrentChapterPos = 0;
            List<NovelCatalogueEntity.NovelContentChapterEntity> chapters = mCatalogue.getVolumeList().get((int) mCurrentVolumePos).getChapterList();
            for (NovelCatalogueEntity.NovelContentChapterEntity entity : chapters) {

                if (entity.getChapterId() == chapterId) {
                    break;
                }

                mCurrentChapterPos++;
            }
        }
    }

    /**
     * 检测缓存章节
     */
    private void checkCacheChapter() {

        cachePreChapter();
        cacheNextChapter();

    }

    /**
     * 缓存前一章
     */
    private void cachePreChapter() {

        long checkVolumeId = 0;
        long checkChapterId = 0;

        if(mPreChapterList!=null&&mPreChapterList.size()!=0){
            return;
        }

        if (mCurrentVolumePos == 0 && mCurrentChapterPos != 0) {
            checkVolumeId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getVolumeId();
            checkChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos - 1).getChapterId();

        } else if (mCurrentVolumePos != 0) {

            if (mCurrentChapterPos == 0) {
                NovelCatalogueEntity.NovelContentVolumeEntity targetVolume = mCatalogue.getVolumeList().get(mCurrentVolumePos - 1);
                checkVolumeId = targetVolume.getVolumeId();
                checkChapterId = targetVolume.getChapterList().get(targetVolume.getChapterList().size() - 1).getChapterId();
            } else {
                NovelCatalogueEntity.NovelContentVolumeEntity targetVolume = mCatalogue.getVolumeList().get(mCurrentVolumePos);
                checkVolumeId = targetVolume.getVolumeId();
                checkChapterId = targetVolume.getChapterList().get(mCurrentChapterPos - 1).getChapterId();
            }

        } else {
            return;
        }

        NovelContentEntity targetContent = mNovelDao.queryBuilder().where(NovelContentEntityDao.Properties.BookId.eq(mCatalogue.getBookId()), NovelContentEntityDao.Properties.VolumeId.eq(checkVolumeId), NovelContentEntityDao.Properties.ChapterId.eq(checkChapterId)).unique();
        if (targetContent == null) {
            mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, checkVolumeId, checkChapterId);
        } else {
            loadCache(targetContent);
        }

    }

    /**
     * 缓存后一章
     */
    private void cacheNextChapter() {
        long checkVolumeId = 0;
        long checkChapterId = 0;

        if (mCatalogue.getVolumeList().size() - 1 > mCurrentVolumePos && mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().size() - 1 == mCurrentChapterPos) {
            checkVolumeId = mCatalogue.getVolumeList().get(mCurrentVolumePos + 1).getVolumeId();
            checkChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos + 1).getChapterList().get(0).getChapterId();
        } else if (mCatalogue.getVolumeList().size() - 1 == mCurrentVolumePos && mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().size() - 1 == mCurrentChapterPos) {
            return;
        } else {
            checkVolumeId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getVolumeId();
            checkChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos + 1).getChapterId();
        }

        NovelContentEntity targetContent = mNovelDao.queryBuilder().where(NovelContentEntityDao.Properties.BookId.eq(mCatalogue.getBookId()), NovelContentEntityDao.Properties.VolumeId.eq(checkVolumeId), NovelContentEntityDao.Properties.ChapterId.eq(checkChapterId)).unique();
        if (targetContent == null) {
            mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, checkVolumeId, checkChapterId);
        } else {
            loadCache(targetContent);
        }
    }


    /**
     * 缓存或覆盖
     *
     * @param entity
     * @return
     */
    private Publisher<NovelContentEntity> saveBook(final NovelContentEntity entity) {

        return new Publisher<NovelContentEntity>() {
            @Override
            public void subscribe(Subscriber<? super NovelContentEntity> s) {
                mNovelDao.insertOrReplace(entity);
                NovelCatalogueEntity.NovelContentVolumeEntity currentVolumeList = mCatalogue.getVolumeList().get(mCurrentVolumePos);
                // 如果不是当前展示的章节，那就不用通知下游去处理，由loadCache方法去在io线程处理
                if (currentVolumeList.getVolumeId() == entity.getVolumeId() && currentVolumeList.getChapterList().get(mCurrentChapterPos).getChapterId() == entity.getChapterId()) {
                    checkCacheChapter();
                    s.onNext(entity);
                } else {
                    loadCache(entity);
                }
            }
        };
    }

    /**
     * 计算装填预加载的内容
     */
    private void loadCache(NovelContentEntity targetContent) {

        long targetContentVolumeId = targetContent.getVolumeId();
        long targetContentChapterId = targetContent.getChapterId();

        if (targetContentVolumeId == mCatalogue.getVolumeList().get(mCurrentVolumePos).getVolumeId()) {
            NovelCatalogueEntity.NovelContentVolumeEntity currentVolumeList = mCatalogue.getVolumeList().get(mCurrentVolumePos);
            if (mCurrentChapterPos > 0 && targetContentChapterId == currentVolumeList.getChapterList().get(mCurrentChapterPos - 1).getChapterId()) {
                mPreCacheEntity = targetContent;
                loadPreCache(mPreCacheEntity);
            } else if (mCurrentChapterPos < currentVolumeList.getChapterList().size() - 1 && targetContentChapterId == currentVolumeList.getChapterList().get(mCurrentChapterPos + 1).getChapterId()) {
                mNextCacheEntity = targetContent;
                loadNextCache(mNextCacheEntity);
            }

        } else {
            if (mCurrentVolumePos > 0 && targetContentVolumeId == mCatalogue.getVolumeList().get(mCurrentVolumePos - 1).getVolumeId()) {
                mPreCacheEntity = targetContent;
                loadPreCache(mPreCacheEntity);
            } else if (mCurrentVolumePos < mCatalogue.getVolumeList().size() - 1 && targetContentVolumeId == mCatalogue.getVolumeList().get(mCurrentVolumePos + 1).getVolumeId()) {
                mNextCacheEntity = targetContent;
                loadNextCache(mNextCacheEntity);
            }
        }

    }

    /**
     * 计算装载前一章
     *
     * @param entity
     */
    private void loadPreCache(final NovelContentEntity entity) {

        if (!TextUtils.isEmpty(entity.getNovelContent()) && mContentPaint != null && mPageHeight > 0 && mPageWidth > 0) {

            //调用异步进行加载
            Single.create(new SingleOnSubscribe<List<NovelPageEntity>>() {
                @Override
                public void subscribe(SingleEmitter<List<NovelPageEntity>> e) throws Exception {
                    e.onSuccess(calPageContent(entity.getNovelContent()));
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
                            mPreChapterList.clear();
                            mPreChapterList.addAll(pages);
                            mPreCacheEntity = null;
                        }

                        @Override
                        public void onError(Throwable e) {
                            //无视错误
                        }
                    });
        }
    }

    /**
     * 计算装载后一章
     *
     * @param entity
     */
    private void loadNextCache(final NovelContentEntity entity) {
        if (!TextUtils.isEmpty(entity.getNovelContent()) && mContentPaint != null && mPageHeight > 0 && mPageWidth > 0) {

            //调用异步进行加载
            Single.create(new SingleOnSubscribe<List<NovelPageEntity>>() {
                @Override
                public void subscribe(SingleEmitter<List<NovelPageEntity>> e) throws Exception {
                    e.onSuccess(calPageContent(entity.getNovelContent()));
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
                            mNextChapterList.clear();
                            mNextChapterList.addAll(pages);
                            mNextCacheEntity = null;
                        }

                        @Override
                        public void onError(Throwable e) {
                            //无视错误
                            Log.d("test", e.toString());
                        }
                    });
        }
    }

    /**
     * 设置目录
     *
     * @param catalogue 目录实体类
     */
    public void setCatalogue(NovelCatalogueEntity catalogue) {
        mCatalogue = catalogue;
    }


    public List<NovelPageEntity> getCurChapterList() {
        return mCurChapterList;
    }

    public NovelPageEntity getCurrentPage(){
        NovelPageEntity result=null;
        int maxPagePos = mCurChapterList.size()-1;
        if(mCurrentPagePos<maxPagePos){
            result=mCurChapterList.get(mCurrentPagePos);
        }
        return result;
    }

    public NovelPageEntity getPrePage(){
        NovelPageEntity result=null;

        // 如果不跳转章节
        if(mCurrentPagePos!=0){
            mCurrentPagePos--;
            result=mCurChapterList.get(mCurrentPagePos);
        }else {
            //如果上一章已经缓存
            if (mPreChapterList != null && mPreChapterList.size() != 0) {

                mNextChapterList.clear();
                mNextChapterList.addAll(mCurChapterList);

                mCurChapterList.clear();

                mCurChapterList.addAll(mPreChapterList);

                mPreChapterList.clear();

                // 跳转到上一章结尾，页码调最大
                mCurrentPagePos=mCurChapterList.size()-1;

                //如果当前章节是当前卷的第一章，那么卷-1，章节最大
                if(mCurrentChapterPos==0){
                    mCurrentVolumePos--;
                    mCurrentChapterPos=mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().size()-1;
                }else{
                    mCurrentChapterPos--;
                }
                // 请求新的预加载内容
                checkCacheChapter();

                result=mCurChapterList.get(mCurrentPagePos);
                mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);


            } else {
                //判断章节是否是0，也就是是不是开头
                if (mCurrentVolumePos == 0 && mCurrentChapterPos == 0) {
                    mStateObserver.setNovelPageState(STATE_NO_PRE);
                } else {

                    mCurChapterList.clear();

                    List<NovelCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();

                    //判断是不是需要跳转到上一卷的最后一章
                    if (mCurrentChapterPos == 0) {
                        mCurrentVolumePos -= 1;
                        mCurrentChapterPos = volumeList.get(mCurrentVolumePos).getChapterList().size() - 1;

                    } else {
                        mCurrentChapterPos -= 1;
                    }
                    currentRequestChapterId = volumeList.get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();
                    currentRequestVolumeId = volumeList.get(mCurrentVolumePos).getVolumeId();
                    //获取请求的卷id和章节id，发送请求
                    mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, currentRequestVolumeId, currentRequestChapterId);
                }

            }
        }

        return result;
    }

    public NovelPageEntity getNextPage() {

        NovelPageEntity result=null;

        int maxPagePos = mCurChapterList.size()-1;

        // 如果不跳转章节
        if(mCurrentPagePos<maxPagePos){
            mCurrentPagePos++;
            result=mCurChapterList.get(mCurrentPagePos);
        }else{

            //如果章节已经预加载
            if (mNextChapterList != null && mNextChapterList.size() != 0) {

                mPreChapterList.clear();
                mPreChapterList.addAll(mCurChapterList);

                mCurChapterList.clear();
                mCurChapterList.addAll(mNextChapterList);

                mNextChapterList.clear();

                // 章节页码归零
                mCurrentPagePos=0;
                //如果当前章节是当前卷的最后一章，那么卷+1，章节归零
                if(mCurrentChapterPos==mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().size()-1){
                    mCurrentVolumePos++;
                    mCurrentChapterPos=0;
                }else{
                    mCurrentChapterPos++;
                }
                // 请求新的预加载内容
                checkCacheChapter();

                result=mCurChapterList.get(mCurrentPagePos);
                mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);


            }else{
                List<NovelCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();

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

        return result;
    }

    /**
     * 分割单个章节的内容并计算每页的内容.
     *
     * @param srcContent 目标内容
     * @return 每页的内容列表
     */
    private List<NovelPageEntity> calPageContent(String srcContent) {

        int curTextHeight = 0;
        int validContentPageHeight = mPageHeight - mTitleTextSize - 2 * mParagraphMargin;

        String[] paragraphs = srcContent.split("\n");

        List<NovelPageEntity> result = new ArrayList<>();
        List<String> chapterLines = new ArrayList<>();

        //遍历段落并计算分行
        for (String paragraph : paragraphs) {
            if (TextUtils.isEmpty(paragraph)) continue;

            while (paragraph.length() > 0) {

                // 如果超过一页，那么新建一页，并添加进集合
                if (curTextHeight > validContentPageHeight) {
                    NovelPageEntity singleEntity = new NovelPageEntity();

                    singleEntity.setLines(new ArrayList<String>(chapterLines));

                    result.add(singleEntity);
                    chapterLines.clear();

                    curTextHeight = 0;
                    curTextHeight += mParagraphMargin;
                    continue;
                } else {
                    //如果没超过一页，那么继续往下加
                    curTextHeight += mContentPaint.getTextSize();
                }
                //计算单行多少个字
                int singleLineWordCount = mContentPaint.breakText(paragraph, true, mPageWidth - 2 * mContentPadding, null);

                // 添加行并重置段落
                String line = paragraph.substring(0, singleLineWordCount);
                chapterLines.add(line);
                paragraph = paragraph.substring(singleLineWordCount);

            }
            curTextHeight += mParagraphMargin;
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
