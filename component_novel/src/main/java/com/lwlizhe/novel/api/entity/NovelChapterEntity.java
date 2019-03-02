package com.lwlizhe.novel.api.entity;

import com.lwlizhe.basemodule.base.adapter.ExpandChildItemEntity;
import com.lwlizhe.basemodule.base.adapter.ExpandParentItemEntity;

import java.util.List;

/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelChapterEntity extends ExpandParentItemEntity<List<NovelChapterEntity.ChaptersBean>> {

    /**
     * volume_id : 4986
     * volume_name : 第一卷
     * volume_order : 10
     * chapters : [{"chapter_id":32908,"chapter_name":"序幕 其之一","chapter_order":10},{"chapter_id":32909,"chapter_name":"序幕 其之二","chapter_order":20},{"chapter_id":32910,"chapter_name":"第一章 掉到浴室的男子","chapter_order":30},{"chapter_id":32911,"chapter_name":"第二章 魔女和人类的战斗","chapter_order":40},{"chapter_id":32912,"chapter_name":"第三章 第一城砦\u2027再次攻防战","chapter_order":50},{"chapter_id":32915,"chapter_name":"终章","chapter_order":60},{"chapter_id":32916,"chapter_name":"后记","chapter_order":70},{"chapter_id":66881,"chapter_name":"插画","chapter_order":80}]
     */

    private int volume_id;
    private String volume_name;
    private int volume_order;
    private List<ChaptersBean> chapters;

    public int getVolume_id() {
        return volume_id;
    }

    public void setVolume_id(int volume_id) {
        this.volume_id = volume_id;
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

    public List<ChaptersBean> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChaptersBean> chapters) {
        this.chapters = chapters;
    }

    @Override
    public int getType() {
        return PARENT_ITEM;
    }

    @Override
    public List<ChaptersBean> getChildData() {
        return chapters;
    }

    public static class ChaptersBean extends ExpandChildItemEntity<Object,NovelChapterEntity> {
        /**
         * chapter_id : 32908
         * chapter_name : 序幕 其之一
         * chapter_order : 10
         */

        private int chapter_id;
        private String chapter_name;
        private int chapter_order;


        public int getChapter_id() {
            return chapter_id;
        }

        public void setChapter_id(int chapter_id) {
            this.chapter_id = chapter_id;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public int getChapter_order() {
            return chapter_order;
        }

        public void setChapter_order(int chapter_order) {
            this.chapter_order = chapter_order;
        }

        @Override
        public int getType() {
            return CHILD_ITEM;
        }

        @Override
        public Object getChildData() {
            return null;
        }
    }
}
