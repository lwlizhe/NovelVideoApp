package com.lwlizhe.basemodule.base.adapter;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public abstract class ExpandParentItemEntity<T> implements BaseExpandItemEntity<T> {

    protected boolean isExpand = false;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }


}
