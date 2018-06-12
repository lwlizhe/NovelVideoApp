package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.entity;

import com.lwlizhe.novelvideoapp.video.entity.video.VideoCommentInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class NovelContentCatalogueEntity {

    private List<NovelContentVolumeEntity> volumeList;

    public List<NovelContentVolumeEntity> getVolumeList() {
        return volumeList;
    }

    public void setVolumeList(List<NovelContentVolumeEntity> volumeList) {
        this.volumeList = volumeList;
    }

    public static class NovelContentVolumeEntity{

        private long volumeId;
        private String volumeName;

        private List<NovelContentChapterEntity> chapterList;

        public long getVolumeId() {
            return volumeId;
        }

        public void setVolumeId(long volumeId) {
            this.volumeId = volumeId;
        }

        public String getVolumeName() {
            return volumeName;
        }

        public void setVolumeName(String volumeName) {
            this.volumeName = volumeName;
        }

        public List<NovelContentChapterEntity> getChapterList() {
            return chapterList;
        }

        public void setChapterList(List<NovelContentChapterEntity> mChapterList) {
            this.chapterList = mChapterList;
        }
    }

    public static class NovelContentChapterEntity{

        private long chapterId;
        private String chapterName;

        private int currentState;

        public long getChapterId() {
            return chapterId;
        }

        public void setChapterId(long chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCurrentState() {
            return currentState;
        }

        public void setCurrentState(int currentState) {
            this.currentState = currentState;
        }
    }
}
