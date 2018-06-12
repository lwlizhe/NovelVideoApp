package com.lwlizhe.novelvideoapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/6/6 0006.
 */

public class TestLayoutManager extends LinearLayoutManager {

    private Method targetMethod;
    private Class<? extends TestLayoutManager> targetClass;

    public TestLayoutManager(Context context) {
        super(context);
    }

    public TestLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public TestLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


        targetClass = this.getClass();
        try {
            targetMethod = targetClass.getDeclaredMethod("scrollBy", Integer.class, RecyclerView.Recycler.class, RecyclerView.State.class);

            targetMethod.setAccessible(true);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int result = 0;

        try {
            targetMethod.invoke(targetClass, dx, recycler, state);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }
}
