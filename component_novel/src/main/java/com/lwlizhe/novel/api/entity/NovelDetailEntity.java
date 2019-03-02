package com.lwlizhe.novel.api.entity;

import java.util.List;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelDetailEntity {


    /**
     * id : 1368
     * name : 穿越龙王与步向灭亡的魔女之国
     * zone : 日本
     * status : 连载中
     * last_update_volume_name : 第九卷
     * last_update_chapter_name : 舞阪洸跨书系企划
     * last_update_volume_id : 8599
     * last_update_chapter_id : 73764
     * last_update_time : 1491754145
     * cover : http://xs.dmzj.com/img/webpic/16/cylwybxmwdmnzg68634l.jpg
     * hot_hits : 582955
     * introduction : 穿越时空的龙王与迈向灭亡的魔女之国。这里是战火纷飞的世界，魔女被人类驱赶、疏远、消灭的世界。一天，那个男人降临了。“黑森林的魔女”ハリガン的面前——从浴室的天花板传。「你，你，你，你这家伙是！？」「胸部啊，而且好大！」——在只有着等待灭亡的魔女的王国中，突然现身的异界男性——「现在这里，随意揉人的胸部啊啊!」「巨乳巨乳巨乳！」——丧失了记忆，持有“龙王”之名的，奇异打扮的男子——「男，男，男人对姐，姐，姐姐的胸部！？」「冷静点，ユウキ！」——于是，“战才”与“魔法”的邂逅，命运的齿轮开始逆转！光辉起舞的战乱无双幻想，在此开幕！
     * types : ["魔法/冒险/后宫/异界/穿越"]
     * authors : 舞阪洸
     * subscribe_num : 12678
     * volume : [{"id":4986,"lnovel_id":1368,"volume_name":"第一卷","volume_order":10,"addtime":1358233145,"sum_chapters":10},{"id":5077,"lnovel_id":1368,"volume_name":"第二卷","volume_order":20,"addtime":1361173404,"sum_chapters":10},{"id":6359,"lnovel_id":1368,"volume_name":"第三卷","volume_order":30,"addtime":1411648046,"sum_chapters":10},{"id":7114,"lnovel_id":1368,"volume_name":"第四卷","volume_order":40,"addtime":1439209050,"sum_chapters":10},{"id":7115,"lnovel_id":1368,"volume_name":"第五卷","volume_order":50,"addtime":1439209108,"sum_chapters":10},{"id":7575,"lnovel_id":1368,"volume_name":"第六卷","volume_order":60,"addtime":1456134298,"sum_chapters":10},{"id":7987,"lnovel_id":1368,"volume_name":"第七卷","volume_order":70,"addtime":1468913980,"sum_chapters":10},{"id":8127,"lnovel_id":1368,"volume_name":"第八卷","volume_order":80,"addtime":1473341410,"sum_chapters":10},{"id":8599,"lnovel_id":1368,"volume_name":"第九卷","volume_order":90,"addtime":1491753497,"sum_chapters":10}]
     */

    private int id;
    private String name;
    private String zone;
    private String status;
    private String last_update_volume_name;
    private String last_update_chapter_name;
    private int last_update_volume_id;
    private int last_update_chapter_id;
    private int last_update_time;
    private String cover;
    private int hot_hits;
    private String introduction;
    private String authors;
    private int subscribe_num;
    private List<String> types;
    private List<VolumeBean> volume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getLast_update_volume_id() {
        return last_update_volume_id;
    }

    public void setLast_update_volume_id(int last_update_volume_id) {
        this.last_update_volume_id = last_update_volume_id;
    }

    public int getLast_update_chapter_id() {
        return last_update_chapter_id;
    }

    public void setLast_update_chapter_id(int last_update_chapter_id) {
        this.last_update_chapter_id = last_update_chapter_id;
    }

    public int getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(int last_update_time) {
        this.last_update_time = last_update_time;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getHot_hits() {
        return hot_hits;
    }

    public void setHot_hits(int hot_hits) {
        this.hot_hits = hot_hits;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getSubscribe_num() {
        return subscribe_num;
    }

    public void setSubscribe_num(int subscribe_num) {
        this.subscribe_num = subscribe_num;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<VolumeBean> getVolume() {
        return volume;
    }

    public void setVolume(List<VolumeBean> volume) {
        this.volume = volume;
    }


    public static class VolumeBean{
        /**
         * id : 4986
         * lnovel_id : 1368
         * volume_name : 第一卷
         * volume_order : 10
         * addtime : 1358233145
         * sum_chapters : 10
         */

        private int id;
        private int lnovel_id;
        private String volume_name;
        private int volume_order;
        private int addtime;
        private int sum_chapters;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLnovel_id() {
            return lnovel_id;
        }

        public void setLnovel_id(int lnovel_id) {
            this.lnovel_id = lnovel_id;
        }

        public String getVolume_name() {
            return volume_name;
        }

        public void setVolume_name(String volume_name) {
            this.volume_name = volume_name;
        }

        public int getVolume_order() {
            return volume_order;
        }

        public void setVolume_order(int volume_order) {
            this.volume_order = volume_order;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public int getSum_chapters() {
            return sum_chapters;
        }

        public void setSum_chapters(int sum_chapters) {
            this.sum_chapters = sum_chapters;
        }

    }
}
