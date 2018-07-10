package com.lwlizhe.novel.widget.novelMenu;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.library.novel.R;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class SimplePageBgItemHolder extends BaseHolder<Drawable> {

    private View mReadBg;
    private ImageView mIvChecked;

    private int mHolderPosition;

    public SimplePageBgItemHolder(View itemView) {
        super(itemView);
        mReadBg = itemView.findViewById(R.id.read_bg_view);
        mIvChecked = itemView.findViewById(R.id.read_bg_iv_checked);

    }

    @Override
    public void setData(Drawable data, int position) {
        mHolderPosition=position;
        mReadBg.setBackgroundDrawable(data);
        mIvChecked.setVisibility(View.GONE);
    }

    public void updateCheckState(int position){
        if(mHolderPosition==position){
            mIvChecked.setVisibility(View.VISIBLE);
        }else{
            mIvChecked.setVisibility(View.GONE);
        }
    }

}
