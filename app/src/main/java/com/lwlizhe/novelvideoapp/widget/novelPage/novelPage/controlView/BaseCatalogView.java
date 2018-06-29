package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelCatalogueEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public interface BaseCatalogView {

    void onOpen(NovelCatalogueEntity catalogueEntity);

    void onClose();

    void onPageInfoChanged(NovelPageInfo pageInfo);
}
