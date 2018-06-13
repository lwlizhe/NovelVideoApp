package com.lwlizhe.novelvideoapp.novel.mvp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.common.CommonActivity;
import com.lwlizhe.novelvideoapp.common.di.component.AppComponent;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.novelvideoapp.novel.di.component.DaggerNovelReadComponent;
import com.lwlizhe.novelvideoapp.novel.di.module.NovelReadModule;
import com.lwlizhe.novelvideoapp.novel.mvp.contract.activity.NovelReadContract;
import com.lwlizhe.novelvideoapp.novel.mvp.presenter.activity.NovelReadPresenter;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentCatalogueEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class NovelReadActivity extends CommonActivity<NovelReadPresenter> implements NovelReadContract.View {

    public static final String NOVEL_ID = "novel_id";
    public static final String NOVEL_CHAPTER_ID = "novel_chapter_id";
    public static final String NOVEL_VOLUME_ID = "novel_volume_id";
    public static final String NOVEL_CHAPTER_LIST = "novel_chapter_list";

    private FrameLayout mRootView;

    private NovelPage mPage;

    private List<NovelChapterEntity> mChapterList;

    private int currentNovelPos = 0;

    private long mNovelId;
    private int mCurrentVolumePos;
    private int mCurrentChapterPos;


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public AppCompatActivity getContext() {
        return this;
    }

    @Override
    public void setNovelContent(long bookId,long volumeId, long chapterId, String content) {

        mPage.setContent(bookId,volumeId, chapterId, content);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

        DaggerNovelReadComponent.builder().appComponent(appComponent).novelReadModule(new NovelReadModule(this)).build().inject(this);

    }

    @Override
    protected View initRootView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return LayoutInflater.from(this).inflate(R.layout.activity_novel_read, null);
    }

    @Override
    protected void initView() {

        mRootView = findViewById(R.id.root_view);
        mPage = new NovelPage(this);
        mPage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mRootView.addView(mPage);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();

        mNovelId = intent.getLongExtra(NOVEL_ID, 1);
        long volumeId = intent.getLongExtra(NOVEL_VOLUME_ID, 1);
        long chapterId = intent.getLongExtra(NOVEL_CHAPTER_ID, 1);

        mChapterList = (List<NovelChapterEntity>) intent.getSerializableExtra(NOVEL_CHAPTER_LIST);

        NovelContentCatalogueEntity catalogueEntity = new NovelContentCatalogueEntity();

        List<NovelContentCatalogueEntity.NovelContentVolumeEntity> mCatalogueVolumeList = new ArrayList<>();

        // 遍历源数据，并创建新的目录结构
        for (NovelChapterEntity srcVolumeEntity : mChapterList) {

            NovelContentCatalogueEntity.NovelContentVolumeEntity volumeEntity = new NovelContentCatalogueEntity.NovelContentVolumeEntity();
            List<NovelContentCatalogueEntity.NovelContentChapterEntity> mCatalogueChapterList = new ArrayList<>();

            volumeEntity.setVolumeId(srcVolumeEntity.getVolume_id());
            volumeEntity.setVolumeName(srcVolumeEntity.getVolume_name());

            for (NovelChapterEntity.ChaptersBean srcChapterEntity : srcVolumeEntity.getChapters()) {
                NovelContentCatalogueEntity.NovelContentChapterEntity chapterEntity = new NovelContentCatalogueEntity.NovelContentChapterEntity();

                chapterEntity.setChapterId(srcChapterEntity.getChapter_id());
                chapterEntity.setChapterName(srcChapterEntity.getChapter_name());

                mCatalogueChapterList.add(chapterEntity);
            }
            volumeEntity.setChapterList(mCatalogueChapterList);

            mCatalogueVolumeList.add(volumeEntity);

        }

        catalogueEntity.setVolumeList(mCatalogueVolumeList);
        // 设置目录，用于判断是否有没有下一页、是否需要请求新章节等
        mPage.setChapterData(catalogueEntity);

        // 遍历寻找卷的初始位置，找到退出循环
        for (NovelChapterEntity entity : mChapterList) {

            if (entity.getVolume_id() == volumeId) {
                break;
            }

            mCurrentVolumePos++;
        }

        // 同理寻找章的初始位置
        List<NovelChapterEntity.ChaptersBean> chapters = mChapterList.get((int) mCurrentVolumePos).getChapters();
        for (NovelChapterEntity.ChaptersBean entity : chapters) {

            if (entity.getChapter_id() == chapterId) {
                break;
            }

            mCurrentChapterPos++;
        }


        mPresenter.getChapter(mNovelId, volumeId, chapterId);

    }

    @Override
    protected void initListener() {

        mPage.setPageStateListener(new NovelPage.OnPageStateChangedListener() {
            @Override
            public void onRequestNewChapter(long requestVolumeId, long requestChapterId) {

                if(mPresenter!=null){
                    mPresenter.getChapter(mNovelId, requestVolumeId, requestChapterId);
                }

            }

            @Override
            public void onOpenChapterList(long currentVolumeId, long currentChapterId) {

            }
        });

    }
}
