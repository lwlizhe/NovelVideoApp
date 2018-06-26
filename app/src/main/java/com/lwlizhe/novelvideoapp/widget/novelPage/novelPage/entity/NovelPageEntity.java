package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class NovelPageEntity {

    private long bookId;

    private long chapterId;

    private long volumeId;

    private String titleName;

    private int currentPagePos;
    private int maxPageCount;

    List<String> lines;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getMaxPageCount() {
        return maxPageCount;
    }

    public void setMaxPageCount(int maxPageCount) {
        this.maxPageCount = maxPageCount;
    }

    public int getCurrentPagePos() {
        return currentPagePos;
    }

    public void setCurrentPagePos(int currentPagePos) {
        this.currentPagePos = currentPagePos;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
