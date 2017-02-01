package com.yemyatthu.lomotifmockup.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.event.MusicEvent;
import com.yemyatthu.lomotifmockup.presenter.TabPagerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class AddMusicFragment extends BaseFragment{
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;

    private List<Fragment> fragments;
    private List<String> titles;
    private TabPagerAdapter tabPagerAdapter;
    private FindMusicFragment findMusicFragment;
    private MyMusicFragment myMusicFragment;

    public static AddMusicFragment newInstance(){
        return new AddMusicFragment();
    }

    public AddMusicFragment(){

    }
    @Override
    public int getContentLayout() {
        return R.layout.fragment_add_music;
    }

    @Override
    public void initComponents() {
        fragments = new ArrayList<>();
        findMusicFragment = FindMusicFragment.newInstance();
        myMusicFragment = MyMusicFragment.newInstance();
        fragments.add(findMusicFragment);
        fragments.add(myMusicFragment);
        titles = new ArrayList<>();
        titles.add("Find Music");
        titles.add("My Music");
        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager(),fragments,titles);
        pager.setAdapter(tabPagerAdapter);
        tab.setupWithViewPager(pager);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(findMusicFragment==null || myMusicFragment == null){
            return;
        }
        findMusicFragment.setMenuVisibility(menuVisible);
        myMusicFragment.setMenuVisibility(menuVisible);
    }

    public void resetAllFragments(){
        findMusicFragment.resetFragment();
        myMusicFragment.resetFragment();
        if(pager.getCurrentItem()>0){
            pager.setCurrentItem(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMusicEvent(MusicEvent event) {
        if(event.fragment instanceof MyMusicFragment){
            findMusicFragment.resetFragment();
        }else if(event.fragment instanceof FindMusicFragment){
            myMusicFragment.resetFragment();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
