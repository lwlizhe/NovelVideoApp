package com.lwlizhe.novelvideoapp.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/6/6 0006.
 */

public class ExpandLinearLayout extends LinearLayout {

    //是否已经展开
    private boolean isExpand;
    //是否正在播放动画;
    private boolean isAnimationPlaying;

    private int maxHeight;

    public ExpandLinearLayout(Context context) {
        this(context, null);
    }

    public ExpandLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        isExpand = false;
        isAnimationPlaying = false;

        this.setOrientation(VERTICAL);
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));

    }

    public boolean isExpand() {
        return isExpand;
    }

    /**
     * 强制展开
     */
    public void expand() {
        if (isExpand) {
            return;
        }

        measure(0, 0);

        maxHeight = getMeasuredHeight();

        final ViewGroup.LayoutParams layoutParams = getLayoutParams();

        layoutParams.height = maxHeight;
        ExpandLinearLayout.this.setLayoutParams(layoutParams);

    }

    public void close() {
        if (!isExpand) {
            return;
        }

        final ViewGroup.LayoutParams layoutParams = getLayoutParams();

        layoutParams.height = 0;
        ExpandLinearLayout.this.setLayoutParams(layoutParams);
    }

    /**
     * 展开和关闭的切换
     */
    public void toggle() {

        if (isAnimationPlaying) {
            return;
        }

        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(isExpand ? maxHeight : 0, isExpand ? 0 : maxHeight);
        animator.setDuration(700);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.height = (int) animation.getAnimatedValue();
                ExpandLinearLayout.this.setLayoutParams(layoutParams);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimationPlaying = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimationPlaying = false;
                isExpand = !isExpand;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                isAnimationPlaying = false;
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                isAnimationPlaying = false;
                isExpand = !isExpand;
            }

            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                isAnimationPlaying = true;
            }
        });
        animator.start();


    }

    /**
     * 添加单个View
     */
    public void addItemView(ExpandViewProvider provider) {

        removeAllViews();

        View childView = provider.provideView();
        if (childView.getParent() != null) {
            childView.bringToFront();
        } else {
            ExpandLinearLayout.this.addView(childView);
        }

        measure(0, 0);

        maxHeight = getMeasuredHeight();

        final ViewGroup.LayoutParams layoutParams = getLayoutParams();

        layoutParams.height = 0;
        ExpandLinearLayout.this.setLayoutParams(layoutParams);
    }

    /**
     * 添加多个View
     */
    public void addItemViews(ExpandViewsProvider provider) {

        removeAllViews();

        for (View childView : provider.provideViews()) {

            if (childView.getParent() != null) {
                childView.bringToFront();
            } else {
                ExpandLinearLayout.this.addView(childView);
            }
        }
        measure(0, 0);

        maxHeight = getMeasuredHeight();

        final ViewGroup.LayoutParams layoutParams = getLayoutParams();

        layoutParams.height = 0;
        ExpandLinearLayout.this.setLayoutParams(layoutParams);
    }

    public interface ExpandViewProvider {
        View provideView();
    }

    public interface ExpandViewsProvider {
        List<View> provideViews();
    }


}
