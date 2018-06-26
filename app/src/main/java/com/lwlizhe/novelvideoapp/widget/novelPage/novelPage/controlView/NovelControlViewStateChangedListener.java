package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.controlView;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelPageInfo;

/**
 * Created by Administrator on 2018/6/23 0023.
 */

public interface NovelControlViewStateChangedListener {

    void onOpenCatalog(long curBookId,long curVolumeId,long curChapterId);

    void onShowControlView();

    void onHideControlView();

    void onPageStateChanged(NovelPageInfo pageInfo);
}
