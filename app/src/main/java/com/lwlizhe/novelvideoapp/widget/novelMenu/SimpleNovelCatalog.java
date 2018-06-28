package com.lwlizhe.novelvideoapp.widget.novelMenu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView.BaseCatalogView;
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
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void onPageInfoChanged(NovelPageInfo pageInfo) {

    }
}
