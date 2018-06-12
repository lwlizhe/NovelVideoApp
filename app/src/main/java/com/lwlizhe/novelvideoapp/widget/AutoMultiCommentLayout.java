package com.lwlizhe.novelvideoapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;



import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class AutoMultiCommentLayout<T> extends LinearLayout {

    private BaseItemDisplayViewCallBack<T> customDisplayCallBack;

    public AutoMultiCommentLayout(Context context) {
        super(context);
    }

    public AutoMultiCommentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMultiCommentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChildCommentData(List<T> childCommentData) {

        removeAllViews();

        if (childCommentData.size() == 0 || customDisplayCallBack == null) {
            return;
        }

        for (T childrenBean : childCommentData) {
            View itemView = customDisplayCallBack.BaseItemDisplay(childrenBean);

            this.addView(itemView);

        }

    }

    public void setCustomBaseItemDisplayView(BaseItemDisplayViewCallBack<T> callBack) {
        this.customDisplayCallBack = callBack;
    }

    public interface BaseItemDisplayViewCallBack<T> {
        View BaseItemDisplay(T childrenBean);
    }

}
