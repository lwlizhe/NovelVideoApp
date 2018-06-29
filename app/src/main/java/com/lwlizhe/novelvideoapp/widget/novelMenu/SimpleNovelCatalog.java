package com.lwlizhe.novelvideoapp.widget.novelMenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.BaseCatalogView;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class SimpleNovelCatalog extends RelativeLayout implements BaseCatalogView {
    public SimpleNovelCatalog(Context context) {
        super(context);
    }

    public SimpleNovelCatalog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleNovelCatalog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        RecyclerView catalog = findViewById(R.id.rv_catalog);

    }

    @Override
    public void onOpen(NovelCatalogueEntity catalogueEntity) {
        this.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClose() {
        this.setVisibility(View.GONE);
    }

    @Override
    public void onPageInfoChanged(NovelPageInfo pageInfo) {

    }
}
