package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader;

import android.view.MotionEvent;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;

/**
 * Created by Administrator on 2018/5/15 0015.
 */

public class SlidingNovelLoader extends BaseNovelLoader {

    public SlidingNovelLoader(NovelPage targetPageView) {
        super(targetPageView);
    }

    @Override
    public void onTouch(MotionEvent event) {
        super.onTouch(event);

        mBitmapManager.onTouch(event);
        mPageAnimationBitmapLoader.onTouch(event);

    }
}
