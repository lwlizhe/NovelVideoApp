package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lwlizhe.novelvideoapp.common.CommonSubscriber;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelInfoEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageEntity;
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

    //*********************************实体对象和数据**********************************************
//    private NovelContentEntityDao mNovelDao;
//    private NovelInfoEntityDao mInfoDao;

    private NovelCatalogueEntity mCatalogue;

    private NovelContentEntity mPreCacheEntity;
    private NovelContentEntity mNextCacheEntity;

    protected List<NovelPageEntity> mPreChapterPageList = new ArrayList<>();
    protected List<NovelPageEntity> mCurrentChapterPageList = new ArrayList<>();
    protected List<NovelPageEntity> mNextChapterPageList = new ArrayList<>();

    //*********************************配置信息和相关对象******************************************

    private Paint mContentPaint;

    private int mPageWidth;
    private int mPageHeight;

    private int mContentTextSize;
    private int mTitleTextSize;
    private int mFooterTextSize;

    private int mModuleMargin;

    private int mParagraphMargin;
    private int mContentPadding;

    private String mCurChapterContent;

    private int mCurrentVolumePos = 0;
    private int mCurrentChapterPos = 0;
    private int mCurrentPagePos = 0;

    private long mCurVolumeId;
    private long mCurChapterId;

    private long currentRequestVolumeId;
    private long currentRequestChapterId;

    private long mBookId;

    //*********************************单例和环境变量**********************************************
    private static NovelContentManager mInstance;

    private NovelRepositoryManager mRepositoryManager;

    protected NovelPageStateObserver mStateObserver;

    private Context mContext;

    //*********************************RX**********************************************************

    private Disposable mPreLoadDisp;
    private Disposable mNextLoadDisop;

    public static NovelContentManager instance(Context mContext) {

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
        mRepositoryManager = NovelRepositoryManager.instance(context);

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

        transformCurrentContent(mCurChapterContent);

        if (mNextCacheEntity != null) {
            mNextChapterPageList.clear();
            loadNextCache(mNextCacheEntity);
        }
        if (mPreCacheEntity != null) {
            mPreChapterPageList.clear();
            loadPreCache(mPreCacheEntity);
        }

    }

    public void setContentTextSize(int contentTextSize) {

        mContentTextSize = contentTextSize;
        mContentPaint.setTextSize(contentTextSize);

    }

    public void setTitleTextSize(int titleTextSize) {
        mTitleTextSize = titleTextSize;
    }

    public void setFooterTextSize(int footerTextSize) {
        mFooterTextSize = footerTextSize;
    }

    public void setParagraphMargin(int margin) {
        mParagraphMargin = margin;
    }

    public void setContentPadding(int padding) {
        mContentPadding = padding;
    }

    public void setPageModuleMargin(int margin) {
        mModuleMargin = margin;
    }

    public void setContentPaint(Paint mTextContentPaint) {
        mContentPaint = mTextContentPaint;
    }

    /**
     * 计算加载目标章节的内容
     */
    private void transformCurrentContent(final String srcContent) {

        if (TextUtils.isEmpty(srcContent)) {
            return;
        }

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
                            mCurrentChapterPageList = pages;
                            mStateObserver.setNovelPageState(STATE_LOADING_FINISH);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //无视错误
                        }
                    });
        }
    }

    /**
     * 强制请求新章节
     */
    public void requestNewChapter(long volumeId, long chapterId) {

        mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, volumeId, chapterId);
        mStateObserver.setNovelPageState(STATE_LOADING);

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
        mBookId = bookId;
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
                        mCurChapterContent = novelContentEntity.getNovelContent();
                        NovelInfoEntity infoEntity = new NovelInfoEntity(bookId, volumeId, chapterId, 0);
                        mRepositoryManager.updateReadInfo(infoEntity);


                        transformCurrentContent(mCurChapterContent);

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

        mStateObserver.setNovelPageState(STATE_LOADING);

        NovelInfoEntity load = mRepositoryManager.getReadInfo(bookId);

        if (load == null) {

            mCurrentVolumePos = 0;
            mCurrentChapterPos = 0;
            mCurVolumeId = mCatalogue.getVolumeList().get(0).getVolumeId();
            mCurChapterId = mCatalogue.getVolumeList().get(0).getChapterList().get(0).getChapterId();

            mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, mCurVolumeId, mCurChapterId);
        } else {

            mBookId = load.getBookId();
            mCurVolumeId = load.getLastReadVolumeId();
            mCurChapterId = load.getLastReadChapterId();
            mCurrentPagePos = load.getLastReadPageNum();

            initPos(mCurVolumeId, mCurChapterId);

            NovelContentEntity targetContent = mRepositoryManager.getChapter(mBookId, mCurVolumeId, mCurChapterId);

            mCurChapterContent = targetContent.getNovelContent();

            transformCurrentContent(mCurChapterContent);

        }

        checkCacheChapter();

    }

    /**
     * 加载目标章节
     *
     * @param bookId
     * @param volumeId
     * @param chapterId
     */
    public void loadTargetChapter(long bookId, long volumeId, long chapterId) {

        mStateObserver.setNovelPageState(STATE_LOADING);

        NovelContentEntity targetContent = mRepositoryManager.getChapter(bookId, volumeId, chapterId);

        mBookId = bookId;
        mCurVolumeId = volumeId;
        mCurChapterId = chapterId;

        if (targetContent != null) {
            mCurrentPagePos = 0;
            initPos(volumeId, chapterId);

            mCurChapterContent = targetContent.getNovelContent();

            NovelInfoEntity infoEntity = new NovelInfoEntity(bookId, volumeId, chapterId, 0);
            mRepositoryManager.updateReadInfo(infoEntity);

            transformCurrentContent(mCurChapterContent);

        } else {
            initPos(volumeId, chapterId);
            mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, volumeId, chapterId);
        }

        checkCacheChapter();

    }

    /**
     * 加载下一章
     */
    public void loadNextChapter() {

        int maxVolumePos = mCatalogue.getVolumeList().size() - 1;
        if (mCurrentVolumePos == maxVolumePos && mCurrentChapterPos == mCatalogue.getVolumeList().get(maxVolumePos).getChapterList().size() - 1) {
            Toast.makeText(mContext, "没有下一章了", Toast.LENGTH_SHORT).show();
        } else {
            getNextChapter();
            if (mNextChapterPageList.size() == 0) {
                cacheNextChapter();
            }

            NovelInfoEntity infoEntity = new NovelInfoEntity(mBookId, mCurVolumeId, mCurChapterId, mCurrentPagePos);
            mRepositoryManager.updateReadInfo(infoEntity);

        }

    }

    /**
     * 加载上一章
     */
    public void loadPreChapter() {
        if (mCurrentVolumePos == 0 && mCurrentChapterPos == 0) {
            Toast.makeText(mContext, "没有上一章了", Toast.LENGTH_SHORT).show();
        } else {
            getPreChapter(false);
            if (mPreChapterPageList.size() == 0) {
                cachePreChapter();
            }

            NovelInfoEntity infoEntity = new NovelInfoEntity(mBookId, mCurVolumeId, mCurChapterId, mCurrentPagePos);
            mRepositoryManager.updateReadInfo(infoEntity);
        }
    }

    /**
     * 加载目标页
     *
     * @param pos 目标页码
     */
    public void loadTargetPagePos(int pos) {
        mCurrentPagePos = pos - 1;

        NovelInfoEntity infoEntity = new NovelInfoEntity(mBookId, mCurVolumeId, mCurChapterId, mCurrentPagePos);
        mRepositoryManager.updateReadInfo(infoEntity);
    }

    /**
     * 初始化在目录中的指针位置
     */
    private void initPos(long volumeId, long chapterId) {
        // 初始化
        mCurrentVolumePos = 0;
        for (NovelCatalogueEntity.NovelContentVolumeEntity volumeEntity : mCatalogue.getVolumeList()) {
            if (volumeEntity.getVolumeId() == volumeId) {
                break;
            }
            mCurrentVolumePos++;
        }


        mCurrentChapterPos = 0;
        List<NovelCatalogueEntity.NovelContentChapterEntity> chapters = mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList();
        for (NovelCatalogueEntity.NovelContentChapterEntity entity : chapters) {

            if (entity.getChapterId() == chapterId) {
                break;
            }

            mCurrentChapterPos++;
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

        if (mPreChapterPageList != null && mPreChapterPageList.size() != 0) {
            return;
        }
        // 看数据库中有没有下一章，有则装载，无则请求
        if (mCurrentVolumePos == 0 && mCurrentChapterPos != 0) {
            checkVolumeId = mCurVolumeId;
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

        NovelContentEntity targetContent = mRepositoryManager.getChapter(mCatalogue.getBookId(), checkVolumeId, checkChapterId);
        if (targetContent == null) {
            mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, checkVolumeId, checkChapterId);
        } else {

            loadPreCache(targetContent);
        }


    }

    /**
     * 缓存后一章
     */
    private void cacheNextChapter() {
        long checkVolumeId = 0;
        long checkChapterId = 0;

        // 看数据库中有没有下一章，有则装载，无则请求
        if (mCatalogue.getVolumeList().size() - 1 > mCurrentVolumePos && mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().size() - 1 == mCurrentChapterPos) {
            checkVolumeId = mCatalogue.getVolumeList().get(mCurrentVolumePos + 1).getVolumeId();
            checkChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos + 1).getChapterList().get(0).getChapterId();
        } else if (mCatalogue.getVolumeList().size() - 1 == mCurrentVolumePos && mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().size() - 1 == mCurrentChapterPos) {
            return;
        } else {
            checkVolumeId = mCurVolumeId;
            checkChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos + 1).getChapterId();
        }

        NovelContentEntity targetContent = mRepositoryManager.getChapter(mCatalogue.getBookId(), checkVolumeId, checkChapterId);
        if (targetContent == null) {
            mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, checkVolumeId, checkChapterId);
        } else {
            loadNextCache(targetContent);
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
                mRepositoryManager.saveChapter(entity);
                // 如果不是当前展示的章节，那就不用通知下游去处理，由loadCache方法去在io线程处理
                if (mCurVolumeId == entity.getVolumeId() && mCurChapterId == entity.getChapterId()) {
//                    checkCacheChapter();
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
    private void loadCache(NovelContentEntity targetContentEntity) {

        long targetContentVolumeId = targetContentEntity.getVolumeId();
        long targetContentChapterId = targetContentEntity.getChapterId();

        // 判断是加载到前一章的缓存还是后一章的缓存中
        if (targetContentVolumeId == mCurVolumeId) {
            NovelCatalogueEntity.NovelContentVolumeEntity currentVolumeList = mCatalogue.getVolumeList().get(mCurrentVolumePos);
            if (mCurrentChapterPos > 0 && targetContentChapterId == currentVolumeList.getChapterList().get(mCurrentChapterPos - 1).getChapterId()) {

                loadPreCache(targetContentEntity);
            } else if (mCurrentChapterPos < currentVolumeList.getChapterList().size() - 1 && targetContentChapterId == currentVolumeList.getChapterList().get(mCurrentChapterPos + 1).getChapterId()) {

                loadNextCache(targetContentEntity);
            }

        } else {
            if (mCurrentVolumePos > 0 && targetContentVolumeId == mCatalogue.getVolumeList().get(mCurrentVolumePos - 1).getVolumeId()) {

                loadPreCache(targetContentEntity);
            } else if (mCurrentVolumePos < mCatalogue.getVolumeList().size() - 1 && targetContentVolumeId == mCatalogue.getVolumeList().get(mCurrentVolumePos + 1).getVolumeId()) {

                loadNextCache(targetContentEntity);
            }
        }

    }

    /**
     * 计算装载前一章缓存
     *
     * @param entity
     */
    private void loadPreCache(final NovelContentEntity entity) {

        //如果有缓存任务，解除
        if (mPreLoadDisp != null) {
            mPreLoadDisp.dispose();
        }
        mPreCacheEntity=entity;

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
                            mPreChapterPageList.clear();
                            mPreChapterPageList.addAll(pages);

                        }

                        @Override
                        public void onError(Throwable e) {
                            //无视错误
                        }
                    });
        }
    }

    /**
     * 计算装载后一章缓存
     *
     * @param entity
     */
    private void loadNextCache(final NovelContentEntity entity) {

        if(entity==null){
            return;
        }

        //如果有缓存任务，解除
        if (mNextLoadDisop != null) {
            mNextLoadDisop.dispose();
        }
        mNextCacheEntity=entity;

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
                            mNextLoadDisop = d;
                        }

                        @Override
                        public void onSuccess(List<NovelPageEntity> pages) {
                            mNextChapterPageList.clear();
                            mNextChapterPageList.addAll(pages);

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

    public NovelCatalogueEntity getCatalogueInfo() {
        return mCatalogue;
    }


    public void refreshCurrent(){

        mContentPaint.setTextSize(mContentTextSize);

        transformCurrentContent(mCurChapterContent);

        if (mNextCacheEntity != null) {
            mNextChapterPageList.clear();
            loadNextCache(mNextCacheEntity);
        }
        if (mPreCacheEntity != null) {
            mPreChapterPageList.clear();
            loadPreCache(mPreCacheEntity);
        }

    }

    /**
     * 生成当前页面
     *
     * @return 页面内容，如果返回为null,说明参数不对
     */
    public NovelPageEntity getCurrentPage() {
        NovelPageEntity result = null;
        int maxPagePos = mCurrentChapterPageList.size() - 1;
        if (mCurrentPagePos <= maxPagePos) {
            result = mCurrentChapterPageList.get(mCurrentPagePos);
            result.setCurrentPagePos(mCurrentPagePos);
            result.setMaxPageCount(mCurrentChapterPageList.size());
        }

        if (result == null) {
            result = new NovelPageEntity();
        }

        NovelCatalogueEntity.NovelContentVolumeEntity volumeEntity = mCatalogue.getVolumeList().get(mCurrentVolumePos);
        result.setBookId(mBookId);
        result.setVolumeId(mCurVolumeId);
        result.setChapterId(mCurChapterId);
        result.setTitleName(volumeEntity.getVolumeName() + " " + volumeEntity.getChapterList().get(mCurrentChapterPos).getChapterName());

        mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);

        return result;
    }

    /**
     * 生成前一页
     *
     * @return 页面内容，如果返回为null,说明参数不对
     */
    public NovelPageEntity getPrePage() {
        NovelPageEntity result;

        // 如果不跳转章节
        if (mCurrentPagePos != 0) {
            mCurrentPagePos--;
            result = createNormalPageEntity(mCurrentChapterPageList.get(mCurrentPagePos));

            mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);
        } else {
            result = getPreChapter(true);
        }

        if (result != null) {

            // 请求新的预加载内容
            cachePreChapter();

            NovelInfoEntity infoEntity = new NovelInfoEntity(mBookId, result.getVolumeId(), result.getChapterId(), result.getCurrentPagePos());
            mRepositoryManager.updateReadInfo(infoEntity);
        }

        return result;
    }

    /**
     * 获取前一章的内容
     *
     * @return
     * @Params isPage 是否是从page计算过来的，用于判断是否需要将currentPagePos归0
     */
    private NovelPageEntity getPreChapter(boolean isPage) {
        NovelPageEntity result = null;

        //如果上一章已经缓存
        if (mPreChapterPageList != null && mPreChapterPageList.size() != 0) {

            //交换缓存信息
            mNextChapterPageList.clear();
            mNextChapterPageList.addAll(mCurrentChapterPageList);

            mCurrentChapterPageList.clear();

            mCurrentChapterPageList.addAll(mPreChapterPageList);

            mPreChapterPageList.clear();

            if(mPreCacheEntity!=null){
                mCurChapterContent=mPreCacheEntity.getNovelContent();
            }

            if (isPage) {
                // 跳转到上一章结尾，页码调最大
                mCurrentPagePos = mCurrentChapterPageList.size() - 1;
            } else {
                mCurrentPagePos = 0;
            }

            //如果当前章节是当前卷的第一章，那么卷-1，章节最大
            calChapterPos(false);

            result = createNormalPageEntity(mCurrentChapterPageList.get(mCurrentPagePos));

            mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);

        } else {

            //同样需要更新缓存信息
            mNextChapterPageList.clear();
            mNextChapterPageList.addAll(mCurrentChapterPageList);

            mCurrentChapterPageList.clear();

            mCurrentChapterPageList.addAll(mPreChapterPageList);

            mPreChapterPageList.clear();

            mCurrentPagePos = 0;

            //判断章节是否是0，也就是是不是开头
            if (mCurrentVolumePos == 0 && mCurrentChapterPos == 0) {
                mStateObserver.setNovelPageState(STATE_NO_PRE);
            } else {

                List<NovelCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();

                //判断是不是需要跳转到上一卷的最后一章
                calChapterPos(false);

                currentRequestChapterId = volumeList.get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();
                currentRequestVolumeId = mCurVolumeId;

                result = createNormalPageEntity(new NovelPageEntity());

                //获取请求的卷id和章节id，发送请求
                mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, currentRequestVolumeId, currentRequestChapterId);

            }

        }

        return result;
    }

    /**
     * 生成下一页
     *
     * @return 页面内容，如果返回为null,说明参数不对
     */
    public NovelPageEntity getNextPage() {

        NovelPageEntity result = null;

        int maxPagePos = mCurrentChapterPageList.size() - 1;

        // 如果不跳转章节
        if (mCurrentPagePos < maxPagePos) {
            mCurrentPagePos++;
            result = createNormalPageEntity(mCurrentChapterPageList.get(mCurrentPagePos));

            mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);
        } else {
            result = getNextChapter();
        }

        if (result != null) {

            // 请求新的预加载内容
            cacheNextChapter();

            NovelInfoEntity infoEntity = new NovelInfoEntity(mBookId, result.getVolumeId(), result.getChapterId(), result.getCurrentPagePos());
            mRepositoryManager.updateReadInfo(infoEntity);
        }

        return result;
    }

    /**
     * 获取下一章
     *
     * @return
     */
    private NovelPageEntity getNextChapter() {
        NovelPageEntity result = null;

        //如果章节已经预加载
        if (mNextChapterPageList != null && mNextChapterPageList.size() != 0) {

            mPreChapterPageList.clear();
            mPreChapterPageList.addAll(mCurrentChapterPageList);

            mCurrentChapterPageList.clear();
            mCurrentChapterPageList.addAll(mNextChapterPageList);

            mNextChapterPageList.clear();

            if(mNextCacheEntity!=null){
                mCurChapterContent=mNextCacheEntity.getNovelContent();
            }

            // 章节页码归零
            mCurrentPagePos = 0;
            //如果当前章节是当前卷的最后一章，那么卷+1，章节归零
            calChapterPos(true);

            result = createNormalPageEntity(mCurrentChapterPageList.get(mCurrentPagePos));

            mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NORMAL);

        } else {

            //同样需要更新缓存信息
            mPreChapterPageList.clear();
            mPreChapterPageList.addAll(mCurrentChapterPageList);

            mCurrentChapterPageList.clear();
            mCurrentChapterPageList.addAll(mNextChapterPageList);

            mNextChapterPageList.clear();

            List<NovelCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();

            int chapterSize = volumeList.get(mCurrentVolumePos).getChapterList().size();

            if (mCurrentVolumePos >= volumeList.size() - 1 && mCurrentChapterPos >= chapterSize - 1) {
                mStateObserver.setNovelPageState(NovelPageStateObserver.STATE_NO_NEXT);
            } else {

                mCurrentPagePos = 0;

                calChapterPos(true);

                currentRequestChapterId = volumeList.get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();
                currentRequestVolumeId = mCurVolumeId;

                result = createNormalPageEntity(new NovelPageEntity());

                mStateObserver.setNovelPageState(STATE_REQUEST_CHAPTER, currentRequestVolumeId, currentRequestChapterId);

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
        int validContentPageHeight = mPageHeight - (mTitleTextSize + mContentPadding + mModuleMargin) - 2 * mParagraphMargin - (mContentPadding + mModuleMargin + mFooterTextSize);// 页面高度-头部-2*段落间距-脚部

        String[] paragraphs = srcContent.split("\n");

        List<NovelPageEntity> result = new ArrayList<>();
        List<String> chapterLines = new ArrayList<>();

        //遍历段落并计算分行
        for (String paragraph : paragraphs) {
            if (TextUtils.isEmpty(paragraph)) continue;

            paragraph = "\u3000\u3000" + paragraph.trim();//首行缩进,加上2空格,去掉所有空格
            while (paragraph.length() > 0) {

                curTextHeight += mContentTextSize;

                // 如果超过一页，那么新建一页，并添加进集合
                if (curTextHeight >= validContentPageHeight) {
                    NovelPageEntity singleEntity = new NovelPageEntity();

                    singleEntity.setLines(new ArrayList<String>(chapterLines));

                    result.add(singleEntity);
                    chapterLines.clear();

                    curTextHeight = 0;
                    continue;
                }

                //计算单行多少个字
                int singleLineWordCount = mContentPaint.breakText(paragraph, true, mPageWidth - 2 * mContentPadding, null);

                // 添加行并重置段落
                String line = paragraph.substring(0, singleLineWordCount);
                chapterLines.add(line);
                paragraph = paragraph.substring(singleLineWordCount);

                curTextHeight += mParagraphMargin;
            }

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

    /**
     * 重新计算指针位置
     *
     * @param isNext 是计算下一章的指针位置，还是上一章的
     */
    private void calChapterPos(boolean isNext) {

        List<NovelCatalogueEntity.NovelContentVolumeEntity> volumeList = mCatalogue.getVolumeList();

        int chapterSize = volumeList.get(mCurrentVolumePos).getChapterList().size();

        if (isNext) {
            if (mCurrentChapterPos >= chapterSize - 1) {
                mCurrentVolumePos += 1;
                mCurVolumeId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getVolumeId();

                mCurrentChapterPos = 0;
                mCurChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();


            } else {
                mCurrentChapterPos += 1;
                mCurChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();
            }
        } else {
            if (mCurrentChapterPos == 0) {
                mCurrentVolumePos -= 1;
                mCurVolumeId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getVolumeId();

                mCurrentChapterPos = volumeList.get(mCurrentVolumePos).getChapterList().size() - 1;
                mCurChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();

            } else {
                mCurrentChapterPos -= 1;
                mCurChapterId = mCatalogue.getVolumeList().get(mCurrentVolumePos).getChapterList().get(mCurrentChapterPos).getChapterId();
            }
        }

    }

    /**
     * 生成一般的pageEntity
     *
     * @param targetPageEntity 要进行设置的PageEntity
     * @return 设置完成之后的结果
     */
    private NovelPageEntity createNormalPageEntity(NovelPageEntity targetPageEntity) {

        NovelCatalogueEntity.NovelContentVolumeEntity volumeEntity = mCatalogue.getVolumeList().get(mCurrentVolumePos);

        targetPageEntity.setCurrentPagePos(mCurrentPagePos);
        targetPageEntity.setMaxPageCount(mCurrentChapterPageList.size() );

        targetPageEntity.setBookId(mBookId);
        targetPageEntity.setVolumeId(volumeEntity.getVolumeId());
        targetPageEntity.setChapterId(volumeEntity.getChapterList().get(mCurrentChapterPos).getChapterId());
        targetPageEntity.setTitleName(volumeEntity.getVolumeName() + " " + volumeEntity.getChapterList().get(mCurrentChapterPos).getChapterName());

        return targetPageEntity;
    }


    public void onDestroy() {
        if (mInstance != null) {
            mInstance = null;
        }

    }

}
