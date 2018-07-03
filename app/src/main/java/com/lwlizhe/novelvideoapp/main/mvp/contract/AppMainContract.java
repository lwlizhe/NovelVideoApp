package com.lwlizhe.novelvideoapp.main.mvp.contract;

import android.support.v7.app.AppCompatActivity;

import com.lwlizhe.basemodule.base.adapter.BaseFragmentPagerAdapter;
import com.lwlizhe.basemodule.mvp.BaseView;
import com.lwlizhe.basemodule.mvp.IModel;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public interface AppMainContract {

    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {

        void setFragmentPagerAdapter(BaseFragmentPagerAdapter adapter);
        void setCurrentViewPager(int index);
        AppCompatActivity getContext();

    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
//        Flowable<List<NovelReCommendEntity>> getNovelCommend(boolean upData);
//        Flowable<List<NovelSearchResultEntity>> getNovelSearchResult(long bigCatId, String keyWords, long pages);

    }

}
