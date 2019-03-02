package com.lwlizhe.video.api.entity;

import java.util.List;

/**
 * Created by lwlizhe on 2019/2/14.
 */

public class DilidiliVideoResourceResponseEntity extends DilidiliBaseData{


    /**
     * errorCode : 0
     * message :
     * version : 20190214
     * data : {"ip":"","referer":"","userAgent":"","container":"","cover":"","playUrl":["https://cdn-4.haku99.com/hls/2019/02/08/jwosK1Gi/playlist.m3u8 \""]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ip :
         * referer :
         * userAgent :
         * container :
         * cover :
         * playUrl : ["https://cdn-4.haku99.com/hls/2019/02/08/jwosK1Gi/playlist.m3u8 \""]
         */

        private String ip;
        private String referer;
        private String userAgent;
        private String container;
        private String cover;
        private List<String> playUrl;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getReferer() {
            return referer;
        }

        public void setReferer(String referer) {
            this.referer = referer;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getContainer() {
            return container;
        }

        public void setContainer(String container) {
            this.container = container;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public List<String> getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(List<String> playUrl) {
            this.playUrl = playUrl;
        }
    }
}
