package com.lwlizhe.novelvideoapp.novel.api.entity;

import java.util.List;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelFilterEntity {


    /**
     * title : 题材
     * items : [{"tag_id":0,"tag_name":"全部"},{"tag_id":2,"tag_name":"恐怖"},{"tag_id":4,"tag_name":"科幻"},{"tag_id":6,"tag_name":"侦探"},{"tag_id":8,"tag_name":"爱情"},{"tag_id":12,"tag_name":"校园"},{"tag_id":14,"tag_name":"神鬼"},{"tag_id":16,"tag_name":"魔法"},{"tag_id":20,"tag_name":"冒险"},{"tag_id":25,"tag_name":"其它"},{"tag_id":40,"tag_name":"搞笑"},{"tag_id":47,"tag_name":"格斗"},{"tag_id":1264,"tag_name":"机战"},{"tag_id":1265,"tag_name":"仙侠"},{"tag_id":1266,"tag_name":"都市"},{"tag_id":1267,"tag_name":"历史"},{"tag_id":1268,"tag_name":"战争"},{"tag_id":1269,"tag_name":"治愈"},{"tag_id":1270,"tag_name":"励志"},{"tag_id":1271,"tag_name":"后宫"},{"tag_id":1272,"tag_name":"百合"},{"tag_id":1273,"tag_name":"耽美"},{"tag_id":1274,"tag_name":"异界"},{"tag_id":1275,"tag_name":"异能"},{"tag_id":1276,"tag_name":"穿越"},{"tag_id":1321,"tag_name":"奇幻"}]
     */

    private String title;
    private List<ItemsBean> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * tag_id : 0
         * tag_name : 全部
         */

        private int tag_id;
        private String tag_name;

        public int getTag_id() {
            return tag_id;
        }

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }
    }
}
