package com.lazy.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.qkl.online.mining.app.R;
import com.qkl.online.mining.app.application.AppContext;
import com.lazy.viewpager.view.indicator.IndicatorViewPager;
import java.util.List;

public class LazyTabAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private String[] mTabTitles;
    private List<Fragment> mFragments;

    public LazyTabAdapter(FragmentManager fragmentManager, String[] tabTitles, List<Fragment> fragments) {
        super(fragmentManager);

        mTabTitles = tabTitles;
        mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mTabTitles.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(AppContext.getInstance()).inflate(R.layout.view_tab_top, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(mTabTitles[position]);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        return mFragments.get(position);
    }
}