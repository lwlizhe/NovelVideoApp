package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.manager;

import android.content.Context;

import com.lwlizhe.novelvideoapp.AppApplication;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.DaoSession;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelContentEntityDao;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelInfoEntity;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity.NovelInfoEntityDao;

/**
 * 小说存储
 * Created by Administrator on 2018/6/22 0022.
 */

public class NovelRepositoryManager {

    private Context mContext;

    private NovelContentEntityDao mNovelDao;
    private NovelInfoEntityDao mInfoDao;

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

        this.mContext=mContext;

        DaoSession mDataDaoSession = ((AppApplication) (mContext.getApplicationContext())).getDaoSession();

        mNovelDao = mDataDaoSession.getNovelContentEntityDao();
        mInfoDao = mDataDaoSession.getNovelInfoEntityDao();

    }

    public void saveChapter(NovelContentEntity entity){

        mNovelDao.insertOrReplace(entity);

    }

    public void updateReadInfo(NovelInfoEntity info){
        mInfoDao.insertOrReplace(info);
    }

    public NovelInfoEntity getReadInfo(long bookId){
        return mInfoDao.load(bookId);
    }

    public NovelContentEntity getChapter(long bookId, long volumeId, long chapterId){

        NovelContentEntity result=null;

        result=mNovelDao.queryBuilder().where(NovelContentEntityDao.Properties.BookId.eq(bookId), NovelContentEntityDao.Properties.VolumeId.eq(volumeId), NovelContentEntityDao.Properties.ChapterId.eq(chapterId)).unique();

        return result;

    }
}
