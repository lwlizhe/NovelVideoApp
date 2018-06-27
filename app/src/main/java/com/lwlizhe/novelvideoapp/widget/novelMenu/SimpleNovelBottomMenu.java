package com.lwlizhe.novelvideoapp.widget.novelMenu;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lwlizhe.basemodule.utils.SystemBarUtils;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.BaseControlView;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

/**
 * 一个简单的底部菜单栏
 * Created by Administrator on 2018/6/26 0026.
 */

public class SimpleNovelBottomMenu extends FrameLayout implements BaseControlView {

    private Context mContext;

    private NovelPage mTargetPage;

    private long bookId;
    private long chapterId;
    private long volumeId;
    private int currentPagePos;

    private int maxPageCount;

    private SeekBar seekBar;

    public SimpleNovelBottomMenu(Context context) {
        super(context);
        init(context);
    }

    public SimpleNovelBottomMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleNovelBottomMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {
        this.mContext=mContext;
        this.setVisibility(View.GONE);
    }

    public void setContent(View view){
        this.addView(view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View rootView = getRootView();

        TextView tvwPreChapter = rootView.findViewById(R.id.read_tv_pre_chapter);
        TextView tvwNextChapter = rootView.findViewById(R.id.read_tv_next_chapter);


        seekBar = rootView.findViewById(R.id.read_sb_chapter_progress);

        seekBar.setMax(maxPageCount);
        seekBar.setProgress(currentPagePos+1);

        tvwPreChapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTargetPage.skipToPreChapter();
            }
        });

        tvwNextChapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTargetPage.skipToNextChapter();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mTargetPage.skipToTargetPagePos(seekBar.getProgress());
            }
        });

    }

    @Override
    public void onOpen(NovelPage targetPage) {
        this.setVisibility(View.VISIBLE);
        SystemBarUtils.showUnStableStatusBar((Activity) mContext);
//        SystemBarUtils.showUnStableNavBar((Activity) mContext);

        mTargetPage=targetPage;
    }

    @Override
    public void onClose(NovelPage targetPage) {
        this.setVisibility(View.GONE);
        SystemBarUtils.hideStableStatusBar((Activity) mContext);
//        SystemBarUtils.hideStableNavBar((Activity) mContext);

        mTargetPage=targetPage;
    }

    @Override
    public void onPageInfoChanged(NovelPageInfo pageInfo) {

        bookId=pageInfo.getBookId();
        volumeId=pageInfo.getVolumeId();
        chapterId=pageInfo.getChapterId();
        currentPagePos=pageInfo.getCurPagePos();

        maxPageCount=pageInfo.getMaxPageCount();

        if(seekBar!=null){
            seekBar.setMax(maxPageCount);
            seekBar.setProgress(currentPagePos+1);
        }
    }

}
