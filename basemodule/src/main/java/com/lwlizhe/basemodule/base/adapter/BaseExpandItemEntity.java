package com.lwlizhe.basemodule.base.adapter;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/7 0007.
 */

public interface BaseExpandItemEntity<T> extends Serializable {

    public static final int PARENT_ITEM = 0;//父布局
    public static final int CHILD_ITEM = 1;//子布局

    int getType();

    T getChildData();
}
