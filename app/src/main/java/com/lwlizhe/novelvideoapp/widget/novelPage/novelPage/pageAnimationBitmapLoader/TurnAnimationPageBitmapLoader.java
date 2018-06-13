package com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.pageAnimationBitmapLoader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.lwlizhe.basemodule.utils.UiUtils;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class TurnAnimationPageBitmapLoader extends BaseAnimationBitmapLoader {

    private Path mCurPagePath;
    private Path mNextPagePath;

    private int mCornerX = 1; // 拖拽点对应的页脚
    private int mCornerY = 1;

    boolean mIsRTandLB; // 是否属于右上左下

    PointF mBezierStart1 = new PointF(); // 贝塞尔曲线起始点
    PointF mBezierControl1 = new PointF(); // 贝塞尔曲线控制点
    PointF mBeziervertex1 = new PointF(); // 贝塞尔曲线顶点
    PointF mBezierEnd1 = new PointF(); // 贝塞尔曲线结束点

    PointF mBezierStart2 = new PointF(); // 另一条贝塞尔曲线
    PointF mBezierControl2 = new PointF();
    PointF mBeziervertex2 = new PointF();
    PointF mBezierEnd2 = new PointF();

    GradientDrawable mBackShadowDrawableLR; // 有阴影的GradientDrawable
    GradientDrawable mBackShadowDrawableRL;
    GradientDrawable mFolderShadowDrawableLR;
    GradientDrawable mFolderShadowDrawableRL;

    GradientDrawable mFrontShadowDrawableHBT;
    GradientDrawable mFrontShadowDrawableHTB;
    GradientDrawable mFrontShadowDrawableVLR;
    GradientDrawable mFrontShadowDrawableVRL;

    int[] mBackShadowColors;// 背面颜色组
    int[] mFrontShadowColors;// 前面颜色组

    private float mMaxLength ;

    float mMiddleX;
    float mMiddleY;
    float mDegrees;
    float mTouchToCornerDis;

    private Paint mPaint;
    ColorMatrixColorFilter mColorMatrixFilter;
    Matrix mMatrix;
    float[] mMatrixArray = {0, 0, 0, 0, 0, 0, 0, 0, 1.0f};

    public TurnAnimationPageBitmapLoader(View targetView) {
        super(targetView);

        mCurPagePath=new Path();
        mNextPagePath=new Path();

        mMaxLength = (float) Math.hypot(mScreenWidth, mScreenHeight);

        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        ColorMatrix cm = new ColorMatrix();//设置颜色数组
        float array[] = {0.55f, 0, 0, 0, 80.0f, 0, 0.55f, 0, 0, 80.0f, 0, 0, 0.55f, 0, 80.0f, 0, 0, 0, 0.2f, 0};
        cm.set(array);
        mColorMatrixFilter = new ColorMatrixColorFilter(cm);
        mMatrix = new Matrix();

        createDrawable();
    }

    @Override
    protected void calTouchPoint() {
        calBezierPoint();
    }

    /**
     * 计算贝塞尔曲线控制点、起始点
     */
    private void calBezierPoint() {
        mMiddleX = (mTouch.x + mCornerX) / 2;
        mMiddleY = (mTouch.y + mCornerY) / 2;
        mBezierControl1.x = mMiddleX - (mCornerY - mMiddleY) * (mCornerY - mMiddleY) / (mCornerX - mMiddleX);
        mBezierControl1.y = mCornerY;
        mBezierControl2.x = mCornerX;
        //mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX) * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);

        float f4 = mCornerY - mMiddleY;
        if (f4 == 0) {
            mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX) * (mCornerX - mMiddleX) / 0.1f;
        } else {
            mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX) * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);
        }

        mBezierStart1.x = mBezierControl1.x - (mCornerX - mBezierControl1.x) / 2;
        mBezierStart1.y = mCornerY;

        // 当mBezierStart1.x < 0或者mBezierStart1.x > 480时
        // 如果继续翻页，会出现BUG故在此限制
        if (mTouch.x > 0 && mTouch.x < mScreenWidth) {
            if (mBezierStart1.x < 0 || mBezierStart1.x > mScreenWidth) {
                if (mBezierStart1.x < 0)
                    mBezierStart1.x = mScreenWidth - mBezierStart1.x;

                float f1 = Math.abs(mCornerX - mTouch.x);
                float f2 = mScreenWidth * f1 / mBezierStart1.x;
                mTouch.x = Math.abs(mCornerX - f2);

                float f3 = Math.abs(mCornerX - mTouch.x)
                        * Math.abs(mCornerY - mTouch.y) / f1;
                mTouch.y = Math.abs(mCornerY - f3);

                mMiddleX = (mTouch.x + mCornerX) / 2;
                mMiddleY = (mTouch.y + mCornerY) / 2;

                mBezierControl1.x = mMiddleX - (mCornerY - mMiddleY) * (mCornerY - mMiddleY) / (mCornerX - mMiddleX);
                mBezierControl1.y = mCornerY;

                mBezierControl2.x = mCornerX;
                //mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX) * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);

                float f5 = mCornerY - mMiddleY;
                if (f5 == 0) {
                    mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX) * (mCornerX - mMiddleX) / 0.1f;
                } else {
                    mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX) * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);
                }
                mBezierStart1.x = mBezierControl1.x - (mCornerX - mBezierControl1.x) / 2;
            }
        }
        mBezierStart2.x = mCornerX;
        mBezierStart2.y = mBezierControl2.y - (mCornerY - mBezierControl2.y) / 2;

        mTouchToCornerDis = (float) Math.hypot((mTouch.x - mCornerX), (mTouch.y - mCornerY));

        mBezierEnd1 = getCross(mTouch, mBezierControl1, mBezierStart1, mBezierStart2);
        mBezierEnd2 = getCross(mTouch, mBezierControl2, mBezierStart1, mBezierStart2);

		/*
         * mBeziervertex1.x 推导
		 * ((mBezierStart1.x+mBezierEnd1.x)/2+mBezierControl1.x)/2 化简等价于
		 * (mBezierStart1.x+ 2*mBezierControl1.x+mBezierEnd1.x) / 4
		 */
        mBeziervertex1.x = (mBezierStart1.x + 2 * mBezierControl1.x + mBezierEnd1.x) / 4;
        mBeziervertex1.y = (2 * mBezierControl1.y + mBezierStart1.y + mBezierEnd1.y) / 4;
        mBeziervertex2.x = (mBezierStart2.x + 2 * mBezierControl2.x + mBezierEnd2.x) / 4;
        mBeziervertex2.y = (2 * mBezierControl2.y + mBezierStart2.y + mBezierEnd2.y) / 4;
    }

    /**
     * 求解直线P1P2和直线P3P4的交点坐标
     *
     * @param P1
     * @param P2
     * @param P3
     * @param P4
     * @return
     */
    public PointF getCross(PointF P1, PointF P2, PointF P3, PointF P4) {
        PointF CrossP = new PointF();
        // 二元函数通式： y=ax+b
        float a1 = (P2.y - P1.y) / (P2.x - P1.x);
        float b1 = ((P1.x * P2.y) - (P2.x * P1.y)) / (P1.x - P2.x);

        float a2 = (P4.y - P3.y) / (P4.x - P3.x);
        float b2 = ((P3.x * P4.y) - (P4.x * P3.y)) / (P3.x - P4.x);
        CrossP.x = (b2 - b1) / (a1 - a2);
        CrossP.y = a1 * CrossP.x + b1;
        return CrossP;
    }

    /**
     * 计算拖拽点对应的拖拽脚
     *
     * @param x
     * @param y
     */
    @Override
    public void calcCornerXY(float x, float y) {
        if (x <= mScreenWidth / 2) {
            mCornerX = 0;
        }else {
            mCornerX = mScreenWidth;
        }
        if (y <= mScreenHeight / 2) {
            mCornerY = 0;
        } else {
            mCornerY = mScreenHeight;
        }

        if ((mCornerX == 0 && mCornerY == mScreenHeight)
                || (mCornerX == mScreenWidth && mCornerY == 0)) {
            mIsRTandLB = true;
        }else {
            mIsRTandLB = false;
        }

    }

    @Override
    protected void drawStatic(Canvas canvas) {
        HashMap<String, Object> nextPageBitmapResult = mBitmapManager.getNextPageBitmap();

        if((int)(nextPageBitmapResult.get("result"))==0){
            canvas.drawBitmap((Bitmap) nextPageBitmapResult.get("bitmap"), 0, 0, null);
        }



    }

    @Override
    protected void drawNextPage(Canvas canvas) {

        drawNextPageCanvas(canvas);

    }

    /**
     * 画下一页
     * @param canvas
     */
    private void drawNextPageCanvas(Canvas canvas) {

        mNextPagePath.reset();
        mNextPagePath.moveTo(mBezierStart1.x, mBezierStart1.y);
        mNextPagePath.lineTo(mBeziervertex1.x, mBeziervertex1.y);
        mNextPagePath.lineTo(mBeziervertex2.x, mBeziervertex2.y);
        mNextPagePath.lineTo(mBezierStart2.x, mBezierStart2.y);
        mNextPagePath.lineTo(mCornerX, mCornerY);
        mNextPagePath.close();

        mDegrees = (float) Math.toDegrees(Math.atan2(mBezierControl1.x - mCornerX, mBezierControl2.y - mCornerY));
        int leftx;
        int rightx;
        GradientDrawable mBackShadowDrawable;
        if (mIsRTandLB) {  //左下及右上
            leftx = (int) (mBezierStart1.x);
            rightx = (int) (mBezierStart1.x + mTouchToCornerDis / 4);
            mBackShadowDrawable = mBackShadowDrawableLR;
        } else {
            leftx = (int) (mBezierStart1.x - mTouchToCornerDis / 4);
            rightx = (int) mBezierStart1.x;
            mBackShadowDrawable = mBackShadowDrawableRL;
        }
        canvas.save();
        try {
            canvas.clipPath(mCurPagePath);
            canvas.clipPath(mNextPagePath, Region.Op.INTERSECT);
        } catch (Exception e) {
        }

        HashMap<String, Object> nextPageBitmapResult = mBitmapManager.getNextPageBitmap();

        if((int)(nextPageBitmapResult.get("result"))==0){
            canvas.drawBitmap((Bitmap) nextPageBitmapResult.get("bitmap"), 0, 0, null);
        }

        canvas.rotate(mDegrees, mBezierStart1.x, mBezierStart1.y);
        mBackShadowDrawable.setBounds(leftx, (int) mBezierStart1.y,
                rightx, (int) (mMaxLength + mBezierStart1.y));//左上及右下角的xy坐标值,构成一个矩形
        mBackShadowDrawable.draw(canvas);
        canvas.restore();

    }

    @Override
    protected void drawCurPage(Canvas canvas) {
        mCurPagePath.reset();

        drawCurrentPageCanvas(canvas);
        drawCurrentPageShadowCanvas(canvas);
        drawCurrentPageBackArea(canvas);

    }

    /**
     * 画翻起页
     * @param canvas
     */
    private void drawCurrentPageBackArea(Canvas canvas) {
        int i = (int) (mBezierStart1.x + mBezierControl1.x) / 2;
        float f1 = Math.abs(i - mBezierControl1.x);
        int i1 = (int) (mBezierStart2.y + mBezierControl2.y) / 2;
        float f2 = Math.abs(i1 - mBezierControl2.y);
        float f3 = Math.min(f1, f2);
        mNextPagePath.reset();
        mNextPagePath.moveTo(mBeziervertex2.x, mBeziervertex2.y);
        mNextPagePath.lineTo(mBeziervertex1.x, mBeziervertex1.y);
        mNextPagePath.lineTo(mBezierEnd1.x, mBezierEnd1.y);
        mNextPagePath.lineTo(mTouch.x, mTouch.y);
        mNextPagePath.lineTo(mBezierEnd2.x, mBezierEnd2.y);
        mNextPagePath.close();
        GradientDrawable mFolderShadowDrawable;
        int left;
        int right;
        if (mIsRTandLB) {
            left = (int) (mBezierStart1.x - 1);
            right = (int) (mBezierStart1.x + f3 + 1);
            mFolderShadowDrawable = mFolderShadowDrawableLR;
        } else {
            left = (int) (mBezierStart1.x - f3 - 1);
            right = (int) (mBezierStart1.x + 1);
            mFolderShadowDrawable = mFolderShadowDrawableRL;
        }
        canvas.save();
        try {
            canvas.clipPath(mCurPagePath);
            canvas.clipPath(mNextPagePath, Region.Op.INTERSECT);
        } catch (Exception e) {
        }

        mPaint.setColorFilter(mColorMatrixFilter);

        float dis = (float) Math.hypot(mCornerX - mBezierControl1.x,
                mBezierControl2.y - mCornerY);
        float f8 = (mCornerX - mBezierControl1.x) / dis;
        float f9 = (mBezierControl2.y - mCornerY) / dis;
        mMatrixArray[0] = 1 - 2 * f9 * f9;
        mMatrixArray[1] = 2 * f8 * f9;
        mMatrixArray[3] = mMatrixArray[1];
        mMatrixArray[4] = 1 - 2 * f8 * f8;
        mMatrix.reset();
        mMatrix.setValues(mMatrixArray);
        mMatrix.preTranslate(-mBezierControl1.x, -mBezierControl1.y);
        mMatrix.postTranslate(mBezierControl1.x, mBezierControl1.y);
        canvas.drawBitmap(mBitmapManager.getCurrentPageBitmap(), mMatrix, mPaint);
        // canvas.drawBitmap(bitmap, mMatrix, null);
        mPaint.setColorFilter(null);
        canvas.rotate(mDegrees, mBezierStart1.x, mBezierStart1.y);
        mFolderShadowDrawable.setBounds(left, (int) mBezierStart1.y, right,
                (int) (mBezierStart1.y + mMaxLength));
        mFolderShadowDrawable.draw(canvas);
        canvas.restore();
    }

    @Override
    protected void startAnimation() {

            int dx, dy;

            if (mCornerX > 0) {
//            dx = -(int) (mScreenWidth + mTouch.x);
                dx = -(int) ( mTouch.x);
            } else {
                dx = (int) (mScreenWidth - mTouch.x );
//            dx = (int) (mScreenWidth - mTouch.x + mScreenWidth);
            }
            if (mCornerY > 0) {
                dy = (int) (mScreenHeight - mTouch.y);
//            dy = (int) (mScreenHeight - mTouch.y);
            } else {
                dy = (int) (1 - mTouch.y); // 防止mTouch.y最终变为0
            }

            mScroller.startScroll((int)mTouch.x, (int)mTouch.y, dx, dy, 300);
            isAnimationRunning = true;
            mTargetView.postInvalidate();

    }

    @Override
    protected void abortAnimation() {
        if (!mScroller.isFinished()){
            mScroller.abortAnimation();
            isAnimationRunning = false;
            mTargetView.postInvalidate();
        }
    }

    /**
     * 画翻起页面
     */
    private void drawCurrentPageCanvas(Canvas canvas) {

//        mCurPagePath.reset();
//
//        mCurPagePath.moveTo(0,0);
//        mCurPagePath.lineTo(mTouch.x,0);
//        mCurPagePath.lineTo(mTouch.x, UiUtils.getScreenHeight());
//        mCurPagePath.lineTo(0,UiUtils.getScreenHeight());
//
//        mCurPagePath.close();
//
//        canvas.save();
//        canvas.clipPath(mCurPagePath, Region.Op.INTERSECT);
//        canvas.drawBitmap(mBitmapManager.getCurrentPageBitmap(), 0, 0, null);
//
//        canvas.restore();

        mCurPagePath.reset();
        mCurPagePath.moveTo(mBezierStart1.x, mBezierStart1.y);
        mCurPagePath.quadTo(mBezierControl1.x, mBezierControl1.y, mBezierEnd1.x,
                mBezierEnd1.y);
        mCurPagePath.lineTo(mTouch.x, mTouch.y);
        mCurPagePath.lineTo(mBezierEnd2.x, mBezierEnd2.y);
        mCurPagePath.quadTo(mBezierControl2.x, mBezierControl2.y, mBezierStart2.x,
                mBezierStart2.y);
        mCurPagePath.lineTo(mCornerX, mCornerY);
        mCurPagePath.close();

        canvas.save();
        canvas.clipPath(mCurPagePath, Region.Op.XOR);
        canvas.drawBitmap(mBitmapManager.getCurrentPageBitmap(), 0, 0, null);
        try {
            canvas.restore();
        } catch (Exception e) {

        }
    }

    /**
     * 画翻起页面的阴影
     */
    private void drawCurrentPageShadowCanvas(Canvas canvas) {

        double degree;
        if (mIsRTandLB) {
            degree = Math.PI / 4 - Math.atan2(mBezierControl1.y - mTouch.y, mTouch.x - mBezierControl1.x);
        } else {
            degree = Math.PI / 4 - Math.atan2(mTouch.y - mBezierControl1.y, mTouch.x - mBezierControl1.x);
        }
        // 翻起页阴影顶点与touch点的距离
        double d1 = (float) 25 * 1.414 * Math.cos(degree);
        double d2 = (float) 25 * 1.414 * Math.sin(degree);
        float x = (float) (mTouch.x + d1);
        float y;
        if (mIsRTandLB) {
            y = (float) (mTouch.y + d2);
        } else {
            y = (float) (mTouch.y - d2);
        }
        mNextPagePath.reset();
        mNextPagePath.moveTo(x, y);
        mNextPagePath.lineTo(mTouch.x, mTouch.y);
        mNextPagePath.lineTo(mBezierControl1.x, mBezierControl1.y);
        mNextPagePath.lineTo(mBezierStart1.x, mBezierStart1.y);
        mNextPagePath.close();
        float rotateDegrees;
        canvas.save();
        try {
            canvas.clipPath(mCurPagePath, Region.Op.XOR);
            canvas.clipPath(mNextPagePath, Region.Op.INTERSECT);
        } catch (Exception e) {
        }

        int leftx;
        int rightx;
        GradientDrawable mCurrentPageShadow;
        if (mIsRTandLB) {
            leftx = (int) (mBezierControl1.x);
            rightx = (int) mBezierControl1.x + 25;
            mCurrentPageShadow = mFrontShadowDrawableVLR;
        } else {
            leftx = (int) (mBezierControl1.x - 25);
            rightx = (int) mBezierControl1.x + 1;
            mCurrentPageShadow = mFrontShadowDrawableVRL;
        }

        rotateDegrees = (float) Math.toDegrees(Math.atan2(mTouch.x - mBezierControl1.x,
                mBezierControl1.y - mTouch.y));
        canvas.rotate(rotateDegrees, mBezierControl1.x, mBezierControl1.y);
        mCurrentPageShadow.setBounds(leftx, (int) (mBezierControl1.y - mMaxLength),
                rightx, (int) (mBezierControl1.y));
        mCurrentPageShadow.draw(canvas);
        canvas.restore();

        mNextPagePath.reset();
        mNextPagePath.moveTo(x, y);
        mNextPagePath.lineTo(mTouch.x, mTouch.y);
        mNextPagePath.lineTo(mBezierControl2.x, mBezierControl2.y);
        mNextPagePath.lineTo(mBezierStart2.x, mBezierStart2.y);
        mNextPagePath.close();
        canvas.save();
        try {
            canvas.clipPath(mCurPagePath, Region.Op.XOR);
            canvas.clipPath(mNextPagePath, Region.Op.INTERSECT);
        } catch (Exception e) {
        }

        if (mIsRTandLB) {
            leftx = (int) (mBezierControl2.y);
            rightx = (int) (mBezierControl2.y + 25);
            mCurrentPageShadow = mFrontShadowDrawableHTB;
        } else {
            leftx = (int) (mBezierControl2.y - 25);
            rightx = (int) (mBezierControl2.y + 1);
            mCurrentPageShadow = mFrontShadowDrawableHBT;
        }
        rotateDegrees = (float) Math.toDegrees(Math.atan2(mBezierControl2.y - mTouch.y, mBezierControl2.x - mTouch.x));
        canvas.rotate(rotateDegrees, mBezierControl2.x, mBezierControl2.y);
        float temp;
        if (mBezierControl2.y < 0)
            temp = mBezierControl2.y - mScreenHeight;
        else
            temp = mBezierControl2.y;

        int hmg = (int) Math.hypot(mBezierControl2.x, temp);
        if (hmg > mMaxLength) {
            mCurrentPageShadow.setBounds((int) (mBezierControl2.x - 25) - hmg, leftx,
                    (int) (mBezierControl2.x + mMaxLength) - hmg, rightx);
        } else {
            mCurrentPageShadow.setBounds((int) (mBezierControl2.x - mMaxLength), leftx,
                    (int) (mBezierControl2.x), rightx);
        }
        mCurrentPageShadow.draw(canvas);
        canvas.restore();

    }

    /**
     * 创建阴影的GradientDrawable
     */
    private void createDrawable() {
        int[] color = { 0x333333, 0xb0333333 };
        mFolderShadowDrawableRL = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT, color);
        mFolderShadowDrawableRL
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFolderShadowDrawableLR = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, color);
        mFolderShadowDrawableLR
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mBackShadowColors = new int[] { 0xff111111, 0x111111 };
        mBackShadowDrawableRL = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT, mBackShadowColors);
        mBackShadowDrawableRL.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mBackShadowDrawableLR = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, mBackShadowColors);
        mBackShadowDrawableLR.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontShadowColors = new int[] { 0x80111111, 0x111111 };
        mFrontShadowDrawableVLR = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, mFrontShadowColors);
        mFrontShadowDrawableVLR
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);
        mFrontShadowDrawableVRL = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT, mFrontShadowColors);
        mFrontShadowDrawableVRL
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontShadowDrawableHTB = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, mFrontShadowColors);
        mFrontShadowDrawableHTB
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontShadowDrawableHBT = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP, mFrontShadowColors);
        mFrontShadowDrawableHBT
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);
    }

    public void onPageTouch(MotionEvent event) {

        if(event.getX() > mScreenWidth / 2&&!isNextEnable){

            if(event.getAction()== MotionEvent.ACTION_DOWN){
                Toast.makeText(mContext, "没有下一页了", Toast.LENGTH_SHORT).show();
            }

            return;
        }

        if(event.getX() < mScreenWidth / 2&&!isPreEnable){

            if(event.getAction()== MotionEvent.ACTION_DOWN){
                Toast.makeText(mContext, "没有上一页了", Toast.LENGTH_SHORT).show();
            }

            return;
        }
        mTouch.x = event.getX();
        mTouch.y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if(isAnimationRunning){
                    abortAnimation();
                }

                isAnimationRunning = false;

                calcCornerXY(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:

                isAnimationRunning=true;
                mTargetView.postInvalidate();
                break;
            case MotionEvent.ACTION_UP:

//                calcCornerXY(event.getX(), event.getY());

            case MotionEvent.ACTION_CANCEL:

                startAnimation();

                break;
        }
    }
}
