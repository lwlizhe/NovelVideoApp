package com.lwlizhe.comic.mvp.ui.adapter;

import android.util.Log;
import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.comic.mvp.ui.adapter.holder.ComicMainBannerHolder;
import com.lwlizhe.comic.mvp.ui.adapter.holder.ComicMainDefaultHolder;
import com.lwlizhe.common.api.comic.entity.ComicRecommendResponse;
import com.lwlizhe.library.comic.R;

import java.util.List;

/**
 * Created by Administrator on 2018/7/11 0011.
 */

public class ComicMainAdapter extends BaseRecyclerViewAdapter<ComicRecommendResponse> {


    public ComicMainAdapter(List<ComicRecommendResponse> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ComicRecommendResponse> getHolder(View v, int viewType) {


        BaseHolder<ComicRecommendResponse> result = null;

        switch (viewType) {
            case 1:
                ComicMainBannerHolder holder = new ComicMainBannerHolder(v);
                holder.setBannerClickListener(new ComicMainBannerHolder.OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(View view, ComicRecommendResponse.DataBean itemData, int position) {
                        Log.d("test",itemData.toString());
                    }
                });
                result = holder;
                break;

            default:
                result = new ComicMainDefaultHolder(v);
                break;
        }
        return result;
    }

    @Override
    public int getLayoutId(int viewType) {

        int layoutId = 0;

        switch (viewType) {
            case 1:
                layoutId = R.layout.item_comic_banner;
                break;
            default:
                layoutId = R.layout.item_default_multi_image;
                break;
        }

        return layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        return mInfos.get(position).getSort();
    }
}
