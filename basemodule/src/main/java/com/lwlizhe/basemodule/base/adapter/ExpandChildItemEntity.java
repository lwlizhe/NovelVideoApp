package com.lwlizhe.basemodule.base.adapter;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public abstract class ExpandChildItemEntity<T,P> implements BaseExpandItemEntity<T> {

    protected boolean isExpand = false;

    protected P mParent;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public void setParent(P parent){
        mParent=parent;
    }

    public P getParent(){
        return mParent;
    }
}
