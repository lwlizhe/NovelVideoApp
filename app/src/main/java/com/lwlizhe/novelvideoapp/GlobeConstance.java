package com.lwlizhe.novelvideoapp;

import com.lwlizhe.basemodule.utils.FileUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/4/10.
 */

public class GlobeConstance {

    /***********************************************************************************************
     * 常量部分
     **********************************************************************************************/
    public static String BOOK_CACHE_PATH = FileUtils.getCachePath()+ File.separator
            + "book_cache"+ File.separator ;

    /***********************************************************************************************
     * 小说部分
     **********************************************************************************************/
    public static final String DMZJ_IMG_REFERER_URL="http://images.dmzj.com/";

    public static final String BILIBILI_BASE_URL="http://app.bilibili.com/";

    public static final String NOVEL_BASE_URL="http://v2api.dmzj.com/novel/";

    public static final String NOVEL_COMMEND_DYNAMIC_KEY="novel_commend_dynamic_key";


    /***********************************************************************************************
     * Bilibili部分
     **********************************************************************************************/

    public static final String APP_KEY="f3bb208b3d081dc8";
    public static final String APP_SECRET="1c15888dc316e05a15fdd0a02ed6584f";

    public static final String BILIBILI_COMMEND_DYANMIC_KEY="bilibili_commend_dyanmic_key";

    public static final String BILI_GO_BASE_URL = "http://bilibili-service.daoapp.io/";
    public static final String RANK_BASE_URL = "http://www.bilibili.com/";
    public static final String APP_BASE_URL = "http://app.bilibili.com/";
    public static final String APP_PLAY_BASE_URL = "https://interface.bilibili.com/";
    public static final String LIVE_BASE_URL = "http://live.bilibili.com/";
    public static final String API_BASE_URL = "http://api.bilibili.cn/";
    public static final String BANGUMI_BASE_URL = "http://bangumi.bilibili.com/";
    public static final String SEARCH_BASE_URL = "http://s.search.bilibili.com/";
    public static final String ACCOUNT_BASE_URL = "https://account.bilibili.com/";
    public static final String USER_BASE_URL = "http://space.bilibili.com/";
    public static final String VIP_BASE_URL = "http://vip.bilibili.com/";

    public static final String SHOP_URL = "http://bmall.bilibili.com/";
    public static final String VIP_URL = "http://vip.bilibili.com/site/vip-faq-h5.html#yv1";
    //用户投稿
    public static final String USER_CONTRIBUTE = "0";
    //用户收藏夹
    public static final String USER_FAVORITES = "1";
    //用户追番
    public static final String USER_CHASE_BANGUMI = "2";
    //用户兴趣圈
    public static final String USER_INTEREST_QUAN = "3";
    //用户投币
    public static final String USER_COINS = "4";
    //用户游戏
    public static final String USER_PLAY_GAME = "5";
    //用户直播状态
    public static final String USER_LIVE_STATUS = "6";
    //用户mid
    public static final String EXTRA_MID = "extra_mid";
    //用户详情界面传递数据
    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";
    public static final String KEY = "login";
    public final static String EXTRA_BANGUMI_KEY = "extra_season_id";
    public static final String SUNDAY_TYPE = "周日";
    public static final String MONDAY_TYPE = "周一";
    public static final String TUESDAY_TYPE = "周二";
    public static final String WEDNESDAY_TYPE = "周三";
    public static final String THURSDAY_TYPE = "周四";
    public static final String FRIDAY_TYEP = "周五";
    public static final String SATURDAY_TYPE = "周六";
    public static final String EXTRA_SPID = "spid";
    public static final String EXTRA_SEASON_ID = "season_id";
    public static final String EXTRA_KEY = "extra_type";
    public static final String EXTRA_ORDER = "extra_order";
    public static final String EXTRA_CID = "cid";
    public static final String EXTRA_ONLINE = "online";
    public static final String EXTRA_FACE = "face";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_PARTITION = "extra_partition";
    public static final String TYPE_TOPIC = "weblink";
    public static final String TYPE_ACTIVITY_CENTER = "activity";
    public static final String STYLE_PIC = "gl_pic";
    public static final String EXTRA_CONTENT = "extra_content";
    public static final String AID = "aid";
    public static String EXTRA_AV = "extra_av";
    public static String EXTRA_IMG_URL = "extra_img_url";
    public static final String EXTRA_INFO = "extra_info";
    public static final String VIDEO_TYPE_MP4 = "mp4";
    public static final String SWITCH_MODE_KEY = "mode_key";
    public static final String EXTRA_RID = "extra_rid";
    public static final String EXTRA_POSITION = "extra_pos";
    public static final int ADVERTISING_RID = 165;

}
