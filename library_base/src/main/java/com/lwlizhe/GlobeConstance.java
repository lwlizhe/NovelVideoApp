package com.lwlizhe;

import com.lwlizhe.basemodule.utils.FileUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/4/10.
 */

public class GlobeConstance {

    public static class CONSTANCE_URL {
        /***********************************************************************************************
         * 常量部分
         **********************************************************************************************/
        public static String BOOK_CACHE_PATH = FileUtils.getCachePath() + File.separator
                + "book_cache" + File.separator;

        /***********************************************************************************************
         * 小说部分
         **********************************************************************************************/
        public static final String DMZJ_IMG_REFERER_URL = "http://images.dmzj.com/";

        public static final String BILIBILI_BASE_URL = "http://app.bilibili.com/";

        public static final String DILIDILI_BASE_URL = "http://app.dilidili.club/";

        public static final String NOVEL_BASE_URL = "https://v3api.dmzj.com/novel/";
        public static final String COMIC_BASE_URL = "https://v3api.dmzj.com/";

        public static final String NOVEL_COMMEND_DYNAMIC_KEY = "novel_commend_dynamic_key";


        /***********************************************************************************************
         * Dilidili部分
         **********************************************************************************************/

        public static final String DILIDILI_URL = "http://m.dilidili.wang";
    }

    public static class CONSTANCE_ROUTER {

        public static final String ROUTER_NOVEL_RECOMMEND = "/novel/recommend";
        public static final String ROUTER_NOVEL_READ = "/novel/read";

        public static final String ROUTER_VIDEO_MAIN_PAGE = "/video/mainPage";

        public static final String ROUTER_COMIC_MAIN_PAGE = "/comic/mainPage";

    }

}
