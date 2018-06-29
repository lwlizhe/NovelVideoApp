package com.lwlizhe.novelvideoapp.widget.novelMenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lwlizhe.basemodule.utils.SystemBarUtils;
import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.BaseControlView;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

/**
 * 一个简单的底部菜单栏(这里就做了最简单的显示隐藏，不过你可以加个动画啥的)
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
        this.mContext = mContext;

    }

    public void setContent(View view) {
        this.addView(view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View rootView = getRootView();

        TextView tvwPreChapter = rootView.findViewById(R.id.read_tv_pre_chapter);
        TextView tvwNextChapter = rootView.findViewById(R.id.read_tv_next_chapter);
        TextView tvwNextSetting = rootView.findViewById(R.id.read_tv_setting);
        TextView tvwNightMode = rootView.findViewById(R.id.read_tv_night_mode);


        seekBar = rootView.findViewById(R.id.read_sb_chapter_progress);

        seekBar.setMax(maxPageCount);
        seekBar.setProgress(currentPagePos + 1);

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

        tvwNextSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mTargetPage.setTextSize(UiUtils.sp2px(30));
            }
        });

        tvwNightMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTargetPage.setBgColor(Color.GRAY);
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

        mTargetPage = targetPage;
    }

    @Override
    public void onClose(NovelPage targetPage) {
        this.setVisibility(View.GONE);
        SystemBarUtils.hideStableStatusBar((Activity) mContext);
//        SystemBarUtils.hideStableNavBar((Activity) mContext);

        mTargetPage = targetPage;
    }

    @Override
    public void onPageInfoChanged(NovelPageInfo pageInfo) {

        bookId = pageInfo.getBookId();
        volumeId = pageInfo.getVolumeId();
        chapterId = pageInfo.getChapterId();
        currentPagePos = pageInfo.getCurPagePos();

        maxPageCount = pageInfo.getMaxPageCount();

        if (seekBar != null) {
            seekBar.setMax(maxPageCount);
            seekBar.setProgress(currentPagePos + 1);
        }
    }

}
