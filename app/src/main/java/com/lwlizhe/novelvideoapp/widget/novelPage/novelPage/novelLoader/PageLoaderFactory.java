package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader;

import android.net.IpPrefix;

import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;

/**
 * Created by Administrator on 2018/5/15 0015.
 */

public class PageLoaderFactory {

    private NovelPage mTargetPage;

    private IPageLoader mNovelLoader;

    public PageLoaderFactory(NovelPage targetPage,int type) {

        mTargetPage = targetPage;
        mNovelLoader=createNovelLoader(type);

    }

    private IPageLoader createNovelLoader(int type) {
        BaseNovelLoader loader = null;

        switch (type) {
            case 0:
                loader=new NetNovelLoader(mTargetPage);
                break;
            case 1:
                break;
        }

        return loader;
    }

    public IPageLoader getNovelLoader(){
        return mNovelLoader;
    }
}
