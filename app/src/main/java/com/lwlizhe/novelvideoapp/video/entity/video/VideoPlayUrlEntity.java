package com.lwlizhe.novelvideoapp.video.entity.video;

import java.util.List;

/**
 * Created by lwlizhe on 2017/7/17.
 * 邮箱：624456662@qq.com
 */

public class VideoPlayUrlEntity {


    /**
     * from : local
     * result : suee
     * format : hdmp4
     * timelength : 191983
     * accept_format : hdmp4,mp4
     * accept_quality : [2,1]
     * seek_param : start
     * seek_type : second
     * durl : [{"order":1,"length":191983,"size":5164942,"url":"http://tx.acgvideo.com/5/79/16546-1-hd.mp4?txTime=1500269468&platform=pc&txSecret=9e18546a8d0b774a17c2540068e03c58&oi=3074246518&rate=10000","backup_url":["http://cn-hbjz5-dx.acgvideo.com/vg7/d/5c/16546-1.mp4?expires=1500269400&platform=pc&ssig=hgjkJ1_Jn4HcXH3VqvBmvA&oi=3074246518&nfa=iCnd1wqqoAsXLyv+e2tfVA==&dynamic=1&hfa=2069828251","http://tx.acgvideo.com/5/79/16546-1.mp4?txTime=1500269468&platform=pc&txSecret=eba30a8e7c2a75cf1009593a7104cec3&oi=3074246518&rate=10000"]}]
     */

    private String from;
    private String result;
    private String format;
    private int timelength;
    private String accept_format;
    private String seek_param;
    private String seek_type;
    private List<Integer> accept_quality;
    private List<DurlBean> durl;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTimelength() {
        return timelength;
    }

    public void setTimelength(int timelength) {
        this.timelength = timelength;
    }

    public String getAccept_format() {
        return accept_format;
    }

    public void setAccept_format(String accept_format) {
        this.accept_format = accept_format;
    }

    public String getSeek_param() {
        return seek_param;
    }

    public void setSeek_param(String seek_param) {
        this.seek_param = seek_param;
    }

    public String getSeek_type() {
        return seek_type;
    }

    public void setSeek_type(String seek_type) {
        this.seek_type = seek_type;
    }

    public List<Integer> getAccept_quality() {
        return accept_quality;
    }

    public void setAccept_quality(List<Integer> accept_quality) {
        this.accept_quality = accept_quality;
    }

    public List<DurlBean> getDurl() {
        return durl;
    }

    public void setDurl(List<DurlBean> durl) {
        this.durl = durl;
    }

    public static class DurlBean {
        /**
         * order : 1
         * length : 191983
         * size : 5164942
         * url : http://tx.acgvideo.com/5/79/16546-1-hd.mp4?txTime=1500269468&platform=pc&txSecret=9e18546a8d0b774a17c2540068e03c58&oi=3074246518&rate=10000
         * backup_url : ["http://cn-hbjz5-dx.acgvideo.com/vg7/d/5c/16546-1.mp4?expires=1500269400&platform=pc&ssig=hgjkJ1_Jn4HcXH3VqvBmvA&oi=3074246518&nfa=iCnd1wqqoAsXLyv+e2tfVA==&dynamic=1&hfa=2069828251","http://tx.acgvideo.com/5/79/16546-1.mp4?txTime=1500269468&platform=pc&txSecret=eba30a8e7c2a75cf1009593a7104cec3&oi=3074246518&rate=10000"]
         */

        private int order;
        private int length;
        private int size;
        private String url;
        private List<String> backup_url;

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getBackup_url() {
            return backup_url;
        }

        public void setBackup_url(List<String> backup_url) {
            this.backup_url = backup_url;
        }
    }
}
