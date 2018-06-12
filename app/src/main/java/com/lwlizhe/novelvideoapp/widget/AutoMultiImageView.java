package com.lwlizhe.novelvideoapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/5/22.
 */

public class AutoMultiImageView<D> extends LinearLayout {

    private ArrayList<View> viewList = new ArrayList<>();
    private List<D> dataBeensList = new ArrayList<>();
    private Context mContext;

    private RecommendCustomLayoutInterface mRecommendCustomLayoutInterface;
    public OnItemClickListener mOnItemClickListener;

    private int column = 3;
    private int itemMargin = 5;
    private int dataSize = 0;

    int childWidth;
    int childHeight;

    float ratio=1;

    public AutoMultiImageView(Context context) {
        this(context, null);
    }

    public AutoMultiImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoMultiImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void setColumn(int column) {
        if (column != 0) {
            this.column = column;
        }
    }

    public void setRatio(float ratio){
        this.ratio=ratio;
    }

    public void setItemMargin(int margin){
        this.itemMargin=margin;
    }


    public void setRecommendCustomLayoutInterface(RecommendCustomLayoutInterface customLayoutInterface) {
        removeAllViews();
        this.dataBeensList.clear();
        this.dataBeensList.addAll(customLayoutInterface.setDatasList());
        this.mRecommendCustomLayoutInterface = customLayoutInterface;
        addViews();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }


    private void addViews() {
        viewList.clear();
        dataSize = dataBeensList.size();
        for (int i = 0; i < dataSize; i++) {

            View view = mRecommendCustomLayoutInterface.createCustomView();
            viewList.add(view);

            if (mOnItemClickListener != null) {
                final int finalI = i;
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(dataBeensList.get(finalI), v, finalI);
                    }
                });
            }

            addView(view);
            mRecommendCustomLayoutInterface.setLayoutDisplay(dataBeensList.get(i), view, i);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

        childWidth = (parentWidth - (column + 1) * itemMargin) / column;
        childHeight = (int) (childWidth * ratio);
        int childWidthSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);

        int childHeightSpec;
        childHeightSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);

        int parentHeightSpec;

        for (View itemView : viewList) {

            itemView.measure(childWidthSpec, childHeightSpec);

        }

        int cols = dataSize % column;
        int rows = dataSize / column;
        int i = 0;
        if (cols > 0) {
            i = (rows + 1) * (itemMargin + childHeight) + itemMargin;
        } else {
            i = rows * (itemMargin + childHeight) + itemMargin;
        }

        parentHeightSpec = MeasureSpec.makeMeasureSpec(i, MeasureSpec.EXACTLY);

        setMeasuredDimension(widthMeasureSpec, parentHeightSpec);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        childLayout(l, t, r, b);

    }

    public interface RecommendCustomLayoutInterface<T extends View, D> {

        void setLayoutDisplay(D itemDataBean, T itemView, int position);

        T createCustomView();

        List<D> setDatasList();
    }

    public interface OnItemClickListener<D> {
        void onItemClick(D itemData, View view, int position);
    }

    private void childLayout(int left, int top, int right, int bottom) {


//        int count = dataSize;
        int colNum = 3;
        if (dataSize == 4) {
            colNum = 2;
        }

        for (int i = 0; i < dataSize; i++) {
            View childView = getChildAt(i);

            if (childView == null) {
                return;
            }

            int rows = i / colNum;
            int cols = i % colNum;

            int childLeft = getPaddingLeft() + (childWidth + itemMargin) * (cols);
            int childTop = getPaddingTop() + (childHeight + itemMargin) * (rows);
            int childRight = childLeft + childWidth;
            int childBottom = childTop + childHeight;
            childView.layout(childLeft, childTop, childRight, childBottom);
        }
    }

}
