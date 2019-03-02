package com.lwlizhe.novel.api.entity;

import java.util.List;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelReCommendEntity {

    /**
     * category_id : 57
     * sort : 0
     * title : 轮番图
     * data : [{"id":2278,"obj_id":239,"title":"[完结]零之使魔","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/181.jpg","url":"","type":2,"sub_title":"","status":"已完结"},{"id":2279,"obj_id":1840,"title":"不正经的魔术讲师与禁忌教典 第8卷","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/182.jpg","url":"","type":2,"sub_title":"","status":"连载中"},{"id":2280,"obj_id":2304,"title":"狼与羊皮纸：狼与辛香料新说","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/184.jpg","url":"","type":2,"sub_title":"","status":"连载中"},{"id":2281,"obj_id":2301,"title":"86- Eighty Six -","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/185.jpg","url":"","type":2,"sub_title":"","status":"连载中"},{"id":2206,"obj_id":1236,"title":"[完结]魔王少女与村民A 第11卷","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/180.jpg","url":"","type":2,"sub_title":"","status":"已完结"}]
     */

    private int category_id;
    private int sort;
    private String title;
    private List<DataBean> data;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2278
         * obj_id : 239
         * title : [完结]零之使魔
         * cover : http://images.dmzj.com/tuijian/xiaoshuo/750-480/181.jpg
         * url :
         * type : 2
         * sub_title :
         * status : 已完结
         */

        private int id;
        private int obj_id;
        private String title;
        private String cover;
        private String url;
        private int type;
        private String sub_title;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getObj_id() {
            return obj_id;
        }

        public void setObj_id(int obj_id) {
            this.obj_id = obj_id;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
