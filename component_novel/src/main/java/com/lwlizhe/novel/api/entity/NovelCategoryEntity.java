package com.lwlizhe.novel.api.entity;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelCategoryEntity{


    /**
     * tag_id : 20
     * title : 冒险
     * cover : http://images.dmzj.com/tuijian/xiaoshuo/fenlei/maoxian.jpg
     */

    private int tag_id;
    private String title;
    private String cover;

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
