package com.yemyatthu.lomotifmockup.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.presenter.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class FeedFragment extends BaseFragment {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;

    private TabPagerAdapter tabPagerAdapter;
    private VideoFragment secondVideoFragment;
    private VideoFragment firstVideoFragment;

    public FeedFragment() {

    }

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public void initComponents() {
        List<Fragment> fragments = new ArrayList<>();
        firstVideoFragment = VideoFragment.newInstance();
        secondVideoFragment = VideoFragment.newInstance();
        fragments.add(firstVideoFragment);
        fragments.add(secondVideoFragment);
        List<String> titles = new ArrayList<>();
        titles.add("Featured");
        titles.add("Followings");
        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager(), fragments, titles);
        pager.setAdapter(tabPagerAdapter);
        tab.setupWithViewPager(pager);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (firstVideoFragment != null && secondVideoFragment != null) {
            firstVideoFragment.setMenuVisibility(menuVisible);
            secondVideoFragment.setMenuVisibility(menuVisible);
        }
    }
}
