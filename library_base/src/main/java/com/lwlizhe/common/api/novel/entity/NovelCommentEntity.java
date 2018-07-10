package com.lwlizhe.common.api.novel.entity;

import java.util.List;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelCommentEntity {


    /**
     * id : 245116
     * obj_id : 2337
     * content : 看了插图确认雷尔是男的……还以为是男装女呢……男主有BL嫌疑啊
     * sender_uid : 102231419
     * like_amount : 0
     * create_time : 1497248518
     * to_uid : 0
     * to_comment_id : 0
     * origin_comment_id : 0
     * hot_comment_amount : 0
     * cover : http://images.dmzj.com/user/45/7c/457cab0612c4d32595a3e6265b75a040.png
     * nickname : heheTvT
     * children : [{"id":244971,"obj_id":2337,"content":"爱丽丝插图完爆大小姐，我喜欢爱丽丝","sender_uid":104443578,"like_amount":"0","create_time":1497232213,"to_uid":104443578,"to_comment_id":244963,"origin_comment_id":244963,"hot_comment_amount":0,"cover":"http://images.dmzj.com/user/cd/a1/cda1269d14278448a3ba66aa246effc7.png","nickname":"黑之天空","at_cover":"http://images.dmzj.com/user/cd/a1/cda1269d14278448a3ba66aa246effc7.png","at_nickname":"黑之天空"}]
     */

    private int id;
    private int obj_id;
    private String content;
    private int sender_uid;
    private String like_amount;
    private int create_time;
    private int to_uid;
    private int to_comment_id;
    private int origin_comment_id;
    private int hot_comment_amount;
    private String cover;
    private String nickname;
    private List<ChildrenBean> children;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSender_uid() {
        return sender_uid;
    }

    public void setSender_uid(int sender_uid) {
        this.sender_uid = sender_uid;
    }

    public String getLike_amount() {
        return like_amount;
    }

    public void setLike_amount(String like_amount) {
        this.like_amount = like_amount;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(int to_uid) {
        this.to_uid = to_uid;
    }

    public int getTo_comment_id() {
        return to_comment_id;
    }

    public void setTo_comment_id(int to_comment_id) {
        this.to_comment_id = to_comment_id;
    }

    public int getOrigin_comment_id() {
        return origin_comment_id;
    }

    public void setOrigin_comment_id(int origin_comment_id) {
        this.origin_comment_id = origin_comment_id;
    }

    public int getHot_comment_amount() {
        return hot_comment_amount;
    }

    public void setHot_comment_amount(int hot_comment_amount) {
        this.hot_comment_amount = hot_comment_amount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * id : 244971
         * obj_id : 2337
         * content : 爱丽丝插图完爆大小姐，我喜欢爱丽丝
         * sender_uid : 104443578
         * like_amount : 0
         * create_time : 1497232213
         * to_uid : 104443578
         * to_comment_id : 244963
         * origin_comment_id : 244963
         * hot_comment_amount : 0
         * cover : http://images.dmzj.com/user/cd/a1/cda1269d14278448a3ba66aa246effc7.png
         * nickname : 黑之天空
         * at_cover : http://images.dmzj.com/user/cd/a1/cda1269d14278448a3ba66aa246effc7.png
         * at_nickname : 黑之天空
         */

        private int id;
        private int obj_id;
        private String content;
        private int sender_uid;
        private String like_amount;
        private int create_time;
        private int to_uid;
        private int to_comment_id;
        private int origin_comment_id;
        private int hot_comment_amount;
        private String cover;
        private String nickname;
        private String at_cover;
        private String at_nickname;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getSender_uid() {
            return sender_uid;
        }

        public void setSender_uid(int sender_uid) {
            this.sender_uid = sender_uid;
        }

        public String getLike_amount() {
            return like_amount;
        }

        public void setLike_amount(String like_amount) {
            this.like_amount = like_amount;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getTo_uid() {
            return to_uid;
        }

        public void setTo_uid(int to_uid) {
            this.to_uid = to_uid;
        }

        public int getTo_comment_id() {
            return to_comment_id;
        }

        public void setTo_comment_id(int to_comment_id) {
            this.to_comment_id = to_comment_id;
        }

        public int getOrigin_comment_id() {
            return origin_comment_id;
        }

        public void setOrigin_comment_id(int origin_comment_id) {
            this.origin_comment_id = origin_comment_id;
        }

        public int getHot_comment_amount() {
            return hot_comment_amount;
        }

        public void setHot_comment_amount(int hot_comment_amount) {
            this.hot_comment_amount = hot_comment_amount;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAt_cover() {
            return at_cover;
        }

        public void setAt_cover(String at_cover) {
            this.at_cover = at_cover;
        }

        public String getAt_nickname() {
            return at_nickname;
        }

        public void setAt_nickname(String at_nickname) {
            this.at_nickname = at_nickname;
        }
    }
}
