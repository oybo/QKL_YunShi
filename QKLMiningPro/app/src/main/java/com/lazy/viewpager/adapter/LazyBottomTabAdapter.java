package com.lazy.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazy.viewpager.view.indicator.IndicatorViewPager;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AppContext;

public class LazyBottomTabAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private String[] mTabTitles;
    private int[] mTabIcons;
    private Fragment[] mFragments;

    public LazyBottomTabAdapter(FragmentManager fragmentManager, String[] tabTitles, int[] tabIcons, Fragment[] fragments) {
        super(fragmentManager);

        mTabTitles = tabTitles;
        mTabIcons = tabIcons;
        mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mTabTitles.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        // 主界面
        if (convertView == null) {
            convertView = LayoutInflater.from(AppContext.getInstance()).inflate(R.layout.view_main_indicator_layout, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(mTabTitles[position]);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, mTabIcons[position], 0, 0);

        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        return mFragments[position];
    }
}