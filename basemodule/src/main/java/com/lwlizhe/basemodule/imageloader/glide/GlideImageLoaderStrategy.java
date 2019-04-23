package com.lwlizhe.basemodule.imageloader.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lwlizhe.basemodule.imageloader.BaseImageLoaderStrategy;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by jess on 8/5/16 16:28
 * contact with jess.yan.effort@gmail.com
 */
@Singleton
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<GlideImageConfig> {

    @Inject
    public GlideImageLoaderStrategy() {

    }

    @Override
    public void loadImage(Context ctx, GlideImageConfig config) {
        if (ctx == null) throw new IllegalStateException("Context is required");
        if (config == null) throw new IllegalStateException("GlideImageConfig is required");
        if (TextUtils.isEmpty(config.getUrl())) throw new IllegalStateException("url is required");
        if (config.getImageView()==null&& config.getTarget()== null) throw new IllegalStateException("imageview or target is required");

        RequestManager manager;

        manager = Glide.with(ctx);//如果context是activity则自动使用Activity的生命周期
        RequestBuilder<Drawable> requestBuilder;
        RequestOptions requestOptions;
        if (!TextUtils.isEmpty(config.getRefererUrl())) {
            GlideUrl glideUrl = new GlideUrl(config.getUrl(), new LazyHeaders
                    .Builder()
                    .addHeader("Referer", config.getRefererUrl())
                    .build());

            requestBuilder = manager.load(glideUrl)
                    .transition(withCrossFade());
        } else {
            requestBuilder = manager.load(config.getUrl())
                    .transition(withCrossFade());
        }

        requestOptions = new RequestOptions().centerCrop();

        switch (config.getCacheStrategy()) {//缓存策略
            case 0:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case 3:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            case 4:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
        }

        if (config.getTransformation() != null) {//glide用它来改变图形的形状
            requestOptions = requestOptions.apply(config.getTransformation());
        }

        if (config.getPlaceholder() != 0)//设置占位符
            requestOptions.placeholder(config.getPlaceholder());

        if (config.getErrorPic() != 0)//设置错误的图片
            requestOptions.error(config.getErrorPic());

        if(config.isCenterCrop())
            requestOptions.centerCrop();

        if(config.isAsBitmap())
            manager.asBitmap();

        if (config.getTarget() != null) {
            requestBuilder.apply(requestOptions).into(config.getTarget());
        } else {
            requestBuilder.apply(requestOptions).into(config.getImageView());
        }
    }

    @Override
    public void clear(final Context ctx, GlideImageConfig config) {
        if (ctx == null) throw new IllegalStateException("Context is required");
        if (config == null) throw new IllegalStateException("GlideImageConfig is required");

        if (config.getImageViews() != null && config.getImageViews().length > 0) {//取消在执行的任务并且释放资源
            for (ImageView imageView : config.getImageViews()) {
                Glide.with(ctx).clear(imageView);
            }
        }

        if (config.getTarget() != null ) {//取消在执行的任务并且释放资源
            Glide.with(ctx).clear(config.getTarget());
        }


        if (config.isClearDiskCache()) {//清除本地缓存
            Observable.just(0)
                    .observeOn(Schedulers.io())
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            Glide.get(ctx).clearDiskCache();
                        }
                    });
        }

        if (config.isClearMemory()) {//清除内存缓存
            Glide.get(ctx).clearMemory();
        }


    }
}
