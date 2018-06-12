package com.lwlizhe.novelvideoapp.novel.api.entity;

import java.util.List;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelRecentUpdataEntity {


    /**
     * id : 1692
     * status : 连载中
     * name : 魔装学园H×H
     * authors : 久慈マサムネ
     * cover : http://xs.dmzj.com/img/webpic/18/mzxyhxh5l.jpg
     * types : ["魔法","校园","冒险","后宫"]
     * last_update_chapter_id : 73551
     * last_update_volume_id : 8231
     * last_update_volume_name : 第八卷
     * last_update_chapter_name : 特典 巴比伦计划准备中
     * last_update_time : 1491233367
     */

    private int id;
    private String status;
    private String name;
    private String authors;
    private String cover;
    private int last_update_chapter_id;
    private int last_update_volume_id;
    private String last_update_volume_name;
    private String last_update_chapter_name;
    private int last_update_time;
    private List<String> types;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getLast_update_chapter_id() {
        return last_update_chapter_id;
    }

    public void setLast_update_chapter_id(int last_update_chapter_id) {
        this.last_update_chapter_id = last_update_chapter_id;
    }

    public int getLast_update_volume_id() {
        return last_update_volume_id;
    }

    public void setLast_update_volume_id(int last_update_volume_id) {
        this.last_update_volume_id = last_update_volume_id;
    }

    public String getLast_update_volume_name() {
        return last_update_volume_name;
    }

    public void setLast_update_volume_name(String last_update_volume_name) {
        this.last_update_volume_name = last_update_volume_name;
    }

    public String getLast_update_chapter_name() {
        return last_update_chapter_name;
    }

    public void setLast_update_chapter_name(String last_update_chapter_name) {
        this.last_update_chapter_name = last_update_chapter_name;
    }

    public int getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(int last_update_time) {
        this.last_update_time = last_update_time;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
