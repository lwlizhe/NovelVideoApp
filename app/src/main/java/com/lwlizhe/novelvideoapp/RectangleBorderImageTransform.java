package com.lwlizhe.novelvideoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class RectangleBorderImageTransform extends BitmapTransformation {

    private Context mContext;
    private int borderWidth;
    private Paint mWhiteBorderPaint;

    public RectangleBorderImageTransform(Context context,int borderWidth) {
        this.mContext=context;
        this.borderWidth=borderWidth;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {

        return createBorderImage(pool,toTransform,outWidth,outHeight);
    }

    private Bitmap createBorderImage(@NonNull BitmapPool pool, @NonNull Bitmap source, int outWidth, int outHeight){
        if (source == null) return null;

        // TODO this could be acquired from the pool too
        Bitmap squared = Bitmap.createBitmap(source, 0, 0, outWidth, outHeight);
        Bitmap result = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);

        canvas.drawRect(0, 0, outWidth, outHeight,paint);
        if (borderWidth != 0) {
            mWhiteBorderPaint=new Paint();
            mWhiteBorderPaint.setColor(Color.WHITE);
            mWhiteBorderPaint.setAntiAlias(true);
            mWhiteBorderPaint.setStyle(Paint.Style.STROKE);
            mWhiteBorderPaint.setStrokeWidth(borderWidth);
            canvas.drawRect(canvas.getClipBounds(),mWhiteBorderPaint);
        }
        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
