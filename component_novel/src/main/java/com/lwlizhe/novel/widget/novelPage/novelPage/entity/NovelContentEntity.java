package com.lwlizhe.novel.widget.novelPage.novelPage.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 小说内容页面
 * Created by Administrator on 2018/6/20 0020.
 */
@Entity(
        indexes = {
                @Index(value = "bookId DESC, volumeId DESC,chapterId DESC", unique = true)
        }
)
public class NovelContentEntity {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private Long bookId;
    @NotNull
    private Long volumeId;
    @NotNull
    private Long chapterId;
    @NotNull
    private String novelContent;

    public NovelContentEntity(@NotNull Long bookId, @NotNull Long volumeId,
                              @NotNull Long chapterId, @NotNull String novelContent) {
        this.bookId = bookId;
        this.volumeId = volumeId;
        this.chapterId = chapterId;
        this.novelContent = novelContent;
    }



    @Generated(hash = 639476078)
    public NovelContentEntity(Long id, @NotNull Long bookId, @NotNull Long volumeId,
            @NotNull Long chapterId, @NotNull String novelContent) {
        this.id = id;
        this.bookId = bookId;
        this.volumeId = volumeId;
        this.chapterId = chapterId;
        this.novelContent = novelContent;
    }

    @Generated(hash = 1920503138)
    public NovelContentEntity() {
    }

    public Long getBookId() {
        return this.bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getVolumeId() {
        return this.volumeId;
    }

    public void setVolumeId(Long volumeId) {
        this.volumeId = volumeId;
    }

    public Long getChapterId() {
        return this.chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getNovelContent() {
        return this.novelContent;
    }

    public void setNovelContent(String novelContent) {
        this.novelContent = novelContent;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
