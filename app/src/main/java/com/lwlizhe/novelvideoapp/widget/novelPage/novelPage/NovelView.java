package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelMenu.SimpleNovelBottomMenu;
import com.lwlizhe.novelvideoapp.widget.novelMenu.SimpleNovelTopMenu;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.BaseControlView;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.NovelControlViewStateChangedListener;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 装载novelView的布局，用于承载小说页+菜单+提示条什么的
 * Created by Administrator on 2018/6/25 0025.
 */

public class NovelView extends RelativeLayout {

    private NovelPage mNovelPage;

    private View mErrorView;

    private ProgressBar mLoadingProgressBar;

    private List<BaseControlView> mControlViewList;

    public NovelView(Context context) {
        super(context);
        init();
    }

    public NovelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NovelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mControlViewList = new ArrayList<>();

        mLoadingProgressBar = new ProgressBar(getContext());

        RelativeLayout.LayoutParams loadingParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        loadingParams.addRule(CENTER_IN_PARENT);

        mLoadingProgressBar.setLayoutParams(loadingParams);


        mNovelPage = new NovelPage(getContext());
        mNovelPage.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mNovelPage.setTipsStateListener(new NovelPage.OnTipStateChangedListener() {
            @Override
            public void onLoading() {
                mLoadingProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFinish() {
                mLoadingProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onNormal() {
                mLoadingProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingError(int reason) {

            }
        });

        mNovelPage.setControlViewStateListener(new NovelControlViewStateChangedListener() {
            @Override
            public void onOpenCatalog(long curBookId, long curVolumeId, long curChapterId) {
                Log.d("test", "test");
            }

            @Override
            public void onShowControlView() {
                Log.d("test", "test");
                for(BaseControlView view:mControlViewList){
                    view.onOpen(mNovelPage);
                }

            }

            @Override
            public void onHideControlView() {
                Log.d("test", "test");

                for(BaseControlView view:mControlViewList){
                    view.onClose(mNovelPage);
                }
            }

            @Override
            public void onPageStateChanged(NovelPageInfo pageInfo) {
                    Log.d("test", "test");
                for(BaseControlView view:mControlViewList){
                    view.onPageInfoChanged(pageInfo);
                }
            }

        });

        this.addView(mNovelPage);
        this.addView(mLoadingProgressBar);
    }

    /**
     * 设置当前展示页面的章节内容
     *
     * @param bookId    书籍id
     * @param chapterId 当前章节id
     * @param content   章节内容
     */
    public void setContent(long bookId, long volumeId, long chapterId, String content) {
        mNovelPage.setContent(bookId, volumeId, chapterId, content);
    }

    /**
     * 设置目录
     *
     * @param catalogueEntity 目录
     */
    public void setCatalogue(NovelCatalogueEntity catalogueEntity) {
        mNovelPage.setCatalogue(catalogueEntity);
    }

    public void loadLastRead(long bookId) {
        mNovelPage.loadLastRead(bookId);
    }

    public void skipToTargetChapter(long novelId, long volumeId, long chapterId) {
        mNovelPage.skipToTargetChapter(novelId, volumeId, chapterId);
    }

    public void setPageStateListener(NovelPage.OnPageStateChangedListener listener) {
        mNovelPage.setPageStateListener(listener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        mControlViewList.clear();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof BaseControlView) {
                mControlViewList.add((BaseControlView) view);
            }
        }

        //在这里重新将所有的View移动到顶部，不影响上面那个遍历
        for(BaseControlView view:mControlViewList){
            ((View)view).bringToFront();
        }
    }

    public void setLoadingError() {
        mLoadingProgressBar.setVisibility(View.GONE);

        if (mErrorView != null) {
            mErrorView.setVisibility(View.VISIBLE);
        }

        mLoadingProgressBar.setVisibility(View.GONE);

    }

    public void setErrorView(View errorView) {

        if (errorView == null) {
            return;
        }

        this.mErrorView = errorView;
        RelativeLayout.LayoutParams loadingParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        loadingParams.addRule(CENTER_IN_PARENT);

        mErrorView.setLayoutParams(loadingParams);

        NovelView.this.addView(mErrorView);
    }
}
