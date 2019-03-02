package com.lwlizhe.novel.api.entity;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelListEntity {


    /**
     * cover : http://xs.dmzj.com/img/webpic/18/mzxyhxh5l.jpg
     * name : 魔装学园H×H
     * authors : 久慈マサムネ
     * id : 1692
     */

    private String cover;
    private String name;
    private String authors;
    private int id;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
