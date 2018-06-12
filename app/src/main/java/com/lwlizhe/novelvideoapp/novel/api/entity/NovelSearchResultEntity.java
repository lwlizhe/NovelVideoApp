package com.lwlizhe.novelvideoapp.novel.api.entity;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelSearchResultEntity {


    /**
     * id : 35221
     * status : 已完结
     * title : 为这美好世界献上祝福
     * last_name : 短篇
     * cover : http://images.dmzj.com/webpic/0/weizhemeihaodeshijiexianshangzhufuduanpian.jpg
     * authors : 安部真弘
     * types : 欢乐向
     * hot_hits : 1
     */

    private int id;
    private String status;
    private String title;
    private String last_name;
    private String cover;
    private String authors;
    private String types;
    private int hot_hits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getHot_hits() {
        return hot_hits;
    }

    public void setHot_hits(int hot_hits) {
        this.hot_hits = hot_hits;
    }
}
