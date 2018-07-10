package com.lwlizhe.novel.widget.novelPage.novelPage.controlView;

import com.lwlizhe.novel.widget.novelPage.novelPage.NovelPage;
import com.lwlizhe.novel.widget.novelPage.novelPage.entity.NovelPageInfo;

/**
 * Created by Administrator on 2018/6/26 0026.
 */

public interface BaseControlView {

    void onOpen(NovelPage targetPage);

    void onClose(NovelPage targetPage);

    void onPageInfoChanged(NovelPageInfo pageInfo);
}
