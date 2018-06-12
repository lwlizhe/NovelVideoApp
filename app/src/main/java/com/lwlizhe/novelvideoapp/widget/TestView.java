package com.lwlizhe.novelvideoapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.lwlizhe.basemodule.utils.UiUtils;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class TestView extends View {

    private float mTouchX=0;
    private float mTouchY=0;

    private int mCornerX = 1; // 拖拽点对应的页脚
    private int mCornerY = 1;

    private Path mPath;
    private Paint mPaint;

    //屏幕的尺寸
    protected int mScreenWidth;
    protected int mScreenHeight;

    float mMiddleX;
    float mMiddleY;

    PointF mBezierStart1 = new PointF(); // 贝塞尔曲线起始点
    PointF mBezierControl1 = new PointF(); // 贝塞尔曲线控制点
    PointF mBeziervertex1 = new PointF(); // 贝塞尔曲线顶点
    PointF mBezierEnd1 = new PointF(); // 贝塞尔曲线结束点

    PointF mBezierStart2 = new PointF(); // 另一条贝塞尔曲线
    PointF mBezierControl2 = new PointF();
    PointF mBeziervertex2 = new PointF();
    PointF mBezierEnd2 = new PointF();

    private Scroller mScroler;

    public TestView(Context context) {
        super(context);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPath = new Path();

        mScroler=new Scroller(getContext());

        mScreenWidth= UiUtils.getScreenWidth();
        mScreenHeight=UiUtils.getScreenHeight();

        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);

        mPaint.setColor(Color.BLACK);
        // 笔宽
        mPaint.setStrokeWidth(10);
        // 空心
        mPaint.setStyle(Paint.Style.STROKE);

        mPath=new Path();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        calcCornerXY(event.getX(),event.getY());

        mTouchX= (int) event.getX();
        mTouchY= (int) event.getY();

        startScrollerAnimation();

        postInvalidate();

        return true;
    }

    private void startScrollerAnimation() {
        int dx, dy;

        if (mCornerX > 0) {
//            dx = -(int) (mScreenWidth + mTouchX);
            dx = -(int) ( mTouchX);
        } else {
            dx = (int) (mScreenWidth - mTouchX );
//            dx = (int) (mScreenWidth - mTouchX + mScreenWidth);
        }
        if (mCornerY > 0) {
            dy = (int) (mScreenHeight - mTouchY);
//            dy = (int) (mScreenHeight - mTouchY);
        } else {
            dy = (int) (1 - mTouchY); // 防止mTouchY最终变为0
        }

        mScroler.startScroll((int)mTouchX, (int)mTouchY, dx, dy, 3000);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if(mScroler.computeScrollOffset()){

            mTouchX=mScroler.getCurrX();
            mTouchY=mScroler.getCurrY();

//            Logger.d("mTouchX:"+mTouchX+",mTouchY:"+mTouchY);

            postInvalidate();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        calcPoints();

        if(mTouchX!=0&&mTouchY!=0){

//            mPath.reset();
//            mPath.moveTo(mTouchX/2,0);
//            mPath.quadTo(mTouchX,0,mTouchX,mTouchY);

            mPath.reset();
            mPath.moveTo(mBezierStart1.x, mBezierStart1.y);
            mPath.quadTo(mBezierControl1.x, mBezierControl1.y, mBezierEnd1.x,
                    mBezierEnd1.y);
            mPath.lineTo(mTouchX, mTouchY);
            mPath.lineTo(mBezierEnd2.x, mBezierEnd2.y);
            mPath.quadTo(mBezierControl2.x, mBezierControl2.y, mBezierStart2.x,
                    mBezierStart2.y);
            mPath.lineTo(mCornerX, mCornerY);
//            mPath.close();

            canvas.drawPath(mPath,mPaint);

        }
    }

    private void calcPoints() {
        mMiddleX = (mTouchX + mCornerX) / 2;
        mMiddleY = (mTouchY + mCornerY) / 2;
        mBezierControl1.x = mMiddleX - (mCornerY - mMiddleY)
                * (mCornerY - mMiddleY) / (mCornerX - mMiddleX);
        mBezierControl1.y = mCornerY;
        mBezierControl2.x = mCornerX;

        float f4 = mCornerY-mMiddleY;
        if (f4 == 0) {
            mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX)
                    * (mCornerX - mMiddleX) / 0.1f;

        }else {
            mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX)
                    * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);
        }
        mBezierStart1.x = mBezierControl1.x - (mCornerX - mBezierControl1.x)
                / 2;
        mBezierStart1.y = mCornerY;

        // 当mBezierStart1.x < 0或者mBezierStart1.x > 480时
        // 如果继续翻页，会出现BUG故在此限制
        if (mTouchX > 0 && mTouchX < mScreenWidth) {
            if (mBezierStart1.x < 0 || mBezierStart1.x > mScreenWidth) {
                if (mBezierStart1.x < 0)
                    mBezierStart1.x = mScreenWidth - mBezierStart1.x;

                float f1 = Math.abs(mCornerX - mTouchX);
                float f2 = mScreenWidth * f1 / mBezierStart1.x;
                mTouchX = Math.abs(mCornerX - f2);

                float f3 = Math.abs(mCornerX - mTouchX)
                        * Math.abs(mCornerY - mTouchY) / f1;
                mTouchY = Math.abs(mCornerY - f3);

                mMiddleX = (mTouchX + mCornerX) / 2;
                mMiddleY = (mTouchY + mCornerY) / 2;

                mBezierControl1.x = mMiddleX - (mCornerY - mMiddleY)
                        * (mCornerY - mMiddleY) / (mCornerX - mMiddleX);
                mBezierControl1.y = mCornerY;

                mBezierControl2.x = mCornerX;

                float f5 = mCornerY-mMiddleY;
                if (f5 == 0) {
                    mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX)
                            * (mCornerX - mMiddleX) / 0.1f;
                }else {
                    mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX)
                            * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);
                }

                mBezierStart1.x = mBezierControl1.x
                        - (mCornerX - mBezierControl1.x) / 2;
            }
        }
        mBezierStart2.x = mCornerX;
        mBezierStart2.y = mBezierControl2.y - (mCornerY - mBezierControl2.y)
                / 2;


        mBezierEnd1 = getCross(new PointF(mTouchX,mTouchY), mBezierControl1, mBezierStart1,
                mBezierStart2);
        mBezierEnd2 = getCross(new PointF(mTouchX,mTouchY), mBezierControl2, mBezierStart1,
                mBezierStart2);

        mBeziervertex1.x = (mBezierStart1.x + 2 * mBezierControl1.x + mBezierEnd1.x) / 4;
        mBeziervertex1.y = (2 * mBezierControl1.y + mBezierStart1.y + mBezierEnd1.y) / 4;
        mBeziervertex2.x = (mBezierStart2.x + 2 * mBezierControl2.x + mBezierEnd2.x) / 4;
        mBeziervertex2.y = (2 * mBezierControl2.y + mBezierStart2.y + mBezierEnd2.y) / 4;
    }

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

    }


}
