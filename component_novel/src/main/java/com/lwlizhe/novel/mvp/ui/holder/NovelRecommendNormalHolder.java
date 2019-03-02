package com.lwlizhe.novel.mvp.ui.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageConfig;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;
import com.lwlizhe.basemodule.utils.UiUtils;
import com.lwlizhe.AppApplication;
import com.lwlizhe.GlobeConstance;
import com.lwlizhe.library.novel.R;
import com.lwlizhe.novel.api.entity.NovelReCommendEntity;
import com.lwlizhe.common.widget.AutoMultiImageView;


import java.util.List;


/**
 * Created by lwlizhe on 2017/6/2.
 * 邮箱：624456662@qq.com
 */

public class NovelRecommendNormalHolder extends BaseHolder<NovelReCommendEntity> {

    public TextView mTitleName;
    public AutoMultiImageView mAutoImageView;

    private GlideImageLoaderStrategy mGlideImageLoader;

//    private ImageLoader mGlideImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
//    private final MainApplication mApplication;

    private LayoutInflater inflater;

    private NormalItemImageClickListener mItemImageClickListener;

    public NovelRecommendNormalHolder(View itemView) {
        super(itemView);
//        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
//        mApplication = (MainApplication) itemView.getContext().getApplicationContext();
//        mGlideImageLoader = mApplication.getAppComponent().imageLoader();

        inflater= LayoutInflater.from(itemView.getContext());
        mTitleName=itemView.findViewById(R.id.tvw_title_name);

        mAutoImageView=itemView.findViewById(R.id.ivw_auto_multi);

        mGlideImageLoader=((AppApplication)(itemView.getContext().getApplicationContext())).getAppComponent().mGlideImageLoader();
    }

    @Override
    public void setData(final NovelReCommendEntity data, int position) {

        mTitleName.setText(data.getTitle());

        final List<NovelReCommendEntity.DataBean> databeans = data.getData();

//        mGlideImageLoader.loadImage(mContext, GlideImageConfig
//                .builder()
//                .url(data.getData().get(0).getCover())
//                .imageView(mAtuoImageView)
//                .refererUrl(GlobeConstance.DMZJ_IMG_REFERER_URL)
//                .build()
//        );

        mAutoImageView.setRatio(1.5f);
        mAutoImageView.setItemMargin(UiUtils.dp2px(5));
        mAutoImageView.setOnItemClickListener(new AutoMultiImageView.OnItemClickListener<NovelReCommendEntity.DataBean>() {
            @Override
            public void onItemClick(NovelReCommendEntity.DataBean itemData, View view, int position) {
                mItemImageClickListener.onItemImageClickListener(itemData,view,position);            }
        });

        mAutoImageView.setRecommendCustomLayoutInterface(new AutoMultiImageView.RecommendCustomLayoutInterface<View,NovelReCommendEntity.DataBean>() {
            @Override
            public void setLayoutDisplay(NovelReCommendEntity.DataBean itemDataBean, View itemView, int position) {

                mGlideImageLoader.loadImage(mContext, GlideImageConfig
                        .builder()
                        .url(itemDataBean.getCover())
                        .imageView((ImageView) itemView.findViewById(R.id.ivw_item_cover))
                        .refererUrl(GlobeConstance.DMZJ_IMG_REFERER_URL)
                        .build());

                ((TextView)itemView.findViewById(R.id.tvw_item_name)).setText(itemDataBean.getTitle());
            }

            @Override
            public View createCustomView() {
                return inflater.inflate(R.layout.layout_novel_recommend_normal_multiimage,null);
            }


            @Override
            public List<NovelReCommendEntity.DataBean> setDatasList() {
                return databeans;
            }
        });


    }


    public void setOnNormalItemImageClickListener(NormalItemImageClickListener itemImageClickListener){

        this.mItemImageClickListener=itemImageClickListener;

    }

    public interface NormalItemImageClickListener{
        void onItemImageClickListener(NovelReCommendEntity.DataBean itemData, View view, int position);
    }
}
