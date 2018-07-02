package com.lwlizhe.novelvideoapp.widget.novelMenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lwlizhe.basemodule.utils.SystemBarUtils;
import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.BaseControlView;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

import java.util.Arrays;

/**
 * 一个简单的底部菜单栏(这里就做了最简单的显示隐藏，不过你可以加个动画啥的)
 * Created by Administrator on 2018/6/26 0026.
 */

public class SimpleNovelBottomMenu extends RelativeLayout implements BaseControlView {

    private Context mContext;

    private NovelPage mTargetPage;

    private long bookId;
    private long chapterId;
    private long volumeId;
    private int currentPagePos;

    private int maxPageCount;

    private SeekBar seekBar;
    private LinearLayout mMenuLayout;

    private TextView mTvwTextSize;


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

        TextView tvwFontPlus = rootView.findViewById(R.id.read_setting_tv_font_plus);
        TextView tvwFontMinus = rootView.findViewById(R.id.read_setting_tv_font_minus);

        mTvwTextSize = rootView.findViewById(R.id.read_setting_tv_font);

        mMenuLayout = rootView.findViewById(R.id.read_setting_ll_menu);

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
                mMenuLayout.setVisibility(mMenuLayout.getVisibility()==VISIBLE?GONE:VISIBLE);
            }
        });

        tvwNightMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTargetPage.setBgColor(Color.GRAY);
            }
        });

        tvwFontPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTargetPage.setTextSize(UiUtils.sp2px(Integer.parseInt(mTvwTextSize.getText().toString())+1));
            }
        });

        tvwFontMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTargetPage.setTextSize(UiUtils.sp2px(Integer.parseInt(mTvwTextSize.getText().toString())-1));
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

        Drawable[] drawables = {
                getDrawable(R.color.nb_read_bg_1)
                , getDrawable(R.color.nb_read_bg_2)
                , getDrawable(R.color.nb_read_bg_3)
                , getDrawable(R.color.nb_read_bg_4)
                , getDrawable(R.color.nb_read_bg_5)};


//        mAdapter = new SimplePageBgAdapter(Arrays.asList(drawables));
//
//        RecyclerView catalog = findViewById(R.id.rv_catalog);
//        catalog.setLayoutManager(new LinearLayoutManager(getContext()));
//        catalog.setAdapter(mAdapter);

    }


    private Drawable getDrawable(int drawRes) {
        return ContextCompat.getDrawable(getContext(), drawRes);
    }

    @Override
    public void onOpen(NovelPage targetPage) {
        this.setVisibility(View.VISIBLE);
        mMenuLayout.setVisibility(GONE);
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
        int textSize  = (int) UiUtils.px2sp(pageInfo.getPageTextSize());
        mTvwTextSize.setText(String.valueOf(textSize));
    }

}
