package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.novelLoader.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class NovelPageEntity {

    private long chapterId;

    private long volumeId;

    List<String> lines;

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
