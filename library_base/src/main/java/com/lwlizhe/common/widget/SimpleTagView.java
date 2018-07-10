package com.lwlizhe.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Scroller;

/**
 * 自己做个模拟的viewPager？
 * 好像有点麻烦唉：还有惯性、缓存……算了，复杂的那些，还是recyclerView嵌套recyclerView吧
 * Created by Administrator on 2018/7/6 0006.
 */

public class SimpleTagView extends LinearLayout {

    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;

    /**
     * 手机按下时的屏幕X坐标
     */
    private float mXDown;
    /**
     * 手机按下时的屏幕Y坐标
     */
    private float mYDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;

    private int mCurrentViewPos;

    private OnCurrentViewChanged mViewChangedListener;


    public SimpleTagView(Context context) {
        super(context);

        init();
    }


    public SimpleTagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SimpleTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        // 第一步，创建Scroller的实例
        mScroller = new Scroller(getContext());
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);

        for (int i = 0; i < getChildCount(); i++) {

            View targetView = getChildAt(i);

            targetView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int i = 0; i < getChildCount(); i++) {
                        if (SimpleTagView.this.getChildAt(i) == v) {

                            if (mCurrentViewPos != i) {
                                mCurrentViewPos = i;
                                if (mViewChangedListener != null) {
                                    mViewChangedListener.onSelectedViewChanged(v, mCurrentViewPos);
                                }
                            }
                            break;
                        }
                    }

                    if (mViewChangedListener != null) {
                        mViewChangedListener.onTagClicked(v);
                    }
                }
            });

        }

        mCurrentViewPos = 0;
        resetSelectedItem();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            // 初始化左右边界值
            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(getChildCount() - 1).getRight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mYDown = ev.getRawY();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    requestParentDisallowInterceptTouchEvent(true);
                    return true;
                }
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    requestParentDisallowInterceptTouchEvent(false);
                } else if (getScrollX() + getClientWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getClientWidth(), 0);
                    requestParentDisallowInterceptTouchEvent(false);
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                requestParentDisallowInterceptTouchEvent(true);
                break;
//            case MotionEvent.ACTION_UP:
//                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
//                int targetIndex = (getScrollX() + getClientWidth() / 2) / getClientWidth();
//                int dx = targetIndex * getClientWidth() - getScrollX();
//                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
//                mScroller.startScroll(getScrollX(), 0, dx, 0);
//                invalidate();
//                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean canScrollHorizontally(int direction) {

        int dx = getScrollX();

        if (direction < 0) {
            return (dx >= leftBorder + getClientWidth());
        } else if (direction > 0) {
            return (dx < rightBorder - getClientWidth());
        } else {
            return false;
        }

    }

    /**
     * 设置父控件的拦截
     *
     * @param disallowIntercept
     */
    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        final ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    private int getClientWidth() {
        return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    public View getCurrentSelectedView() {
        return getChildAt(mCurrentViewPos);
    }

    public void setCurrentPos(int pos) {
        mCurrentViewPos = pos;
        resetSelectedItem();
    }

    public void setOnSelectedChangedListener(OnCurrentViewChanged listener) {

        if (listener != null) {
            mViewChangedListener = listener;
        }
    }

    private void resetSelectedItem() {
        for (int i = 0; i < getChildCount(); i++) {

            View targetView = getChildAt(i);

            targetView.setSelected(i == mCurrentViewPos);
            targetView.setFocusable(i == mCurrentViewPos);
            targetView.setPressed(i == mCurrentViewPos);

            if (targetView instanceof RadioButton) {
                ((RadioButton) targetView).setChecked(i == mCurrentViewPos);
            }

        }
    }

    public interface OnCurrentViewChanged {

        void onSelectedViewChanged(View currentSelectedView, int currentSelectedPos);

        void onTagClicked(View clickTag);

    }

}
