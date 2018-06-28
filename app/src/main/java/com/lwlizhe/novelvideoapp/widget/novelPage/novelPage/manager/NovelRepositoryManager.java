package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager;

import android.content.Context;

/**
 * 小说存储
 * Created by Administrator on 2018/6/22 0022.
 */

public class NovelRepositoryManager {

    private static NovelRepositoryManager mInstance;

    public static NovelRepositoryManager instance(Context mContext){

        if(mInstance==null){
            synchronized (NovelRepositoryManager.class){
                if(mInstance==null){
                    mInstance=new NovelRepositoryManager(mContext);
                }
            }
        }


        return mInstance;
    }

    private NovelRepositoryManager(Context mContext) {

    }
}
