package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public class NovelPageInfo {

    private long bookId;
    private long volumeId;
    private long chapterId;

    private int pageTextSize;

    private int curPagePos;
    private int maxPageCount;

    private int pageState;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public int getPageTextSize() {
        return pageTextSize;
    }

    public void setPageTextSize(int pageTextSize) {
        this.pageTextSize = pageTextSize;
    }

    public int getCurPagePos() {
        return curPagePos;
    }

    public void setCurPagePos(int curPagePos) {
        this.curPagePos = curPagePos;
    }

    public int getMaxPageCount() {
        return maxPageCount;
    }

    public void setMaxPageCount(int maxPageCount) {
        this.maxPageCount = maxPageCount;
    }

    public int getPageState() {
        return pageState;
    }

    public void setPageState(int pageState) {
        this.pageState = pageState;
    }
}
