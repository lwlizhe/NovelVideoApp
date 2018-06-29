package com.lwlizhe.novelvideoapp.widget.novelMenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.BaseControlView;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

/**
 * 一个简单的顶部菜单栏(这里就做了最简单的显示隐藏，不过你可以加个动画啥的)
 * Created by Administrator on 2018/6/26 0026.
 */

public class SimpleNovelTopMenu extends FrameLayout implements BaseControlView {
    public SimpleNovelTopMenu(@NonNull Context context) {
        super(context);
        init();
    }

    public SimpleNovelTopMenu(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleNovelTopMenu(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View rootView = getRootView();

        TextView tvwBrief = rootView.findViewById(R.id.read_tv_brief);

        tvwBrief.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "正在开发", Toast.LENGTH_SHORT).show();
            }
        });
        TextView tvwCommunity = rootView.findViewById(R.id.read_tv_community);

        tvwCommunity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "正在开发", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onOpen(NovelPage targetPage) {
        this.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClose(NovelPage targetPage) {
        this.setVisibility(View.GONE);
    }

    @Override
    public void onPageInfoChanged(NovelPageInfo pageInfo) {

    }
}
