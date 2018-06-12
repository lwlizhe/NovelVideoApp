package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.pageAnimationBitmapLoader;

import android.view.View;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class PageAnimationBitmapLoaderFactory {

    private View mNovelPage;

    public PageAnimationBitmapLoaderFactory(View mNovelPage) {
        this.mNovelPage = mNovelPage;
    }

    public BaseAnimationBitmapLoader createNovelLoader(int type) {
        BaseAnimationBitmapLoader loader = null;

        switch (type) {
            case 0:
                loader = new TurnAnimationPageBitmapLoader(mNovelPage);
                break;
            case 1:
                break;
        }

        return loader;
    }

}
