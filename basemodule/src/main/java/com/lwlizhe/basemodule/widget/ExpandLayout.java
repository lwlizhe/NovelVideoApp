package com.lwlizhe.basemodule.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lizhe on 2019/3/4.
 */

public class ExpandLayout extends ViewGroup {

    private TextView[] currentControlView = new TextView[]{};

    private boolean isHidden = true;
    private boolean isAnimationPlaying = false;

    private SparseIntArray currentChildViewMaxHeightMap = new SparseIntArray();

    public ExpandLayout(Context context) {
        super(context);
    }

    public ExpandLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(getChildCount()==0){
            return;
        }
        View child = getChildAt(0);

        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            marginParams = new ViewGroup.MarginLayoutParams(layoutParams);
        }

        final int widthSpec = getChildMeasureSpec(widthMeasureSpec,
                getPaddingLeft() + getPaddingRight() +
                        marginParams.leftMargin + marginParams.rightMargin, marginParams.width);
        final int heightSpec = getChildMeasureSpec(heightMeasureSpec,
                getPaddingTop() + getPaddingBottom() +
                        marginParams.topMargin + marginParams.bottomMargin , marginParams.height);

        child.measure(widthSpec, heightSpec);

        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec), resolveSize(child.getMeasuredHeight(), heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if(getChildCount()==0){
            return;
        }
        View child = getChildAt(0);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            marginParams = new ViewGroup.MarginLayoutParams(layoutParams);
        }

        int left = paddingLeft + marginParams.leftMargin;
        int top = paddingTop + marginParams.topMargin;
        int right = left + r;
//        int right = left + child.getMeasuredWidth();
        int bottom = top + child.getMeasuredHeight();

        child.layout(left, top, right, bottom);
    }

    public void addControlViewId(int id) {
        View targetView = findViewById(id);
        if (targetView != null && targetView instanceof TextView) {
            int length = currentControlView.length;
            currentControlView = Arrays.copyOf(currentControlView, ++length);
            currentControlView[length - 1] = (TextView) targetView;

            int i = MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST);

            targetView.measure(i, 0);

            currentChildViewMaxHeightMap.put(id, 0);

        }
    }

    public void addControlViewIds(int[] ids) {
        for (int id : ids) {
            addControlViewId(id);
        }
    }

    public void toggle() {

        if (isAnimationPlaying) {
            return;
        }

        isAnimationPlaying = true;

        isHidden = !isHidden;

        startAnimation(isHidden);

    }

    public void startAnimation(final boolean isTurnToHidden) {

        AnimatorSet set = new AnimatorSet();

        List<Animator> animators = new ArrayList<>();

        for (final TextView targetView : currentControlView) {

            ValueAnimator animator = TextViewUtils.setMaxLinesWithAnimation(targetView, isTurnToHidden ? 1 : Integer.MAX_VALUE);

            if (animator == null) {
                continue;
            }

            if (isTurnToHidden) {
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
            } else {
                animator.setInterpolator(new BounceInterpolator());
            }

            animators.add(animator);
        }

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimationPlaying = false;
            }
        });

        set.playTogether(animators);
        set.setDuration(1000);
        set.start();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final int count = getChildCount();
        if (count > 1) {
            throw new RuntimeException("ExpandLayout 目前只允许有一个子View");
        }
    }

}
