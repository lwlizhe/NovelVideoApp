package com.lwlizhe.common.api.video.entity;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public abstract class BaseMultiItemData {

    private int clickItemPos=0;

    public abstract int getType();

    public int getClickItemPos() {
        return clickItemPos;
    }

    public void setClickItemPos(int clickItemPos) {
        this.clickItemPos = clickItemPos;
    }
}
