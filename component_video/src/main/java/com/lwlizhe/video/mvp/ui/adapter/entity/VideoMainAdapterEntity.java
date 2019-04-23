package com.lwlizhe.video.mvp.ui.adapter.entity;

import com.lwlizhe.video.api.entity.BaseMultiItemData;

/**
 * Created by lizhe on 2019/4/11.
 */

public abstract class VideoMainAdapterEntity<T> extends BaseMultiItemData {

    protected T mData;

    public static final int MULTI_TYPE_BANNER=0;
    public static final int MULTI_TYPE_WEEK=1;
    public static final int MULTI_TYPE_EDIT_PICK=2;
    public static final int MULTI_TYPE_UGC_PICK=3;

    public T getData() {
        return mData;
    }

    public void setData(T mData) {
        this.mData = mData;
    }
}
