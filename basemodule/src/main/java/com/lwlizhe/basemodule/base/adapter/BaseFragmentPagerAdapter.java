package com.lwlizhe.basemodule.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mData = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<Fragment> data) {
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<Fragment> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setTitles(List<String> titles) {
        mTitle.clear();
        mTitle.addAll(titles);
    }

    @Override
    public Fragment getItem(int position) {

        if (position <= mData.size())
            return mData.get(position);
        else
            return null;

    }

    @Override
    public int getCount() {
        int count;
        if (mData != null && mData.size() > 0) {
            count = mData.size();
        } else {
            count = 0;
        }

        return count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title = null;

        if (position <= mTitle.size())
            title = mTitle.get(position);
        else
            title = null;

        return title;
    }

//    public Parcelable saveState() {
//        return null;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Fragment f = (Fragment) super.instantiateItem(container, position);
//        View view = f.getView();
//        if (view != null)
//            container.addView(view);
//        return f;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        View view = mData.get(position).getView();
//        if (view != null)
//            container.removeView(view);
//    }
}
