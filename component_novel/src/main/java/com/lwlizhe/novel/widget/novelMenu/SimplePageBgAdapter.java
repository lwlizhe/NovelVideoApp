package com.lwlizhe.novel.widget.novelMenu;


import android.graphics.drawable.Drawable;
import android.view.View;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.base.adapter.BaseRecyclerViewAdapter;
import com.lwlizhe.library.novel.R;

import java.util.List;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class SimplePageBgAdapter extends BaseRecyclerViewAdapter<Drawable> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Drawable> {

    private int selectedItemPos=0;

    public SimplePageBgAdapter(List<Drawable> infos) {
        super(infos);
        setOnItemClickListener(this);
    }

    @Override
    public BaseHolder<Drawable> getHolder(View v, int viewType) {
        SimplePageBgItemHolder itemHolder = new SimplePageBgItemHolder(v);
        itemHolder.updateCheckState(selectedItemPos);
        return itemHolder;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_read_bg;
    }


    @Override
    public void onItemClick(View view, int viewType, Drawable data, int position) {
        selectedItemPos=position;
        notifyDataSetChanged();
    }
}
