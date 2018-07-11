package com.lwlizhe.common.api.comic.entity;

import java.util.List;

public class ComicRecommendResponse {

    /**
     * category_id : 46
     * title : 大图推荐
     * sort : 1
     * data : [{"cover":"http://images.dmzj.com/tuijian/750_480/170103wudongqingchuntj2.jpg","title":"舞动青春·「广播体操」动画化","sub_title":"舞动青春·「广播体操」动画化","type":1,"url":" ","obj_id":11138,"type":"连载中"},{"cover":"http://images.dmzj.com/tuijian/750_480/170105xinwentj2.jpg","title":"充值不如靠BUG？阴阳师\u201c大新闻\u201d让人心寒","sub_title":"充值不如靠BUG？阴阳师\u201c大新闻\u201d让人心寒","type":7,"url":"http://v2.api.dmzj.com/article/show/v2/8467.html","obj_id":8467,"type":""},{"cover":"http://images.dmzj.com/tuijian/750_480/0104heibai01.jpg","title":"黑白无双·新年快乐~！","sub_title":"黑白无双·新年快乐~！","type":1,"url":"","obj_id":16028,"type":"连载中"},{"cover":"http://images.dmzj.com/tuijian/750_480/161230gouliangtj2.jpg","title":"完全没有恋爱感情的青梅竹马·汪！","sub_title":"完全没有恋爱感情的青梅竹马·汪！","type":1,"url":"","obj_id":33322,"type":"连载中"},{"cover":"http://images.dmzj.com/tuijian/750_480/170103shuangxingtj2.jpg","title":"生孩子才是本体的阴阳师漫画·主动入赘","sub_title":"生孩子才是本体的阴阳师漫画·主动入赘","type":1,"url":"","obj_id":13318,"type":"连载中"}]
     */

    private int category_id;
    private String title;
    private int sort;
    private List<DataBean> data;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cover : http://images.dmzj.com/tuijian/750_480/170103wudongqingchuntj2.jpg
         * title : 舞动青春·「广播体操」动画化
         * sub_title : 舞动青春·「广播体操」动画化
         * type : 1
         * url :
         * obj_id : 11138
         * type : 连载中
         */

        private String cover;
        private String title;
        private String sub_title;
        private int type;
        private String url;
        private int obj_id;
        private int id;
        private String status;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getObj_id() {
            return obj_id;
        }

        public void setObj_id(int obj_id) {
            this.obj_id = obj_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}