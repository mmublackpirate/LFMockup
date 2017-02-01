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


public class AddPhotoVideosFragment extends BaseFragment {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;

    private List<Fragment> fragments;
    private List<String> titles;
    private TabPagerAdapter tabPagerAdapter;
    private AlbumFragment albumFragment;
    private MotifFragment motifFragment;

    public static AddPhotoVideosFragment newInstance(){
        return new AddPhotoVideosFragment();
    }

    public AddPhotoVideosFragment(){

    }
    @Override
    public int getContentLayout() {
        return R.layout.fragment_add_photos_videos;
    }

    @Override
    public void initComponents() {
        fragments = new ArrayList<>();
        albumFragment = AlbumFragment.newInstance();
        motifFragment = MotifFragment.newInstance();
        fragments.add(albumFragment);
        fragments.add(motifFragment);
        titles = new ArrayList<>();
        titles.add("Album");
        titles.add("Motif");
        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager(),fragments,titles);
        pager.setAdapter(tabPagerAdapter);
        tab.setupWithViewPager(pager);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(albumFragment==null || motifFragment == null){
            return;
        }
        albumFragment.setMenuVisibility(menuVisible);
        motifFragment.setMenuVisibility(menuVisible);
    }

    public void resetAllFragments(){
        albumFragment.resetFragment();
        motifFragment.resetFragment();
        if(pager.getCurrentItem()>0){
            pager.setCurrentItem(0);
        }
    }
}
