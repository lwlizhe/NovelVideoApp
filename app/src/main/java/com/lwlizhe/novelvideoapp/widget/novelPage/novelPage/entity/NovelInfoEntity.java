package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 小说相关信息
 * Created by Administrator on 2018/6/21 0021.
 */
@Entity
public class NovelInfoEntity {

    @Id
    private Long bookId;

    private long lastReadVolumeId;
    private long lastReadChapterId;

    private int lastReadPageNum;
    @Generated(hash = 1226015390)
    public NovelInfoEntity(Long bookId, long lastReadVolumeId,
            long lastReadChapterId, int lastReadPageNum) {
        this.bookId = bookId;
        this.lastReadVolumeId = lastReadVolumeId;
        this.lastReadChapterId = lastReadChapterId;
        this.lastReadPageNum = lastReadPageNum;
    }
    @Generated(hash = 1999038609)
    public NovelInfoEntity() {
    }
    public Long getBookId() {
        return this.bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public long getLastReadVolumeId() {
        return this.lastReadVolumeId;
    }
    public void setLastReadVolumeId(long lastReadVolumeId) {
        this.lastReadVolumeId = lastReadVolumeId;
    }
    public long getLastReadChapterId() {
        return this.lastReadChapterId;
    }
    public void setLastReadChapterId(long lastReadChapterId) {
        this.lastReadChapterId = lastReadChapterId;
    }
    public int getLastReadPageNum() {
        return this.lastReadPageNum;
    }
    public void setLastReadPageNum(int lastReadPageNum) {
        this.lastReadPageNum = lastReadPageNum;
    }


}
