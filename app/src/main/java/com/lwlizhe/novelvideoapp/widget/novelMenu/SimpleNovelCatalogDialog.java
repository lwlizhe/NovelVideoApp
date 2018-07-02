package com.lwlizhe.novelvideoapp.widget.novelMenu;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.lwlizhe.basemodule.base.adapter.BaseExpandItemEntity;
import com.lwlizhe.novelvideoapp.novel.api.entity.NovelChapterEntity;
import com.lwlizhe.novelvideoapp.novel.mvp.ui.adapter.NovelDetailChapterAdapter;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class SimpleNovelCatalogDialog extends Dialog{

    public SimpleNovelCatalogDialog(@NonNull Context context) {
        super(context);
    }

    public SimpleNovelCatalogDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
}
